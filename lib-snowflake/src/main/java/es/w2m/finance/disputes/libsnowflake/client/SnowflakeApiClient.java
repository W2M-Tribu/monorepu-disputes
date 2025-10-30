package es.w2m.finance.disputes.libsnowflake.client;

import feign.RequestLine;

import java.util.Map;

public interface SnowflakeApiClient {

    @RequestLine("GET /test-snowflake")
    Map<String, Object> getDispute();
}