package es.w2m.finance.disputes.edgeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SnowflakeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnowflakeApiApplication.class, args);
    }

}
