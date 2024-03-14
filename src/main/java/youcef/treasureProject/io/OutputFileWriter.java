package youcef.treasureProject.io;

import youcef.treasureProject.enums.Orientation;
import youcef.treasureProject.model.Position;
import youcef.treasureProject.model.TreasureMap;

import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {

    public static String SEPERATOR = " - ";

    public static void writeFile (TreasureMap treasureMap, String fileName){

        try (FileWriter fileWriter = new FileWriter(fileName)){
            StringBuilder fileWritterSB = new StringBuilder();
            fileWritterSB.append("C").append(SEPERATOR).append(treasureMap.getHorizontalSize()).append(SEPERATOR).append(treasureMap.getVerticalSize()).append("\n");

            treasureMap.getMountains().stream().forEach(mountain ->
                    fileWritterSB.append("M").append(SEPERATOR).append(mountain.getHorizontal()).append(SEPERATOR).append(mountain.getVertical()).append("\n"));

            treasureMap.getTreasures().forEach((pos,num) ->
                    fileWritterSB.append("T").append(SEPERATOR).append(pos.getHorizontal()).append(SEPERATOR).append(pos.getVertical()).append(SEPERATOR).append(num).append("\n"));

            treasureMap.getAdventurers().stream().forEach(adventurer ->
                    fileWritterSB.append("A").append(SEPERATOR).append(adventurer.getName()).append(SEPERATOR)
                            .append(adventurer.getPosition().getHorizontal()).append(SEPERATOR).append(adventurer.getPosition().getVertical()).append(SEPERATOR)
                            .append(orientationtoString(adventurer.getOrientation())).append(SEPERATOR).append(adventurer.getTreasureNumbers()).append("\n"));
            fileWriter.write(fileWritterSB.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String orientationtoString (Orientation orientation){

        switch (orientation){
            case SOUTH -> {return "S";}
            case NORTH -> {return "N";}
            case WEST -> {return "O";}
            case EAST -> {return "E";}
            default -> throw new UnsupportedOperationException(orientation + " is not an orientation");
        }

    }
}
