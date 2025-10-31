package es.w2m.finance.disputes.w2mdisputes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {
        "es.w2m.finance.disputes.libsnowflake.client",
        "es.w2m.finance.disputes.w2mdisputes.infrastructure.client"
})
@SpringBootApplication
public class W2mDisputesApplication {

    public static void main(final String[] args) {
        SpringApplication.run(W2mDisputesApplication.class, args);
    }

}
