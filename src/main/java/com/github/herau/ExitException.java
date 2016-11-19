package com.github.herau;

import org.springframework.boot.ExitCodeGenerator;

/**
 * Exception
 */
class ExitException extends RuntimeException implements ExitCodeGenerator {

    ExitException(String message) {
        super(message);
    }

    @Override
    public int getExitCode() {
        return -1;
    }

}
