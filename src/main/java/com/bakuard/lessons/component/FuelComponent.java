package com.bakuard.lessons.component;

import java.util.Objects;

public class FuelComponent {

    private int quantity;
    private int consumption;

    public FuelComponent(int quantity, int consumption) {
        this.quantity = quantity;
        this.consumption = consumption;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getConsumption() {
        return consumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelComponent that = (FuelComponent) o;
        return quantity == that.quantity && consumption == that.consumption;
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, consumption);
    }

    @Override
    public String toString() {
        return "FuelComponent{" +
                "quantity=" + quantity +
                ", consumption=" + consumption +
                '}';
    }

}
