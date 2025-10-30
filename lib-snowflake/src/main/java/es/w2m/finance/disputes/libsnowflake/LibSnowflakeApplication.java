package es.w2m.finance.disputes.libsnowflake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "es.w2m.finance.disputes")
@SpringBootApplication
public class LibSnowflakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibSnowflakeApplication.class, args);
	}

}
