package creatures;

import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public void move() {
        energy -= 0.03;
        energy = Math.max(energy, 0);
    }

    public void stay() {
        energy -= 0.01;
        energy = Math.max(energy, 0);
    }

    public Clorus replicate() {
        energy *= 0.5;
        return new Clorus(this.energy);
    }

    public Color color() {
        return color(34, 0, 231);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        Direction emptyDir = HugLifeUtils.randomEntry(empties);
        if (plips.size() != 0) {
            Direction dir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, dir);
        }
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, emptyDir);
        }
        return new Action(Action.ActionType.MOVE, emptyDir);
    }
}
