package ru.boris.peremoga.swing;

/**
 * Created by boris on 13.04.2015.
 */
public interface MouseController {

    public int getMouseSpeedY();
    public int getMouseSpeedX();

    void calculateSpeed();
}