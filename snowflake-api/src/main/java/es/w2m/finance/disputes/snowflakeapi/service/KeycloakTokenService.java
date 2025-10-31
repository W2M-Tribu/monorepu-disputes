package es.w2m.finance.disputes.snowflakeapi.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Service;

@Service
public class KeycloakTokenService {

    private final OAuth2AuthorizedClientManager clientManager;

    public KeycloakTokenService(final OAuth2AuthorizedClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public String getToken() {
        final OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak")
                .principal("edge-service")  // Puede ser un nombre cualquiera
                .build();

        final OAuth2AuthorizedClient client = this.clientManager.authorize(request);
        if (client == null) {
            throw new IllegalStateException("No se pudo obtener el token de Keycloak");
        }
        return client.getAccessToken().getTokenValue();
    }
}
