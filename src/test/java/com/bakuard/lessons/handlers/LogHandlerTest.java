package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.CommandException;
import com.bakuard.lessons.command.LogCommand;
import com.bakuard.lessons.command.UnsafeCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogHandlerTest {

    @Test
    @DisplayName("""
            handle():
             add logCommand to queue
            """)
    public void handle1() {
        Executor executor = Mockito.mock(Executor.class);
        Logger logger = LoggerFactory.getLogger(LoggerFactory.class.getName());
        LogHandler handler = new LogHandler(executor, logger);

        handler.handle(new CommandException(), new UnsafeCommand());

        Mockito.verify(executor).add(Mockito.any(LogCommand.class));
    }

}