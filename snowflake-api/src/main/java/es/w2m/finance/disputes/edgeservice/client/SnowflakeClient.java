package es.w2m.finance.disputes.edgeservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "snowflakeClient", url = "http://localhost:8082")
public interface SnowflakeClient {

    @PostMapping(value = "/api/v2/statements", consumes = "application/json")
    ResponseEntity<Map<String, Object>> sendQuery(
            @RequestBody String query,
            @RequestHeader("Authorization") String bearerToken,
            @RequestHeader("Content-Type") String contentType
    );
}
