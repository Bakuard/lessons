package com.bakuard.lessons.lesson5.system;

import com.bakuard.lessons.lesson5.components.Movable;

public class SystemMove implements System<Movable> {

    @Override
    public void update(Movable c) {
        c.setPosition(
                c.getPosition().add(c.getVelocity())
        );
    }

}
