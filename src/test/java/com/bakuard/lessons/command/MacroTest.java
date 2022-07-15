package com.bakuard.lessons.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

class MacroTest {

    @Test
    @DisplayName("""
            Macro(command, commands):
             command is null
             => exception
            """)
    public void constructor1() {
        Assertions.assertThrows(NullPointerException.class, () -> new Macro(null, () -> {}));
    }

    @Test
    @DisplayName("""
            Macro(command, commands):
             commands is null
             => exception
            """)
    public void constructor2() {
        Assertions.assertThrows(NullPointerException.class, () -> new Macro(() -> {}, null));
    }

    @Test
    @DisplayName("""
            Macro(command, commands):
             commands contains null
             => exception
            """)
    public void constructor3() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new Macro(() -> {}, null, () -> {}));
    }

    @Test
    @DisplayName("""
            execute():
             command execution order is preserved
            """)
    public void execute1() {
        final AtomicInteger atomicInteger = new AtomicInteger(0);

        Macro macro = new Macro(
                () -> Assertions.assertEquals(1, atomicInteger.incrementAndGet()),
                () -> Assertions.assertEquals(2, atomicInteger.incrementAndGet()),
                () -> Assertions.assertEquals(3, atomicInteger.incrementAndGet())
        );

        macro.execute();
    }

}