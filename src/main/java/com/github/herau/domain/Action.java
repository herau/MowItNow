package com.github.herau.domain;

import com.github.herau.service.Movement;
import lombok.Value;

import java.util.List;
import java.util.Map;

/**
 * Result of the input file parsing
 */
@Value
public class Action {

    Grass grass;

    Map<Mow, List<Movement>> movementsByMow;
}
