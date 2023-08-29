package br.com.dfdevforge.sisfinstatement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SisfinStatementApplication {
	public static void main(String[] args) {
		SpringApplication.run(SisfinStatementApplication.class, args);
	}
}