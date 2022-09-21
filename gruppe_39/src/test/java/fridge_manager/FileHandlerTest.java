package fridge_manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import fridge_manager.IO.FileHandler;

public class FileHandlerTest {

    static FridgeManager fridge;

    @BeforeAll
    public static void startUp() {
        fridge = new FridgeManager(50, 50);
    }

    @Test
    public void testFileSavingAndRetriving() {
        fridge.addFreezerContent(new Food("Pizza", 1, "Mr.meseeks", "2022.12.12"));
        fridge.addFridgeContent(new Food("Pizza", 1, "Mr.meseeks", "2022.12.12"));

        FileHandler fileHandler = new FileHandler();
        fileHandler.saveObject(fridge);
        assertEquals(fridge, fileHandler.loadFridgeManager());
    }
}
