package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.input.rest;


import es.w2m.finance.disputes.w2mdisputes.application.port.input.GetAggregatedDisputeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dispute")
public class DemoController {

    private final GetAggregatedDisputeUseCase getUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<?> getDispute(@PathVariable final String id) {
        //todo: ¿Creo un paquete tipo demo?
        return ResponseEntity.ok(this.getUseCase.get(id));
    }
}