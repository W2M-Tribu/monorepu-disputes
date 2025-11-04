package es.w2m.finance.snowflakeapi.disputes.statement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementResponse {
    private Map<String, Object> snowflakeResponse;
    private String keycloakToken;
}