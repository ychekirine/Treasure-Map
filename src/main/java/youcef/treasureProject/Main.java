package youcef.treasureProject;


import youcef.treasureProject.io.EntryFileParser;
import youcef.treasureProject.io.OutputFileWriter;
import youcef.treasureProject.model.TreasureMap;


public class Main {

    public static void main(String[] args) {
        TreasureMap treasureMap;
        EntryFileParser parser = new EntryFileParser();
        if (args.length == 0) {
             treasureMap= parser.parse("src/main/resources/input.txt").orElseThrow();
        }
        else {
            treasureMap = parser.parse("src/main/resources/" + args[0]).orElseThrow();
        }
        treasureMap.movementSimulation();
        OutputFileWriter.writeFile(treasureMap, "src/main/resources/output.txt");

    }
}
