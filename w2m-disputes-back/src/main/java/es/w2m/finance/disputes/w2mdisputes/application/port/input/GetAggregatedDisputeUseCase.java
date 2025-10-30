package es.w2m.finance.disputes.w2mdisputes.application.port.input;
import java.util.Map;

public interface GetAggregatedDisputeUseCase {
    Map<String,Object> get(String id);
}