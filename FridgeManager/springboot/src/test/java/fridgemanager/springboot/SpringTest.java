package fridgemanager.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { FridgeManagerApp.class, FridgeManagerService.class, FridgeManagerController.class })
@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { FridgeManagerApp.class, FridgeManagerController.class, FridgeManagerService.class })
@WebMvcTest
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
            assertEquals(fridgeManager.getFridgeMaxsize(), 25);
            assertEquals(fridgeManager.getFreezerMaxsize(), 25);
            assertNotEquals(fridgeManager.getFridgeContents(), null);
            assertNotEquals(fridgeManager.getFreezerContents(), null);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFridgeIntTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(todoUrl("getFrigdeMaxsize"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            int fridgeSize = objectMapper.readValue(result.getResponse().getContentAsString(),
                    Integer.class);
            assertEquals(fridgeSize, 25);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFrezzerIntTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(todoUrl("getFreezerMaxsize"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            int freezerSize = objectMapper.readValue(result.getResponse().getContentAsString(),
                    Integer.class);
            assertEquals(freezerSize, 25);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addFridgeAndGetContentTest() throws Exception {
        Food cake = new Food("null", "null", 5, LocalDate.now(), "null");
        String contentCake = objectMapper.writeValueAsString(cake);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(todoUrl("addFridgeContent")).content(contentCake)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MvcResult getResult = mockMvc.perform(MockMvcRequestBuilders.get(todoUrl("getFridgeContent"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        try {
            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Food.class);

            List<Food> content = objectMapper.readValue(getResult.getResponse().getContentAsString(), javaType);
            assertNotEquals(content, null);
            // assertEquals(content, new ArrayList<Food>(List.of(cake)));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
