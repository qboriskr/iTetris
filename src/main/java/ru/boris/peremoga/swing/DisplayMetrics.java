package ru.boris.peremoga.swing;

/**
 * Created by boris on 18.04.2015.
 */
public interface DisplayMetrics {

    public int fontSize();

    public int width();

    public int height();

    int controlsY();

    int controlsX();

    int timerY();

    int timerX();

    int itemUnderCursorY();

    int itemUnderCursorX();

    int unitStatusY();

    int unitStatusX();

    boolean headless();
}
