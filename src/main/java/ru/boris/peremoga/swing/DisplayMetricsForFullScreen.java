package ru.boris.peremoga.swing;

import java.awt.*;

/**
 * Created by boris on 20.04.2015.
 */

public class DisplayMetricsForFullScreen implements DisplayMetrics {
    private final int width;
    private final int height;

    static public Rectangle getScreenBounds(Window wnd) {
        Rectangle sb;
        Insets si = getScreenInsets(wnd);

        if (wnd == null) {
            sb = GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration()
                    .getBounds();
        } else {
            sb = wnd
                    .getGraphicsConfiguration()
                    .getBounds();
        }

        sb.x += si.left;
        sb.y += si.top;
        sb.width -= si.left + si.right;
        sb.height -= si.top + si.bottom;
        return sb;
    }

    static public Insets getScreenInsets(Window wnd) {
        Insets si;

        if (wnd == null) {
            si = Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment
                    .getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration());
        } else {
            si = wnd.getToolkit().getScreenInsets(wnd.getGraphicsConfiguration());
        }
        return si;
    }

    public DisplayMetricsForFullScreen() {
        height = (int) getScreenBounds(null).getHeight();
        width = (int) getScreenBounds(null).getWidth();
    }

    @Override
    public int fontSize() {
        return 20;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public int controlsY() {
        return 1;
    }

    @Override
    public int controlsX() {
        return 1;
    }

    @Override
    public int timerY() {
        return 1;
    }

    @Override
    public int timerX() {
        return width / fontSize() - 7;
    }

    @Override
    public int itemUnderCursorY() {
        return bottomLine();
    }

    @Override
    public int itemUnderCursorX() {
        return 2;
    }

    @Override
    public int unitStatusY() {
        return bottomLine();
    }

    private int bottomLine() {
        return height / fontSize() - 1;
    }

    @Override
    public int unitStatusX() {
        return width / fontSize() - 9;
    }

    @Override
    public boolean headless() {
        return true;
    }
}
