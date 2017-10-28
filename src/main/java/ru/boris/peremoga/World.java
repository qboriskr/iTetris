package ru.boris.peremoga;

import java.awt.*;

public class World {

    public World() {

        hero = new Hero(new Point(1, 8));
    }

    private int[][] area = {
            {1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    private Hero hero;

    private int getAreaItem(int x, int y) {
        return area[x][y];
    }

    private int getAreaItem(Point p) {
        return area[p.x][p.y];
    }
}
