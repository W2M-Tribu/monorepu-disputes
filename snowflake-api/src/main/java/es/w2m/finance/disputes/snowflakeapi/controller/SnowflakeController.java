package es.w2m.finance.disputes.snowflakeapi.controller;

import es.w2m.finance.disputes.snowflakeapi.client.SnowflakeClient;
import es.w2m.finance.disputes.snowflakeapi.service.KeycloakTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SnowflakeController {

    private final SnowflakeClient snowflakeClient;

    private final KeycloakTokenService keycloakTokenService;

    @PreAuthorize("hasRole('disputes')")
    @GetMapping("/test-snowflake")
    public ResponseEntity<Map<String, Object>> testQuery() {
        final String query = "{ \"statement\": \"SELECT * FROM users\" }";

        // 1. Llamada a WireMock (mock de Snowflake)
        final ResponseEntity<Map<String, Object>> result = this.snowflakeClient.sendQuery(
                query,
                "Bearer abc123", // token esperado por el mock
                "application/json"
        );

        // 2. Obtener el token real de Keycloak
        final String token = this.keycloakTokenService.getToken();

        // 3. Construir la respuesta combinada
        final Map<String, Object> combined = new HashMap<>();
        combined.put("wiremockResponse", result.getBody());
        combined.put("keycloakToken", token);

        return ResponseEntity.ok(combined);
    }
}
