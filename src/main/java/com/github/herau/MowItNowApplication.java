package com.github.herau;

import com.github.herau.service.MowService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

@SpringBootApplication
public class MowItNowApplication implements CommandLineRunner{

    private final ApplicationProperties properties;

    private final MowService service;

    public MowItNowApplication(ApplicationProperties properties, MowService service) {
        this.properties = properties;
        this.service = service;
    }

	public static void main(String[] args) {
		SpringApplication.run(MowItNowApplication.class, args);
	}

	@Bean
    public static Validator configurationPropertiesValidator() {
        return new ApplicationPropertiesValidator();
    }

    @Override
    public void run(String... strings) throws Exception {
        service.launch(properties.getInputFile());
    }
}
