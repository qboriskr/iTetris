package ru.boris.peremoga;

import ru.boris.peremoga.core.Game;
import ru.boris.peremoga.core.RandomMoveStrategy;
import ru.boris.peremoga.core.Strategy;
import ru.boris.peremoga.core.World;
import ru.boris.peremoga.swing.DisplayMetricsWindowed640;
import ru.boris.peremoga.swing.SwingEngine;

import java.io.IOException;

public class Application {

    public static String version = "RL 1.0";

    public static void main(String[] args) throws InterruptedException, IOException {
        World world = new World();
        Strategy strategy = new RandomMoveStrategy(world);
        UiEngine engine = new SwingEngine(new DisplayMetricsWindowed640());
        engine.setMapScale(1);
        Game game = new Game(world, strategy, engine);
        while (!game.isFinished()) {
            game.show();
            game.checkState();
            game.tick();
            Thread.sleep(1);
        }
    }

}
