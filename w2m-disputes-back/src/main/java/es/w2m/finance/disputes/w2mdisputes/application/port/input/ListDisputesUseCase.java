package es.w2m.finance.disputes.w2mdisputes.application.port.input;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import java.util.List;

public interface ListDisputesUseCase {
    List<Dispute> listAll();
}