package fridgemanager.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import fridgemanager.core.FridgeManager;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


/**
   * this is a controller that utilizes the restAPI for controlling the program.
  */
public class RemoteFridgeAccess {
  private URI endpointBaseUri;
    
  private static final String APPLICATION_JSON = "springboot/fridgemanager/getFridgeContent";

  private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
  
  private static final String ACCEPT_HEADER = "Accept";
  
  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  private ObjectMapper objectMapper;
  
  private FridgeManager fridgeManager;
  /**
   * basic constructor.
  */
  
  public RemoteFridgeAccess(URI endpointBaseUri) {
    this.endpointBaseUri = endpointBaseUri;
  }

  /**
   * returns the fridgemanager object.
  */

  public FridgeManager getFridgeManager() {
    if (fridgeManager == null) {
      HttpRequest request = HttpRequest.newBuilder(endpointBaseUri)
          .header(ACCEPT_HEADER, APPLICATION_JSON)
          .GET()
          .build();
      try {
        final HttpResponse<String> response = HttpClient
            .newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());
        this.fridgeManager = objectMapper.readValue(response.body(), FridgeManager.class);
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    //Her må vi få inn en else kanskje? **TODO
    System.out.println(fridgeManager.toString());
    return fridgeManager;
  }
  private void addToFridge(){};
  private void addToFreezer(){};
  private void removeFreezerContent(){};
  private void removeFridgeContent(){};
  private void handleRemoveSpecificAmount(){};

  /**
  * only for testing purposes. to be removed.
  */

  public static void main(String[] args) throws URISyntaxException {
    URI uri = new URI("localhost:8080");
    RemoteFridgeAccess access = new RemoteFridgeAccess(uri);
    access.getFridgeManager();
  }







  //   private void putTodoList(AbstractTodoList todoList) {
  //     try {
  //       String json = objectMapper.writeValueAsString(todoList);
  //       HttpRequest request = HttpRequest.newBuilder(todoListUri(todoList.getName()))
  //           .header(ACCEPT_HEADER, APPLICATION_JSON)
  //           .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
  //           .PUT(BodyPublishers.ofString(json))
  //           .build();
  //       final HttpResponse<String> response =
  //           HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
  //       String responseString = response.body();
  //       Boolean added = objectMapper.readValue(responseString, Boolean.class);
  //       if (added != null) {
  //         todoModel.putTodoList(todoList);
  //       }
  //     } catch (IOException | InterruptedException e) {
  //       throw new RuntimeException(e);
  //     }
  //  }
}
