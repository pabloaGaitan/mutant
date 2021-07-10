package com.meli.mutant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MeliMutantApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeliMutantApplication.class, args);
	}

}
