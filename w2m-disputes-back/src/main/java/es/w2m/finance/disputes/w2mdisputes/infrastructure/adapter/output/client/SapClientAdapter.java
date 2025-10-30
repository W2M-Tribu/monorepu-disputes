package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.client;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SapStatusPort;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.client.SapClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SapClientAdapter implements SapStatusPort {

    private final SapClient feign;

    @Override
    public String getStatus() {
        return (String) feign.getStatus().get("status");
    }
}
