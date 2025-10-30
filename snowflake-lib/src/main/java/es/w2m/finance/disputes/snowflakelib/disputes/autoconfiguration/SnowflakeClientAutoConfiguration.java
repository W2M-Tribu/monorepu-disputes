package es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration;

import es.w2m.finance.disputes.snowflakelib.disputes.api.client.SnowflakeApiClient;
import es.w2m.finance.disputes.snowflakelib.disputes.autoconfiguration.properties.SnowflakeClientProperties;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

@AutoConfiguration
@EnableConfigurationProperties(SnowflakeClientProperties.class)
public class SnowflakeClientAutoConfiguration {

    @Bean
    @ConditionalOnProperty("w2m.snowflake.base-url")
    public OAuth2FeignInterceptor snowflakeOauth2Interceptor(
            @Autowired OAuth2AuthorizedClientManager manager,
            SnowflakeClientProperties props) {
        return new OAuth2FeignInterceptor(manager,
                props.getClientRegistrationId(),
                props.getPrincipal());
    }

    @Bean
    @ConditionalOnProperty("w2m.snowflake.base-url")
    public SnowflakeApiClient snowflakeApiClient(Feign.Builder builder,
                                                 OAuth2FeignInterceptor oauth2,
                                                 SnowflakeClientProperties props) {
        return builder.requestInterceptor(oauth2)
                .target(SnowflakeApiClient.class, props.getBaseUrl());
    }
}