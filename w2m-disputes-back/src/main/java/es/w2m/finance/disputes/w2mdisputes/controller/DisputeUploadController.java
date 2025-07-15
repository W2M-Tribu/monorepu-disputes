package es.w2m.finance.disputes.w2mdisputes.controller;

import es.w2m.finance.disputes.w2mdisputes.service.DisputeImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/disputes")
@RequiredArgsConstructor
public class DisputeUploadController {

    private final DisputeImportService importService;

    /**
     * Uploads an Excel file in the expected format and stores each row as a DisputeRecord.
     *
     * @param file the Excel file containing dispute data
     * @return a success or error message
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded.");
        }

        try {
            importService.importFromExcel(file);
            return ResponseEntity.ok("Disputes imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error importing Excel: " + e.getMessage());
        }
    }

    //SOLO PARA VER LOS DATOS DE LA TABLA
    @GetMapping
    public ResponseEntity<?> getAllDisputes() {
        return ResponseEntity.ok(importService.getAllDisputes());
    }

}
