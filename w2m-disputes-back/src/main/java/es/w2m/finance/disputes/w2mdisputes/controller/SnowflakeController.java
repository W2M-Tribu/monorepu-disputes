package es.w2m.finance.disputes.w2mdisputes.controller;

import es.w2m.finance.disputes.w2mdisputes.client.EdgeServiceClien;
import es.w2m.finance.disputes.w2mdisputes.security.KeycloakTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SnowflakeController {

    private final EdgeServiceClien edgeServiceClien;

    private final KeycloakTokenService keycloakTokenService;

    @GetMapping("/test-snowflake")
    public ResponseEntity<Map<String, Object>> testQuery() {
        String query = "{ \"statement\": \"SELECT * FROM users\" }";

        // 1. Llamada a WireMock (mock de Snowflake)
        ResponseEntity<Map<String, Object>> result = edgeServiceClien.sendQuery();
        // 2. Obtener el token real de Keycloak
        String token = keycloakTokenService.getToken();

        // 3. Construir la respuesta combinada
        Map<String, Object> combined = new HashMap<>();
        combined.put("wiremockResponse", result.getBody());
        combined.put("keycloakToken", token);

        return ResponseEntity.ok(combined);
    }
}
