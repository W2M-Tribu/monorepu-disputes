package es.w2m.finance.disputes.w2mdisputes.application.usecase;

import es.w2m.finance.disputes.w2mdisputes.application.port.input.ImportDisputesUseCase;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.ExcelParserPort;
import es.w2m.finance.disputes.w2mdisputes.application.port.output.SaveDisputePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImportDisputesService implements ImportDisputesUseCase {

    private final ExcelParserPort excel;
    private final SaveDisputePort save;

    @Override
    @Transactional
    public void importExcel(MultipartFile file) {
        excel.parse(file).forEach(save::save);
    }

}
