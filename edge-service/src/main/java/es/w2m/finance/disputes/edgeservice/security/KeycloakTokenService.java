package es.w2m.finance.disputes.edgeservice.security;

import jakarta.annotation.PostConstruct;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class KeycloakTokenService {

    private final OAuth2AuthorizedClientManager clientManager;

    public KeycloakTokenService(OAuth2AuthorizedClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public String getToken() {
        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak")
                .principal("edge-service")  // Puede ser un nombre cualquiera
                .build();

        OAuth2AuthorizedClient client = clientManager.authorize(request);
        if (client == null) {
            throw new IllegalStateException("No se pudo obtener el token de Keycloak");
        }
        return client.getAccessToken().getTokenValue();
    }
}
