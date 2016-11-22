package com.github.herau.observers;

import com.github.herau.domain.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Display in the console log the mower movements
 */
public class MowerPositionConsoleDisplay implements Observer {

    private static Logger logger = LoggerFactory.getLogger(MowerPositionConsoleDisplay.class);

    private final Observable observable;

    public MowerPositionConsoleDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
        if (observable instanceof Mower) {
            logger.info("New Mower at position: {}", observable.toString());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mower) {
            display();
        }
    }

    public void display() {
        logger.info("Mower Position: {}", observable.toString());
    }
}
