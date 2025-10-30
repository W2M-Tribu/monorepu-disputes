package es.w2m.finance.disputes.libsnowflake.configuration.properties;

import lombok.Data;

@Data
public class OAuth2Configuration {
    private String clientId = "";
    private String clientRegistrationId = "keycloak";
    private String principal = "snowflake-client";
}
