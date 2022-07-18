package com.bakuard.lessons;

import com.bakuard.lessons.command.CommandException;
import com.bakuard.lessons.command.DoubleRepeatCommand;
import com.bakuard.lessons.command.RepeatCommand;
import com.bakuard.lessons.command.UnsafeCommand;
import com.bakuard.lessons.handlers.*;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Executor executor = new Executor(exceptionHandler);
        executor.add(new UnsafeCommand());

        exceptionHandler.addHandler(
                CommandException.class, UnsafeCommand.class, new RepeatCommandHandler(executor)
        );
        exceptionHandler.addHandler(
                CommandException.class, RepeatCommand.class, new DoubleRepeatCommandHandler(executor)
        );
        exceptionHandler.addHandler(
                CommandException.class, DoubleRepeatCommand.class,
                new LogHandler(executor, LoggerFactory.getLogger(Main.class.getName()))
        );

        executor.run();
    }

}
