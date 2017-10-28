package ru.boris.peremoga.swing;

/**
 * Created by boris on 18.04.2015.
 */


// Metrics for 640 x 640 resolution, 32 pixels for sprite
public class DisplayMetricsWindowed640 implements DisplayMetrics {

    @Override
    public int fontSize() {
        return 20;
    }

    @Override
    public int width() {
        return 640;
    }

    @Override
    public int height() {
        return 640;
    }

    @Override
    public int controlsY() {
        return 1;
    }

    @Override
    public int controlsX() {
        return 0;
    }

    @Override
    public int timerY() {
        return 1;
    }

    @Override
    public int timerX() {
        return 27;
    }

    @Override
    public int itemUnderCursorY() {
        return 31;
    }

    @Override
    public int itemUnderCursorX() {
        return 2;
    }

    @Override
    public int unitStatusY() {
        return 31;
    }

    @Override
    public int unitStatusX() {
        return 23;
    }

    @Override
    public boolean headless() {
        return false;
    }
}
