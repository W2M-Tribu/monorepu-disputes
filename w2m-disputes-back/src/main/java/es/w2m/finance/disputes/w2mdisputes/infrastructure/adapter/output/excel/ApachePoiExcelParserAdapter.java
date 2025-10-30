package es.w2m.finance.disputes.w2mdisputes.infrastructure.adapter.output.excel;

import es.w2m.finance.disputes.w2mdisputes.application.port.output.ExcelParserPort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApachePoiExcelParserAdapter implements ExcelParserPort {

    @Override
    @SneakyThrows
    public List<Dispute> parse(MultipartFile file) {
        List<Dispute> list = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet("Disputas");
            if (sheet == null) {
                return list;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                LocalDate openedDate = getDate(row, 1);
                String clientName = getString(row, 4);
                String invoiceNumber = getString(row, 7);

                if (openedDate == null ||
                        clientName == null || clientName.isBlank() ||
                        invoiceNumber == null || invoiceNumber.isBlank()) {
                    continue;
                }

                Dispute dispute = Dispute.builder()
                        .id(null)
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

                list.add(dispute);
            }
        }
        return list;
    }


    private static String getString(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return (cell != null) ? cell.toString().trim() : null;
    }

    private static Double getDouble(Row row, int idx) {
        try {
            Cell cell = row.getCell(idx);
            return (cell != null) ? cell.getNumericCellValue() : null;
        } catch (Exception ignored) {
            return null;
        }
    }

    private static LocalDate getDate(Row row, int idx) {
        try {
            Cell cell = row.getCell(idx);
            if (cell == null || cell.getCellType() != CellType.NUMERIC) return null;
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } catch (Exception ignored) {
            return null;
        }
    }
}
