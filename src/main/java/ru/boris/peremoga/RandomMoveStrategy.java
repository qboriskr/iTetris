package ru.boris.peremoga;

import java.util.Random;

public class RandomMoveStrategy implements Strategy {
    public RandomMoveStrategy(World world) {
        w = world;
        random = new Random(1);
    }

    @Override
    public Move getMove() {
        return Move.values()[random.nextInt(Move.values().length)];
    }

    private Random random;
    private World w;
}
