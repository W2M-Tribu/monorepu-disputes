package com.example.iamock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class CategoryController {

    @GetMapping("/category")
    public Map<String, String> getCategory() {
        return Map.of("category", "incorrect number of vacation days");
    }
}
