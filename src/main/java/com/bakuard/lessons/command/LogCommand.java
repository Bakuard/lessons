package com.bakuard.lessons.command;

import org.slf4j.Logger;

public class LogCommand implements Command {

    private Exception exception;
    private Logger logger;

    public LogCommand(Exception exception, Logger logger) {
        this.exception = exception;
        this.logger = logger;
    }

    @Override
    public void execute() {
        logger.info(exception.getMessage());
    }

}
