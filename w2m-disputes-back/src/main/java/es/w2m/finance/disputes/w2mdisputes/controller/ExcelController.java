package es.w2m.finance.disputes.w2mdisputes.controller;

import es.w2m.finance.disputes.w2mdisputes.service.DisputeExcelImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")
@RequiredArgsConstructor
public class ExcelController {

    private final DisputeExcelImportService excelImportService;

    /**
     * Receives a single Excel file and a type parameter ("customer" or "supplier"),
     * and stores the content into the corresponding database table.
     *
     * @param file the Excel file to upload
     * @param type the type of dispute: "customer" or "supplier"
     * @return a message with the result of the operation
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("The uploaded file is empty.");
        }

        try {
            switch (type.toLowerCase()) {
                case "customer" -> {
                    excelImportService.importCustomerExcel(file);
                    return ResponseEntity.ok("Customer Excel imported successfully.");
                }
                case "supplier" -> {
                    excelImportService.importSupplierExcel(file);
                    return ResponseEntity.ok("Supplier Excel imported successfully.");
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Invalid type. Must be 'customer' or 'supplier'.");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }
}
