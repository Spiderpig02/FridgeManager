package fridge_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test Food.
 */
public class FoodTest {

    /**
    * Simple constructortest
    */
    @Test
    public void testConstructor(){
        Food food = new Food("Paprika",4, "30.01.2022","Ola");
        assertEquals(4,food.getQuantity());
        assertEquals("Paprika",food.getName());
        assertEquals("Ola",food.getOwner());
        assertEquals("30.01.2022",food.getExpirationDate());
    }

    @Test
    public void testChangeQuantity(){
        Food food = new Food("Paprika",4, "30.01.2022","Ola");
        assertEquals(4, food.getQuantity());

        food.changeQuantity(1500);

        assertEquals(1500, food.getQuantity());
    }
}
