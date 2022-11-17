package fridgemanager.springboot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fridgemanager.json.FileHandler;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { FridgeManagerApp.class, FridgeManagerController.class, FridgeManagerService.class })
@WebMvcTest
public class SpringbootTest {

    /**
     * RecordClassForHTTPRequests
     */
    public record RecordClassForHTTPRequests() {
        public static String getFridge = "/getFridgeContent";
        public static String getFreezer = "/getFreezerContent";
        public static String getFreezerSize = "/getFreezerMaxsize";
        public static String getFridgeSize = "/getFrigdeMaxsize";
        public static String removeFridgeContent = "/removeFridgeContent/{id}";
        public static String removeFreezerContent = "/removeFreezerContent/{id}";
        public static String setQuantity = "/setQuantity/{quantity}";
        public static String addFridgeContent = "/addFridgeContent";
        public static String addFreezerContent = "/addFreezerContent";
    }

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeAll
    public void setup() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(FileHandler.createJacsonModule());
    }

    @BeforeEach
    public void beforeEach() {
        DeleteLocalSave.deleteFile();
    }

    @AfterAll
    public void afterAll() {
        DeleteLocalSave.deleteFile();
    }

}
