package com.bakuard.lessons.command;

import com.bakuard.lessons.component.MoveComponent;

public class Move implements Command {

    private MoveComponent c;

    public Move(MoveComponent c) {
        this.c = c;
    }

    @Override
    public void execute() {
        c.getPosition().add(c.getVelocity());
    }

}
