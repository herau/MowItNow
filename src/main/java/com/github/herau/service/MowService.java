package com.github.herau.service;

import com.github.herau.domain.Cardinal;
import com.github.herau.domain.Grass;
import com.github.herau.domain.Mow;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Service which move a mow in a grass according to its limit and a list of movement steps
 */
@Service
public class MowService {

    private static final Cardinal[] DIRECTIONS = Cardinal.values();

    /**
     * Move on the grass the mow according to the movement instructions and return this position
     * @return the mow position after all the instruction steps
     * @param grass
     * @param mow
     * @param movements
     */
    public String move(Grass grass, Mow mow, List<Movement> movements) {
        int grassX = grass.getX();
        int grassY = grass.getY();

        movements.forEach(movement -> {
            switch (movement) {
                case A:
                    Cardinal direction = mow.getDirection();

                    int xStep = 0;
                    int yStep = 0;
                    switch (direction) {
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
                            break;
                    }

                    final int nextX = mow.getX() + xStep;
                    final int nextY = mow.getY() + yStep;

                    if (nextX >= 0 && nextX <= grassX && nextY >= 0 && nextY <= grassY) {
                        mow.setX(nextX);
                        mow.setY(nextY);
                    }
                    break;
                case D:
                    mow.setDirection(DIRECTIONS[Math.floorMod(mow.getDirection().ordinal() + 1, DIRECTIONS.length - 1)]);
                    break;
                case G:
                    mow.setDirection(DIRECTIONS[Math.floorMod(mow.getDirection().ordinal() - 1, DIRECTIONS.length)]);
                    break;
                default:
                    // nothing to do
                    break;
            }
        });

        return mow.toString();
    }
}
