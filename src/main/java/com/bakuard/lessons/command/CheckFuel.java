package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;

public class CheckFuel implements Command {

    private FuelComponent c;

    public CheckFuel(FuelComponent c) {
        this.c = c;
    }

    @Override
    public void execute() {
        if(c.getQuantity() - c.getConsumption() < 0)
            throw new CommandException("Not enough fuel.");
    }

}
