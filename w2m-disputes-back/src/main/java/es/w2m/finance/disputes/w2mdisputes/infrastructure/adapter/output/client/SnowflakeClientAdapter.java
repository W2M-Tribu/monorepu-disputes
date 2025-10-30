package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.client;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SnowflakePort;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.client.SnowflakeApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SnowflakeClientAdapter implements SnowflakePort {

    private final SnowflakeApiClient feign;

    @Override
    public Map<String, Object> fetchDispute(String id) {
        return feign.getDispute();
    }
}
