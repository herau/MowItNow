package com.github.herau;

import com.github.herau.configuration.ApplicationProperties;
import com.github.herau.configuration.ApplicationPropertiesValidator;
import com.github.herau.domain.Action;
import com.github.herau.domain.Lawn;
import com.github.herau.service.FileParserService;
import com.github.herau.service.MowerService;
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

    private final MowerService mowerService;

    private final FileParserService fileParser;

    public MowItNowApplication(ApplicationProperties properties, MowerService mowerService, FileParserService fileParser) {
        this.properties = properties;
        this.mowerService = mowerService;
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

        final Lawn lawn = action.getLawn();

        action.getMovementsByMow().forEach((mower, movements) -> {
            logger.info("Initial Position - " + mower);
            String mowPosition = mowerService.move(lawn, mower, movements);
            logger.info("Final Position   - " + mowPosition);
        });
    }
}
