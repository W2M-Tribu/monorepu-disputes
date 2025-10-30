package es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "w2m.snowflake")
public class SnowflakeClientProperties {
    /**
     * URL base del microservicio B (edge-service).
     * Ej: https://edge-service:8081
     */
    private String baseUrl;

    /**
     * Id de registro OAuth2 (Spring Security client registration).
     * Ej: keycloak
     */
    private String clientRegistrationId = "keycloak";

    /**
     * Principal usado para client_credentials.
     */
    private String principal = "snowflake-client";

    /**
     * Tiempo de espera Feign (ms).
     */
    private int connectTimeoutMs = 1500;
    private int readTimeoutMs = 3000;

    /**
     * Retries (0 para deshabilitar).
     */
    private int maxRetries = 0;
}