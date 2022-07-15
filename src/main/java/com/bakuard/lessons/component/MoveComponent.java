package com.bakuard.lessons.component;

import java.util.Objects;

public class MoveComponent {

    private Vector position;
    private Vector velocity;

    public MoveComponent() {
        position = new Vector(0 ,0);
        velocity = new Vector(0, 0);
    }

    public Vector getPosition() {
        return position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveComponent that = (MoveComponent) o;
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
