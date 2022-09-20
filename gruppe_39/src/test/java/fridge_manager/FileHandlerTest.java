package fridge_manager;

import org.junit.jupiter.api.BeforeAll;

public class FileHandlerTest {

    FridgeManager fridge;

    @BeforeAll
    public void startUp() {
        fridge = new FridgeManager();
        fridge.Frigde(50, 50);
    }
}
