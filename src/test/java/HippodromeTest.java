import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void getHorses() {
        List<Horse> h = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            h.add(new Horse("Test "+i, i));
        }
        Hippodrome hip=new Hippodrome(h);
        assertEquals(h,hip.getHorses(), "Returned horse list should match the expected horse list.");
    }
    @Test
    public void testMoveTest(){
        List<Horse> h=new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            h.add(mock(Horse.class));
        }
        new Hippodrome(h).move();
        for(Horse horse: h){
            verify(horse).move();
        }
    }

    @Test
    void testGetWinner() {
        List<Horse> h = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            h.add(new Horse("Test "+i, i, i + 0.1));
        }
        Hippodrome hippodrome = new Hippodrome(h);
        Horse winner = hippodrome.getWinner();
        assertEquals(h.get(29), winner, "Horse3 should be the winner with the largest distance.");
    }

    @Test
    void testIfHorsesIsNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        },  "Expected IllegalArgumentException to be thrown");
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void testIfListIsEmpty() {
        List<Horse> emptyList = new ArrayList<>();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(emptyList);
        },  "Expected IllegalArgumentException to be thrown");
        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }
}