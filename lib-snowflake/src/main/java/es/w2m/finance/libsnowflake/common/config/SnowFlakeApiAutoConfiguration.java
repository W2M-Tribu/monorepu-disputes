package es.w2m.finance.libsnowflake.common.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        FeignBuilderConfig.class,
        FeignAuthConfig.class
})
public class SnowFlakeApiAutoConfiguration {
}
