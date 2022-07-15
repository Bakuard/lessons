package com.bakuard.lessons.command;

import com.bakuard.lessons.component.MoveComponent;
import com.bakuard.lessons.component.RotateComponent;
import com.bakuard.lessons.component.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChangeVelocityTest {

    @Test
    @DisplayName("""
            execute():
             change velocity vector
            """)
    public void execute1() {
        MoveComponent moveComponent = new MoveComponent();
        moveComponent.getVelocity().setXY(5, 5);
        RotateComponent rotateComponent = new RotateComponent(0, 45, 360);
        ChangeVelocity changeVelocity = new ChangeVelocity(moveComponent, rotateComponent);

        changeVelocity.execute();

        Assertions.assertTrue(
                new Vector(7.07, 0).equalsWithTolerance(moveComponent.getVelocity(), 0.01)
        );
    }

}