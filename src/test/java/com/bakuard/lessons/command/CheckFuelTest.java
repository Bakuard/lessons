package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CheckFuelTest {

    @Test
    @DisplayName("""
            execute():
             there is enough fuel
             => do nothing
            """)
    public void execute1() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(100);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(100);
        CheckFuel checkFuel = new CheckFuel(fuelComponent);

        Assertions.assertDoesNotThrow(checkFuel::execute);
    }

    @Test
    @DisplayName("""
            execute():
             there is not enough fuel
             => do nothing
            """)
    public void execute2() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(100);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(101);
        CheckFuel checkFuel = new CheckFuel(fuelComponent);

        Assertions.assertThrows(CommandException.class, checkFuel::execute);
    }

    @Test
    @DisplayName("""
            execute():
             quantity fuel = 0 but consumption = 0
             => do nothing
            """)
    public void execute3() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(0);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(0);
        CheckFuel checkFuel = new CheckFuel(fuelComponent);

        Assertions.assertDoesNotThrow(checkFuel::execute);
    }

}