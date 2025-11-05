package es.w2m.finance.disputes.w2mdisputes.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.w2m.finance.disputes.w2mdisputes.infrastructure.client.IaClient;
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
public class IaFeignCoreConfig {

    @Bean
    @Qualifier("iaDecoder")
    public Decoder iaDecoder(final ObjectMapper mapper) {
        return new JacksonDecoder(mapper);
    }

    @Bean
    @Qualifier("iaEncoder")
    public Encoder iaEncoder(final ObjectMapper mapper) {
        return new JacksonEncoder(mapper);
    }

    @Bean
    @Qualifier("iaFeignClient")
    @ConditionalOnMissingBean(name = "iaFeignClient")
    public Client iaFeignClient() {
        return new OkHttpClient();
    }

    @Bean
    @Qualifier("iaFeignLogLevel")
    public Logger.Level iaLogLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    @Qualifier("iaStripAuth")
    public RequestInterceptor iaStripAuth() {
        return template -> {
            try {
                template.removeHeader("Authorization");
            } catch (final NoSuchMethodError e) {
                template.header("Authorization");
            }
        };
    }

    @Bean
    public IaClient iaClient(
            @Value("${mock-server.base-url}") final String baseUrl,
            @Qualifier("iaFeignClient") final Client client,
            @Qualifier("iaDecoder") final Decoder decoder,
            @Qualifier("iaEncoder") final Encoder encoder,
            @Qualifier("iaFeignLogLevel") final Logger.Level logLevel,
            final List<RequestInterceptor> interceptors,
            @Qualifier("iaStripAuth") final RequestInterceptor stripAuth
    ) {
        return Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .logLevel(logLevel)
                .requestInterceptors(interceptors)
                .requestInterceptor(stripAuth)
                .target(IaClient.class, baseUrl);
    }
}
