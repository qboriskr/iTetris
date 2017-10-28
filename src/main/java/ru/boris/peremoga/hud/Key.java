package ru.boris.peremoga.hud;

/**
 * Created by boris on 08.04.2015.
 */
public interface Key {
    public boolean escape();
    public boolean enter();

    public boolean left();
    public boolean right();
    public boolean up();
    public boolean down();

    public boolean isChar();
    public char getChar();

    boolean triggered();
}
