package com.github.herau;

import com.github.herau.configuration.ApplicationProperties;
import com.github.herau.configuration.ApplicationPropertiesValidator;
import com.github.herau.domain.Action;
import com.github.herau.domain.Grass;
import com.github.herau.service.FileParserService;
import com.github.herau.service.MowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;

import java.nio.file.Path;

@SpringBootApplication
public class MowItNowApplication implements CommandLineRunner{

    private static final Logger logger = LoggerFactory.getLogger(MowItNowApplication.class);

    private final ApplicationProperties properties;

    private final MowService mowService;

    private final FileParserService fileParser;

    public MowItNowApplication(ApplicationProperties properties, MowService mowService, FileParserService fileParser) {
        this.properties = properties;
        this.mowService = mowService;
        this.fileParser = fileParser;
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
        final Path inputFilePath = properties.getInputFile();

        Action action = fileParser.parse(inputFilePath);

        final Grass grass = action.getGrass();

        action.getMovementsByMow().forEach((mow, movements) -> {
            logger.info("Initial Position - " + mow);
            String mowPosition = mowService.move(grass, mow, movements);
            logger.info("Final Position   - " + mowPosition);
        });
    }
}
