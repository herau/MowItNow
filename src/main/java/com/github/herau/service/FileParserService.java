package com.github.herau.service;

import com.github.herau.domain.Action;
import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Mow;
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
     * Parse the input file according to the specific format below.
     * The first row corresponds to the coordinates of the upper right corner of the grass, those in the lower left corner are assumed to be (0,0).
     * The rest of the file allows to control all the mowers that have been deployed. Each lawnmower has two lines for it:
     *   <ul>
     *       <li>The first line shows the initial position of the mower and its orientation. The position and orientation are provided in the form of 2 digits and a letter, separated by a space.</li>
     *       <li>The second line is a series of instructions instructing the lawnmower to explore the lawn. The instructions are a sequence of characters without spaces.</li>
     *   </ul>
     * @param inputFilePath {@link Path} of the input file
     * @return {@link Action} An object which contains information about the grass, the mows and the steps instructions
     * @throws IOException if any IO exception occurred during the file reading
     */
    public Action parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(inputFilePath);

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
