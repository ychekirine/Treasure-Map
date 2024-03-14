package youcef.treasureProject.model;


import java.util.Objects;

public class Position {

    private final int horizontal;
    private final int vertical;

    public Position(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public int getVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return horizontal == position.horizontal && vertical == position.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    @Override
    public String toString() {
        return "Position{" +
                "horizontal=" + horizontal +
                ", vertical=" + vertical +
                '}';
    }
}
