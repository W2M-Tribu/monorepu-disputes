package es.w2m.finance.snowflakeapi.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.w2m.finance.snowflakeapi.common.client.SnowflakeClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class SnowflakeCoreFeignConfig {

    @Bean
    public Decoder feignDecoder(final ObjectMapper mapper) {
        return new JacksonDecoder(mapper);
    }

    @Bean
    public Encoder feignEncoder(final ObjectMapper mapper) {
        return new JacksonEncoder(mapper);
    }

    @Bean
    @ConditionalOnMissingBean(feign.Client.class)
    public feign.Client feignClient() {
        return new feign.okhttp.OkHttpClient();
    }

    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

    @Bean
    public SnowflakeClient snowflakeClient(
            @Value("${w2m.rest.client.services.snowflake-client.service-url}") final String baseUrl,
            final feign.Client client,
            final Decoder decoder,
            final Encoder encoder,
            final feign.Logger.Level logLevel,
            final java.util.List<RequestInterceptor> interceptors
    ) {
        return Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .logLevel(logLevel)
                .requestInterceptors(interceptors)
                .target(SnowflakeClient.class, baseUrl);
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(final OAuth2AuthorizedClientManager manager) {
        return template -> {
            // URL base del target (requiere feign >= 12.x)
            final String targetUrl = template.feignTarget() != null ? template.feignTarget().url() : "";

            // No añadir Authorization para el mock
            if (targetUrl.contains("mockserver.internal.w2m.com")) {
                return; // skip
            }

            final var authReq = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
                    .principal("edge-service")
                    .build();
            final var client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }
            template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
        };
    }
}