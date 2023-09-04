import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    public void nullNameException(){
       IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
       assertEquals("Horses cannot be null.",e.getMessage());
    }

    @Test
    public void emptyList(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.",e.getMessage());

    }

    @Test
    void getHorses() {
        //arrange
        List <Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        //act
        Hippodrome hippodrome = new Hippodrome(horses);
        //assert
        assertEquals(horses,hippodrome.getHorses());

    }
    @Test
    public void move()  {
        //arrange
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        //act
        new Hippodrome(horses).move();

        //assert
        for (Horse horse : horses){
            verify(horse).move();
        }

    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Pegas",1,2.9999);
        Horse horse2 = new Horse("Vova",1,3);
        Horse horse3 = new Horse("Eva",1,1);
        Horse horse4 = new Horse("Molnia",1,1);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4));
        assertSame(horse2,hippodrome.getWinner());
    }
}