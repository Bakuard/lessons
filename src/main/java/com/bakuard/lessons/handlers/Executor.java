package com.bakuard.lessons.handlers;

import com.bakuard.lessons.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class Executor {

    private Deque<Command> queue;
    private ExceptionHandler exceptionHandler;

    public Executor(ExceptionHandler exceptionHandler) {
        queue = new ArrayDeque<>();
        this.exceptionHandler = exceptionHandler;
    }

    public void add(Command command) {
        queue.addFirst(command);
    }

    public void run() {
        while(!queue.isEmpty()) {
            Command command = queue.removeLast();
            try {
                command.execute();
            } catch(Exception e) {
                exceptionHandler.handle(e, command);
            }
        }
    }

}
