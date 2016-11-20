package com.github.herau.service;

import com.github.herau.domain.Cardinal;

/**
 * Created by n27 on 11/20/16.
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
