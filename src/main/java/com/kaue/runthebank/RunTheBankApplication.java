package com.kaue.runthebank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZoneOffset;
import java.util.TimeZone;


@SpringBootApplication
public class RunTheBankApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
		SpringApplication.run(RunTheBankApplication.class, args);
	}

}
