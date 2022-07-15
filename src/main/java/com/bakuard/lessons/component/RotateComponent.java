package com.bakuard.lessons.component;

import java.util.Objects;

public class RotateComponent {

    private int direction;
    private int angularVelocity;
    private int directionsNumber;

    public RotateComponent(int direction, int angularVelocity, int directionsNumber) {
        this.direction = direction;
        this.angularVelocity = angularVelocity;
        this.directionsNumber = directionsNumber;
    }

    public int getDirection() {
        return direction;
    }

    public int getAngularVelocity() {
        return angularVelocity;
    }

    public int getDirectionsNumber() {
        return directionsNumber;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RotateComponent that = (RotateComponent) o;
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
