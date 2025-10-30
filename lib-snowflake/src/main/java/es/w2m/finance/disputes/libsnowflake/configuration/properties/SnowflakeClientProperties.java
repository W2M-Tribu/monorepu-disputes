package es.w2m.finance.disputes.libsnowflake.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "w2m.rest.client.services.snowflake-client")
public class SnowflakeClientProperties {

    private String serviceUrl;
    private int connectTimeoutMs = 1500;
    private int readTimeoutMs = 3000;
    private int maxRetries = 0;

    private OAuth2Configuration oauth2 = new OAuth2Configuration();
}