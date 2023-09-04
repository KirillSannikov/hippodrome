import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void nullNameException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ","\t\t","\n\n\n\n"})
    public void blankNameException(String name){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name,1,1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }
    @Test
    public void negativeSpeedException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Kolya",-51,1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceException(){
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("Kolya",5,-2));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }
    @Test
    public void getName() {
        Horse horse = new Horse("Kolya",5,1);

        assertEquals("Kolya", horse.getName());
    }
    @Test
    public void getDistance()  {
        Horse horse = new Horse("Kolya",1,76);

        assertEquals(76, horse.getDistance());
    }
    @Test
    public void getSpeed()  {
        Horse horse = new Horse("Kolya",185,1);

        assertEquals(185, horse.getSpeed());
    }
    @Test
    public void zeroDistance(){
        Horse horse = new Horse("Kolya",185);
        assertEquals(0,horse.getDistance());
    }

    @Test
   public void moveRandom() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("Kolya",185,1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }


    }
    @ParameterizedTest
    @ValueSource(doubles = {0.1,0.2,0.5,0.9,1.0,999.999,0.0})
    void move(double random){
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("Kolya",31,283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);

            horse.move();

            assertEquals(283 + 31 * random,horse.getDistance());
        }
    }


}