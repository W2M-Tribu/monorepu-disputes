package es.w2m.finance.disputes.w2mdisputes.application.usecase;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.CreateDisputeInputPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SaveDisputePort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateDisputeUseCase implements CreateDisputeInputPort {

    private final SaveDisputePort  saveDisputePort;

    public void  createDispute(List<Dispute> disputes) {
        //todo: obtain the rest of the disputes info
        disputes.forEach(saveDisputePort::save);
    }
}
