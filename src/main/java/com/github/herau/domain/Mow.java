package com.github.herau.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Mow instance is representing by its position (x, y) and its cardinal direction {@link Cardinal}
 */
@Data
@AllArgsConstructor
public class Mow {

    private int x;

    private int y;

    private Cardinal direction;

    public String toString() {
        return x + " " + y + " " + direction;
    }
}
