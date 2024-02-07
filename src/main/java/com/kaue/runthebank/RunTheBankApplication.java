package com.kaue.runthebank;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;

@OpenAPIDefinition(info = @Info(
		title = "Run the Bank - API",
		version = "1.0",
		description = "Cadastro de clientes, contas e pagamentos entre contas"
))
@SpringBootApplication
public class RunTheBankApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
		SpringApplication.run(RunTheBankApplication.class, args);
	}

}
