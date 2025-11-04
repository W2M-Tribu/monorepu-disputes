package es.w2m.finance.libsnowflake.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.w2m.finance.libsnowflake.disputes.client.SnowflakeApiClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@Configuration
public class FeignBuilderConfig {

    @Bean
    @Qualifier("sfApiDecoder")
    public Decoder sfApiDecoder(final ObjectMapper mapper) {
        return new JacksonDecoder(mapper);
    }

    @Bean
    @Qualifier("sfApiEncoder")
    public Encoder sfApiEncoder(final ObjectMapper mapper) {
        return new JacksonEncoder(mapper);
    }

    @Bean
    @Qualifier("sfApiFeignClient")
    @ConditionalOnMissingBean(name = "sfApiFeignClient")
    public feign.Client sfApiFeignClient() {
        return new feign.okhttp.OkHttpClient();
    }

    @Bean
    @Qualifier("sfApiLogLevel")
    public feign.Logger.Level sfApiLogLevel() {
        return feign.Logger.Level.FULL;
    }

    @Bean
    @Qualifier("sfApiAuthInterceptor")
    public RequestInterceptor sfApiAuthInterceptor(final OAuth2AuthorizedClientManager manager) {
        return template -> {
            final var authReq = OAuth2AuthorizeRequest
                    .withClientRegistrationId("keycloak")
                    .principal("edge-service")
                    .build();

            final var client = manager.authorize(authReq);
            if (client == null || client.getAccessToken() == null) {
                throw new IllegalStateException("Could not obtain access token (client_credentials).");
            }
            template.header("Authorization", "Bearer " + client.getAccessToken().getTokenValue());
        };
    }

    @Bean
    public SnowflakeApiClient snowflakeApiClient(
            @Value("${w2m.rest.client.services.snowflake-client.service-url}") final String baseUrl,
            @Qualifier("sfApiFeignClient") final feign.Client client,
            @Qualifier("sfApiDecoder") final Decoder decoder,
            @Qualifier("sfApiEncoder") final Encoder encoder,
            @Qualifier("sfApiLogLevel") final feign.Logger.Level logLevel,
            @Qualifier("sfApiAuthInterceptor") final RequestInterceptor authInterceptor
    ) {
        return Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .logLevel(logLevel)
                .requestInterceptor(authInterceptor)
                .target(SnowflakeApiClient.class, baseUrl);
    }
}
