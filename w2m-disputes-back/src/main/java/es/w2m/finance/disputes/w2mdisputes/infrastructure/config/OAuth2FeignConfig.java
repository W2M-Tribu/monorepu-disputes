package es.w2m.finance.disputes.w2mdisputes.infrastructure.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@AutoConfiguration
@ConditionalOnBean(OAuth2AuthorizedClientManager.class)
public class OAuth2FeignConfig {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(final OAuth2AuthorizedClientManager manager) {
        return template -> {
            // En Feign 12/13 puedes obtener el target:
            final String targetUrl = template.feignTarget() != null ? template.feignTarget().url() : "";

            // No añadir Authorization cuando el destino es el mock
            if (targetUrl.contains("mockserver.internal.w2m.com")) {
                return; // skip
            }

            final var authReq = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
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