package ru.boris.peremoga;

import burlap.behavior.singleagent.Episode;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.domain.singleagent.gridworld.GridWorldTerminalFunction;
import burlap.domain.singleagent.gridworld.state.GridWorldState;
import burlap.mdp.singleagent.SADomain;
import burlap.mdp.singleagent.environment.Environment;
import burlap.mdp.singleagent.environment.SimulatedEnvironment;
import burlap.statehashing.simple.SimpleHashableStateFactory;
import ru.boris.peremoga.core.Game;
import ru.boris.peremoga.core.Strategy;
import ru.boris.peremoga.core.World;
import ru.boris.peremoga.swing.DisplayMetricsWindowed640;
import ru.boris.peremoga.swing.SwingEngine;

import java.awt.*;
import java.io.IOException;

public class Application {

    public static String version = "RL 1.0";

    public static void main(String[] args) throws InterruptedException, IOException {
        World world = new World();
        world.setTf(findTerminalState(world));
        SADomain domain = world.generateDomain();
        Environment env = new SimulatedEnvironment(domain, findBeginState(world));
//create a Q-learning agent
        QLearning agent = new QLearning(domain, 0.99, new SimpleHashableStateFactory(), 1.0, 1.0);

        Episode ep = null;
        for (int i = 0; i < 1000; i++) {
            ep = agent.runLearningEpisode(env);
            System.out.println(ep.actionString());
            env.resetEnvironment();
        }
        play(world, ep);
    }

    private static GridWorldTerminalFunction findTerminalState(World world) {
        for (int y = 0; y < world.getAreaHeight(); y++) {
            for (int x = 0; x < world.getAreaWidth(); x++) {
                if (world.getAreaItem(y, x) == 3)
                    // Здесь используется формат координат x,y
                    return new GridWorldTerminalFunction(x, y);
            }
        }
        throw new RuntimeException("Terminal state not found!");
    }

    private static GridWorldState findBeginState(World world) {
        Point p = world.getHero().getCurrentPosition();
        // Здесь используется формат координат x,y
        return new GridWorldState(p.x, p.y);
    }

    private static void play(World world, Episode ep) throws InterruptedException, IOException {
        Strategy strategy = new RLStrategy(world, ep);
        UiEngine engine = new SwingEngine(new DisplayMetricsWindowed640());
        engine.setMapScale(1);
        Game game = new Game(world, strategy, engine);
        while (!game.isFinished()) {
            game.show();
            game.checkState();
            game.tick();
            Thread.sleep(100);
        }
    }

}
