package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHandler {

    private Map<Class<? extends Exception>, Map<Class<? extends Command>, Handler>> handlers;

    public ExceptionHandler() {
        handlers = new HashMap<>();
    }

    public <E extends Class<? extends Exception>, C extends Class<? extends Command>> void addHandler(
            E exceptionType, C commandType, Handler handler) {
        if(!handlers.containsKey(exceptionType)) handlers.put(exceptionType, new HashMap<>());
        handlers.get(exceptionType).put(commandType, handler);
    }

    public void handle(Exception exception, Command command) {
        handlers.get(exception.getClass()).
                get(command.getClass()).
                handle(exception, command);
    }

}
