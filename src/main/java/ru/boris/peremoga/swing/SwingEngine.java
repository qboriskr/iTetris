package ru.boris.peremoga.swing;

import ru.boris.peremoga.Application;
import ru.boris.peremoga.UiEngine;
import ru.boris.peremoga.hud.Hud;
import ru.boris.peremoga.hud.ObjectId;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by boris on 08.04.2015.
 */
public final class SwingEngine implements UiEngine {

    private Display display;

    private int textCursorY;
    private int textCursorX;

    private Hud hud;

    private DisplayMetrics displayMetrics;

    private Graphics g = null;
    private BufferStrategy bs = null;
    private SpriteTranslator spriteTranslator;
    private SpriteFactory spriteFactory;


    private Font font;

    private Color defaultColor = new Color(245, 215, 175);

    private BufferedImage backgroundScreen = null;
    private boolean plasma = false;

    public SwingEngine(DisplayMetrics displayMetrics) throws IOException {
        this.displayMetrics = displayMetrics;
        hud = new GraphicalHud();

        display = new Display(Application.version, displayMetrics.width(), displayMetrics.height(), displayMetrics.headless());

        /*
        // hides cursor
        display.getFrame().setCursor(display.getFrame().getToolkit().createCustomCursor(
                new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
                "null"));
        */

        spriteFactory = new SpriteFactory();
        spriteFactory.init();

        spriteTranslator = new SpriteTranslator(spriteFactory);

        font = new Font("Arial", Font.PLAIN, displayMetrics.fontSize());

        // String screenName = "tetris.png";
        // backgroundScreen = ImageLoader.loadImage(screenName);
    }

    public int getCanvasHeight() {
        return display.getCanvas().getHeight();
    }

    public int getCanvasWidth() {
        return display.getCanvas().getWidth();
    }

    @Override
    public int getTerminalHeight() {
        return getCanvasHeight() / spriteFactory.getSpriteSize();
    }

    @Override
    public int getTerminalWidth() {
        return getCanvasWidth() / spriteFactory.getSpriteSize();
    }


    @Override
    public void cleanUp() {
        display.getFrame().dispose();
    }


    @Override
    public void moveCursor(int y, int x) {
        this.textCursorY = y * displayMetrics.fontSize();
        this.textCursorX = x * displayMetrics.fontSize();
    }

    @Override
    public void printString(String message) {
        g.drawString(message, textCursorX, textCursorY);
        textCursorX += displayMetrics.fontSize() * message.length();
    }

    @Override
    public void setDefaultColor() {
        g.setColor(defaultColor);
        g.setFont(font);
    }

    @Override
    public boolean terminalPresent() {
        return true;
    }

    @Override
    public Hud getHud() {
        return hud;
    }

    @Override
    public void drawCursorOver(int wy, int wx, ObjectId obj) {
        draw(wy, wx, obj);
        draw(wy, wx, ObjectId.CURSOR);
    }

    @Override
    public void setMapScale(int mapScale) {
        spriteFactory.setSpriteScale(mapScale);
    }

    @Override
    public DisplayMetrics getDisplayMetrics() {
        return displayMetrics;
    }

    @Override
    public void beginRendering() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(2);
            bs = display.getCanvas().getBufferStrategy();
        }
        g = bs.getDrawGraphics();

        if (plasma) {
            plasmaPeriod--;
            if (plasmaPeriod < 0) {
                plasmaPeriod = 3;
                if (backgroundScreen != null) {
                    g.drawImage(backgroundScreen, 0, 0, null);
                }
                //drawPlasma(g, getCanvasWidth() - 240, getCanvasHeight());
            }
        } else {
            // Clear Screen
            g.setColor(Color.black);
            g.fillRect(0, 0, getCanvasWidth(), getCanvasHeight());
        }
    }

    private int plasmaPeriod = 0;

    @Override
    public void draw(int y, int x, ObjectId objectId) {
        if (g != null) {
            g.drawImage(spriteTranslator.convert(objectId),
                    x * spriteFactory.getSpriteSize() + hud.getXMapCorner(), y * spriteFactory.getSpriteSize() + hud.getYMapCorner(),
                    spriteFactory.getSpriteSize(), spriteFactory.getSpriteSize(), null);
        }
    }

    @Override
    public void finishRendering() {
        if (bs != null) {
            g.dispose();
            bs.show();
        }
    }


    private class GraphicalHud implements Hud {
        @Override
        public int getYMapCorner() {
            return 0;
        }

        @Override
        public int getXMapCorner() {
            return 0;
        }

        @Override
        public int getHudWidth() {
            return 0;
        }

        public int getMapWindowHeight() {
            return getTerminalHeight();
        }

        public int getMapWindowWidth() {
            return getTerminalWidth();
        }

    }
}
