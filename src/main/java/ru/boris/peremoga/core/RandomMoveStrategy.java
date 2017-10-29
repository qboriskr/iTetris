package ru.boris.peremoga.core;

import java.util.Random;

public class RandomMoveStrategy implements Strategy {
    public RandomMoveStrategy(World world) {
        w = world;
        random = new Random(1);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Move getMove(World w) {
        return Move.values()[random.nextInt(Move.values().length)];
    }

    private Random random;
    private World w;
}
