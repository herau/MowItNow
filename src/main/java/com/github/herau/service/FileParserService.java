package com.github.herau.service;

import com.github.herau.domain.Action;
import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Movement;
import com.github.herau.domain.Mow;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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
        String[] grassLimits = grassLimit.split(" ");
        final Grass grass = new Grass(Integer.parseInt(grassLimits[0]), Integer.parseInt(grassLimits[1]));

        final Map<Mow, Stream<Movement>> movementsByMow = new LinkedHashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] mowPositions = lines.get(i).split(" ");
            String mowSteps = lines.get(++i);

            Mow mow = new Mow(Integer.parseInt(mowPositions[0]),
                              Integer.parseInt(mowPositions[1]),
                              Cardinal.valueOf(mowPositions[2]));

            movementsByMow.put(mow, Arrays.stream(mowSteps.split("")).map(Movement::valueOf));
        }

        return new Action(grass, movementsByMow);
    }
}
