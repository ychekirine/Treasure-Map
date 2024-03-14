package youcef.treasureProject.io;

import youcef.treasureProject.enums.Orientation;
import youcef.treasureProject.model.Adventurer;
import youcef.treasureProject.model.Position;
import youcef.treasureProject.model.TreasureMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EntryFileParser {


    public Optional<TreasureMap> parse (String filePath){

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            int[] mapSize;
            int[] treasureArray;
            int horizontalSize = 0;
            int verticalSize = 0;
            LinkedList<Adventurer> adventurers = new LinkedList<>();
            List<Position> mountains = new ArrayList<>();
            Map<Position,Integer> treasures = new HashMap<>();
            String line;

            while ((line = reader.readLine()) != null){
                String firstElementInLine = (line.split(" - ") [0]);
                switch (firstElementInLine){
                    case "C" -> {mapSize = mapParser(line); horizontalSize = mapSize[0]; verticalSize = mapSize[1];}
                    case "A" -> {adventurers.add(adventurerParser(line));}
                    case "M" -> {mountains.add(mountainParser(line));}
                    case "T" -> {treasureArray = treasureParser(line);
                                treasures.put(new Position(treasureArray[0], treasureArray[1]), treasureArray[2]);}
                }
            }

            TreasureMap treasureMap = new TreasureMap(horizontalSize, verticalSize, adventurers, mountains, treasures);
            if (treasureMap.isTreasureMapValid()){
                return Optional.of(treasureMap);
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String parseName (String name){
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Received an empty adventurer name");
        }
        return name;
    }

    private Position parsePosition (String horizontalPos, String verticalPos){
        if (horizontalPos == null || verticalPos == null || horizontalPos.isEmpty() || verticalPos.isEmpty()){
            throw new IllegalArgumentException("Received an empty adventurer position");
        }
        int horizontalPosition = Integer.valueOf(horizontalPos);
        int verticalPosition = Integer.valueOf(verticalPos);
        if (horizontalPosition < 0 || verticalPosition < 0){
            throw new IllegalArgumentException("Horizontal and vertical position must be greater than 0");
        }
        return new Position(horizontalPosition, verticalPosition);
    }

    private Orientation parseOrientation (String orientation){
        if (orientation == null || orientation.length() != 1) {
            throw new IllegalArgumentException("Received an invalid orientation:" + orientation);
        }
        return stringToOrientation(orientation);
    }

    private Queue<Character> parseMovements (String movement){
        if (movement == null || movement.isEmpty()) {
            throw new IllegalArgumentException("Received an empty adventurer name");
        }
        return stringToQueueMovement(movement);
    }

    // TODO Need to verify all args to parse return an Adventurer + maybe use optional
    //  + ABOVE ALL CHANGE STRUCTURE OF ALL PARSERS TO TAKE THE TREASURE MAP
    public Adventurer adventurerParser (String line){
        String[] adventurerInfoAsArray = line.split(" - ");
        if (adventurerInfoAsArray.length != 6){
            throw new IllegalArgumentException("Adventurer must have exaclty 6 args !");
        }
        else {
            String name = parseName(adventurerInfoAsArray[1]);
            Position adventurerPosition = parsePosition(adventurerInfoAsArray[2], adventurerInfoAsArray[3]);
            Orientation adventurerOrientation = parseOrientation(adventurerInfoAsArray[4]);
            Queue<Character> adventurerMovement = parseMovements(adventurerInfoAsArray[5]);

            return new Adventurer(name, adventurerMovement, adventurerOrientation, adventurerPosition);
        }
    }

    public static int[] mapParser (String line){
        String[] mapInfoAsArray = line.split(" - ");
        if (mapInfoAsArray.length != 3){
            throw new IllegalArgumentException("Map must have exaclty 3 args !");
        }
        int width = Integer.valueOf(mapInfoAsArray[1]);
        int length = Integer.valueOf(mapInfoAsArray[2]);
        if (width < 0 || length < 0){
            throw new IllegalArgumentException("Width and length must be greater than 0");
        }
        return new int[]{width, length};
    }

    public Position mountainParser (String line){
        String[] mountainInfoAsArray = line.split(" - ");
        if (mountainInfoAsArray.length != 3){
            throw new IllegalArgumentException("Mountain must have exaclty 3 args !");
        }
        return parsePosition(mountainInfoAsArray[1], mountainInfoAsArray[2]);
    }

    public int[] treasureParser(String line){
        String[] treasureInfoAsArray = line.split(" - ");
        if (treasureInfoAsArray.length != 4){
            throw new IllegalArgumentException("Treasure must have exaclty 4 args !");
        }
        int horizontalPos = Integer.valueOf(treasureInfoAsArray[1]);
        int verticalPos = Integer.valueOf(treasureInfoAsArray[2]);
        int treasureNumbers = Integer.valueOf(treasureInfoAsArray[3]);

        if (treasureNumbers <= 0){
            throw new IllegalArgumentException ("Treasure numbers must be greather than 0");
        }
        if (horizontalPos < 0 || verticalPos < 0){
            throw new IllegalArgumentException("Horizontal and vertical position must be greater than 0");
        }
        return new int[]{horizontalPos, verticalPos, treasureNumbers};
    }

    public Orientation stringToOrientation (String stringOrientation){

        switch (stringOrientation){
            case "S" -> {return Orientation.SOUTH;}
            case "N" -> {return Orientation.NORTH;}
            case "O" -> {return Orientation.WEST;}
            case "E" -> {return Orientation.EAST;}
            default -> throw new UnsupportedOperationException(stringOrientation + " is not an orientation");
        }

    }

    public Queue<Character> stringToQueueMovement (String stringMovement){
        return stringMovement.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c.equals('A') || c.equals('G') || c.equals('D'))
                .collect(Collectors.toCollection(LinkedList::new));
    }


}
