package com.bakuard.lessons.command;

public class UnsafeCommand implements Command {

    public UnsafeCommand() {

    }

    @Override
    public void execute() {
        throw new CommandException();
    }

}
