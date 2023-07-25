import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {


    @Test
    void testGetName() {
        Horse horse = new Horse("TestHorse", 0, 0);
        String actualName = horse.getName();
        assertEquals("TestHorse", actualName, "Returned name should match the expected name.");
    }

    @Test
    void testGetSpeed() {
        Horse horse = new Horse("Test", 2, 0);
        double actualSpeed = horse.getSpeed();
        assertEquals(2,actualSpeed, "Returned speed should match the expected speed.");
//        easy test
//        Horse horse2 = new Horse("Test", 2, 0);
//        assertEquals(2,3, "Returned speed should match the expectedspeed.");
    }

    @Test
    void testGetDistance() {
        Horse horse = new Horse("Test", 2, 3);
        double distance= horse.getDistance();
        assertEquals(3,distance, "Returned distance should match the expected distance.");
    }

    @Test
    public void getZeroDistanceTest()  {
        Horse horse = new Horse("Test",1);
        assertEquals(0,horse.getDistance(),"Returned distance should match the expected distance." );
    }

    @Test
    void testMoveParameter() {
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)){
            new Horse("Test", 1,1).move();
            mockStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 1.1, 3.5, 6.6})
    public void testGetRandomTest(double random){
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)){
            Horse horse = new Horse("Test", 2,3);
            mockStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            horse.move();
            assertEquals(3+2*random,horse.getDistance());
        }
    }
    @Test
    void testIfNameIsNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Horse(null, 0, 0);
        },  "Expected IllegalArgumentException to be thrown");
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"  ", "\t", "\n"})
    void testIfNameIsBlank(String input) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(input, 0, 0);
        }, "Expected IllegalArgumentException to be thrown");
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void testIfSpeedIsNegatative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Test", -1, 0);
        },  "Expected IllegalArgumentException to be thrown");
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    void testIfDistanceIsNegatative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Test", 1, -1);
        },  "Expected IllegalArgumentException to be thrown");
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }


}