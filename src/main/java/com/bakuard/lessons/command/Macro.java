package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;
import com.bakuard.lessons.component.MoveComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Macro implements Command {

    public static Macro moveAndBurnFuel(MoveComponent moveComponent, FuelComponent fuelComponent) {
        return new Macro(
                new CheckFuel(fuelComponent),
                new Move(moveComponent),
                new BurnFuel(fuelComponent)
        );
    }


    private List<Command> commands;

    public Macro(Command command, Command... commands) {
        Objects.requireNonNull(command, "all commands can't be null");
        Objects.requireNonNull(commands, "all commands can't be null");
        Arrays.stream(commands).forEach(c -> Objects.requireNonNull(c, "all commands can't be null"));

        this.commands = new ArrayList<>();
        this.commands.add(command);
        this.commands.addAll(List.of(commands));
    }

    @Override
    public void execute() {
        commands.forEach(Command::execute);
    }

}
