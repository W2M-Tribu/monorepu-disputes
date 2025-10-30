package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.repository;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.entity.DisputeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataDisputeRepository extends JpaRepository<DisputeRecordEntity, Long> {}
