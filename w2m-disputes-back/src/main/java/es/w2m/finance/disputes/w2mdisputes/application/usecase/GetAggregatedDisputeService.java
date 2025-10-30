package es.w2m.finance.disputes.w2mdisputes.application.usecase;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.GetAggregatedDisputeUseCase;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.AiCategoryPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SapStatusPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SnowflakePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAggregatedDisputeService implements GetAggregatedDisputeUseCase {

    private final SnowflakePort snowflake;
    private final SapStatusPort sap;
    private final AiCategoryPort ai;

    @Override
    public Map<String, Object> get(String id) {
        var dispute = snowflake.fetchDispute(id);
        return Map.of(
                "dispute", dispute,
                "sap_status", sap.getStatus(),
                "ai_category", ai.getCategory()
        );
    }

}
