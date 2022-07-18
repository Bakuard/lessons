package com.bakuard.lessons.command;

public class RepeatCommand implements Command {

    private Command command;

    public RepeatCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }

}
