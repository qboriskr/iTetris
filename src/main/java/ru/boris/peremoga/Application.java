package ru.boris.peremoga;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        World world = new World();
        Strategy strategy = new RandomMoveStrategy(world);
        Game game = new Game(world, strategy);
        while (!game.isFinished()) {
            game.show();
            game.tick();
            Thread.sleep(100);
        }
    }

}
