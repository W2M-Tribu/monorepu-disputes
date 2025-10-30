package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.mapper;

import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.persistence.entity.DisputeRecordEntity;
import org.springframework.stereotype.Component;

@Component
public class DisputePersistenceMapper {

    public Dispute toDomain(DisputeRecordEntity entity) {
        if (entity == null) return null;

        return Dispute.builder()
                .id(entity.getId())
                .clientName(entity.getClientName())
                .invoiceNumber(entity.getInvoiceNumber())
                .disputeStatus(entity.getDisputeStatus())
                .disputedAmount(entity.getDisputedAmount())
                .build();
    }

    public DisputeRecordEntity toEntity(Dispute dispute) {
        if (dispute == null) return null;

        return DisputeRecordEntity.builder()
                .id(dispute.getId())
                .clientName(dispute.getClientName())
                .invoiceNumber(dispute.getInvoiceNumber())
                .disputeStatus(dispute.getDisputeStatus())
                .disputedAmount(dispute.getDisputedAmount())
                .build();
    }
}
