package ru.boris.peremoga.core;

import ru.boris.peremoga.UiEngine;
import ru.boris.peremoga.hud.ObjectId;

import java.awt.*;

import static java.lang.Math.min;

public class Game {
    private long tickCount;

    public Game(World world, Strategy strategy, UiEngine engine) {
        tickCount = 0;
        w = world;
        s = strategy;
        ui = engine;
    }

    private World w;  // world
    private Strategy s;  // current strategy
    private UiEngine ui; // ui engine

    private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void checkState() {
        if (w.getAreaItem(w.getHero().getCurrentPosition()) == 3) {
            System.out.println("Exit found!");
            finished = true;
        }
    }

    public void tick() {
        tickCount++;
        Move m = s.getMove();
        w.move(m);
        System.out.println(m);
    }

    public void show() {
        ui.beginRendering();
        showWorld();
        ui.finishRendering();

        System.out.println(tickCount + ":" + w.show());
    }

    Point windowCorner = new Point(0, 0);

    private void showWorld() {
        int windowHeight = min(w.getAreaWidth(), ui.getHud().getMapWindowHeight());
        int windowWidth = min(w.getAreaHeight(), ui.getHud().getMapWindowWidth());
        Point currentPosition = w.getHero().getCurrentPosition();

        for (int wy = 0; wy < windowHeight; wy++) {
            for (int wx = 0; wx < windowWidth; wx++) {
                int y = wy + windowCorner.y;
                int x = wx + windowCorner.x;

                ObjectId obj = getObjectBy(w.getAreaItem(x, y));
                if (currentPosition.x == x && currentPosition.y == y) obj = ObjectId.HUMAN_CIVILIAN;
                ui.draw(wy, wx, obj);
            }
        }
    }

    private ObjectId getObjectBy(int areaItem) {
        switch (areaItem) {
            case 0:
                return ObjectId.GRASS;
            case 1:
                return ObjectId.WALL;
            case 2:
                return ObjectId.HUMAN_CIVILIAN;
            case 3:
                return ObjectId.DOOR;
        }
        return ObjectId.EMPTY;
    }

}
