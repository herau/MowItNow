package com.github.herau.domain;

import lombok.Value;

/**
 * Grass representation with its limit (xMin=0, yMin=0) and (xMax, yMax)
 */
@Value
public class Grass {

    int xMin = 0;

    int yMin = 0;

    int xMax;

    int yMax;

    /**
     * @param x x position
     * @param y y position
     * @return true if the position, representing by the x and y values, is inside the grass ( according to the grass limits)
     */
    public boolean isInside(int x, int y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}
