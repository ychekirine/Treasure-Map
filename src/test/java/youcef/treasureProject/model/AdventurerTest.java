package youcef.treasureProject.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import youcef.treasureProject.enums.Orientation;
import youcef.treasureProject.io.EntryFileParser;

import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {

    private static Stream<Arguments> turnRightAdventurerOrientations() {
        return Stream.of(
                Arguments.of("WhenAdventurerNorthThenAdventurerEast", Orientation.NORTH, Orientation.EAST),
                Arguments.of("WhenAdventurerEastThenAdventurerSouth", Orientation.EAST, Orientation.SOUTH),
                Arguments.of("WhenAdventurerSouthThenAdventurerWest", Orientation.SOUTH, Orientation.WEST),
                Arguments.of("WhenAdventurerWestThenAdventurerNorth", Orientation.WEST, Orientation.NORTH)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("turnRightAdventurerOrientations")
    void utest_turn_right(String name, Orientation initOrientation , Orientation expected) {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), initOrientation, new Position(0,0));
        lambdaAdventurer.turnRight();
        assertEquals(expected, lambdaAdventurer.getOrientation());
    }

    private static Stream<Arguments> turnLeftAdventurerOrientations() {
        return Stream.of(
                Arguments.of("WhenAdventurerNorthThenAdventurerWest", Orientation.NORTH, Orientation.WEST),
                Arguments.of("WhenAdventurerWestThenAdventurerSouth", Orientation.WEST, Orientation.SOUTH),
                Arguments.of("WhenAdventurerSouthThenAdventurerEast", Orientation.SOUTH, Orientation.EAST),
                Arguments.of("WhenAdventurerEastThenAdventurerNorth", Orientation.EAST, Orientation.NORTH)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("turnLeftAdventurerOrientations")
    void utest_turn_left(String name, Orientation initOrientation , Orientation expected) {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), initOrientation, new Position(0,0));
        lambdaAdventurer.turnLeft();
        assertEquals(expected, lambdaAdventurer.getOrientation());
    }


    @Test
    void utest_add_treasure_should_return_1() {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(0,0));
        assertEquals(0,lambdaAdventurer.getTreasureNumbers());
        lambdaAdventurer.addTreasure();
        assertEquals(1, lambdaAdventurer.getTreasureNumbers());
    }

    private static Stream<Arguments> moveForwardAdventurerOrientation() {
        return Stream.of(
                Arguments.of("WhenAdventurer_North_1_1ThenAdventurer_North_1_0", Orientation.NORTH, 1, 0),
                Arguments.of("WhenAdventurer_East_1_1ThenAdventurer_East_1_0", Orientation.EAST, 2, 1),
                Arguments.of("WhenAdventurer_South_1_1ThenAdventurer_South_1_0", Orientation.SOUTH, 1, 2),
                Arguments.of("WhenAdventurer_West_1_1ThenAdventurer_West_1_0", Orientation.WEST, 0, 1)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("moveForwardAdventurerOrientation")
    void utest_move_forward(String name, Orientation orientation, int expectedHorizontalPos, int expectedVerticalPos) {
        Adventurer lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), orientation,new Position(1,1));
        lambdaAdventurer.moveForward();
        assertEquals(new Position(expectedHorizontalPos,expectedVerticalPos), lambdaAdventurer.getPosition());
    }

}