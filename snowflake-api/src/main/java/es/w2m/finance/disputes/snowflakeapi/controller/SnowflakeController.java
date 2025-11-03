package es.w2m.finance.disputes.snowflakeapi.controller;

import es.w2m.finance.disputes.snowflakeapi.client.SnowflakeClient;
import es.w2m.finance.disputes.snowflakeapi.dto.SnowflakeStatementRequest;
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
        // El encoder Jackson pondrá Content-Type: application/json
        // El RequestInterceptor OAuth2 añadirá Authorization: Bearer <token>
        final var request = new SnowflakeStatementRequest("SELECT * FROM users");

        // 1) Llamada al mock (o Snowflake real)
        final Map<String, Object> wiremockResponse = this.snowflakeClient.sendQuery(request);

        // 2) Obtener token real de Keycloak (si quieres mostrarlo en la respuesta)
        final String token = this.keycloakTokenService.getToken();

        // 3) Respuesta combinada
        final Map<String, Object> combined = new HashMap<>();
        combined.put("wiremockResponse", wiremockResponse);
        combined.put("keycloakToken", token);

        return ResponseEntity.ok(combined);
    }
}