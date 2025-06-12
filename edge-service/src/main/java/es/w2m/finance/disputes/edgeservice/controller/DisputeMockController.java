package es.w2m.finance.disputes.edgeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/dispute")
public class DisputeMockController {

    // ⚠️ MOCK PARA PRUEBAS. REEMPLAZAR POR LA LÓGICA REAL CUANDO SE CONECTE A SNOWFLAKE U OTROS
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDispute(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
                "id", id,
                "amount", "1000€",
                "description", "Mock dispute description"
        ));
    }
}
