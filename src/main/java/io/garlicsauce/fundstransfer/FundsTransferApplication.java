package io.garlicsauce.fundstransfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FundsTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundsTransferApplication.class, args);
	}
}
