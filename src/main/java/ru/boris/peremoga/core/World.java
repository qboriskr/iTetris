package ru.boris.peremoga.core;

import burlap.debugtools.RandomFactory;
import burlap.domain.singleagent.gridworld.GridWorldTerminalFunction;
import burlap.domain.singleagent.gridworld.state.GridAgent;
import burlap.domain.singleagent.gridworld.state.GridLocation;
import burlap.domain.singleagent.gridworld.state.GridWorldState;
import burlap.mdp.auxiliary.DomainGenerator;
import burlap.mdp.auxiliary.common.NullTermination;
import burlap.mdp.core.StateTransitionProb;
import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.action.Action;
import burlap.mdp.core.action.UniversalActionType;
import burlap.mdp.core.oo.propositional.PropositionalFunction;
import burlap.mdp.core.oo.state.OOState;
import burlap.mdp.core.oo.state.ObjectInstance;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.common.UniformCostRF;
import burlap.mdp.singleagent.model.FactoredModel;
import burlap.mdp.singleagent.model.RewardFunction;
import burlap.mdp.singleagent.model.statemodel.FullStateModel;
import burlap.mdp.singleagent.oo.OOSADomain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static burlap.domain.singleagent.gridworld.GridWorldDomain.CLASS_AGENT;
import static burlap.domain.singleagent.gridworld.GridWorldDomain.CLASS_LOCATION;

public class World implements DomainGenerator {

    protected GridWorldTerminalFunction tf;
    protected RewardFunction rf;

    private double[][] transitionDynamics;


    /**
     * Will set the domain to use deterministic action transitions.
     */
    public void setDeterministicTransitionDynamics(){
        int na = 4;
        transitionDynamics = new double[na][na];
        for(int i = 0; i < na; i++){
            for(int j = 0; j < na; j++){
                if(i != j){
                    transitionDynamics[i][j] = 0.;
                }
                else{
                    transitionDynamics[i][j] = 1.;
                }
            }
        }
    }

    public World() {
        this.setDeterministicTransitionDynamics();
        hero = new Hero(new Point(1, 8));
    }

    private int mapWidth = 10;
    private int mapHeight = 10;

    private int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 1, 3, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    public Hero getHero() {
        return hero;
    }

    private Hero hero;


    private Point getNewPoint(Move move) {
        Point result = hero.getCurrentPosition();

        switch (move) {
            case Up:
                result.y--;
                break;
            case Down:
                result.y++;
                break;
            case Right:
                result.x++;
                break;
            case Left:
                result.x--;
                break;
        }
        return result;
    }


    public static Move actionIndToMove(int i) {
        switch (i) {
            case 0: return Move.Up;
            case 1: return Move.Down;
            case 2: return Move.Left;
            case 3: return Move.Right;
        }
        return Move.None;
    }

    public static int actionInd(String name) {
        if (name.equals(Move.Up.toString())) {
            return 0;
        } else if (name.equals(Move.Down.toString())) {
            return 1;
        } else if (name.equals(Move.Left.toString())) {
            return 2;
        } else if (name.equals(Move.Right.toString())) {
            return 3;
        }
        throw new RuntimeException("Unknown action " + name);
    }

    public boolean canMove(Move move) {
        return canMove(getNewPoint(move));
    }

    private boolean canMove(Point newPoint) {
        return getAreaItem(newPoint) != 1;
    }

    public boolean move(Move move) {
        if (canMove(move)) {
            hero.setCurrentPosition(getNewPoint(move));
            return true;
        }
        return false;
    }


    public int getAreaItem(int y, int x) {
        return map[y][x];
    }

    public int getAreaItem(Point p) {
        return map[p.y][p.x];
    }

    public String show() {
        return "Hero position: " + hero.getCurrentPosition();
    }

    public int getAreaWidth() {
        return mapWidth;
    }

    public int getAreaHeight() {
        return mapHeight;
    }

