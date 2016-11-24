package com.github.herau.domain;

import lombok.Value;

import java.util.Map;
import java.util.stream.Stream;

/**
 * Mowing steps
 */
@Value
public class MowingAction {

    Lawn lawn;

    Map<Mower, Stream<Movement>> movementsByMow;
}
