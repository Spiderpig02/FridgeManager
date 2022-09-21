package fridge_manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    }
}
