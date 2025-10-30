package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.input.rest;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.ListDisputesUseCase;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/disputes")
public class GetAllDisputesController {

    private final ListDisputesUseCase listUC;

    @GetMapping
    public ResponseEntity<?> getAllDisputes() {
        final List<Dispute> disputes = this.listUC.listAll();
        return ResponseEntity.ok(disputes);
    }
}