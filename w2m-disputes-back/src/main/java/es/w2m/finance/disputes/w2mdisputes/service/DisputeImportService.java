package es.w2m.finance.disputes.w2mdisputes.service;

import es.w2m.finance.disputes.w2mdisputes.model.Dispute;
import es.w2m.finance.disputes.w2mdisputes.repository.DisputeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisputeImportService {

    private final DisputeRepository repository;

    public void importFromExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheet("Disputas");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Requisitos mínimos para considerar válida una fila
                LocalDate openedDate = getDate(row, 1);
                String clientName = getString(row, 4);
                String invoiceNumber = getString(row, 7);

                if (openedDate == null ||
                        clientName == null || clientName.isBlank() ||
                        invoiceNumber == null || invoiceNumber.isBlank()) {
                    continue;
                }

                Dispute record = Dispute.builder()
                        .manager(getString(row, 0))
                        .openedDate(openedDate)
                        .jira(getString(row, 2))
                        .bp(getString(row, 3))
                        .clientName(clientName)
                        .loc(getString(row, 5))
                        .disputeStatus(getString(row, 6))
                        .invoiceNumber(invoiceNumber)
                        .invoiceDate(getDate(row, 8))
                        .agencyReference(getString(row, 9))
                        .delegation(getString(row, 10))
                        .invoiceAmount(getDouble(row, 11))
                        .currency(getString(row, 12))
                        .clientAmount(getDouble(row, 13))
                        .disputedAmount(getDouble(row, 14))
                        .finalSale(getDouble(row, 15))
                        .finalCurrency(getString(row, 16))
                        .clientComment(getString(row, 17))
                        .approvalStatus(getString(row, 18))
                        .internalComment(getString(row, 19))
                        .category(getString(row, 20))
                        .jiraHandledDate(getDate(row, 21))
                        .jiraClosedDate(getDate(row, 22))
                        .handledBy(getString(row, 23))
                        .build();

                repository.save(record);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error processing Excel file: " + e.getMessage(), e);
        }
    }

    private String getString(Row row, int index) {
        Cell cell = row.getCell(index);
        return (cell != null) ? cell.toString().trim() : null;
    }

    private LocalDate getDate(Row row, int index) {
        try {
            Cell cell = row.getCell(index);
            if (cell == null || cell.getCellType() != CellType.NUMERIC) return null;
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    private Double getDouble(Row row, int index) {
        try {
            Cell cell = row.getCell(index);
            if (cell == null) return null;
            return cell.getNumericCellValue();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Dispute> getAllDisputes() {
        return repository.findAll();
    }
}
