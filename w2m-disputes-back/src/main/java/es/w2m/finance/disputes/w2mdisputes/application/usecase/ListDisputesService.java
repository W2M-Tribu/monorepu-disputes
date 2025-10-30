package es.w2m.finance.disputes.w2mdisputes.application.usecase;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.ListDisputesUseCase;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.LoadAllDisputesPort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListDisputesService implements ListDisputesUseCase {

    private final LoadAllDisputesPort load;

    @Override
    public List<Dispute> listAll() {
        return load.findAll();
    }

}
