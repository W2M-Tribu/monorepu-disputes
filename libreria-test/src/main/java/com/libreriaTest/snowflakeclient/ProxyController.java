package com.libreriaTest.snowflakeclient;

import com.libreria.apiclient.ApiClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProxyController {

    private final ApiClient apiClient;

    public ProxyController(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @PostMapping("/probar")
    public ResponseEntity<String> probar(@RequestBody ProbarRequestDTO body) {
        if (body == null || body.getUrl() == null || body.getUrl().isBlank()) {
            return ResponseEntity.badRequest().body("Falta 'url' en el body");
        }
        ResponseEntity<String> resp = apiClient.get('/test-snowflake');
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
}
