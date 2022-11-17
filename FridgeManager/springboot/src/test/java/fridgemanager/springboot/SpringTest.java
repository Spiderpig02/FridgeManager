package fridgemanager.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

public class SpringTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(FileHandler.createJacsonModule());
        DeleteLocalSave.deleteFile();
    }

    private String todoUrl(String... segments) {
        String url = "/" + FridgeManagerController.FridgeManagerServicePath;
        for (String segment : segments) {
            url = url + "/" + segment;
        }
        return url;
    }

    @Test
    public void initTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(todoUrl())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            FridgeManager fridgeManager = objectMapper.readValue(result.getResponse().getContentAsString(),
                    FridgeManager.class);
            System.out.println(fridgeManager);
            assertEquals(fridgeManager.getFridgeMaxsize(), 25);
            assertEquals(fridgeManager.getFreezerMaxsize(), 25);
            assertNotEquals(fridgeManager.getFridgeContents(), null);
            assertNotEquals(fridgeManager.getFreezerContents(), null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getIntTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(todoUrl("getFrigdeMaxsize"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            FridgeManager fridgeManager = objectMapper.readValue(result.getResponse().getContentAsString(),
                    FridgeManager.class);
            System.out.println(fridgeManager);
            assertEquals(fridgeManager.getFridgeMaxsize(), 25);
            assertEquals(fridgeManager.getFreezerMaxsize(), 25);
            assertNotEquals(fridgeManager.getFridgeContents(), null);
            assertNotEquals(fridgeManager.getFreezerContents(), null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
