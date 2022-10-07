package fridge_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Unit test Fridgemanager.
 */
public class FridgeManagerTest {
    
    FridgeManager fridge;
    FridgeManager freezer;

    Food banan;
    Food eple;
    Food tomat;
    Food mugg;
    
    /**
    * Initilize Frigemanager and Food variables
    */
    @BeforeEach
    void init() {
        fridge=new FridgeManager(3,3);
        freezer=new FridgeManager(3,3);

        banan=new Food("Banan", 8, "10.02.2022", "Halvor");
        eple=new Food("Eple", 1, "15.02.2022", "Halvor");
        tomat=new Food("Tomat", 8, "12.02.2022", "Halvor");
        mugg=new Food("Mugg", 100, "30.02.2050", "Naturen");
    }

    
    /**
    * Simple constructortest
    */
    @Test
    public void testConstructor(){

        //Checking the size of the Freezer and Fridge
        assertEquals(3, fridge.getFreezerMaxsize());
        assertEquals(3, fridge.getFridgeMaxsize());

        
        //Triggering the IllegalArgumentException. Size need to be a positive number
        assertThrows(IllegalArgumentException.class, () -> {
            FridgeManager fridgeNeg =new FridgeManager(-2,7);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            FridgeManager freezerNeg =new FridgeManager(7,-2);
        });

    }


    /**
    * Testing that addfridge content works, and that it returns a copy of the list and not the real list
    */
    @Test
    public void testAddFridgeContents(){

        fridge.addFridgeContent(banan);
        fridge.addFridgeContent(eple);
      
        List<Food> fridgecontent=new ArrayList<>();
        fridgecontent.add(banan);
        fridgecontent.add(eple);
        
        //Check if the list fridgecontent equals the content in the Fridge
        assertEquals(fridgecontent, fridge.getFridgeContents());



        fridge.addFridgeContent(tomat);

        //Check if the list fridgecontent is different from the content in the Fridge
        assertFalse(fridgecontent.equals(fridge.getFridgeContents()));



        //Triggering the IllegalArgumentException. Too many object are added to the Fridge
        assertThrows(IllegalArgumentException.class, () -> {
            fridge.addFridgeContent(mugg);
        });
    }

    /**
    * Testing that addfreezercontent works, and that it returns a copy of the list and not the real list
    */
    @Test
    public void testAddFreezerContents(){ 

        freezer.addFreezerContent(banan);
        freezer.addFreezerContent(eple);
        
        List<Food> freezercontent=new ArrayList<>();
        freezercontent.add(banan);
        freezercontent.add(eple);

        //Check if the list freezercontent equals the content in the Freezer
        assertEquals(freezercontent, freezer.getFreezerContents());



        freezer.addFreezerContent(tomat);

        //Check if the list fridgecontent is different from the content in the Fridge
        assertFalse(freezercontent.equals(freezer.getFreezerContents()));
        


        //Triggering the IllegalArgumentException. Too many object are added to the Freezer
        assertThrows(IllegalArgumentException.class, () -> {
            freezer.addFreezerContent(mugg);
        });
    }

    /**
    * Test for removeFrigdecontent
    */
    @Test
    public void testRemoveFridgeContents(){ 
        
        fridge.addFridgeContent(banan);
        fridge.addFridgeContent(eple);
        fridge.removeFridgeContent(eple);

        List<Food> fridgecontent=new ArrayList<>();
        fridgecontent.add(banan);
        fridgecontent.add(eple);

        //Check if the list fridgecontent is different from the content in the Fridge
        assertFalse(fridge.getFridgeContents().equals(fridgecontent));

        //Trying to delete an object that is not in the fridge
        fridge.removeFridgeContent(mugg);
    }

    /**
    * Test for removeFreezercontent
    */
    @Test
    public void testRemoveFreezerContents(){
        
        freezer.addFreezerContent(banan);
        freezer.addFreezerContent(eple);
        freezer.removeFreezerContent(eple);

        List<Food> freezercontent=new ArrayList<>();
        freezercontent.add(banan);
        freezercontent.add(eple);      

        //Check if the list freezercontent is different from the content in the Freezer
        assertFalse(freezer.getFreezerContents().equals(freezercontent));

        //Trying to delete an object that is not in the freezer
        freezer.removeFreezerContent(mugg);
    }
}