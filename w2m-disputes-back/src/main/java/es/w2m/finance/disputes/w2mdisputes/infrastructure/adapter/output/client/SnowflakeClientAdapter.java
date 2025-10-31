package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.client;

import es.w2m.finance.disputes.libsnowflake.client.SnowflakeApiClient;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SnowflakePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SnowflakeClientAdapter implements SnowflakePort {

    private final SnowflakeApiClient snowflakeApiClient;

    @Override
    public Map<String, Object> fetchDispute(final String id) {
        return this.snowflakeApiClient.getDispute();
    }
}
