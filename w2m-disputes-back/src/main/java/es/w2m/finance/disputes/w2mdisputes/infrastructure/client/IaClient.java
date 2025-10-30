package es.w2m.finance.disputes.w2mdisputes.infrastructure.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "iaClient", url = "http://localhost:8082")
public interface IaClient {
    @GetMapping("/api/v1/category")
    Map<String, Object> getCategory();
}