    /**
     * Returns a deep copy of the map being used for the domain
     * @return a deep copy of the map being used in the domain
     */
    public int [][] getMap(){
        int [][] cmap = new int[this.map.length][this.map[0].length];
        for(int i = 0; i < this.map.length; i++){
            for(int j = 0; j < this.map[0].length; j++){
                cmap[i][j] = this.map[i][j];
            }
        }
        return cmap;
    }


    /**
     * Will set the movement direction probabilities based on the action chosen. The index (0,1,2,3) indicates the
     * direction north,south,east,west, respectively and the matrix is organized by transitionDynamics[selectedDirection][actualDirection].
     * For instance, the probability of the agent moving east when selecting north would be specified in the entry transitionDynamics[0][2]
     *
     * @param transitionDynamics entries indicate the probability of movement in the given direction (second index) for the given action selected (first index).
     */
    public void setTransitionDynamics(double [][] transitionDynamics){
        this.transitionDynamics = transitionDynamics.clone();
    }

    public double [][] getTransitionDynamics(){
        double [][] copy = new double[transitionDynamics.length][transitionDynamics[0].length];
        for(int i = 0; i < transitionDynamics.length; i++){
            for(int j = 0; j < transitionDynamics[0].length; j++){
                copy[i][j] = transitionDynamics[i][j];
            }
        }
        return copy;
    }


    public static class WorldModel implements FullStateModel {


        /**
         * The map of the world
         */
        int [][] map;


        /**
         * Returns the change in x and y position for a given direction number.
         * @param i the direction number (0,1,2,3 indicates north,south,east,west, respectively)
         * @return the change in direction for x and y; the first index of the returned double is change in x, the second index is change in y.
         */
        protected static int [] movementDirectionFromIndex(int i){
            int [] result = null;
            switch (i) {
                case 0:
                    result = new int[]{0,-1};
                    break;

                case 1:
                    result = new int[]{0,1};
                    break;

                case 2:
                    result = new int[]{1,0};
                    break;

                case 3:
                    result = new int[]{-1,0};
                    break;

                default:
                    break;
            }

            return result;
        }


        /**
         * Matrix specifying the transition dynamics in terms of movement directions. The first index
         * indicates the action direction attempted (ordered north, south, east, west) the second index
         * indicates the actual resulting direction the agent will go (assuming there is no wall in the way).
         * The value is the probability of that outcome. The existence of walls does not affect the probability
         * of the direction the agent will actually go, but if a wall is in the way, it will affect the outcome.
         * For instance, if the agent selects north, but there is a 0.2 probability of actually going east and
         * there is a wall to the east, then with 0.2 probability, the agent will stay in place.
         */
        protected double[][] transitionDynamics;

        protected Random rand = RandomFactory.getMapped(0);


        public WorldModel(int[][] map, double[][] transitionDynamics) {
            this.map = map;
            this.transitionDynamics = transitionDynamics;
        }

        @Override
        public java.util.List<StateTransitionProb> stateTransitions(State s, Action a) {

            double [] directionProbs = transitionDynamics[actionInd(a.actionName())];

            List<StateTransitionProb> transitions = new ArrayList<StateTransitionProb>();
            for(int i = 0; i < directionProbs.length; i++){
                double p = directionProbs[i];
                if(p == 0.){
                    continue; //cannot transition in this direction
                }
                State ns = s.copy();
                int [] dcomps = movementDirectionFromIndex(i);
                ns = move(ns, dcomps[0], dcomps[1]);

                //make sure this direction doesn't actually stay in the same place and replicate another no-op
                boolean isNew = true;
                for(StateTransitionProb tp : transitions){
                    if(tp.s.equals(ns)){
                        isNew = false;
                        tp.p += p;
                        break;
                    }
                }

                if(isNew){
                    StateTransitionProb tp = new StateTransitionProb(ns, p);
                    transitions.add(tp);
                }

            }
            return transitions;
        }

