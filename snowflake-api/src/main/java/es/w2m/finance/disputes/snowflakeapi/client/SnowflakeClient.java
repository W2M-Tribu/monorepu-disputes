package es.w2m.finance.disputes.snowflakeapi.client;

import es.w2m.finance.disputes.snowflakeapi.dto.SnowflakeStatementRequest;
import feign.Headers;
import feign.RequestLine;

import java.util.Map;

public interface SnowflakeClient {

    @RequestLine("POST /api/v2/statements")
    @Headers("Content-Type: application/json")
    Map<String, Object> sendQuery(SnowflakeStatementRequest body);
}
