package com.github.herau.domain;

import lombok.Value;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Result of the input file parsing
 */
@Value
public class Action {

    Grass grass;

    Map<Mow, Stream<Movement>> movementsByMow;
}
