package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence;

import es.w2m.finance.disputes.w2mdisputes.application.port.output.SaveDisputePort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.mapper.DisputePersistenceMapper;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.repository.SpringDataDisputeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveDisputesOutputToDatabaseAdapter implements SaveDisputePort {
    private final SpringDataDisputeRepository dataDisputeRepository;
    private final DisputePersistenceMapper disputePersistenceMapper;

    @Override
    public Dispute save(final Dispute dispute) {
        final var saved = this.dataDisputeRepository.save(this.disputePersistenceMapper.toEntity(dispute));
        return this.disputePersistenceMapper.toDomain(saved);
    }
}
