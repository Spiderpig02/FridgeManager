package fridgemanager.ui;

import java.net.URI;
import java.net.URISyntaxException;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * Controller for Fridge.
 */
public class RemoteFridgeController {

  @FXML
  private TextField textfieldFood;
  @FXML
  private TextField textfieldQuantity;
  @FXML
  private TextField textfieldExpiration;
  @FXML
  private TextField textfieldOwner;
  @FXML
  private TextField textfieldFoodRemove;
  @FXML
  private TextField textfieldQuantityRemove;
  @FXML
  private Text removetext;
  @FXML
  private Text removeSpecificAmount;
  @FXML
  private Text foodtext;
  @FXML
  private Text quantitytext;
  @FXML
  private Text removeFromText;
  @FXML
  private Text errortext;
  @FXML
  private Button fridgeButton;
  @FXML
  private Button freezerButton;
  @FXML
  private Button removebutton;
  @FXML
  private Button removebutton2;
  @FXML
  private ListView<Food> fridgecontent;
  @FXML
  private ListView<Food> freezercontent;
  @FXML
  private ChoiceBox<String> dropDownMenu;

  private Food toBeRemoved;
  private Boolean infridge;
  private Boolean infreezer;
  private String[] options = { "fridge", "freezer" };
  private String choice;
  private RemoteFridgeAccess remoteFridgeAccess;

  /**
   * Initializes Controller by creating a new fridgemanager-object.
   * 
   * @throws URISyntaxException
   */
  public RemoteFridgeController() throws URISyntaxException {
    remoteFridgeAccess = new RemoteFridgeAccess(new URI("http://localhost:8080/fridgemanager"));
  }

  /**
   * Starts program by initializing UI.
   */
  @FXML
  private void initialize() {
    startup();
    updateContent();
    // if (fridgemanager.getFridgeContents().size() > 0
    // || fridgemanager.getFreezerContents().size() > 0) {
    // showRemovalMenu();
    // }
    if (remoteFridgeAccess.getFridgeMaxsize() > 0
        || remoteFridgeAccess.getFreezerMaxsize() > 0) {
      showRemovalMenu();
    }
  }

  /**
   * Setting FXML-elements to correct state upon startup.
   */
  @FXML
  private void startup() {
    fridgeButton.setDisable(true);
    freezerButton.setDisable(true);
    removetext.setVisible(false);
    removebutton.setVisible(false);
    removebutton.setDisable(true);

    removeSpecificAmount.setVisible(false);
    foodtext.setVisible(false);
    quantitytext.setVisible(false);
    removeFromText.setVisible(false);
    textfieldFoodRemove.setVisible(false);
    textfieldQuantityRemove.setVisible(false);
    dropDownMenu.setVisible(false);
    removebutton2.setVisible(false);

    dropDownMenu.getItems().addAll(options);
    dropDownMenu.setOnAction(this::getChoice);
  }

  /**
   * Enables the "Add To Fridge" and "Add To Freezer"-buttons.
   */
  @FXML
  private void enableAddButtons() {
    if (!textfieldFood.getText().equals("")
        && !textfieldQuantity.getText().equals("")
        && !textfieldExpiration.getText().equals("")
        && !textfieldOwner.getText().equals("")) {
      fridgeButton.setDisable(false);
      freezerButton.setDisable(false);
    } else {
      fridgeButton.setDisable(true);
      freezerButton.setDisable(true);
    }
  }

  /**
   * Creates a new food item from input given by user and adds this to the fridge.
   */
  @FXML
  private void addToFridge() {
    showRemovalMenu();
    if (createFoodFromInput() != null) {
      // fridgemanager.addFridgeContent(createFoodFromInput());
      remoteFridgeAccess.addFridgeContent(createFoodFromInput());
    }
    updateContent();

    // Clears textfields after each input
    clearInput();
    fridgeButton.setDisable(true);
    freezerButton.setDisable(true);
  }

  /**
   * Creates a new food item from input given by user and adds this to the
   * freezer.
   */
  @FXML
  private void addToFreezer() {
    showRemovalMenu();
    if (createFoodFromInput() != null) {
      // fridgemanager.addFreezerContent(createFoodFromInput());
      remoteFridgeAccess.addFreezerContent(createFoodFromInput());
    }
    updateContent();

    // Clears textfields after each input
    clearInput();
    fridgeButton.setDisable(true);
    freezerButton.setDisable(true);
  }

  /**
   * Clears input in textfields.
   */
  @FXML
  private void clearInput() {
    textfieldFood.clear();
    textfieldQuantity.clear();
    textfieldExpiration.clear();
    textfieldOwner.clear();
  }

  /**
   * Shows FXML-elements connected to the removal-menu.
   */
  @FXML
  private void showRemovalMenu() {
    removetext.setVisible(true);
    removebutton.setVisible(true);
    removebutton.setDisable(false);

    removeSpecificAmount.setVisible(true);
    foodtext.setVisible(true);
    quantitytext.setVisible(true);
    removeFromText.setVisible(true);
    textfieldFoodRemove.setVisible(true);
    textfieldQuantityRemove.setVisible(true);
    dropDownMenu.setVisible(true);
    removebutton2.setVisible(true);
  }

  /**
   * Food item generated from input.
   */
  @FXML
  private Food createFoodFromInput() {
    String food = textfieldFood.getText();
    int quantity = Integer.parseInt(textfieldQuantity.getText());
    String expiration = textfieldExpiration.getText();
    String owner = textfieldOwner.getText();
    if (validateInput(food, quantity, expiration, owner) == true) {
      Food returnFood = new Food(food, quantity, expiration, owner);
      return returnFood;
    } else {
      showErrorMessage("Invalid input!");
      return null;
    }
  }

