package com.github.herau.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by n27 on 11/19/16.
 */
@Service
public class MowService {

    public void launch(Path inputFile) {
        try {
            List<String> lines = Files.readAllLines(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
