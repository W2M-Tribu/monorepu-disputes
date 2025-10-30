package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.client;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.AiCategoryPort;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.client.IaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IaClientAdapter implements AiCategoryPort {

    private final IaClient feign;

    @Override
    public String getCategory() {
        return (String) feign.getCategory().get("category");
    }
}
