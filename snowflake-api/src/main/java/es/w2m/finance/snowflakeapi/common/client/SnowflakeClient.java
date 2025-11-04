package es.w2m.finance.snowflakeapi.common.client;

import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface SnowflakeClient {

    @RequestLine("POST /api/v2/statements")
    @Headers("Content-Type: application/json")
    Map<String, Object> sendQuery(SnowflakeRequest body);
}
