package es.w2m.finance.snowflakeapi.disputes.statement;

import es.w2m.finance.snowflakeapi.common.client.SnowflakeClient;
import es.w2m.finance.snowflakeapi.common.client.request.SnowflakeRequest;
import es.w2m.finance.snowflakeapi.common.security.KeycloakTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
class StatementService {

    private final SnowflakeClient snowflakeClient;
    private final KeycloakTokenService keycloakTokenService;

    public StatementResponse executeTestQuery() {
        // 1. Preparar la solicitud
        final var request = new SnowflakeRequest("SELECT * FROM users");

        // 2. Llamada al mock (o Snowflake real)
        final Map<String, Object> wiremockResponse = this.snowflakeClient.sendQuery(request);

        // 3. Obtener token real de Keycloak
        final String token = this.keycloakTokenService.getToken();

        // 4. Respuesta combinada
        return new StatementResponse(wiremockResponse, token);
    }
}