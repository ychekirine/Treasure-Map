package youcef.treasureProject.io;

import org.junit.jupiter.api.Test;
import youcef.treasureProject.enums.Orientation;

import static org.junit.jupiter.api.Assertions.*;

class OutputFileWriterTest {

    @Test
    void orientation_to_string_should_return_n(){
        assertEquals("N", OutputFileWriter.orientationtoString(Orientation.NORTH));
    }

    @Test
    void orientation_to_string_should_return_s(){
        assertEquals("S", OutputFileWriter.orientationtoString(Orientation.SOUTH));
    }

    @Test
    void orientation_to_string_should_return_e(){
        assertEquals("E", OutputFileWriter.orientationtoString(Orientation.EAST));
    }

    @Test
    void orientation_to_string_should_return_o(){
        assertEquals("O", OutputFileWriter.orientationtoString(Orientation.WEST));
    }

}