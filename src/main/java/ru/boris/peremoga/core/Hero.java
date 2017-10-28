package ru.boris.peremoga.core;

import java.awt.*;

public class Hero {

    public Hero(Point p) {
        currentPoaition = p;
    }

    private Point currentPoaition;

    public Point getCurrentPosition() {
        return new Point(currentPoaition);
    }

    public void setCurrentPosition(Point newPoint) {
        currentPoaition = newPoint;
    }
}
