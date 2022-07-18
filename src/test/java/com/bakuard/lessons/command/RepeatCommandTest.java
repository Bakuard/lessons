package com.bakuard.lessons.command;

import org.mockito.Mockito;

class RepeatCommandTest {

    public void execute() {
        Command command = Mockito.mock(Command.class);
        RepeatCommand repeatCommand = new RepeatCommand(command);

        repeatCommand.execute();

        Mockito.verify(command, Mockito.times(1)).execute();
    }

}