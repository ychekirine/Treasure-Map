package youcef.treasureProject.model;

import org.junit.jupiter.api.Test;
import youcef.treasureProject.enums.Orientation;
import youcef.treasureProject.io.EntryFileParser;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    @Test
    void turn_right_should_return_east_from_north() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(0,0));
        lambdaAdventurer.turnRight();
        assertEquals(Orientation.EAST, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_right_should_return_south_from_east() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(0,0));
        lambdaAdventurer.turnRight();
        assertEquals(Orientation.SOUTH, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_right_should_return_west_from_south() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(0,0));
        lambdaAdventurer.turnRight();
        assertEquals(Orientation.WEST, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_right_should_return_north_from_west() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(0,0));
        lambdaAdventurer.turnRight();
        assertEquals(Orientation.NORTH, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_left_should_return_west_from_north() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(0,0));
        lambdaAdventurer.turnLeft();
        assertEquals(Orientation.WEST, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_left_should_return_south_from_west() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(0,0));
        lambdaAdventurer.turnLeft();
        assertEquals(Orientation.SOUTH, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_left_should_return_east_from_south() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(0,0));
        lambdaAdventurer.turnLeft();
        assertEquals(Orientation.EAST, lambdaAdventurer.getOrientation());
    }

    @Test
    void turn_left_should_return_north_from_east() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(0,0));
        lambdaAdventurer.turnLeft();
        assertEquals(Orientation.NORTH, lambdaAdventurer.getOrientation());
    }

    @Test
    void add_treasure_should_return_1() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(0,0));
        lambdaAdventurer.addTreasure();
        assertEquals(1, lambdaAdventurer.getTreasureNumbers());
    }

    @Test
    void add_treasure_should_return_2() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(0,0));
        lambdaAdventurer.addTreasure();
        lambdaAdventurer.addTreasure();
        assertEquals(2, lambdaAdventurer.getTreasureNumbers());
    }

    @Test
    void move_forward_should_return_horizontal1_vertical0() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(1,1));
        lambdaAdventurer.moveForward();
        assertEquals(new Position(1,0), lambdaAdventurer.getPosition());
    }

    @Test
    void move_forward_should_return_horizontal2_vertical1() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(1,1));
        lambdaAdventurer.moveForward();
        assertEquals(new Position(2,1), lambdaAdventurer.getPosition());
    }

    @Test
    void move_forward_should_return_horizontal1_vertical2() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(1,1));
        lambdaAdventurer.moveForward();
        assertEquals(new Position(1,2), lambdaAdventurer.getPosition());
    }

    @Test
    void move_forward_should_return_horizontal0_vertical1() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(1,1));
        lambdaAdventurer.moveForward();
        assertEquals(new Position(0,1), lambdaAdventurer.getPosition());
    }
}