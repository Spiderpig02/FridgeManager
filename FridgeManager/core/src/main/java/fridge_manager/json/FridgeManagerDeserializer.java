package fridge_manager.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;

/*
 * FridgeManagerDeserializer class handles the deserializing of json to a FridgeManager object
 */
public class FridgeManagerDeserializer extends JsonDeserializer<FridgeManager> {

    private FoodDeserializer foodDeserializer = new FoodDeserializer();

    /*
     * format: { "FridgeMaxSize": "Int", "FridgeContents": [Food,...,...,...],
     * "FreezerMaxSize":
     * "Int",
     * "FreezerContents": [Food,...,...,...] }
     */
    /*
     * The method takes in json text and returns a FridgeManager object if
     * successful, null otherwise s
     */
    @Override
    public FridgeManager deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /*
     * This is a private helping method for splitting up the complexiy. Returns
     * FridgeManager object if successful, null otherwise
     */
    private FridgeManager deserialize(JsonNode treeNode) {
        if (treeNode instanceof ObjectNode) {
            FridgeManager fridgeManager = null;
            JsonNode FridgeMaxSizeNode = treeNode.get("FridgeMaxSize");
            JsonNode FreezerMaxSizeNode = treeNode.get("FreezerMaxSize");
            if (!(FridgeMaxSizeNode instanceof ValueNode) && !(FreezerMaxSizeNode instanceof ValueNode)) {
                return null;
            }
            else{
                fridgeManager = new FridgeManager(FridgeMaxSizeNode.asInt(), FreezerMaxSizeNode.asInt());
            }

            JsonNode FridgeContentsNode = treeNode.get("FridgeContents");
            JsonNode FreezerContentsNode = treeNode.get("FreezerContents");

            boolean hasFridgeContents = FridgeContentsNode instanceof ArrayNode;
            boolean hasFreezerContents = FreezerContentsNode instanceof ArrayNode;

            if (hasFridgeContents) {
                for (JsonNode elementNode : ((ArrayNode) FridgeContentsNode)) {
                    Food food = foodDeserializer.deserializer(elementNode);
                    if (food != null) {
                        fridgeManager.addFridgeContent(food);
                    }
                }
            }
            if (hasFreezerContents) {
                for (JsonNode elementNode : ((ArrayNode) FreezerContentsNode)) {
                    Food food = foodDeserializer.deserializer(elementNode);
                    if (food != null) {
                        fridgeManager.addFreezerContent(food);
                    }
                }
            }
            return fridgeManager;
        }
        return null;
    }

}
