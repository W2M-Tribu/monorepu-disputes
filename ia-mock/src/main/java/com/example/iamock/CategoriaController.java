package com.example.iamock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class CategoriaController {

    @GetMapping("/categoria")
    public Map<String, String> getCategoria() {
        return Map.of("categoria", "número de días de vacaciones incorrecto");
    }
}