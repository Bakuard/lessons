package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;
import com.bakuard.lessons.command.LogCommand;
import org.slf4j.Logger;

public class LogHandler implements Handler {

    private Executor executor;
    private Logger logger;

    public LogHandler(Executor executor, Logger logger) {
        this.executor = executor;
        this.logger = logger;
    }

    @Override
    public void handle(Exception exception, Command command) {
        executor.add(new LogCommand(exception, logger));
    }

}
