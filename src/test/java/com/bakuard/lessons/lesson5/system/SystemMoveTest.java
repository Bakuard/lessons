package com.bakuard.lessons.lesson5.system;

import com.bakuard.lessons.lesson5.Vector;
import com.bakuard.lessons.lesson5.components.Movable;
import com.bakuard.lessons.lesson5.components.MovableComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SystemMoveTest {

    @Test
    @DisplayName("""
            update(component):
             velocity is not zero vector
             => update component position
            """)
    public void update1() {
        Movable movable = new MovableComponent();
        movable.setPosition(new Vector(12 ,5));
        movable.setVelocity(new Vector(-7, 3));
        SystemMove systemMove = new SystemMove();

        systemMove.update(movable);

        Assertions.assertEquals(new Vector(5, 8), movable.getPosition());
    }

    @Test
    @DisplayName("""
            update(component):
             component.getPosition() not supported
             => exception
            """)
    public void update2() {
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenThrow(new UnsupportedOperationException());
        SystemMove systemMove = new SystemMove();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemMove.update(movable));
    }

    @Test
    @DisplayName("""
            update(component):
             component.getVelocity() not supported
             => exception
            """)
    public void update3() {
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getVelocity()).thenThrow(new UnsupportedOperationException());
        SystemMove systemMove = new SystemMove();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemMove.update(movable));
    }

    @Test
    @DisplayName("""
            update(component):
             component.setPosition() not supported
             => exception
            """)
    public void update4() {
        Movable movable = Mockito.mock(Movable.class);
        Mockito.when(movable.getPosition()).thenReturn(new Vector(0, 0));
        Mockito.when(movable.getVelocity()).thenReturn(new Vector(0, 0));
        Mockito.doThrow(new UnsupportedOperationException()).
                when(movable).
                setPosition(Mockito.any());
        SystemMove systemMove = new SystemMove();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemMove.update(movable));
    }

}