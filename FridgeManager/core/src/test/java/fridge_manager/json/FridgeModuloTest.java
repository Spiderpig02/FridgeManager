package fridge_manager.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;

public class FridgeModuloTest {
    
    private static ObjectMapper mapper;
    Food paprika;
    Food banan;
    FridgeManager fridgemanager;
    String fridgeManagerWithTwoItems;

    /*
     * Setting up mapping from JSON
     */
    @BeforeAll
    public static void setUp(){
        mapper = new ObjectMapper();
        mapper.registerModule(new FridgeManagerModule());
    }
    
    /*
     * Initializing all variables
     */
    @BeforeEach
    void init() {
        paprika = new Food("Paprika",4, "30.01.2022","Ola");
        banan = new Food("Banan", 8, "10.02.2022", "Halvor");
        fridgemanager = new FridgeManager(3, 3);

        /*
        * format:
        * {
        * "FridgeMaxSize": "Int", "FridgeConters": [Food,...,...,...],
        * 
        * "FreezerMaxSize":, "Int", "FreezerContents": [Food,...,...,...]
        * }
        */
        fridgeManagerWithTwoItems =
        "{\"FridgeMaxSize\":3," + 
        "\"FridgeConters\":[{\"Name\":\"Banan\",\"Quantity\":8,\"Owner\":\"Halvor\",\"ExpirationDate\":\"10.02.2022\"}]," +
            
        "\"FreezerMaxSize\":3," + 
        "\"FreezerContents\":[{\"Name\":\"Paprika\",\"Quantity\":4,\"Owner\":\"Ola\",\"ExpirationDate\":\"30.01.2022\"}]}";
    }

    /*
     * Testing the Serializer
     */
    @Test
    public void testSerializers() throws JsonProcessingException{
        fridgemanager.addFreezerContent(paprika);
        fridgemanager.addFridgeContent(banan);

        try {
            assertEquals(fridgeManagerWithTwoItems,
            mapper.writeValueAsString(fridgemanager));

        } catch (JsonProcessingException e) {
            fail();
        }
    }
}