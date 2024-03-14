package youcef.treasureProject.io;

import org.junit.jupiter.api.Test;
import youcef.treasureProject.enums.Orientation;
import youcef.treasureProject.model.Adventurer;
import youcef.treasureProject.model.Position;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class EntryFileParserTest {

    private EntryFileParser entryFileParser = new EntryFileParser();

    @Test
    void adventurer_parser_should_return_exception() {

        Throwable exception = assertThrows(
                    IllegalArgumentException.class, () -> {
                        String adventurerString = "Lara - 1 - 1 - S - AADADAGGA";
                        entryFileParser.adventurerParser(adventurerString);
                    }
            );

            assertEquals("Adventurer must have exaclty 6 args !", exception.getMessage());
    }

    @Test
    void adventurer_parser_should_return_new_adventurer(){

        Queue<Character> movement = entryFileParser.stringToQueueMovement("AADADAGGA");
        Orientation orientation = entryFileParser.stringToOrientation("S");
        Adventurer lara = new Adventurer("Lara", movement, orientation,new Position(1,1));

        assertEquals(lara, entryFileParser.adventurerParser("A - Lara - 1 - 1 - S - AADADAGGA"));
    }

    @Test
    void map_parser_should_return_map_size_array(){

        int[] mapSize = {3,3};
        int[] mapParserSize = EntryFileParser.mapParser("C - 3 - 3");

        assertEquals(mapSize[0], mapParserSize[0]);
        assertEquals(mapSize[1], mapParserSize[1]);
    }

    @Test
    void map_parser_should_return_exception(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    String mountainString = "3 - 3";
                    EntryFileParser.mapParser(mountainString);
                }
        );

        assertEquals("Map must have exaclty 3 args !", exception.getMessage());
    }

    @Test
    void mountain_parser_should_return_exception(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    String mountainString = "2 - 2";
                    entryFileParser.mountainParser(mountainString);
                }
        );

        assertEquals("Mountain must have exaclty 3 args !", exception.getMessage());
    }

    @Test
    void mountain_parser_should_return_mountain_position(){

        assertEquals(new Position(2,2), entryFileParser.mountainParser("M - 2 - 2"));
    }

    @Test
    void treasure_parser_should_return_treasure_size_array(){

        int[] treasureSize = {1,1,3};
        int[] treasureParserSize = entryFileParser.treasureParser("T - 1 - 1 - 3");

        assertEquals(treasureSize[0], treasureParserSize[0]);
        assertEquals(treasureSize[1], treasureParserSize[1]);
        assertEquals(treasureSize[2], treasureParserSize[2]);
    }

    @Test
    void treasure_parser_should_return_argument_exception(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    String treasureString = "T - 1 - 1 - 0";
                    entryFileParser.treasureParser(treasureString);
                }
        );

        assertEquals("Treasure numbers must be greather than 0", exception.getMessage());
    }

    @Test
    void treasure_parser_should_return_treasure_number_exception(){
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    String treasureString = "3 - 3 - 3";
                    entryFileParser.treasureParser(treasureString);
                }
        );

        assertEquals("Treasure must have exaclty 4 args !", exception.getMessage());
    }

    @Test
    void string_to_orientation_should_return_north() {
        assertEquals(Orientation.NORTH,entryFileParser.stringToOrientation("N"));
    }

    @Test
    void string_to_orientation_should_return_south() {
        assertEquals(Orientation.SOUTH,entryFileParser.stringToOrientation("S"));
    }

    @Test
    void string_to_orientation_should_return_east() {
        assertEquals(Orientation.EAST,entryFileParser.stringToOrientation("E"));
    }

    @Test
    void string_to_orientation_should_return_west() {
        assertEquals(Orientation.WEST,entryFileParser.stringToOrientation("O"));
    }

    @Test
    void string_to_orientation_should_return_exception(){
        Throwable exception = assertThrows(
                UnsupportedOperationException.class, () -> {
                    String stringOrientation = "A";
                    entryFileParser.stringToOrientation(stringOrientation);
                }
        );
        assertEquals("A is not an orientation", exception.getMessage());
    }

    @Test
    void string_to_queue_movement_should_return_aga() {
        Queue<Character> movement = new LinkedList<>();
        movement.add('A');
        movement.add('G');
        movement.add('A');

        assertEquals(movement, entryFileParser.stringToQueueMovement("AGA"));
    }

    @Test
    void string_to_queue_movement_should_return_agaada() {
        Queue<Character> movement = new LinkedList<>();
        movement.add('A');
        movement.add('G');
        movement.add('A');
        movement.add('A');
        movement.add('D');
        movement.add('A');

        assertEquals(movement, entryFileParser.stringToQueueMovement("AGAADA"));
    }

    @Test
    void string_to_queue_movement_should_return_adg_filtered() {
        Queue<Character> movement = new LinkedList<>();
        movement.add('A');
        movement.add('D');
        movement.add('G');

        assertEquals(movement, entryFileParser.stringToQueueMovement("AEZDRGHFZEZEE"));
    }

    @Test
    void string_to_queue_movement_should_return_agd_filtered() {
        Queue<Character> movement = new LinkedList<>();
        movement.add('A');
        movement.add('D');
        movement.add('G');

        assertNotEquals(movement, entryFileParser.stringToQueueMovement("AEZGRDHFZEZEE"));
    }


}