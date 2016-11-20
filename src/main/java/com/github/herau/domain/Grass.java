package com.github.herau.domain;

import lombok.Value;

/**
 * Grass representation with its limit (xMin, yMin) and (xMax, yMax)
 */
@Value
public class Grass {

    int xMin = 0;

    int yMin = 0;

    int xMax;

    int yMax;

    public boolean isInside(int x, int y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}
