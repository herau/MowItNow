package com.github.herau.service;

import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Lawn;
import com.github.herau.domain.Movement;
import com.github.herau.domain.Mower;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * Service which mow a lawn (according to its limit) with a mower and a list of movement steps
 */
@Service
public class MowerService {

    private static final Logger logger = LoggerFactory.getLogger(MowerService.class);

    private static final Cardinal[] DIRECTIONS = Cardinal.values();

    /**
     * Move on the lawn the mower according to the movement instructions and return this position
     * @return the mower position after all the instruction steps
     * @param lawn The lawn in which the mowers are deployed and moved
     * @param mower The mower to move in the lawn
     * @param movements The List of the movement steps
     */
    public String move(Lawn lawn, Mower mower, Stream<Movement> movements) {
        logger.debug("Moving the mower [{}] on the lawn [{}]", mower, lawn);

        movements.forEach(movement -> {
            logger.debug("Current mower position [{}]", mower);
            logger.debug("Next movement [{}]", movement);
            switch (movement) {
                case A:
                    int xStep = 0;
                    int yStep = 0;
                    switch (mower.getDirection()) {
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

                    final int nextX = mower.getX() + xStep;
                    final int nextY = mower.getY() + yStep;

                    if (lawn.isInside(nextX, nextY)) {
                        mower.setX(nextX);
                        mower.setY(nextY);
                    }
                    break;
                case D:
                    mower.setDirection(DIRECTIONS[Math.floorMod(mower.getDirection().ordinal() + 1, DIRECTIONS.length)]);
                    break;
                case G:
                    mower.setDirection(DIRECTIONS[Math.floorMod(mower.getDirection().ordinal() - 1, DIRECTIONS.length)]);
                    break;
                default:
                    // nothing to do
                    break;
            }
        });

        logger.debug("Current mower position [{}]", mower);

        return mower.toString();
    }
}
