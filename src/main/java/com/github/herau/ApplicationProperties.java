package com.github.herau;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties(prefix = "mow")
@Data
class ApplicationProperties {

    /**
     * Input file path
     */
    Path inputFile;

}
