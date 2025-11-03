package es.w2m.finance.disputes.w2mdisputes.infrastructure.client;

import feign.RequestLine;

import java.util.Map;

public interface SapClient {

    @RequestLine("GET /api/v1/status")
    Map<String, Object> getStatus();
}
