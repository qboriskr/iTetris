package ru.boris.peremoga;

public class Game {
    public Game(World world, Strategy strategy) {
        w = world;
        s = strategy;
    }

    private World w;
    private Strategy s;

    private boolean finished;

    public boolean isFinished() {
        return finished;
    }

    public void tick() {
        if (w.getAreaItem(w.getHero().getCurrentPosition()) == 3) {
            System.out.println("Exit found!");
            finished = true;
        }

        Move m = s.getMove();
        w.move(m);
        System.out.println(m);
    }

    public void show() {
        System.out.println(w.show());
    }
}
