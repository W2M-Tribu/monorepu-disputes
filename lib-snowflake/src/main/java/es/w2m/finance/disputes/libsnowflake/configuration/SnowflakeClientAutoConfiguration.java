package es.w2m.finance.disputes.libsnowflake.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import(SnowflakeFeignConfig.class)
public class SnowflakeClientAutoConfiguration {}