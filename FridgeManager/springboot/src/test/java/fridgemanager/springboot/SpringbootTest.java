package fridgemanager.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { FridgeManagerApp.class, FridgeManagerController.class, FridgeManagerService.class })
@WebMvcTest
public class SpringbootTest {

    /**
     * RecordClassForHTTPRequests
     */
    public record RecordClassForHTTPRequests() {
        public static String initHTTP = "http://localhost:8080/fridgemanager";
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

    private static ObjectMapper objectMapper;
    private static final String APPLICATION_JSON = "application/json";

    private static final String ACCEPT_HEADER = "Accept";

    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    @BeforeAll
    public static void setup() throws Exception {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(FileHandler.createJacsonModule());
    }

    @BeforeEach
    public void beforeEach() {
        DeleteLocalSave.deleteFile();
    }

    @Test
    public void initTest() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(RecordClassForHTTPRequests.initHTTP))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .GET()
                .build();
        try {
            final HttpResponse<String> response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            FridgeManager fridgeManager = objectMapper.readValue(responseString, FridgeManager.class);
            assertEquals(fridgeManager.getFridgeMaxsize(), 25);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void afterAll() {
        DeleteLocalSave.deleteFile();
    }

}
