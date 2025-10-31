package es.w2m.finance.disputes.libsnowflake.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class SnowFlakeApiFeignConfig {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(final OAuth2AuthorizedClientManager manager) {
        return template -> {
            final var authReq = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak")
                    .principal("w2m-disputes-service")
                    .build();

            final var client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }
            template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
        };
    }
}