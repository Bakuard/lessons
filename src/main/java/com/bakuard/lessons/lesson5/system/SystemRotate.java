package com.bakuard.lessons.lesson5.system;

import com.bakuard.lessons.lesson5.components.Rotable;

public class SystemRotate implements System<Rotable> {

    @Override
    public void update(Rotable c) {
        c.setDirection(
                (c.getDirection() + c.getAngularVelocity()) % c.getDirectionsNumber()
        );
    }

}
