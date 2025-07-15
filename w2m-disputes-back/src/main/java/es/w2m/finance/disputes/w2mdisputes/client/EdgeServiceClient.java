package es.w2m.finance.disputes.w2mdisputes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "edgeServiceClient", url = "http://localhost:8080")
public interface EdgeServiceClient {

    @GetMapping(value = "/test-snowflake", consumes = "application/json")
    ResponseEntity<Map<String, Object>> sendQuery();
}