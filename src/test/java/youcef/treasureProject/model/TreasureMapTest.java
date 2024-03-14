package youcef.treasureProject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import youcef.treasureProject.enums.Orientation;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    void is_in_map_range_north_2_2_should_return_true() {

        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(2,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(true, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_north_1_0_should_return_false() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(1,0));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(false, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_east_0_0_should_return_true() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(0,0));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(true, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_east_2_2_should_return_false() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(2,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(false, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_south_0_0_should_return_true() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(0,0));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(true, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_south_2_2_should_return_false() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(2,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(false, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_west_0_0_should_return_false() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(0,0));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(false, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_in_map_range_west_2_2_should_return_false() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(2,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        assertEquals(true, treasureMap.isForwardInMapRange(firstAdventurer.getOrientation(), firstAdventurer.getPosition()));
    }

    @Test
    void is_mountain_or_adventurer_forward_should_return_true_mountain_north_case() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.NORTH,new Position(1,2));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        List<Position> adventurersPosition = adventurerList.stream().map(adventurer -> adventurer.getPosition()).collect(Collectors.toList());
        assertEquals(true, treasureMap.isMountainOrAdventurerForward(firstAdventurer.getOrientation(),firstAdventurer.getPosition(),mountains,adventurersPosition));
    }

    @Test
    void is_mountain_or_adventurer_forward_should_return_true_adventurer_south_case() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.SOUTH,new Position(2,2));
        Adventurer adventurerSouthOfLambda  = new Adventurer("South of lambda", new LinkedList<>(), Orientation.SOUTH,new Position(2,3));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);
        adventurerList.add(adventurerSouthOfLambda);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        List<Position> adventurersPosition = adventurerList.stream().map(adventurer -> adventurer.getPosition()).collect(Collectors.toList());
        assertEquals(true, treasureMap.isMountainOrAdventurerForward(firstAdventurer.getOrientation(),firstAdventurer.getPosition(),mountains,adventurersPosition));
    }

    @Test
    void is_mountain_or_adventurer_forward_should_return_false_east_case() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.EAST,new Position(2,2));
        Adventurer otherAdventurer  = new Adventurer("other", new LinkedList<>(), Orientation.SOUTH,new Position(2,3));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);
        adventurerList.add(otherAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        List<Position> adventurersPosition = adventurerList.stream().map(adventurer -> adventurer.getPosition()).collect(Collectors.toList());
        assertEquals(false, treasureMap.isMountainOrAdventurerForward(firstAdventurer.getOrientation(),firstAdventurer.getPosition(),mountains,adventurersPosition));
    }

    @Test
    void is_mountain_or_adventurer_forward_should_return_false_west_case() {
        lambdaAdventurer = new Adventurer("lambda", new LinkedList<>(), Orientation.WEST,new Position(2,2));
        Adventurer otherAdventurer  = new Adventurer("other", new LinkedList<>(), Orientation.SOUTH,new Position(2,3));
        adventurerList = new LinkedList<>();
        adventurerList.add(lambdaAdventurer);
        adventurerList.add(otherAdventurer);

        TreasureMap treasureMap = new TreasureMap(3,3,adventurerList,mountains,treasures);
        Adventurer firstAdventurer = treasureMap.getAdventurers().get(0);
        List<Position> adventurersPosition = adventurerList.stream().map(adventurer -> adventurer.getPosition()).collect(Collectors.toList());
        assertEquals(false, treasureMap.isMountainOrAdventurerForward(firstAdventurer.getOrientation(),firstAdventurer.getPosition(),mountains,adventurersPosition));
    }

}