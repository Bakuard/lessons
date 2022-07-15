package com.bakuard.lessons.command;

import com.bakuard.lessons.component.MoveComponent;
import com.bakuard.lessons.component.RotateComponent;

public class ChangeVelocity implements Command {

    private MoveComponent move;
    private RotateComponent rotate;

    public ChangeVelocity(MoveComponent moveComponent,
                          RotateComponent rotateComponent) {
        this.move = moveComponent;
        this.rotate = rotateComponent;
    }

    @Override
    public void execute() {
        double length = move.getVelocity().getLength();
        double newX = Math.cos(Math.toRadians(360.0 / rotate.getDirectionsNumber() * rotate.getDirection()));
        double newY = Math.sin(Math.toRadians(360.0 / rotate.getDirectionsNumber() * rotate.getDirection()));
        move.getVelocity().setX(newX * length);
        move.getVelocity().setY(newY * length);
    }

}
