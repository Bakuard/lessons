package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;
import com.bakuard.lessons.command.CommandException;
import com.bakuard.lessons.command.RepeatCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class RepeatCommandHandlerTest {

    @Test
    @DisplayName("""
            handle(exception, command):
             add to executor RepeatCommand
            """)
    public void handle1() {
        Executor executor = Mockito.mock(Executor.class);
        Command innerCommand = Mockito.mock(Command.class);
        RepeatCommandHandler repeatCommandHandler = new RepeatCommandHandler(executor);

        repeatCommandHandler.handle(new CommandException(), innerCommand);

        Mockito.verify(executor).add(Mockito.any(RepeatCommand.class));
    }

}