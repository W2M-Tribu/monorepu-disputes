package es.w2m.finance.disputes.libsnowflake.configuration;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import es.w2m.finance.disputes.libsnowflake.client.SnowflakeApiClient;
import es.w2m.finance.disputes.libsnowflake.client.interceptor.OAuth2FeignInterceptor;
import es.w2m.finance.disputes.libsnowflake.configuration.properties.SnowflakeClientProperties;
import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(SnowflakeClientProperties.class)
public class SnowflakeClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ErrorDecoder snowflakeErrorDecoder() {
        return (methodKey, response) ->
                new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.valueOf(response.status()),
                        "Snowflake upstream error (" + response.status() + ") in " + methodKey
                );
    }

    @Bean
    @ConditionalOnMissingBean
    public Feign.Builder snowflakeFeignBuilder(ErrorDecoder errorDecoder, SnowflakeClientProperties props) {
        return Feign.builder()
                .client(new OkHttpClient())
                .errorDecoder(errorDecoder)
                .logger(new feign.slf4j.Slf4jLogger("SnowflakeClient"))
                .logLevel(Logger.Level.BASIC)
                .retryer(props.getMaxRetries() > 0
                        ? new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), props.getMaxRetries())
                        : Retryer.NEVER_RETRY)
                .options(new feign.Request.Options(
                        props.getConnectTimeoutMs(), TimeUnit.MILLISECONDS,
                        props.getReadTimeoutMs(), TimeUnit.MILLISECONDS,
                        true));
    }

    @Bean
    @ConditionalOnProperty("w2m.rest.client.services.snowflake-client.service-url")
    public SnowflakeApiClient snowflakeClient(Feign.Builder builder,
                                              SnowflakeClientProperties props,
                                              OAuth2AuthorizedClientManager oAuth2Manager) {

        if (oAuth2Manager == null || StringUtils.isBlank(props.getOauth2().getClientId())) {
            throw new IllegalStateException("OAuth2 client credentials not configured (clientId missing)");
        }

        var oauth2 = new OAuth2FeignInterceptor(
                oAuth2Manager,
                props.getOauth2().getClientRegistrationId(),
                props.getOauth2().getPrincipal()
        );

        return builder.requestInterceptor(oauth2)
                .target(SnowflakeApiClient.class, props.getServiceUrl());
    }
}