package es.w2m.finance.disputes.w2mdisputes.repository;

import es.w2m.finance.disputes.w2mdisputes.model.Dispute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRepository extends JpaRepository<Dispute, Long> {
}
