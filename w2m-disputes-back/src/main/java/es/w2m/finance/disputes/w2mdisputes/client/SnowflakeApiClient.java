package es.w2m.finance.disputes.w2mdisputes.client;

import es.w2m.finance.disputes.w2mdisputes.config.OAuth2FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
        name = "snowflakeApiClient",
        url = "http://localhost:8081",
        configuration = OAuth2FeignConfig.class
)
public interface SnowflakeApiClient {

    @GetMapping("/test-snowflake")
    Map<String, Object> getDispute();
}