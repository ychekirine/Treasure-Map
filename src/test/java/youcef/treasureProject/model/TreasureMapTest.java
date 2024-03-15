package youcef.treasureProject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import youcef.treasureProject.enums.Orientation;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreasureMapTest {

    private Adventurer lambdaAdventurer;
    private LinkedList<Adventurer> adventurerList;
    private List<Position> mountains;
    private Map<Position, Integer> treasures;


    @BeforeEach
    void setUp(){
        Position mountainPosition = new Position(1,1);
        Position treasurePosition = new Position(2,2);

        mountains = new ArrayList<>();
        mountains.add(mountainPosition);

        treasures = new HashMap<>();
        treasures.put(treasurePosition, 3);
    }

    private static Stream<Arguments> IsInMapRangeAdventurerPositions() {
        return Stream.of(
                Arguments.of("WhenAdventurer_North_2_2AndMapSize3_3ThenTrue", Orientation.NORTH, 2, 2, true),
                Arguments.of("WhenAdventurer_North_0_0AndMapSize3_3ThenFalse", Orientation.NORTH, 0, 0, false),
                Arguments.of("WhenAdventurer_East_0_0AndMapSize3_3ThenTrue", Orientation.EAST, 0, 0, true),
                Arguments.of("WhenAdventurer_East_2_2AndMapSize3_3ThenFalse", Orientation.EAST, 2, 2, false),
                Arguments.of("WhenAdventurer_South_0_0AndMapSize3_3ThenTrue", Orientation.SOUTH, 0, 0, true),
                Arguments.of("WhenAdventurer_South_2_2AndMapSize3_3ThenFalse", Orientation.SOUTH, 2, 2, false),
                Arguments.of("WhenAdventurer_West_0_0AndMapSize3_3ThenFalse", Orientation.WEST, 0, 0, false),
                Arguments.of("WhenAdventurer_West_2_2AndMapSize3_3ThenTrue", Orientation.WEST, 2, 2, true)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("IsInMapRangeAdventurerPositions")
    void utest_is_forward_in_map_range(String name, Orientation orientation, int horizontalPosition, int verticalPosition, boolean expect) {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), orientation,new Position(horizontalPosition,verticalPosition));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(expect, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    private static Stream<Arguments> IsMountainOrAdventurerForwardAdventurerPositions() {
        return Stream.of(
                Arguments.of("WhenAdventurer_North_1_2AndMountain1_1ThenTrue", Orientation.NORTH, 1, 2, true),
                Arguments.of("WhenAdventurer_North_2_2AndMountain1_1ThenFalse", Orientation.NORTH, 2, 2, false),
                Arguments.of("WhenAdventurer_East_1_2AndAdventurer2_2ThenTrue", Orientation.EAST, 1, 2, true),
                Arguments.of("WhenAdventurer_East_0_2AndAdventurer2_2ThenFalse", Orientation.EAST, 0, 2, false),
                Arguments.of("WhenAdventurer_South_2_1AndAdventurer2_2ThenTrue", Orientation.SOUTH, 2, 1, true),
                Arguments.of("WhenAdventurer_South_0_1AndAdventurer2_2ThenFalse", Orientation.SOUTH, 0, 1, false),
                Arguments.of("WhenAdventurer_West_2_1AndMountain1_1ThenTrue", Orientation.WEST, 2, 1, true),
                Arguments.of("WhenAdventurer_West_1_2AndMountain1_1ThenFalse", Orientation.WEST, 1, 2, false)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("IsMountainOrAdventurerForwardAdventurerPositions")
    void utest_is_mountain_or_adventurer_forward(String name, Orientation orientation, int horizontalPos, int verticalPos, boolean expect) {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), orientation,new Position(horizontalPos,verticalPos));
        Adventurer otherAdventurer  = new Adventurer("other", new LinkedList<>(), Orientation.SOUTH,new Position(2,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);
        adventurerList.add(otherAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        List<Position> adventurersPosition = adventurerList.stream().map(adventurer -> adventurer.getPosition()).collect(Collectors.toList());
        assertEquals(expect, treasureMap.isMountainOrAdventurerForward(firstAdventurer.getOrientation(),firstAdventurer.getPosition(),mountains,adventurersPosition));
    }


}