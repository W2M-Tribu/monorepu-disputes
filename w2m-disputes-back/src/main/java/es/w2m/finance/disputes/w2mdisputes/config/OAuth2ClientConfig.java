package es.w2m.finance.disputes.w2mdisputes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OAuth2ClientConfig {
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService
    ) {
        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager manager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        authorizedClientService
                );
        manager.setAuthorizedClientProvider(provider);

        return manager;
    }

    @Bean
    public RestTemplate oauthRestTemplate(OAuth2AuthorizedClientManager manager) {
        RestTemplate rt = new RestTemplate();
        rt.getInterceptors().add((req, body, exec) -> {
            OAuth2AuthorizeRequest authReq = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak")
                    .principal("w2m-disputes-service")
                    .build();

            OAuth2AuthorizedClient client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }

            req.getHeaders().setBearerAuth(client.getAccessToken().getTokenValue());
            return exec.execute(req, body);
        });
        return rt;
    }

    @Bean
    public RestTemplate plainRestTemplate() {
        return new RestTemplate();
    }
}
