package youcef.treasureProject.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import youcef.treasureProject.enums.Orientation;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OutputFileWriterTest {

    private static Stream<Arguments> OrientationToStringOrientations() {
        return Stream.of(
                Arguments.of("WhenOrientationNorthThenStringN", Orientation.NORTH, "N"),
                Arguments.of("WhenOrientationEastThenStringE", Orientation.EAST, "E"),
                Arguments.of("WhenOrientationSouthThenStringS", Orientation.SOUTH, "S"),
                Arguments.of("WhenOrientationWestThenStringO", Orientation.WEST, "O")
        );
    }

    @ParameterizedTest(name ="{0}")
    @MethodSource("OrientationToStringOrientations")
    void utest_orientation_to_string(String name, Orientation orientationToString, String expected){
        assertEquals(expected, OutputFileWriter.orientationtoString(orientationToString));
    }



}