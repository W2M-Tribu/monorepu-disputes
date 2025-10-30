package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.input.rest;


import es.w2m.finance.disputes.w2mdisputes.application.port.input.GetAggregatedDisputeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dispute")
public class DisputeController {

    private final GetAggregatedDisputeUseCase getUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDispute(@PathVariable String id) {
        return ResponseEntity.ok(getUseCase.get(id));
    }
}