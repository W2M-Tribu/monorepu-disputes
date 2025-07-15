package es.w2m.finance.disputes.w2mdisputes.repository;

import es.w2m.finance.disputes.w2mdisputes.model.DisputeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisputeRecordRepository extends JpaRepository<DisputeRecord, Long> {
}
