package com.github.herau.service;

import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Movement;
import com.github.herau.domain.Mow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Service which move a mow in a grass according to its limit and a list of movement steps
 */
@Service
public class MowService {

    private static final Logger loggger = LoggerFactory.getLogger(MowService.class);

    private static final Cardinal[] DIRECTIONS = Cardinal.values();

    /**
     * Move on the grass the mow according to the movement instructions and return this position
     * @return the mow position after all the instruction steps
     * @param grass The grass in which the mowers are deployed and moved
     * @param mow The mow to move in the grass
     * @param movements The List of the movement steps
     */
    public String move(Grass grass, Mow mow, Stream<Movement> movements) {
        loggger.debug("Moving the mow [{}] on the grass [{}]", mow, grass);

        movements.forEach(movement -> {
            loggger.debug("Current mow position [{}]", mow);
            loggger.debug("Next movement [{}]", movement);
            switch (movement) {
                case A:
                    int xStep = 0;
                    int yStep = 0;
                    switch (mow.getDirection()) {
                        case E:
                            xStep = 1;
                            break;
                        case N:
                            yStep = 1;
                            break;
                        case S:
                            yStep = -1;
                            break;
                        case W:
                            xStep = -1;
                            break;
                        default:
                            // Do nothing
                            break;
                    }

                    final int nextX = mow.getX() + xStep;
                    final int nextY = mow.getY() + yStep;

                    if (grass.isInside(nextX, nextY)) {
                        mow.setX(nextX);
                        mow.setY(nextY);
                    }
                    break;
                case D:
                    mow.setDirection(DIRECTIONS[Math.floorMod(mow.getDirection().ordinal() + 1, DIRECTIONS.length)]);
                    break;
                case G:
                    mow.setDirection(DIRECTIONS[Math.floorMod(mow.getDirection().ordinal() - 1, DIRECTIONS.length)]);
                    break;
                default:
                    // nothing to do
                    break;
            }
        });

        loggger.debug("Current mow position [{}]", mow);

        return mow.toString();
    }
}
