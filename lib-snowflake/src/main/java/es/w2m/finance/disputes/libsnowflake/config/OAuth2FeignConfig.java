package es.w2m.finance.disputes.libsnowflake.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.*;

@AutoConfiguration
@ConditionalOnBean(OAuth2AuthorizedClientManager.class)
public class OAuth2FeignConfig {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2AuthorizedClientManager manager) {
        return template -> {
            var authReq = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak")
                    .principal("w2m-disputes-service")
                    .build();

            var client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }
            template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
        };
    }
}