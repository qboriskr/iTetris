package ru.boris.peremoga.core;

public interface Strategy {
    Move getMove(World w);

    boolean isFinished();
}
