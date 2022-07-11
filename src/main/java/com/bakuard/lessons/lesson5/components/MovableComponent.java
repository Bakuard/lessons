package com.bakuard.lessons.lesson5.components;

import com.bakuard.lessons.lesson5.Vector;

import java.util.Objects;

public class MovableComponent implements Movable {

    private Vector position;
    private Vector velocity;

    public MovableComponent() {
        position = new Vector(0 ,0);
        velocity = new Vector(0, 0);
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public void setPosition(Vector position) {
        this.position = Objects.requireNonNull(position, "position can't be null");
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.velocity = Objects.requireNonNull(velocity, "velocity can't be null");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovableComponent that = (MovableComponent) o;
        return position.equals(that.position) && velocity.equals(that.velocity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, velocity);
    }

    @Override
    public String toString() {
        return "MovableComponent{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }

}
