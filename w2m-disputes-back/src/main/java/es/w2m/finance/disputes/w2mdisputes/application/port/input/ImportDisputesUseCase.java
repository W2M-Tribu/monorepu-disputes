package es.w2m.finance.disputes.w2mdisputes.application.port.input;
import org.springframework.web.multipart.MultipartFile;

public interface ImportDisputesUseCase {
    void importExcel(MultipartFile file);
}