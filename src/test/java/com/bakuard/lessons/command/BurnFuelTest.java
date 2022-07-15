package com.bakuard.lessons.command;

import com.bakuard.lessons.component.FuelComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class BurnFuelTest {

    @Test
    @DisplayName("""
            execute():
             consumption > 0
             => reduce quantity
            """)
    public void execute1() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(100);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(90);
        Mockito.doAnswer(invocationOnMock -> {
            int arg = (Integer)invocationOnMock.getArguments()[0];
            Assertions.assertEquals(10, arg);
            return null;
        }).when(fuelComponent).setQuantity(Mockito.anyInt());
        BurnFuel burnFuel = new BurnFuel(fuelComponent);

        burnFuel.execute();
    }

    @Test
    @DisplayName("""
            execute():
             consumption = 0
             => doesn't change quantity
            """)
    public void execute2() {
        FuelComponent fuelComponent = Mockito.mock(FuelComponent.class);
        Mockito.when(fuelComponent.getQuantity()).thenReturn(100);
        Mockito.when(fuelComponent.getConsumption()).thenReturn(0);
        Mockito.doAnswer(invocationOnMock -> {
            int arg = (Integer)invocationOnMock.getArguments()[0];
            Assertions.assertEquals(100, arg);
            return null;
        }).when(fuelComponent).setQuantity(Mockito.anyInt());
        BurnFuel burnFuel = new BurnFuel(fuelComponent);

        burnFuel.execute();
    }

}