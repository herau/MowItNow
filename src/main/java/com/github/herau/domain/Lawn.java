package com.github.herau.domain;

import lombok.Value;

/**
 * Lawn representation with its limit (xMin=0, yMin=0) and (xMax, yMax)
 */
@Value
public class Lawn {

    int xMin = 0;

    int yMin = 0;

    int xMax;

    int yMax;

    /**
     * @param x x position
     * @param y y position
     * @return true if the position, representing by the x and y values, is inside the lawn ( according to the lawn limits)
     */
    public boolean isInside(int x, int y) {
        return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
    }
}
