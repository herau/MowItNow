package com.github.herau.observers;

import com.github.herau.domain.Lawn;
import com.github.herau.domain.Mower;
import com.github.herau.dto.MowerSteps;
import com.github.herau.dto.MowersDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by n27 on 11/22/16.
 */
public class MowerStepsObserver implements Observer {

    private final List<Observable> observables;

    private final List<MowerSteps> mowerSteps;

    private final MowersDto mowersDto;

    public MowerStepsObserver(Lawn lawn) {
        this.observables = new LinkedList<>();
        this.mowerSteps = new LinkedList<>();
        this.mowersDto = new MowersDto(lawn, mowerSteps);
    }

    public void addObservable(Observable observable) {
        observables.add(observable);
        observable.addObserver(this);

        if (observable instanceof Mower) {
            Mower mower = (Mower) observable;

            List<MowerSteps.Step> steps = new ArrayList<>();
            steps.add(new MowerSteps.Step(mower.getX(), mower.getY(), mower.getDirection()));
            mowerSteps.add(new MowerSteps(steps));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Mower) {
            Mower mower = (Mower) o;
            mowerSteps.get(observables.indexOf(o))
                      .getSteps()
                      .add(new MowerSteps.Step(mower.getX(), mower.getY(), mower.getDirection()));
        }
    }

    public Object getDto() {
        return mowersDto;
    }
}
