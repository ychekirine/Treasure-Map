package youcef.treasureProject.model;
import youcef.treasureProject.enums.Orientation;

import java.util.Objects;
import java.util.Queue;


public class Adventurer {

    private final String name;
    private final Queue<Character> movement;
    private Orientation orientation;
    private Position position;
    private int TreasureNumbers;


    public Adventurer(String name, Queue<Character> movement, Orientation orientation, Position position) {
        this.name = name;
        this.movement = movement;
        this.orientation = orientation;
        this.position = position;
        this.TreasureNumbers = 0;
    }

    public void turnRight (){
        switch (this.orientation){
            case NORTH -> this.orientation = Orientation.EAST;
            case EAST -> this.orientation = Orientation.SOUTH;
            case SOUTH -> this.orientation = Orientation.WEST;
            case WEST -> this.orientation = Orientation.NORTH;
            default -> throw new UnsupportedOperationException("You can not turn right with the orientation " + this.orientation);
        }
    }

    public void turnLeft (){
        switch (this.orientation){
            case NORTH -> this.orientation = Orientation.WEST;
            case WEST -> this.orientation = Orientation.SOUTH;
            case SOUTH -> this.orientation = Orientation.EAST;
            case EAST -> this.orientation = Orientation.NORTH;
            default -> throw new UnsupportedOperationException("You can not turn left with the orientation " + this.orientation);
        }
    }

    public void addTreasure(){
        TreasureNumbers++;
    }

    public void moveForward (){
        switch (this.orientation){
            case NORTH -> this.position = new Position(this.getPosition().getHorizontal(), this.position.getVertical()-1);
            case EAST -> this.position = new Position(this.getPosition().getHorizontal()+1, this.position.getVertical());
            case SOUTH -> this.position = new Position(this.getPosition().getHorizontal(), this.position.getVertical()+1);
            case WEST -> this.position = new Position(this.getPosition().getHorizontal()-1, this.position.getVertical());
            default -> throw new UnsupportedOperationException("You can not move forward with the orientation " + this.orientation);
        }
    }

    public String getName() {
        return name;
    }

    public Queue<Character> getMovement() {
        return movement;
    }

    public int getTreasureNumbers() {
        return TreasureNumbers;
    }

    public void setTreasureNumbers(int treasureNumbers) {
        TreasureNumbers = treasureNumbers;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Adventurer{" +
                "name='" + name + '\'' +
                ", movement='" + movement + '\'' +
                ", TreasureNumbers=" + TreasureNumbers +
                ", orientation=" + orientation +
                ", position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adventurer that = (Adventurer) o;
        return TreasureNumbers == that.TreasureNumbers && Objects.equals(name, that.name) && Objects.equals(movement, that.movement) && orientation == that.orientation && position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, movement, TreasureNumbers, orientation, position);
    }

}
