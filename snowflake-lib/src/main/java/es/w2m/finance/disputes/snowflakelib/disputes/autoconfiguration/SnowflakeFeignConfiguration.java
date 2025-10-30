package es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration;

import es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration.properties.SnowflakeClientProperties;
import feign.Feign;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
class SnowflakeFeignConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ErrorDecoder snowflakeErrorDecoder() {
        return new SnowflakeErrorDecoder();
    }

    @Bean
    Feign.Builder snowflakeFeignBuilder(ObjectProvider<ErrorDecoder> errorDecoderProvider,
                                        SnowflakeClientProperties props) {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .client(new OkHttpClient())
                .logger(new feign.slf4j.Slf4jLogger("SnowflakeClient"))
                .logLevel(Logger.Level.BASIC)
                .retryer(props.getMaxRetries() > 0
                        ? new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), props.getMaxRetries())
                        : Retryer.NEVER_RETRY)
                .options(new feign.Request.Options(
                        props.getConnectTimeoutMs(),
                        TimeUnit.MILLISECONDS,
                        props.getReadTimeoutMs(),
                        TimeUnit.MILLISECONDS,
                        true))
                .errorDecoder(errorDecoderProvider.getIfAvailable(SnowflakeErrorDecoder::new))
                .decoder(new OptionalDecoder(new feign.jackson.JacksonDecoder()));
    }
}
