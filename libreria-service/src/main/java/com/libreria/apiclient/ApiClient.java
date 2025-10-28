package com.libreria.apiclient;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class ApiClient {
    private final RestTemplate restTemplate;
    private final ApiClientProperties properties;

    public ApiClient(RestTemplate restTemplate, ApiClientProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }


    public ResponseEntity<String> get(String path) {
        String finalPath = (path != null && !path.isBlank())
                ? (path.startsWith("/") ? path : "/" + path)
                : "";

        String url = org.springframework.web.util.UriComponentsBuilder
                .fromHttpUrl(properties.getBaseUrl())
                .path(finalPath)
                .toUriString();

        return restTemplate.getForEntity(url, String.class);
    }

}
