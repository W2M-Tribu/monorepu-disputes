package es.w2m.finance.disputes.w2mdisputes.application.port.output;
import java.util.Map;

public interface SnowflakePort {
    Map<String,Object> fetchDispute(String id);
}