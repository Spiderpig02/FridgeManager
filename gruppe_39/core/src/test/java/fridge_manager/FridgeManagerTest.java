package fridge_manager;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test Fridgemanager.
 */
public class FridgeManagerTest {
    
    /**
    * Simple constructortest
    */
    @Test
    public void testConstructor(){
        FridgeManager fridge=new FridgeManager(5,7);
        assertEquals(7, fridge.getFreezerMaxsize());
        assertEquals(5, fridge.getFridgeMaxsize());
    }

    /**
    * Testing that addfridge content works, and that it returns a copy of the list and not the real list
    */
    @Test
    public void testFridgeContents(){ 
        FridgeManager fridge=new FridgeManager(5,7);
        Food banan=new Food("Banan", 8, "Halvor", "10.02.2022");
        Food eple=new Food("Eple", 1, "Halvor", "15.02.2022");
        Food tomat=new Food("Tomat", 8, "Halvor", "12.02.2022");
        Food mugg=new Food("Mugg", 100, "Naturen", "10.02.2050");
        
        List<Food> fridgecontent=new ArrayList<>();

        fridgecontent.add(banan);
        fridgecontent.add(eple);
        fridgecontent.add(mugg);

        fridge.addFridgeContent(banan);
        fridge.addFridgeContent(eple);
        fridge.addFridgeContent(mugg);

        assertEquals(fridgecontent, fridge.getFridgeContents());

        List<Food> liste=fridge.getFridgeContents();
        
        liste.add(tomat);
        assertFalse(liste.equals(fridge.getFridgeContents()));
    }
    /**
    * Testing that addfreezercontent works, and that it returns a copy of the list and not the real list
    */
    @Test
    public void testFreezerContents(){ 
        FridgeManager fridge=new FridgeManager(5,7);
        
        Food brusboks=new Food("Brusboks", 1, "Halvor", "20.09.2022");
        Food batteri=new Food("Batteri", 10, "Halvor", "10.10.2036");
        Food tomat=new Food("Tomat", 8, "Halvor", "12.02.2022");

        List<Food> freezercontent=new ArrayList<>();

        freezercontent.add(brusboks);
        freezercontent.add(batteri);

        fridge.addFreezerContent(brusboks);
        fridge.addFreezerContent(batteri);

        assertEquals(freezercontent, fridge.getFreezerContents());

        List<Food> liste=fridge.getFridgeContents();
        
        liste.add(tomat);
        assertFalse(liste.equals(fridge.getFridgeContents()));
    }

    /**
    * Test for removeFreezercontent
    */
    @Test
    public void testRemoveFreezerContents(){ 
        FridgeManager fridge=new FridgeManager(5,7);
        
        Food brusboks=new Food("Brusboks", 1, "Halvor", "20.09.2022");
        Food batteri=new Food("Batteri", 10, "Halvor", "10.10.2036");
        Food tomat=new Food("Tomat", 8, "Halvor", "12.02.2022");

        List<Food> freezercontent=new ArrayList<>();

        freezercontent.add(brusboks);
        freezercontent.add(batteri);

        fridge.addFreezerContent(brusboks);
        fridge.addFreezerContent(batteri);
        
        fridge.addFreezerContent(tomat);
        fridge.removeFreezerContent(tomat);
        

        assertTrue(fridge.getFreezerContents().equals(freezercontent));
    }

    /**
    * Test for removeFrigdecontent
    */
    @Test
    public void testRemoveFridgeContents(){ 
        FridgeManager fridge=new FridgeManager(5,7);
        
        Food brusboks=new Food("Brusboks", 1, "Halvor", "20.09.2022");
        Food batteri=new Food("Batteri", 10, "Halvor", "10.10.2036");
        Food tomat=new Food("Tomat", 8, "Halvor", "12.02.2022");

        List<Food> fridgecontent=new ArrayList<>();

        fridgecontent.add(brusboks);
        fridgecontent.add(batteri);

        fridge.addFridgeContent(brusboks);
        fridge.addFridgeContent(batteri);
        
        fridge.addFridgeContent(tomat);
        fridge.removeFridgeContent(tomat);

        assertTrue(fridge.getFridgeContents().equals(fridgecontent));
    }
}