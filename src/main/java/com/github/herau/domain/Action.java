package com.github.herau.domain;

import com.github.herau.service.Movement;
import lombok.Value;

import java.util.List;
import java.util.Map;

/**
 * Created by n27 on 11/20/16.
 */
@Value
public class Action {

    Grass grass;

    Map<Mow, List<Movement>> movementsByMow;
}
