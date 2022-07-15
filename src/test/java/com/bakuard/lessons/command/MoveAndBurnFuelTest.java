package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;
import com.bakuard.lessons.component.MoveComponent;
import com.bakuard.lessons.component.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MoveAndBurnFuelTest {

    @Test
    @DisplayName("""
            Macro(CheckFuel, Move, BurnFuel):
             not enough fuel
             => don't move and burn fuel
            """)
    public void execute1() {
        Move move = Mockito.mock(Move.class);
        Mockito.doAnswer(invocationOnMock -> {
            Assertions.fail("Don't call move.execute()");
            return null;
        }).when(move).execute();
        BurnFuel burnFuel = Mockito.mock(BurnFuel.class);
        Mockito.doAnswer(invocationOnMock -> {
            Assertions.fail("Don't call burnFuel.execute()");
            return null;
        }).when(burnFuel).execute();

        Macro macro = new Macro(
                new CheckFuel(new FuelComponent(10, 11)),
                move,
                burnFuel
        );

        Assertions.assertThrows(CommandException.class, macro::execute);
    }

    @Test
    @DisplayName("""
            Macro(CheckFuel, Move, BurnFuel):
             enough fuel
             => move and burn fuel
            """)
    public void execute2() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(4);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(10);
        Mockito.doAnswer(invocationOnMock -> {
            int newQuantity = invocationOnMock.getArgument(0);
            Assertions.assertEquals(6, newQuantity);
            return null;
        }).when(fuelComponent).setQuantity(Mockito.anyInt());
        MoveComponent moveComponent = new MoveComponent();
        moveComponent.getVelocity().setXY(-5, 5);
        moveComponent.getPosition().setXY(10, 10);
        Macro macro = new Macro(
                new CheckFuel(fuelComponent),
                new Move(moveComponent),
                new BurnFuel(fuelComponent)
        );

        macro.execute();

        Assertions.assertEquals(new Vector(5, 15), moveComponent.getPosition());
    }

}
