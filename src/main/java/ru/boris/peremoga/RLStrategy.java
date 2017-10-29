package ru.boris.peremoga;

import burlap.behavior.singleagent.Episode;
import ru.boris.peremoga.core.Move;
import ru.boris.peremoga.core.Strategy;
import ru.boris.peremoga.core.World;

public class RLStrategy implements Strategy {
    Episode ep;
    World w;
    int t = 0;

    public RLStrategy(World world, Episode episode) {
        t = 0;
        this.w = world;
        this.ep = episode;
    }

    @Override
    public Move getMove() {
        System.out.println("Step " + t + " of " + ep.numActions());
        if(t>=ep.numActions()) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Move.None;
        }
        int actionIndex = World.actionInd(ep.action(t).actionName());
        t++;
        return World.actionIndToMove(actionIndex);
    }
}
