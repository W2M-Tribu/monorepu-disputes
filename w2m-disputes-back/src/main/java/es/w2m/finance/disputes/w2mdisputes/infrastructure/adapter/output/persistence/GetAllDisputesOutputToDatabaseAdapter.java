package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence;

import es.w2m.finance.disputes.w2mdisputes.application.port.output.GetAllDisputesOutputPort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.mapper.DisputePersistenceMapper;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.repository.SpringDataDisputeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllDisputesOutputToDatabaseAdapter implements GetAllDisputesOutputPort {
    private final SpringDataDisputeRepository repo;
    private final DisputePersistenceMapper mapper;

    @Override
    public List<Dispute> findAll() {
        return this.repo.findAll().stream().map(this.mapper::toDomain).toList();
    }
}
