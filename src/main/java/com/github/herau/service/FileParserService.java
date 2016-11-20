package com.github.herau.service;

import com.github.herau.domain.Action;
import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Mow;
import com.sun.javaws.exceptions.ExitException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service which parse a text file which contains grass limit, mow positions and movements instructions
 */
@Service
public class FileParserService {

    /**
     * Parse the input file according to the specific format
     * <p>
     *     * The first row corresponds to the coordinates of the upper right corner of the grass, those in the lower left corner are assumed to be (0,0)
     *     *  The rest of the file allows to control all the mowers that have been deployed. Each lawnmower has two lines for it:
      * The first line shows the initial position of the mower and its orientation. The position and orientation are provided in the form of 2 digits and a letter, separated by a space
      * The second line is a series of instructions instructing the lawnmower to explore the lawn. The instructions are a sequence of characters without spaces.
     * </p>
     * @param inputFilePath {@link Path} of the input file
     * @return {@link Action} An object which contains information about the grass, the mows and the steps instructions
     * @throws ExitException if any IO exception occurred during the file parsing
     */
    public Action parse(Path inputFilePath) throws ExitException {
        List<String> lines;
        try {
            lines = Files.readAllLines(inputFilePath);
        } catch (IOException e) {
            throw new ExitException("Unable to parse the input file", e);
        }

        String grassLimit = lines.get(0);
        final Grass grass = new Grass(Integer.parseInt(grassLimit.substring(0, 1)), Integer.parseInt(grassLimit.substring(1, 2)));

        final Map<Mow, List<Movement>> movementsByMow = new HashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            final String mowPosition = lines.get(i);
            final String mowSteps = lines.get(++i);

            Mow mow = new Mow(Integer.parseInt(mowPosition.substring(0, 1)),
                              Integer.parseInt(mowPosition.substring(1, 2)),
                              Cardinal.valueOf(mowPosition.substring(2, 3)));

            List<Movement> movements =
                    Arrays.stream(mowSteps.split("")).map(Movement::valueOf).collect(Collectors.toList());
            movementsByMow.put(mow, movements);
        }

        return new Action(grass, movementsByMow);
    }
}
