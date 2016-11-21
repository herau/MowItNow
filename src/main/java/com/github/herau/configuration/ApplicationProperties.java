package com.github.herau.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

/**
 * Application configuration properties
 */
@Component
@ConfigurationProperties(prefix = "mow")
@Data
public class ApplicationProperties {

    /**
     * Input file path
     */
    Path inputFile;

}
