package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;
import com.bakuard.lessons.command.DoubleRepeatCommand;

public class DoubleRepeatCommandHandler implements Handler {

    private Executor executor;

    public DoubleRepeatCommandHandler(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void handle(Exception exception, Command command) {
        executor.add(new DoubleRepeatCommand(command));
    }

}
