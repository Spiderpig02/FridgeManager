package fridgemanager.ui;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * Utilizes Rest-API to control the program.
 */
public class RemoteFridgeAccess {
  private URI endpointBaseUri;

  private static final String APPLICATION_JSON = "application/json";

  private static final String ACCEPT_HEADER = "Accept";

  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  private ObjectMapper objectMapper;

  /**
   * Basic Constructor
   * @param endpointBaseUri
   */
  public RemoteFridgeAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(FileHandler.createJacsonModule());
    objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
  }

  /**
   * Create a specified URI.
   * 
   * @param add - addition to baseURi
   * @return URI 
   */
  private URI makeUri(String add) {
    return endpointBaseUri.resolve(endpointBaseUri + add);
  }

  /**
   * Add-request to server.
   * 
   * @param food
   * @param function
   */
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

  /**
   * remove-request to server.
   * 
   * @param food
   * @param function
   */
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

  /**
   * get-request to server to retrieve either fridgecontent or freezercontent.
   * 
   * @param function
   * @return List<Food>
   */
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

      CollectionType javaType = objectMapper.getTypeFactory()
          .constructCollectionType(List.class, Food.class);

      List<Food> content = objectMapper.readValue(responseString, javaType);
      return content;

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * get-request to server to retrieve an int. 
   * 
   * @param function
   * @return int
   */
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

  /**
   * get-request to server to retrieve FridgeManager-object.
   * 
   * @return FridgeManager.
   */
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

  /**
   * Adds Food using addFridgeContent-method
   * 
   * @param food
   */
  public void addFridgeContent(Food food) {
    add(food, "/addFridgeContent");
  }

  /**
   * Adds Food using addFreezerContent-method.
   * 
   * @param food
   */
  public void addFreezerContent(Food food) {
    add(food, "/addFreezerContent");
  }
  
  /**
   * Removes Food using removeFridgeContent-method.
   * 
   * @param food
   */
  public void removeFridgeContent(Food food) {
    remove(food, "/removeFridgeContent");
  }

  /**
   * Removes Food using removeFreezerContent-method.
   * 
   * @param food
   */
  public void removeFreezerContent(Food food) {
    remove(food, "/removeFreezerContent");
  }

  /**
   * Retrieves Food from server using getFridgeContent-method.
   * 
   * @return List<Food> fridgecontent.
   */
  public List<Food> getFrigdeContent() {
    return get("/getFridgeContent");
  }

  /**
   * Retrieves Food from server using getFreezerContent-method.
   * 
   * @return List<Food> freezercontent.
   */
  public List<Food> getFreezerContent() {
    return get("/getFreezerContent");
  }

  /**
   * Retrieves fridgemaxize from server using getFridgeMaxsize-method.
   * 
   * @return int fridgemaxsize.
   */
  public int getFridgeMaxsize() {
    return getint("/getFrigdeMaxsize");
  }

  /**
   * Retrieves freezermaxize from server using getFreezerMaxsize-method.
   * 
   * @return int freezermaxsize.
   */
  public int getFreezerMaxsize() {
    return getint("/getFreezerMaxsize");
  }

  /**
   * Sets new quantity for Food-item on server.
   * 
   * @param quantity
   * @param food
   */
  public void setQuantity(int quantity, Food food) {
    try {
      String json = objectMapper.writeValueAsString(food);
      HttpRequest request = HttpRequest
          .newBuilder(makeUri("/setQuantity/" + String.valueOf(quantity)))
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
}
