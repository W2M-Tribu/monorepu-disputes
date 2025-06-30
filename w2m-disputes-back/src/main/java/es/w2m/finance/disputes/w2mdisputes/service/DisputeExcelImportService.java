package es.w2m.finance.disputes.w2mdisputes.service;

import es.w2m.finance.disputes.w2mdisputes.model.CustomerDispute;
import es.w2m.finance.disputes.w2mdisputes.model.SupplierDispute;
import es.w2m.finance.disputes.w2mdisputes.repository.CustomerDisputeRepository;
import es.w2m.finance.disputes.w2mdisputes.repository.SupplierDisputeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class DisputeExcelImportService {

    private final CustomerDisputeRepository customerRepo;
    private final SupplierDisputeRepository supplierRepo;

    public void importCustomerExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                CustomerDispute dispute = CustomerDispute.builder()
                        .summary(getCell(row, 0))
                        .description(getCell(row, 1))
                        .invoiceNumber(getCell(row, 2))
                        .loc(getCell(row, 3))
                        .amount(getCell(row, 4))
                        .build();

                customerRepo.save(dispute);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error importing customer Excel", e);
        }
    }

    public void importSupplierExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                SupplierDispute dispute = SupplierDispute.builder()
                        .bp(getCell(row, 0))
                        .summary(getCell(row, 1))
                        .description(getCell(row, 2))
                        .invoiceNumber(getCell(row, 3))
                        .loc(getCell(row, 4))
                        .amount(getCell(row, 5))
                        .supplierComment(getCell(row, 6))
                        .build();

                supplierRepo.save(dispute);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error importing supplier Excel", e);
        }
    }

    private String getCell(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return (cell != null) ? cell.toString().trim() : "";
    }
}
