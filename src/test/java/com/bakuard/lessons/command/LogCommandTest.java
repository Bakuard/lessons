package com.bakuard.lessons.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

class LogCommandTest {

    @Test
    @DisplayName("""
            execute():
             log exception
            """)
    public void execute1() {
        CommandException exception = Mockito.mock(CommandException.class);
        Mockito.when(exception.getMessage()).thenReturn("mock message");
        Logger logger = Mockito.mock(Logger.class);
        LogCommand logCommand = new LogCommand(exception, logger);

        logCommand.execute();

        Mockito.verify(logger, Mockito.times(1)).info(Mockito.anyString());
    }

}