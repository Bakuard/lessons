package com.bakuard.lessons;

import com.bakuard.lessons.command.Command;
import com.bakuard.lessons.command.CommandException;
import com.bakuard.lessons.command.DoubleRepeatCommand;
import com.bakuard.lessons.command.RepeatCommand;
import com.bakuard.lessons.handlers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

public class RepeatCommandTwoTimeTest {

    @Test
    @DisplayName("""
            repeat unsuccess command two time, then log it
            """)
    public void test1() {
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        Executor executor = new Executor(exceptionHandler);
        Command unsafeCommand = Mockito.mock(Command.class);
        CommandException exception = Mockito.mock(CommandException.class);
        Mockito.when(exception.getMessage()).thenReturn("mock message");
        Mockito.doThrow(exception).when(unsafeCommand).execute();
        Logger logger = Mockito.mock(Logger.class);
        executor.add(unsafeCommand);
        exceptionHandler.addHandler(exception.getClass(), unsafeCommand.getClass(), new RepeatCommandHandler(executor));
        exceptionHandler.addHandler(exception.getClass(), RepeatCommand.class, new DoubleRepeatCommandHandler(executor));
        exceptionHandler.addHandler(exception.getClass(), DoubleRepeatCommand.class, new LogHandler(executor, logger));

        executor.run();

        Mockito.verify(unsafeCommand, Mockito.times(3)).execute();
        Mockito.verify(logger).info(Mockito.anyString());
    }

}
