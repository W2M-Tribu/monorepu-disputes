package es.w2m.finance.disputes.w2mdisputes.infrastructure.service;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GetDisputesFromExcelService {
    List<Dispute> getDisputesFromExcel(MultipartFile file);
}