        @Override
        public State sample(State s, Action a) {

            s = s.copy();

            double [] directionProbs = transitionDynamics[actionInd(a.actionName())];
            double roll = rand.nextDouble();
            double curSum = 0.;
            int dir = 0;
            for(int i = 0; i < directionProbs.length; i++){
                curSum += directionProbs[i];
                if(roll < curSum){
                    dir = i;
                    break;
                }
            }

            int [] dcomps = movementDirectionFromIndex(dir);
            return move(s, dcomps[0], dcomps[1]);

        }

        /**
         * Attempts to move the agent into the given position, taking into account walls and blocks
         * @param s the current state
         * @param xd the attempted new X position of the agent
         * @param yd the attempted new Y position of the agent
         * @return input state s, after modification
         */
        protected State move(State s, int xd, int yd){

            GridWorldState gws = (GridWorldState)s;

            int ax = gws.agent.x;
            int ay = gws.agent.y;

            int nx = ax+xd;
            int ny = ay+yd;

            //hit wall, so do not change position
            if(nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || map[ny][nx] == 1){
                /*??????? странные условия, видимо для других игр
                (xd > 0 && (map[ax][ay] == 3 || map[ax][ay] == 4)) || (xd < 0 && (map[nx][ny] == 3 || map[nx][ny] == 4)) ||
                    (yd > 0 && (map[ax][ay] == 2 || map[ax][ay] == 4)) || (yd < 0 && (map[nx][ny] == 2 || map[nx][ny] == 4))
                 */
                nx = ax;
                ny = ay;
            } else if (map[ny][nx] == 3) {
                System.out.println("Exit found!");
            }

            GridAgent nagent = gws.touchAgent();
            nagent.x = nx;
            nagent.y = ny;

            return s;
        }

    }


    /**
     * Propositional function for determining if the agent is at the same position as a given location object
     * @author James MacGlashan
     *
     */
    public class AtLocationPF extends PropositionalFunction {


        /**
         * Initializes with given name domain and parameter object class types
         * @param name name of function
         * @param parameterClasses the object class types for the parameters
         */
        public AtLocationPF(String name, String[] parameterClasses) {
            super(name, parameterClasses);
        }

        @Override
        public boolean isTrue(OOState st, String... params) {

            ObjectInstance agent = st.object(params[0]);
            ObjectInstance location = st.object(params[1]);

            int ax = (Integer)agent.get("x");
            int ay = (Integer)agent.get("y");

            int lx = (Integer)location.get("x");
            int ly = (Integer)location.get("y");

            if(ax == lx && ay == ly){
                return true;
            }

            return false;
        }


    }


    @Override
    public OOSADomain generateDomain() {

        OOSADomain domain = new OOSADomain();
        int [][] cmap = this.getMap();
        domain.addStateClass(CLASS_AGENT, GridAgent.class).addStateClass(CLASS_LOCATION, GridLocation.class);
        WorldModel smodel = new WorldModel(cmap, getTransitionDynamics());
        RewardFunction rf = this.rf;
        TerminalFunction tf = this.tf;

        if(rf == null){
            rf = new UniformCostRF();
        }

        if(tf == null){
            tf = new NullTermination();
        }

        FactoredModel model = new FactoredModel(smodel, rf, tf);
        domain.setModel(model);

        domain.addActionTypes(
                new UniversalActionType(Move.Up.toString()),
                new UniversalActionType(Move.Down.toString()),
                new UniversalActionType(Move.Left.toString()),
                new UniversalActionType(Move.Right.toString()));

//        OODomain.Helper.addPfsToDomain(domain, this.generatePfs());
        return domain;
    }


    public void setTf(GridWorldTerminalFunction tf) {
        this.tf = tf;
    }

    public GridWorldTerminalFunction getTf() {
        return tf;
    }

    public RewardFunction getRf() {
        return rf;
    }

    public void setRf(RewardFunction rf) {
        this.rf = rf;
    }

}
