package com.github.herau.configuration;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Application argument validator
 */
public class ApplicationPropertiesValidator implements Validator{

    private static final String ARG_ERROR = "10";

    @Override
    public boolean supports(Class<?> type) {
        return ApplicationProperties.class == type;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ApplicationProperties properties = (ApplicationProperties) o;

        Path inputFilePath = properties.getInputFile();

        if (inputFilePath != null && Files.notExists(inputFilePath)){
            errors.rejectValue("inputFile", ARG_ERROR, "The file located by this path [" + inputFilePath + "] does not exist");
        }
    }
}
