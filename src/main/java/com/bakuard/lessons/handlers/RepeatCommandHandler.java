package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;
import com.bakuard.lessons.command.RepeatCommand;

public class RepeatCommandHandler implements Handler {

    private Executor executor;

    public RepeatCommandHandler(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void handle(Exception exception, Command command) {
        executor.add(new RepeatCommand(command));
    }

}
