package fridgemanager.ui;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * this is a controller that utilizes the restAPI for controlling the program.
 */
public class RemoteFridgeAccess {
  private URI endpointBaseUri;

  private static final String APPLICATION_JSON = "application/json";

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

  private void add(Food food, String function) {
    try {
      String json = objectMapper.writeValueAsString(food);
      HttpRequest request = HttpRequest.newBuilder(makeUri(function))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .POST(BodyPublishers.ofString(json))
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println("add(" + food.toString() + ") response: " + responseString);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private void remove(Food food, String function) {
    try {
      HttpRequest request = HttpRequest.newBuilder(makeUri(function + "/" + food.getId()))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .header(food.getId(), APPLICATION_JSON)
          .DELETE()
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println("remove(" + food.toString() + ") response: " + responseString);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Food> get(String function) {
    HttpRequest request = HttpRequest.newBuilder(makeUri(function))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());

      String responseString = response.body();
      System.out.println(function + " response: " + responseString);

      List<Food> freezerContent = new ArrayList<Food>();
      for (Object object : objectMapper.readValue(response.body(), List.class)) {
        freezerContent.add((Food) object);
      }
      return freezerContent;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private int getint(String function) {
    HttpRequest request = HttpRequest.newBuilder(makeUri(function))
        .header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println(function + " response: " + responseString);
      int frigdeMaxsize = objectMapper.readValue(response.body(), int.class);
      return frigdeMaxsize;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
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
      String responseString = response.body();
      System.out.println("getFridgeManager" + " response: " + responseString);
      FridgeManager fridgeManager = objectMapper.readValue(response.body(), FridgeManager.class);
      return fridgeManager;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void addFridgeContent(Food food) {
    add(food, "/addFridgeContent");
  }

  public void addFreezerContent(Food food) {
    add(food, "/addFreezerContent");
  }

  public void removeFreezerContent(Food food) {
    remove(food, "/removeFreezerContent");
  }

  public void removeFridgeContent(Food food) {
    remove(food, "/removeFridgeContent");
  }

  public List<Food> getFreezerContent() {
    return get("/getFreezerContent");
  }

  public List<Food> getFrigdeContent() {
    return get("/getFridgeContent");
  }

  public int getFridgeMaxsize() {
    return getint("/getFrigdeMaxsize");
  }

  public int getFreezerMaxsize() {
    return getint("/getFreezerMaxsize");
  }

  public void setQuantity(int quantity, Food food) {
    try {
      String json = objectMapper.writeValueAsString(food);
      HttpRequest request = HttpRequest.newBuilder(makeUri("/setQuantity/" + String.valueOf(quantity)))
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .PUT(BodyPublishers.ofString(json))
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      String responseString = response.body();
      System.out.println("changed(" + food.toString() + ") response: " + responseString);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) throws URISyntaxException {
    RemoteFridgeAccess remoteFridgeAccess = new RemoteFridgeAccess(new URI("http://localhost:8080/fridgemanager"));
    System.out.println(remoteFridgeAccess.getFrigdeContent());
  }
}