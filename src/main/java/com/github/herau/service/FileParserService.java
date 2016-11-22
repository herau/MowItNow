package com.github.herau.service;

import com.github.herau.domain.Action;
import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Lawn;
import com.github.herau.domain.Movement;
import com.github.herau.domain.Mower;
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
 * Service which parse a text file which contains lawn limit, mower positions and movements instructions
 */
@Service
public class FileParserService {

    /**
     * Parse the input file according to the specific format below.
     * The first row corresponds to the coordinates of the upper right corner of the lawn, those in the lower left corner are assumed to be (0,0).
     * The rest of the file allows to control all the mowers that have been deployed. Each lawnmower has two lines for it:
     *   <ul>
     *       <li>The first line shows the initial position of the mower and its orientation. The position and orientation are provided in the form of 2 digits and a letter, separated by a space.</li>
     *       <li>The second line is a series of instructions instructing the lawnmower to explore the lawn. The instructions are a sequence of characters without spaces.</li>
     *   </ul>
     * @param inputFilePath {@link Path} of the input file
     * @return {@link Action} An object which contains information about the lawn, the mows and the steps instructions
     * @throws IOException if any IO exception occurred during the file reading
     */
    public Action parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(inputFilePath);

        String lawnLimit = lines.get(0);
        String[] lawnLimits = lawnLimit.split(" ");
        final Lawn lawn = new Lawn(Integer.parseInt(lawnLimits[0]), Integer.parseInt(lawnLimits[1]));

        final Map<Mower, Stream<Movement>> movementsByMow = new LinkedHashMap<>();
        for (int i = 1; i < lines.size(); i++) {
            String[] mowPositions = lines.get(i).split(" ");
            String mowSteps = lines.get(++i);

            Mower mower = new Mower(Integer.parseInt(mowPositions[0]),
                                    Integer.parseInt(mowPositions[1]),
                                    Cardinal.valueOf(mowPositions[2]));

            movementsByMow.put(mower, Arrays.stream(mowSteps.split("")).map(Movement::valueOf));
        }

        return new Action(lawn, movementsByMow);
    }
}
