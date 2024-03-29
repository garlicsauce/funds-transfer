package io.garlicsauce.fundstransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
@EnableSwagger2
public class FundsTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundsTransferApplication.class, args);
	}
}
