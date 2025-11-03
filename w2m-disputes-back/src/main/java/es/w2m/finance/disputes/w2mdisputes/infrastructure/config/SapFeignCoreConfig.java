package es.w2m.finance.disputes.w2mdisputes.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.client.SapClient;
import feign.Client;
import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SapFeignCoreConfig {

    @Bean
    @Qualifier("sapDecoder")
    public Decoder sapDecoder(final ObjectMapper mapper) {
        return new JacksonDecoder(mapper);
    }

    @Bean
    @Qualifier("sapEncoder")
    public Encoder sapEncoder(final ObjectMapper mapper) {
        return new JacksonEncoder(mapper);
    }

    @Bean
    @Qualifier("sapFeignClient")
    @ConditionalOnMissingBean(name = "sapFeignClient")
    public Client sapFeignClient() {
        return new OkHttpClient();
    }

    @Bean
    @Qualifier("sapFeignLogLevel")
    public Logger.Level sapLogLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    public SapClient sapClient(
            @Value("${mock-server.base-url}") final String baseUrl,
            @Qualifier("sapFeignClient") final Client client,
            @Qualifier("sapDecoder") final Decoder decoder,
            @Qualifier("sapEncoder") final Encoder encoder,
            @Qualifier("sapFeignLogLevel") final Logger.Level logLevel,
            final List<RequestInterceptor> interceptors
    ) {
        return Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .logLevel(logLevel)
                .requestInterceptors(interceptors)
                .target(SapClient.class, baseUrl);
    }
}
