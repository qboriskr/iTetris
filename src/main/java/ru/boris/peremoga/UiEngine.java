package ru.boris.peremoga;

import ru.boris.peremoga.hud.Hud;
import ru.boris.peremoga.hud.ObjectId;
import ru.boris.peremoga.swing.DisplayMetrics;

/**
 * Created by boris on 08.04.2015.
 */
public interface UiEngine {

    int getTerminalHeight();

    int getTerminalWidth();

    void cleanUp();

    void finishRendering();

    void moveCursor(int y, int x);

    void printString(String message);

    void setDefaultColor();

    void draw(int y, int x, ObjectId objectId);

    void beginRendering();

    boolean terminalPresent();

    Hud getHud();

    void drawCursorOver(int wy, int wx, ObjectId obj);

    void setMapScale(int mapScale);

    DisplayMetrics getDisplayMetrics();
}
