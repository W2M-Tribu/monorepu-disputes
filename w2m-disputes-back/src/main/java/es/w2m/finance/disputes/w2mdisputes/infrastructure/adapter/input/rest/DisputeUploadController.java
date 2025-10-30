package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.input.rest;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.ImportDisputesUseCase;
import es.w2m.finance.disputes.w2mdisputes.application.port.input.ListDisputesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/disputes")
public class DisputeUploadController {

    private final ImportDisputesUseCase importUC;
    private final ListDisputesUseCase listUC;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) return ResponseEntity.badRequest().body("No file uploaded.");
        importUC.importExcel(file);
        return ResponseEntity.ok("Disputes imported successfully.");
    }

    @GetMapping
    public ResponseEntity<?> getAllDisputes() {
        return ResponseEntity.ok(listUC.listAll());
    }
}