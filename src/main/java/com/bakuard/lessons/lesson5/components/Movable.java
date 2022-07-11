package com.bakuard.lessons.lesson5.components;

import com.bakuard.lessons.lesson5.Vector;

public interface Movable {

    public Vector getPosition();

    public Vector getVelocity();

    public void setPosition(Vector position);

    public void setVelocity(Vector velocity);

}
