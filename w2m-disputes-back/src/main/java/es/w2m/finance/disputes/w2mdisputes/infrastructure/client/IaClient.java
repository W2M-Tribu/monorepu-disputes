package es.w2m.finance.disputes.w2mdisputes.infrastructure.client;

import feign.RequestLine;

import java.util.Map;

public interface IaClient {

    @RequestLine("GET /disputes/ia/api/v1/category")
    Map<String, Object> getCategory();
}
