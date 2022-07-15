package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;

public class BurnFuel implements Command {

    private FuelComponent c;

    public BurnFuel(FuelComponent c) {
        this.c = c;
    }

    @Override
    public void execute() {
        c.setQuantity(Math.max(0, c.getQuantity() - c.getConsumption()));
    }

}
