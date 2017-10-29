package ru.boris.peremoga.core;

import burlap.domain.singleagent.gridworld.state.GridWorldState;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.state.State;

public class CustomCostRF implements burlap.mdp.singleagent.model.RewardFunction {

    public CustomCostRF(World world) {
        w = world;
    }


    @Override
    public double reward(State s, Action a, State sprime) {
        GridWorldState s1 = (GridWorldState)s;
        if(w.getAreaItem(s1.agent.y, s1.agent.x) == 5) return -10000;
        //if(w.getAreaItem(s1.agent.y, s1.agent.x) == 4) return -0.5;
        return -1;
    }

    private World w;
}