  /**
   * Handle a mouse click on an fridge element.
   */
  @FXML
  private void handleMouseClickFridge(MouseEvent mouseevent) {
    toBeRemoved = fridgecontent.getSelectionModel().getSelectedItem();
    infridge = true;
  }

  /**
   * Handle a mouse click on an freezer element.
   */
  @FXML
  private void handleMouseClickFreezer(MouseEvent mouseevent) {
    toBeRemoved = freezercontent.getSelectionModel().getSelectedItem();
    infreezer = true;
  }

  @FXML
  private void handleRemove() {
    if (infridge != null) {
      if (infridge == true) {
        // fridgemanager.removeFridgeContent(toBeRemoved);
        remoteFridgeAccess.removeFridgeContent(toBeRemoved);
        infridge = false;
      }
    }
    if (infreezer != null) {
      if (infreezer == true) {
        // fridgemanager.removeFreezerContent(toBeRemoved);
        remoteFridgeAccess.removeFreezerContent(toBeRemoved);
        infreezer = false;
      }
    }

    updateContent();

    this.toBeRemoved = null;
    if (fridgecontent.getItems().size() == 0 && freezercontent.getItems().size() == 0) {
      removebutton.setDisable(true);
    }
  }

  /**
   * Registers what the user has selected in the dropdown-menu.
   *
   * @param event from ActionEvent (mouse click).
   */
  public void getChoice(ActionEvent event) {
    this.choice = dropDownMenu.getValue();
  }

  /**
   * Removes specific amount of food from either fridge or freezer.
   * Dependes on input given from user.
   */
  @FXML
  private void handleRemoveSpecificAmount() {
    String foodname = textfieldFoodRemove.getText();
    Integer quantity = Integer.parseInt(textfieldQuantityRemove.getText());
    if (validateRemovalInput(foodname, quantity) == true) {
      if (choice == "fridge") {
        for (Food food : remoteFridgeAccess.getFrigdeContent()) {
          if (food.getName().toLowerCase().equals(foodname.toLowerCase())) {
            if (food.getQuantity() >= quantity) {
              food.setQuantity(food.getQuantity() - quantity);
              quantity -= food.getQuantity();
              break;

            } else if (quantity > food.getQuantity()) {
              quantity -= food.getQuantity();
              food.setQuantity(0);
            }
          }
        }
      } else if (choice == "freezer") {
        for (Food food : remoteFridgeAccess.getFreezerContent()) {
          if (food.getName().toLowerCase().equals(foodname.toLowerCase())) {
            if (food.getQuantity() >= quantity) {
              food.setQuantity(food.getQuantity() - quantity);
              quantity -= food.getQuantity();
              break;

            } else if (quantity > food.getQuantity()) {
              quantity -= food.getQuantity();
              food.setQuantity(0);
            }
          }
        }
      }
      updateContent();
    } else {
      showErrorMessage("Invalid input!");
    }
  }

  /**
   * Refreshes content in fridge and freezer by retrieving content from
   * fridgemanager.
   */
  @FXML
  private void updateContent() {
    fridgecontent.getItems().clear();

    // for (Food food : fridgemanager.getFridgeContents()) {
    // if (food.getQuantity() == 0) {
    // fridgemanager.getFridgeContents().remove(food);
    // } else {
    // fridgecontent.getItems().add(food);
    // }
    // }
    for (Food food : remoteFridgeAccess.getFrigdeContent()) {
      if (food.getQuantity() == 0) {
        remoteFridgeAccess.removeFridgeContent(food);
      } else {
        fridgecontent.getItems().add(food);
      }
    }

    freezercontent.getItems().clear();

    // for (Food food : fridgemanager.getFreezerContents()) {
    // if (food.getQuantity() == 0) {
    // fridgemanager.getFreezerContents().remove(food);
    // } else {
    // freezercontent.getItems().add(food);
    // }
    // }
    for (Food food : remoteFridgeAccess.getFreezerContent()) {
      if (food.getQuantity() == 0) {
        remoteFridgeAccess.removeFreezerContent(food);
      } else {
        freezercontent.getItems().add(food);
      }
    }
  }

  /**
   * Displays error message.
   */
  @FXML
  private void showErrorMessage(String message) {
    errortext.setText(message);
  }

  /**
   * Hides/removes error message when users selects a textfield.
   */
  @FXML
  private void hideErrorMessage() {
    errortext.setText("");
  }

  /**
   * Validate input in textfield.
   * Return true if input is approved, false if not.
   */
  private Boolean validateInput(String food, int quantity, String expiration, String owner) {
    Boolean approved = true;
    for (Character letter : food.toCharArray()) {
      if (Character.isDigit(letter) == true) {
        approved = false;
      }
    }
    if (quantity < 0) {
      approved = false;
    }
    try {
      String[] exp = expiration.split("\\.");
      if (exp.length != 3) {
        approved = false;
      }
    } catch (Exception e) {
      System.out.println(e);
      approved = false;
    }
    for (Character letter : owner.toCharArray()) {
      if (Character.isDigit(letter) == true) {
        approved = false;
      }
    }
    return approved;
  }

  /**
   * Validate input in remove textfield.
   * Return true if input is approved, false if not.
   */
  private Boolean validateRemovalInput(String food, int quantity) {
    Boolean approved = true;
    for (Character letter : food.toCharArray()) {
      if (Character.isDigit(letter) == true) {
        approved = false;
      }
    }
    if (quantity < 0) {
      approved = false;
    }
    return approved;
  }

  /**
   * Getter fridgemanager.
   */
  public FridgeManager getFridgeManager() {
    return remoteFridgeAccess.getFridgeManager();
  }
}
