package es.w2m.finance.disputes.w2mdisputes.infrastructure.client;

import es.w2m.finance.disputes.w2mdisputes.infrastructure.config.OAuth2FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
        name = "snowflakeApiClient",
        url = "${w2m.rest.client.services.snowflake-client.service-url:}",
        configuration = OAuth2FeignConfig.class
)
public interface SnowflakeApiClient {

    @GetMapping("/test-snowflake")
    Map<String, Object> getDispute();
}