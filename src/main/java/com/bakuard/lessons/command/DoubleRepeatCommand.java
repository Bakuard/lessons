package com.bakuard.lessons.command;

public class DoubleRepeatCommand implements Command {

    private Command command;

    public DoubleRepeatCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }

}
