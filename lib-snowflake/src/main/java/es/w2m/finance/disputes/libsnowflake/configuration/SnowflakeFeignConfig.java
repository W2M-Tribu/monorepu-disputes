package es.w2m.finance.disputes.libsnowflake.configuration;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import es.w2m.finance.disputes.libsnowflake.client.interceptor.OAuth2FeignInterceptor;
import es.w2m.finance.disputes.libsnowflake.configuration.properties.SnowflakeClientProperties;
import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.server.ResponseStatusException;

@Configuration
@EnableConfigurationProperties(SnowflakeClientProperties.class)
public class SnowflakeFeignConfig {

    @Bean
    public ErrorDecoder snowflakeErrorDecoder() {
        return (methodKey, response) ->
                new ResponseStatusException(
                        HttpStatus.valueOf(response.status()),
                        "Snowflake upstream error (" + response.status() + ") in " + methodKey
                );
    }

    @Bean
    public Logger.Level feignLoggerLevel() { return Logger.Level.BASIC; }

    @Bean
    public Retryer retryer(SnowflakeClientProperties props) {
        return props.getMaxRetries() > 0 ? new Retryer.Default() : Retryer.NEVER_RETRY;
    }

    @Bean
    public RequestInterceptor oauth2Interceptor(
            OAuth2AuthorizedClientManager oAuth2Manager,
            SnowflakeClientProperties props
    ) {

        if (oAuth2Manager == null || StringUtils.isBlank(props.getOauth2().getClientId())) {
            throw new IllegalStateException("OAuth2 client credentials not configured (clientId missing)");
        }

        return new OAuth2FeignInterceptor(
                oAuth2Manager,
                props.getOauth2().getClientRegistrationId(),
                props.getOauth2().getPrincipal()
        );
    }
}
