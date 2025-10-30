package es.w2m.finance.disputes.w2mdisputes.application.port.output;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;

public interface SaveDisputePort {
    Dispute save(Dispute d);
}