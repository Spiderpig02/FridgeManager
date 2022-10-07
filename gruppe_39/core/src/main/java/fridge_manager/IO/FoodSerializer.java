package fridge_manager.IO;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import fridge_manager.Food;

/**
 * FoodSerializer class handles the serializing of the Food object
 */
public class FoodSerializer extends JsonSerializer<Food> {

    /*
     * format: { "Name": "String", "Quantity": int, "Owner": "String",
     * "ExpirationDate": "String" }
     */
    /**
     * The method for converting Food objects to a jason format, with the expected
     * outcome above this coment
     */
    @Override
    public void serialize(Food food, JsonGenerator jGenerator, SerializerProvider sProvider) throws IOException {

        jGenerator.writeStartObject();

        jGenerator.writeStringField("Name", food.getName());
        jGenerator.writeNumberField("Quantity", food.getQuantity());
        jGenerator.writeStringField("Owner", food.getOwner());
        jGenerator.writeStringField("ExpirationDate", food.getExpirationDate());

        jGenerator.writeEndObject();

    }

}
