package com.bakuard.lessons.lesson5.system;

import com.bakuard.lessons.lesson5.components.Rotable;
import com.bakuard.lessons.lesson5.components.RotableComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SystemRotateTest {

    @Test
    @DisplayName("""
            update(component):
             component.getAngularVelocity() not supported
             => exception
            """)
    void update1() {
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getAngularVelocity()).
                thenThrow(new UnsupportedOperationException());
        SystemRotate systemRotate = new SystemRotate();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemRotate.update(rotable));
    }

    @Test
    @DisplayName("""
            update(component):
             component.getDirection() not supported
             => exception
            """)
    void update2() {
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getDirection()).
                thenThrow(new UnsupportedOperationException());
        SystemRotate systemRotate = new SystemRotate();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemRotate.update(rotable));
    }

    @Test
    @DisplayName("""
            update(component):
             component.getDirectionsNumber() not supported
             => exception
            """)
    void update3() {
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.when(rotable.getDirectionsNumber()).
                thenThrow(new UnsupportedOperationException());
        SystemRotate systemRotate = new SystemRotate();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemRotate.update(rotable));
    }

    @Test
    @DisplayName("""
            update(component):
             component.setDirection() not supported
             => exception
            """)
    void update4() {
        Rotable rotable = Mockito.mock(Rotable.class);
        Mockito.doThrow(new UnsupportedOperationException()).
                when(rotable).setDirection(Mockito.anyInt());
        Mockito.when(rotable.getDirectionsNumber()).
                thenReturn(10);
        SystemRotate systemRotate = new SystemRotate();

        Assertions.assertThrows(UnsupportedOperationException.class,
                () -> systemRotate.update(rotable));
    }

    @Test
    @DisplayName("""
            update(component):
             getAngularVelocity() == 0
             => component state must be the same
            """)
    void update5() {
        SystemRotate systemRotate = new SystemRotate();
        Rotable actual = new RotableComponent(1, 0, 8);

        systemRotate.update(actual);

        Rotable expected = new RotableComponent(1, 0, 8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            update(component):
             getDirection() + getAngularVelocity() < getDirectionsNumber(),
             getAngularVelocity() > 0
            """)
    void update6() {
        SystemRotate systemRotate = new SystemRotate();
        Rotable actual = new RotableComponent(1, 2, 8);

        systemRotate.update(actual);

        Rotable expected = new RotableComponent(3, 2, 8);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("""
            update(component):
             getDirection() + getAngularVelocity() = getDirectionsNumber(),
             getAngularVelocity() > 0
            """)
    void update7() {
        SystemRotate systemRotate = new SystemRotate();
        Rotable actual = new RotableComponent(1, 7, 8);

        systemRotate.update(actual);

        Rotable expected = new RotableComponent(0, 7, 8);
        Assertions.assertEquals(expected, actual);
    }

}