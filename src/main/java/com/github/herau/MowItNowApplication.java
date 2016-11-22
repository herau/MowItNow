package com.github.herau;

import com.github.herau.configuration.ApplicationPropertiesValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

@SpringBootApplication
public class MowItNowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MowItNowApplication.class, args);
	}

	@Bean
    public static Validator configurationPropertiesValidator() {
        return new ApplicationPropertiesValidator();
    }

}
