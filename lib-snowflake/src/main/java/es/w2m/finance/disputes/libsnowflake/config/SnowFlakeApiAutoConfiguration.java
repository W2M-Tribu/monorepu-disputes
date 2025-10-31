package es.w2m.finance.disputes.libsnowflake.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        SnowFlakeApiFeignConfig.class,
        SnowFlakeApiClientConfig.class
})
public class SnowFlakeApiAutoConfiguration {
}
