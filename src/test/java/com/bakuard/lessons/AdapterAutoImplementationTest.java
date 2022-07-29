package com.bakuard.lessons;

import com.bakuard.lessons.component.Command;
import com.bakuard.lessons.component.Movable;
import com.bakuard.lessons.component.Vector;
import com.bakuard.lessons.fabric.AdapterAutoImplFabric;
import com.bakuard.lessons.fabric.Fabric;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AdapterAutoImplementationTest {

    @BeforeEach
    public void beforeEach() {
        IoC.removeScope("test");
    }

    @Test
    @DisplayName("""
            auto implement movable interface:
             method getVelocity();
            """)
    public void movableImpl1() {
        Fabric<Vector> velocity = Mockito.mock(Fabric.class);
        Mockito.when(velocity.create(Mockito.any())).thenReturn(new Vector());
        IoC.scope("test").register(
                "movable",
                new AdapterAutoImplFabric<>(Movable.class, IoC.scope("test"), "movable")
        );
        IoC.scope("test").register("movable.getVelocity", velocity);

        Movable movable = IoC.scope("test").resolve("movable", new Params());
        movable.getVelocity();

        Mockito.verify(velocity, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("""
            auto implement movable interface:
             method getPosition();
            """)
    public void movableImpl2() {
        Fabric<Vector> position = Mockito.mock(Fabric.class);
        Mockito.when(position.create(Mockito.any())).thenReturn(new Vector());
        IoC.scope("test").register(
                "movable",
                new AdapterAutoImplFabric<>(Movable.class, IoC.scope("test"), "movable")
        );
        IoC.scope("test").register("movable.getPosition", position);

        Movable movable = IoC.scope("test").resolve("movable", new Params());
        movable.getPosition();

        Mockito.verify(position, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    @DisplayName("""
            auto implement movable interface:
             method setPosition();
            """)
    public void movableImpl3() {
        IoC mock = Mockito.mock(IoC.class);
        IoC.scope("test").register(
                "movable",
                new AdapterAutoImplFabric<>(Movable.class, mock, "movable")
        );

        Movable movable = IoC.scope("test").resolve("movable", new Params());
        movable.setPosition(new Vector(10, 20));

        Mockito.verify(mock, Mockito.times(1)).register(
                Mockito.eq("movable.setPosition"),
                Mockito.any()
        );
    }

    @Test
    @DisplayName("""
            auto implement movable interface:
             method finish();
            """)
    public void movableImpl4() {
        Command finish = Mockito.mock(Command.class);
        IoC.scope("test").register(
                "movable",
                new AdapterAutoImplFabric<>(Movable.class, IoC.scope("test"), "movable")
        );
        IoC.scope("test").register("movable.finish", params -> finish);

        Movable movable = IoC.scope("test").resolve("movable", new Params());
        movable.finish();

        Mockito.verify(finish, Mockito.times(1)).execute();
    }

}