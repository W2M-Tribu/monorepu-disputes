package es.w2m.finance.disputes.w2mdisputes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class W2mDisputesApplication {

    public static void main(String[] args) {
        SpringApplication.run(W2mDisputesApplication.class, args);
    }

}
