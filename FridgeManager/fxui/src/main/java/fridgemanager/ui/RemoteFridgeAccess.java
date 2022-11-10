package fridgemanager.ui;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * this is a controller that utilizes the restAPI for controlling the program.
 */
public class RemoteFridgeAccess {
  private URI endpointBaseUri;

  private static final String APPLICATION_JSON = "application/json";

  private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

  private static final String ACCEPT_HEADER = "Accept";

  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  private ObjectMapper objectMapper;

  /**
   * basic constructor.
   */

  public RemoteFridgeAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(FileHandler.createJacsonModule());
    objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
  }

  /**
   * returns the fridgemanager object.
   */

  private URI makeUri(String add) {
    return endpointBaseUri.resolve(endpointBaseUri + add);
  }

  public FridgeManager getFridgeManager() {
    HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .GET()
        .build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      FridgeManager fridgeManager = objectMapper.readValue(response.body(), FridgeManager.class);
      return fridgeManager;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void addToFridge() {
  }

  private void addToFreezer() {
  }

  private void removeFreezerContent() {
  }

  private void removeFridgeContent() {
  }

  public List<Food> getFreezerContent() {
    HttpRequest request = HttpRequest.newBuilder(makeUri("/getFreezerContent"))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      List<Food> freezerContent = objectMapper.readValue(response.body(), List.class);
      return freezerContent;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Food> getFrigdeContent() {
    HttpRequest request = HttpRequest.newBuilder(makeUri("/getFridgeContent"))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      List<Food> freezerContent = objectMapper.readValue(response.body(), List.class);
      return freezerContent;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public int getFridgeMaxsize() {
    HttpRequest request = HttpRequest.newBuilder(makeUri("/getFrigdeMaxsize"))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      int frigdeMaxsize = objectMapper.readValue(response.body(), int.class);
      return frigdeMaxsize;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public int getFreezerMaxsize() {
    HttpRequest request = HttpRequest.newBuilder(makeUri("/getFreezerMaxsize"))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      int frigdeMaxsize = objectMapper.readValue(response.body(), int.class);
      return frigdeMaxsize;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * only for testing purposes. to be removed.
   */

  // private void putTodoList(FridgeManager fridgemanager) {
  // try {
  // String json = objectMapper.writeValueAsString(fridgemanager);
  // HttpRequest request = HttpRequest.newBuilder(todoListUri(todoList.getName()))
  // .header(ACCEPT_HEADER, APPLICATION_JSON)
  // .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
  // .PUT(BodyPublishers.ofString(json))
  // .build();
  // final HttpResponse<String> response =
  // HttpClient.newBuilder().build().send(request,
  // HttpResponse.BodyHandlers.ofString());
  // String responseString = response.body();
  // Boolean added = objectMapper.readValue(responseString, Boolean.class);
  // if (added != null) {
  // todoModel.putTodoList(todoList);
  // }
  // } catch (IOException | InterruptedException e) {
  // throw new RuntimeException(e);
  // }
  // }

  public static void main(String[] args) throws URISyntaxException {
    System.out.println("\n\n\n\n");
    RemoteFridgeAccess access = new RemoteFridgeAccess(new URI("http://localhost:8080/fridgemanager"));
    access.getFridgeManager();
    System.out.println(access.getFreezerContent());
    System.err.println("a");
  }

  // private void putTodoList(AbstractTodoList todoList) {
  // try {
  // String json = objectMapper.writeValueAsString(todoList);
  // HttpRequest request = HttpRequest.newBuilder(todoListUri(todoList.getName()))
  // .header(ACCEPT_HEADER, APPLICATION_JSON)
  // .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
  // .PUT(BodyPublishers.ofString(json))
  // .build();
  // final HttpResponse<String> response =
  // HttpClient.newBuilder().build().send(request,
  // HttpResponse.BodyHandlers.ofString());
  // String responseString = response.body();
  // Boolean added = objectMapper.readValue(responseString, Boolean.class);
  // if (added != null) {
  // todoModel.putTodoList(todoList);
  // }
  // } catch (IOException | InterruptedException e) {
  // throw new RuntimeException(e);
  // }
  // }
}
