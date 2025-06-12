package es.w2m.finance.disputes.edgeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador mock dentro del edge-service.
 *
 * Su objetivo es simular la respuesta que se obtendría desde una fuente real de datos,
 * como podría ser Snowflake u otro sistema de backend en el futuro.
 *
 * Este mock permite que el microservicio de disputas pueda funcionar en modo PMV,
 * recibiendo datos simulados sin depender de una base de datos o sistema externo real.
 *
 * Instrucciones cumplidas:
 * - Responde a llamadas del microservicio de disputas con una disputa mock
 * - Proporciona los campos esperados: id, cantidad y descripción
 */
@RestController
@RequestMapping("/dispute")
public class DisputeMockController {

    /**
     * Endpoint mock que devuelve una disputa estática para cualquier ID solicitado.
     *
     * @param id Identificador de la disputa recibido por URL
     * @return Un objeto JSON con datos simulados de disputa
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDispute(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
                "id", id,
                "amount", "1000€",
                "description", "Mock dispute description"
        ));
    }
}
