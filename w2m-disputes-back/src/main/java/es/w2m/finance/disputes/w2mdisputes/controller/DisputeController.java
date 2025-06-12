package es.w2m.finance.disputes.w2mdisputes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/dispute")
public class DisputeController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{id}")
    public ResponseEntity<?> getDispute(@PathVariable String id) {
        // 🔧 CAMBIAMOS LOS PUERTOS A LOS INTERNOS DE LOS CONTENEDORES DOCKER

        // Call to edge-service (puerto interno 8080, no 8081)
        Map<?, ?> dispute = restTemplate.getForObject("http://edge-service:8080/dispute/" + id, Map.class);

        // Call to sap-mock (puerto interno 3001)
        Map<?, ?> sapStatus = restTemplate.getForObject("http://sap-mock:3001/status", Map.class);

        // Call to ia-mock (puerto interno 3002)
        Map<?, ?> aiCategory = restTemplate.getForObject("http://ia-mock:3002/category", Map.class);

        return ResponseEntity.ok(Map.of(
                "dispute", dispute,
                "sap_status", sapStatus.get("status"),
                "ai_category", aiCategory.get("category")
        ));
    }
}
