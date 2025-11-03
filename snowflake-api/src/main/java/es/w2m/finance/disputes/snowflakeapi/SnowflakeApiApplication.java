package es.w2m.finance.disputes.snowflakeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {
        "es.w2m.finance.disputes.snowflakeapi.client"
})
public class SnowflakeApiApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SnowflakeApiApplication.class, args);
    }

}
