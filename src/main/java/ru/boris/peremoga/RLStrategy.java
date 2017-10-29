package ru.boris.peremoga;

import burlap.behavior.singleagent.Episode;
import burlap.domain.singleagent.gridworld.state.GridWorldState;
import ru.boris.peremoga.core.Move;
import ru.boris.peremoga.core.Strategy;
import ru.boris.peremoga.core.World;

import java.awt.*;

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
    public boolean isFinished() {
        return t >= ep.numTimeSteps();
    }

    @Override
    public Move getMove(World w) {
        if(t>=ep.numTimeSteps()) {
            /*try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        else {
            System.out.println("Step " + t + " of " + ep.numTimeSteps());
            GridWorldState s = (GridWorldState)ep.state(t);
            w.getHero().setCurrentPosition(new Point(s.agent.x, s.agent.y));
            t++;
        }
        return Move.None;
    }

    /*
    тут у нас не получилось с командами - кажется ошибка в маппинге, перепутались left&right...

    @Override
    public Move getMove() {
        System.out.println("Step " + t + " of " + ep.numActions());

        //System.out.println(ep.stateSequence);
        GridWorldState s = (GridWorldState)ep.state(t));
        System.out.println(s.agent.x);

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
     */
}
