package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.LoadAllDisputesPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SaveDisputePort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.mapper.DisputePersistenceMapper;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.repository.SpringDataDisputeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DisputePersistenceAdapter implements SaveDisputePort, LoadAllDisputesPort {
    private final SpringDataDisputeRepository repo;
    private final DisputePersistenceMapper mapper;

    @Override
    public Dispute save(Dispute dispute) {
        var saved = repo.save(mapper.toEntity(dispute));
        return mapper.toDomain(saved);
    }
    @Override
    public List<Dispute> findAll() {
        return repo.findAll().stream().map(mapper::toDomain).toList();
    }
}
