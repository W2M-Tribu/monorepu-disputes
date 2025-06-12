package com.example.iamock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * Mock del servicio de Inteligencia Artificial (IA).
 *
 * Este controlador forma parte del PMV y simula la clasificación automática
 * de una disputa basada en su contenido. Devuelve una categoría fija como ejemplo.
 *
 * Instrucciones cumplidas:
 * - El microservicio de disputas debe consultar a un mock de IA.
 * - Este endpoint responde con una categoría fija como si fuera generada por un modelo IA.
 */
@RestController
public class CategoryController {

    /**
     * Endpoint que simula la respuesta de un sistema de IA para clasificar disputas.
     *
     * @return Un JSON con la categoría simulada
     */
    @GetMapping("/category")
    public Map<String, String> getCategory() {
        return Map.of("category", "incorrect number of vacation days");
    }
}
