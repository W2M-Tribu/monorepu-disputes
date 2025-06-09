package com.example.sapmock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class EstadoController {

    @GetMapping("/estado")
    public Map<String, String> getEstado() {
        return Map.of("estado", "pausado");
    }
}