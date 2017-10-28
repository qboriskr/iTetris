package ru.boris.peremoga.core;

import java.awt.*;

public class World {

    public World() {
        hero = new Hero(new Point(1, 8));
    }

    private int areaWidth = 10;
    private int areaHeight = 10;

    private int[][] area = {
            {1, 1, 1, 1, 1, 1, 1, 1, 3, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Hero getHero() {
        return hero;
    }

    private Hero hero;


    private Point getNewPoint(Move move) {
        Point result = hero.getCurrentPosition();

        switch (move) {
            case Down:
                result.y++;
                break;
            case Up:
                result.y--;
                break;
            case Right:
                result.x++;
                break;
            case Left:
                result.x--;
                break;
        }
        return result;
    }

    public boolean canMove(Move move) {
        return canMove(getNewPoint(move));
    }

    private boolean canMove(Point newPoint) {
        return getAreaItem(newPoint) != 1;
    }

    public boolean move(Move move) {
        if (canMove(move)) {
            hero.setCurrentPosition(getNewPoint(move));
            return true;
        }
        return false;
    }


    public int getAreaItem(int y, int x) {
        return area[y][x];
    }

    public int getAreaItem(Point p) {
        return area[p.y][p.x];
    }

    public String show() {
        return "Hero position: " + hero.getCurrentPosition();
    }

    public int getAreaWidth() {
        return areaWidth;
    }

    public int getAreaHeight() {
        return areaHeight;
    }
}
