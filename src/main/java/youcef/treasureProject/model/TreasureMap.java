package youcef.treasureProject.model;

import youcef.treasureProject.enums.Orientation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreasureMap {

    private final int horizontalSize;
    private final int verticalSize;
    private final LinkedList<Adventurer> adventurers;
    private final List<Position> mountains;
    private Map<Position,Integer> treasures;

    public TreasureMap(int horizontalSize, int verticalSize, LinkedList<Adventurer> adventurers, List<Position> mountains, Map<Position,Integer> treasures) {
        this.horizontalSize = horizontalSize;
        this.verticalSize = verticalSize;
        this.adventurers = adventurers;
        this.mountains = mountains;
        this.treasures = treasures;
    }

    public boolean isPossibleToMoveForward (Orientation orientation, Position position, List<Position> mountains, List<Position> adventurersPosition){
        return isForwardInMapRange(orientation, position) && !isMountainOrAdventurerForward(orientation, position, mountains, adventurersPosition);
    }

    public boolean isMountainOrAdventurerForward (Orientation orientation, Position position, List<Position> mountains, List<Position> adventurersPosition){
        Position futurPosition;
        switch (orientation) {
            case NORTH -> {futurPosition = new Position(position.getHorizontal(),position.getVertical() - 1);
                            return mountains.contains(futurPosition) || adventurersPosition.contains(futurPosition);}

            case EAST -> {futurPosition = new Position(position.getHorizontal() +1, position.getVertical());
                            return mountains.contains(futurPosition) || adventurersPosition.contains(futurPosition);}

            case SOUTH -> {futurPosition = new Position(position.getHorizontal(), position.getVertical() + 1);
                            return mountains.contains(futurPosition) || adventurersPosition.contains(futurPosition);}

            case WEST -> {futurPosition = new Position(position.getHorizontal() -1, position.getVertical());
                            return mountains.contains(futurPosition) || adventurersPosition.contains(futurPosition);}

            default -> throw new UnsupportedOperationException("Move Forward with orientation " + orientation + " and position" + position + "can not be done");
        }

    }

    public boolean isInMapRange (Position position) {
        return position.getHorizontal()  >= 0 && position.getHorizontal() <= horizontalSize -1
                && position.getVertical() >= 0 && position.getVertical() <= verticalSize - 1 ;
    }

    public boolean isForwardInMapRange (Orientation orientation, Position position) {
        switch (orientation){
            case NORTH -> {return isInMapRange(new Position(position.getHorizontal(), position.getVertical() -1));}
            case EAST -> {return isInMapRange(new Position(position.getHorizontal() + 1, position.getVertical()));}
            case SOUTH -> {return isInMapRange(new Position(position.getHorizontal(), position.getVertical()+1));}
            case WEST -> {return isInMapRange(new Position(position.getHorizontal() -1, position.getVertical()));}
            default -> throw new UnsupportedOperationException("Move Forward with orientation " + orientation + " can not be done");
        }
    }

    public void movementSimulation (){

        while (!adventurers.stream().allMatch(adventurer -> adventurer.getMovement().isEmpty())) {
            for (Adventurer adventurer : this.adventurers) {
                char movement = adventurer.getMovement().poll();
                if (movement == 'A') {
                    List<Position> adventurersPosition = adventurers.stream().map(a -> a.getPosition()).collect(Collectors.toList());
                    if (isPossibleToMoveForward(adventurer.getOrientation(), adventurer.getPosition(), mountains, adventurersPosition)) {
                        adventurer.moveForward();
                        Position adventurerPosition = adventurer.getPosition();
                        if (treasures.containsKey(adventurerPosition)){
                            int treasureNumberAtAdventurerPosition = treasures.get(adventurerPosition);
                            adventurer.addTreasure();
                            treasures.put(adventurerPosition, --treasureNumberAtAdventurerPosition);
                            if (treasureNumberAtAdventurerPosition == 0){
                                treasures.remove(adventurerPosition, treasureNumberAtAdventurerPosition);
                            }
                        }
                    }
                } else if (movement == 'D') {
                    adventurer.turnRight();
                } else if (movement == 'G') {
                    adventurer.turnLeft();
                }
            }
        }
    }

    public boolean isTreasureMapValid(){
        if (horizontalSize <= 0 || verticalSize <= 0){
            throw new IllegalArgumentException("horizontal size " + horizontalSize +  " and vertical size " + verticalSize + " must be greater than 0");
        }
        for (Adventurer adventurer: adventurers) {
            if (!isInMapRange(adventurer.getPosition())){
                throw new IllegalArgumentException("Adventurer " + adventurer + " must be in range map " +
                        "for map: [" +horizontalSize + "," + verticalSize + "]");
            }
        }
        for (Position mountain: mountains){
            if (!isInMapRange(mountain)){
                throw new IllegalArgumentException("Mountain " + mountain + " must be in range map " +
                        "for map: [" +horizontalSize + "," + verticalSize + "]");
            }
        }
        for (Position treasure: treasures.keySet().stream().collect(Collectors.toList())) {
            if (!isInMapRange(treasure)){
                throw new IllegalArgumentException("Treasure " + treasure + " must be in range map " +
                        "for map: [" +horizontalSize + "," + verticalSize + "]");
            }
        }
        return true;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public LinkedList<Adventurer> getAdventurers() {
        return adventurers;
    }

    public List<Position> getMountains() {
        return mountains;
    }

    public Map<Position,Integer> getTreasures() {
        return treasures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreasureMap that = (TreasureMap) o;
        return horizontalSize == that.horizontalSize && verticalSize == that.verticalSize && Objects.equals(adventurers, that.adventurers) && Objects.equals(mountains, that.mountains) && Objects.equals(treasures, that.treasures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontalSize, verticalSize, adventurers, mountains, treasures);
    }

    @Override
    public String toString() {
        return "TreasureMap{" +
                "horizontalSize=" + horizontalSize +
                ", verticalSize=" + verticalSize +
                ", adventurers=" + adventurers +
                ", mountains=" + mountains +
                ", treasures=" + treasures +
                '}';
    }
}
