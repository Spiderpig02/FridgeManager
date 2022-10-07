package fridge_manager.IO;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.ValueNode;

import fridge_manager.Food;

/**
 * FoodDeserializier handles the task of deserializing json text to a Food class
 */
public class FoodDeserializer extends JsonDeserializer<Food> {

    /*
     * format: { "Name": "String", "Quantity": int, "Owner": "String",
     * "ExpirationDate": "String" }
     */
    /**
     * This methode handles the main task of getting json text inn and returning a
     * Food object if there was any, or null else
     */
    @Override
    public Food deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JacksonException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserializer((JsonNode) treeNode);
    }

    /**
     * A helping methode that alows us to deserialize jsonNode objects and returns
     * Food object if sucsessfull, else null
     */
    public Food deserializer(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            String name = null;
            int quantity = 0;
            String owner = null;
            String expirationDate = null;
            JsonNode nameNode = jsonNode.get("Name");
            if (nameNode instanceof TextNode) {
                name = nameNode.asText();
            }
            JsonNode quantityNode = jsonNode.get("Quantity");
            if (quantityNode instanceof ValueNode) {
                quantity = quantityNode.asInt();
            }
            JsonNode OwnerNode = jsonNode.get("Owner");
            if (OwnerNode instanceof TextNode) {
                owner = OwnerNode.asText();
            }
            JsonNode ExpirationDateNode = jsonNode.get("ExpirationDate");
            if (ExpirationDateNode instanceof TextNode) {
                expirationDate = ExpirationDateNode.asText();
            }

            return new Food(name, quantity, expirationDate, owner);

        }
        return null;
    }

}
