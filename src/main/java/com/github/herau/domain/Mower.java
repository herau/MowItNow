package com.github.herau.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Observable;

/**
 * Mower instance is representing by its position (x, y) and its cardinal direction {@link Cardinal}
 */
@Data
@AllArgsConstructor
public class Mower extends Observable {

    private int x;

    private int y;

    private Cardinal direction;

    /**
     * Notify the mower movement to observers
     */
    public void positionChanged() {
        setChanged();
        notifyObservers();
    }

    public String toString() {
        return x + " " + y + " " + direction;
    }
}
