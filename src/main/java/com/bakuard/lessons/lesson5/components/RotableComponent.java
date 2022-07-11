package com.bakuard.lessons.lesson5.components;

import java.util.Objects;

public class RotableComponent implements Rotable {

    private int direction;
    private int angularVelocity;
    private int directionsNumber;

    public RotableComponent(int direction, int angularVelocity, int directionsNumber) {
        this.direction = direction;
        this.angularVelocity = angularVelocity;
        this.directionsNumber = directionsNumber;
    }

    @Override
    public int getDirection() {
        return direction;
    }

    @Override
    public int getAngularVelocity() {
        return angularVelocity;
    }

    @Override
    public int getDirectionsNumber() {
        return directionsNumber;
    }

    @Override
    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotableComponent that = (RotableComponent) o;
        return direction == that.direction &&
                angularVelocity == that.angularVelocity &&
                directionsNumber == that.directionsNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, angularVelocity, directionsNumber);
    }

    @Override
    public String toString() {
        return "RotableComponent{" +
                "direction=" + direction +
                ", angularVelocity=" + angularVelocity +
                ", directionsNumber=" + directionsNumber +
                '}';
    }

}
