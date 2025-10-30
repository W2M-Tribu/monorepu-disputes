package es.w2m.finance.disputes.libsnowflake.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({es.w2m.finance.disputes.libsnowflake.configuration.SnowflakeClientConfiguration.class})
public class SnowflakeClientAutoConfiguration {}