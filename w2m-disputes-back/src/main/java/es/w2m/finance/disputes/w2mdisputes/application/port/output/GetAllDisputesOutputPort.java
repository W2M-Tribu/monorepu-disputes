package es.w2m.finance.disputes.w2mdisputes.application.port.output;

import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;

import java.util.List;

public interface GetAllDisputesOutputPort {
    List<Dispute> findAll();
}