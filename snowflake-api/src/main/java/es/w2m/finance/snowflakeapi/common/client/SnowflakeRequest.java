package es.w2m.finance.snowflakeapi.common.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SnowflakeRequest {
    private String body;
}