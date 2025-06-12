package es.w2m.finance.disputes.w2mdisputes.controller;

import es.w2m.finance.disputes.w2mdisputes.client.EdgeServiceClien;
import es.w2m.finance.disputes.w2mdisputes.security.KeycloakTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador auxiliar para pruebas.
 * Esta clase permite probar el funcionamiento del mock de Snowflake (edge-service)
 * y la recuperación de un token real de autenticación desde Keycloak.
 */
@RequiredArgsConstructor
@RestController
public class SnowflakeController {

    // Cliente que se comunica con el servicio edge (mock de Snowflake)
    private final EdgeServiceClien edgeServiceClien;

    // Servicio que simula la obtención de un token de autenticación desde Keycloak
    private final KeycloakTokenService keycloakTokenService;

    /**
     * Endpoint de prueba que simula el envío de una consulta a Snowflake
     * y recupera un token desde Keycloak. Útil para verificar los mocks y la configuración de seguridad.
     *
     * No forma parte del flujo principal de disputa, pero puede usarse para debug o testing del mock.
     *
     * @return JSON con la respuesta del mock de Snowflake + el token generado por Keycloak
     */
    @GetMapping("/test-snowflake")
    public ResponseEntity<Map<String, Object>> testQuery() {
        String query = "{ \"statement\": \"SELECT * FROM users\" }";

        // 1. Llamada al mock de Snowflake a través del cliente (edge-service)
        ResponseEntity<Map<String, Object>> result = edgeServiceClien.sendQuery();

        // 2. Obtener token desde Keycloak (simulado)
        String token = keycloakTokenService.getToken();

        // 3. Construir respuesta con ambos resultados para verificación
        Map<String, Object> combined = new HashMap<>();
        combined.put("wiremockResponse", result.getBody());
        combined.put("keycloakToken", token);

        return ResponseEntity.ok(combined);
    }
}
