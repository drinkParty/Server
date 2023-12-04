package com.s1350.sooljangmacha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class SooljangmachaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SooljangmachaApplication.class, args);
	}

}
