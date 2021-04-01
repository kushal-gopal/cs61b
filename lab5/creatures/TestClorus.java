package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(5);
        assertEquals(5, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(4.97, c.energy(), 0.01);
        c.move();
        assertEquals(4.94, c.energy(), 0.01);
        c.stay();
        assertEquals(4.93, c.energy(), 0.01);
        c.stay();
        assertEquals(4.92, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(5);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        //There exists an empty space and an plip in TOP direction.
        c = new Clorus(1);
        surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        //No plips but empty space exists, hence it replicates.
        c = new Clorus(1);
        surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);

        assertEquals(expected, actual);

        //Same conditions as the previous one except energy of the clorus is less than one.
        //No plips but empty space exists, hence it moves.
        c = new Clorus(0.5);
        surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());

        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.MOVE, Direction.LEFT);

        assertEquals(expected, actual);
    }
}
