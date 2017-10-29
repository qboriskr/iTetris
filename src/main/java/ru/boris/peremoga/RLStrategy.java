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
        return t >= ep.numActions();
    }

    @Override
    public Move getMove(World w) {
        if (t < ep.numActions()) {
            Point p = w.getHero().getCurrentPosition();
            //System.out.println("Action " + t + " of " + ep.numActions() + ": " + ep.action(t).actionName());
            //System.out.println("Hero x="+p.x+", y="+p.y);
            GridWorldState s = (GridWorldState)ep.state(t);
            //System.out.println("State x="+s.agent.x+", y="+s.agent.y);
            //w.getHero().setCurrentPosition(new Point(s.agent.x, s.agent.y));
            int actionIndex = World.actionInd(ep.action(t).actionName());
            t++;
            return World.actionIndToMove(actionIndex);
        } else {
            t++;
            return Move.None;
        }
    }

    @Deprecated
    public Move getMoveOld(World w) {
        if (t < ep.numTimeSteps()) {
            //Point p = w.getHero().getCurrentPosition();
            //System.out.println("Step " + t + " of " + ep.numTimeSteps() + ": " + ep.action(t).actionName());
            //System.out.println("Hero x="+p.x+", y="+p.y);
            GridWorldState s = (GridWorldState) ep.state(t);
            w.getHero().setCurrentPosition(new Point(s.agent.x, s.agent.y));
            int actionIndex = World.actionInd(ep.action(t).actionName());
            t++;
            return World.actionIndToMove(actionIndex);
        }
        return Move.None;
    }
}
