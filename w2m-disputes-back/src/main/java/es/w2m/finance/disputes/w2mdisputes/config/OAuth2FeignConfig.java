package es.w2m.finance.disputes.w2mdisputes.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
@RequiredArgsConstructor
public class OAuth2FeignConfig {

    private final OAuth2AuthorizedClientManager manager;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return template -> {
            OAuth2AuthorizeRequest authReq = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak")
                    .principal("w2m-disputes-service")
                    .build();

            OAuth2AuthorizedClient client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }
            template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
        };
    }
}
