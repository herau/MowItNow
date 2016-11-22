package com.github.herau.domain;

/**
 * Representation of the all allowed movements for a {@link Mower} inside a {@link Lawn}
 */
public enum Movement {
    /**
     * spin 90° to right
     */
    D,
    /**
     * spin 90° to left
     */
    G,
    /**
     * Move to one square in the current cardinal direction {@link Cardinal}
     */
    A
}
