package es.w2m.finance.disputes.w2mdisputes.infrastructure.service;

import es.w2m.finance.disputes.w2mdisputes.application.port.output.ExcelParserPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SaveDisputePort;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetDisputesFromExcelServiceImpl implements GetDisputesFromExcelService {

    private final ExcelParserPort excel;

    @Override
    @Transactional
    public List<Dispute> getDisputesFromExcel(MultipartFile file) {
        return excel.parse(file);
    }

}
