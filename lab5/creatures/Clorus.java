package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import static huglife.HugLifeUtils.randomEntry;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature{

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * Creates Clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Creates clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Returns a color with red = 34, green = 0, and green = 231.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * When clorus chooses to attack, it gains the creature's energy.
     */
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    /**
     * Clorus losses 0.03 amount of energy on move action.
     */
    public void move() {
        energy = energy - 0.03;
    }

    /**
     * Clorus losses 0.01 amount of energy on stay action.
     */
    public void stay() {
        energy = energy - 0.01;
    }

    /**
     * When clorus chooses to replicate, it keeps 50% of its energy.
     * The other 50% goes to its offspring.
     * No energy is lost to the environment.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    /**
     * Cloruses take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent places, STAY.
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbours) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> anyPlips = new ArrayDeque<>();
        for (Direction d : neighbours.keySet()) {
            if (neighbours.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            } else if (neighbours.get(d).name().equals("plip")) {
                anyPlips.add(d);
            }
        }

        //Rule 1:
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        //Rule 2:
        if (anyPlips.size() > 0) {
            Direction d = randomEntry(anyPlips);
            return new Action(Action.ActionType.ATTACK, d);
        }

        //Rule 3:
        if (energy >= 1.0) {
            Direction d = randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        //Rule 4:
        Direction d = randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, d);
    }
}

