package es.w2m.finance.disputes.snowflakelib.disputes.api.client;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

public interface SnowflakeApiClient {

    @GetMapping("/test-snowflake")
    Map<String, Object> getDispute();
}