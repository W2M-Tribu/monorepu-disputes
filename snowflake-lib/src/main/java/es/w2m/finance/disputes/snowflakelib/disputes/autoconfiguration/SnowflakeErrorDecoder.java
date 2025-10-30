package es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.web.server.ResponseStatusException;

public class SnowflakeErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        return new ResponseStatusException(
                org.springframework.http.HttpStatus.valueOf(response.status()),
                "Snowflake upstream error (" + response.status() + ") in " + methodKey);
    }
}