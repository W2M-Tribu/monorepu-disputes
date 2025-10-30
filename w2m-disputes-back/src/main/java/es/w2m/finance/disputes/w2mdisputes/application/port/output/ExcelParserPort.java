package es.w2m.finance.disputes.w2mdisputes.application.port.output;
import es.w2m.finance.disputes.w2mdisputes.domain.model.Dispute;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ExcelParserPort {
    List<Dispute> parse(MultipartFile file);
}