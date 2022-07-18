package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;

public interface Handler {

    public void handle(Exception exception, Command command);

}
