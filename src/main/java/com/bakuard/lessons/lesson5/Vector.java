package com.bakuard.lessons.lesson5;

public record Vector(double x, double y) {

    public Vector setX(double x) {
        return new Vector(x, y);
    }

    public Vector setY(double y) {
        return new Vector(x, y);
    }

    public Vector add(Vector vector) {
        return new Vector(x + vector.x, y + vector.y);
    }

    public double getLength() {
        return Math.sqrt(x*x + y*y);
    }

}
