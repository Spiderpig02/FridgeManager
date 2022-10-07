package fridge_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Unit test Food.
 */
public class FoodTest {

    Food paprika;

    /**
    * Initilize Food variables
    */
    @BeforeEach
    void init() {
        paprika = new Food("Paprika",4, "30.01.2022","Ola");
    }

    /**
    * Simple constructortest
    */
    @Test
    public void testConstructor(){

        //Checking if the initilized variables are correct
        assertEquals(4,paprika.getQuantity());
        assertEquals("Paprika",paprika.getName());
        assertEquals("Ola",paprika.getOwner());
        assertEquals("30.01.2022",paprika.getExpirationDate());



        //Testing IllegalArgumentException for negative input
        assertThrows(IllegalArgumentException.class, () -> {
            Food laks = new Food("Laks",-4, "31.01.2022","Ola");
        });

    }

    /**
    * Testing changeQuantity
    */
    @Test
    public void testChangeQuantity(){

        paprika.changeQuantity(1500);

        //Checking if the quantity is changed
        assertEquals(1500, paprika.getQuantity());



        //Testing IllegalArgumentException for negative input
        assertThrows(IllegalArgumentException.class, () -> {
            paprika.changeQuantity(-2);
        });

    }

    /**
    * Testing toString method
    */
    @Test
    public void testToString(){
        assertEquals(paprika.toString(), paprika.getQuantity()+" "+paprika.getName()+", "+paprika.getOwner()+" sin, går ut: "+paprika.getExpirationDate());
    }
}
