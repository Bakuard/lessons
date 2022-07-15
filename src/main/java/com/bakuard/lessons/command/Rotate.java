package com.bakuard.lessons.command;

import com.bakuard.lessons.component.RotateComponent;

public class Rotate implements Command {

    private RotateComponent c;

    public Rotate(RotateComponent c) {
        this.c = c;
    }

    @Override
    public void execute() {
        c.setDirection(
                (c.getDirection() + c.getAngularVelocity()) % c.getDirectionsNumber()
        );
    }

}
