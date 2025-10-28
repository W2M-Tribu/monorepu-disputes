package com.libreria.apiclient;


import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@EnableConfigurationProperties(ApiClientProperties.class)
public class ApiClientAutoConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApiClient apiClient(RestTemplate restTemplate, ApiClientProperties props) {
        return new ApiClient(restTemplate, props);
    }
}