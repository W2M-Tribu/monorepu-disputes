package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.input.rest;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.CreateDisputeInputPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.input.ListDisputesUseCase;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.service.GetDisputesFromExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/disputes")
public class CreateDisputesController {

    private final CreateDisputeInputPort createDisputeInputPort;
    private final GetDisputesFromExcelService getDisputesFromExcelService;
    private final ListDisputesUseCase listUC;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("excel") final MultipartFile excel) {
        //todo: avisar si no viene excel a traves de un DTO
        final List<Dispute> disputes = this.getDisputesFromExcel(excel);
        this.createDisputeInputPort.createDispute(disputes);
        return ResponseEntity.ok("Disputes saved successfully.");
    }

    private List<Dispute> getDisputesFromExcel(final MultipartFile excel) {
        final List<Dispute> disputes = this.getDisputesFromExcelService.getDisputesFromExcel(excel);
        return disputes;
    }
}