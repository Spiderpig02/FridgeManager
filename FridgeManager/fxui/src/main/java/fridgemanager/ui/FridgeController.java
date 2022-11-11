package fridgemanager.ui;

//imports
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.time.LocalDate;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class FridgeController {
    
  @FXML private TextField textfieldFood;
  @FXML private TextField textfieldQuantity;
  @FXML private TextField textfieldExpiration;
  @FXML private TextField textfieldOwner;
  @FXML private TextField textFieldFoodRemove;
  @FXML private TextField textFieldQuantityRemove;
  @FXML private Text removeText;
  @FXML private Text removeSpecificAmount;
  @FXML private Text foodText;
  @FXML private Text quantityText;
  @FXML private Text removeFromText;
  @FXML private Text errorText;
  // @FXML private Button fridge_button;
  // @FXML private Button freezer_button;
  @FXML private Button removeButton;
  @FXML private ListView<Food> fridgeContent;
  @FXML private ListView<Food> freezerContent;
  @FXML private ImageView trashcanFridge1;
  @FXML private ImageView trachcanFreezer1;
  @FXML private ChoiceBox<String> dropDownMenuAdd;
  @FXML private ChoiceBox<String> dropDownMenuQuantity;
  @FXML private ChoiceBox<String> dropDownMenuRemove;
  
  
  private FridgeManager fridgemanager;
  private Food to_be_removed;
  private FileHandler filehandler;
  private Boolean infridge;
  private Boolean infreezer;
  private String[] options = {"fridge", "freezer"};
  private String[] units = {"liter", "stk", "gram", "kilo"};
  private String addchoice;
  private String unitchoice;
  private String choice;
  
  /**
   * Initializes Controller by creating a new fridgemanager-object
   */
  public FridgeController() {
    this.filehandler = new FileHandler();
    loadOrCreateFridgeManager();
  }

  /**
   * Gets called on the start of program to determen if there was a prior save or not
   * If its the first time the program launches, the methode returns a new FridgeManager
   * Else the methode loads in the allredy saved FridgeManager 
   */
  private void loadOrCreateFridgeManager() {
      FridgeManager tempFridge = filehandler.loadFridgeManager();
      if (tempFridge == null) {
          this.fridgemanager = new FridgeManager(10, 10);
      } else {
          this.fridgemanager = tempFridge;
      }
  }
  
  /**
   * Starts program by initializing UI
   */
  @FXML
  private void initialize() {
      startup();
      updateContent();
      if(fridgemanager.getFridgeContents().size() > 0|| fridgemanager.getFreezerContents().size() > 0) {
          ShowRemovalMenu();
      }

  }
  
  /**
   * Setting FXML-elements to correct state upon startup
   */
  @FXML
  private void startup() {
      removeText.setVisible(false);
    //   trashcanFridge1.setVisible(false);
    //   trachcanFreezer1.setVisible(false);

      removeSpecificAmount.setVisible(false);
      foodText.setVisible(false);
      quantityText.setVisible(false);
      removeFromText.setVisible(false);
      textFieldFoodRemove.setVisible(false);
      textFieldQuantityRemove.setVisible(false);
      dropDownMenuRemove.setVisible(false);
      removeButton.setVisible(false);

      dropDownMenuAdd.getItems().addAll(options);
      dropDownMenuQuantity.getItems().addAll(units);
      dropDownMenuRemove.getItems().addAll(options);
      
      dropDownMenuAdd.setOnAction(this::getAddChoice);
      dropDownMenuQuantity.setOnAction(this::getUnitChoice);
      dropDownMenuRemove.setOnAction(this::getRemovalChoice);
  }

  @FXML
  private void openTrashCanFridge() {
      // Image image = new Image(getClass().getResourceAsStream("/pictures_fxui/trashcan-closed.jpg"));
      // System.out.println(image.toString());
      
      // trashcanFridge1.setImage(image);
      // trashcanFridge1.setVisible(true);
  }

  @FXML
  private void openTrashCanFreezer() {
      // Image image = new Image(getClass().getResourceAsStream("/pictures_fxui/trashcan-closed.jpg"));
      // System.out.println(image.toString());
      // trachcanFreezer1.setImage(image);
      // trachcanFreezer1.setVisible(true);
  }
  
  public void getAddChoice(ActionEvent event) {
      this.addchoice = dropDownMenuAdd.getValue();
  }

  public void getUnitChoice(ActionEvent event) {
      this.unitchoice = dropDownMenuQuantity.getValue();
  }

  /**
   * Registers what the user has selected in the dropdown-menu
   * @param ActionEvent event (mouse click)
   */
  public void getRemovalChoice(ActionEvent event) {
      this.choice = dropDownMenuRemove.getValue();
  }


  @FXML
  private void addOnEnter(KeyEvent event) {
      switch (event.getCode()) {
          case ENTER:
              addFood();
              break;
          default:
              break;
      }
  }

  @FXML
  private void addFood() {
      ShowRemovalMenu();
      if (CreateFoodFromInput() != null) {
          if (addchoice == "fridge")  {
              fridgemanager.addFreezerContent(CreateFoodFromInput());
          }
          else if (addchoice == "freezer") {
              fridgemanager.addFreezerContent(CreateFoodFromInput());
          }
          ClearInput();
      }
      updateContent();
      filehandler.saveObject(this.fridgemanager);
  }

  /**
   * Creates a new food item from input given by user and adds this to the fridge
   */
  // @FXML
  // private void addToFridge() {
  //     ShowRemovalMenu();
  //     if (CreateFoodFromInput() != null) {
  //         fridgemanager.addfridgeContent(CreateFoodFromInput());
  //     }
  //     UpdateContent();

  //     ClearInput();
  //     fridge_button.setDisable(true);
  //     freezer_button.setDisable(true);
  //     filehandler.saveObject(this.fridgemanager);
  // }    

  /**
   * Creates a new food item from input given by user and adds this to the freezer
   */
  // @FXML
  // private void addToFreezer() {
  //     ShowRemovalMenu();
  //     if (CreateFoodFromInput() != null) {
  //         fridgemanager.addfreezerContent(CreateFoodFromInput());
  //     }
  //     UpdateContent();

  //     //Clears textfields after each input
  //     ClearInput();
  //     fridge_button.setDisable(true);
  //     freezer_button.setDisable(true);
  //     filehandler.saveObject(this.fridgemanager);
  // }    

  /**
   * Clears input in textfields
   */
  @FXML
  private void ClearInput() {
      textfieldFood.clear();
      textfieldQuantity.clear();
      textfieldExpiration.clear();
      textfieldOwner.clear();
  }

  /**
   * Shows FXML-elements connected to the removal-menu
   */
  @FXML
  private void ShowRemovalMenu() {
      removeText.setVisible(true);
      trashcanFridge1.setVisible(true);
      trachcanFreezer1.setVisible(true);

      removeSpecificAmount.setVisible(true);
      foodText.setVisible(true);
      quantityText.setVisible(true);
      removeFromText.setVisible(true);
      textFieldFoodRemove.setVisible(true);
      textFieldQuantityRemove.setVisible(true);
      dropDownMenuRemove.setVisible(true);
      removeButton.setVisible(true);
  }

  @FXML
  private void HideRemovalMenu() {
      removeText.setVisible(false);
      trashcanFridge1.setVisible(false);
      trachcanFreezer1.setVisible(false);

      removeSpecificAmount.setVisible(false);
      foodText.setVisible(false);
      quantityText.setVisible(false);
      removeFromText.setVisible(false);
      textFieldFoodRemove.setVisible(false);
      textFieldQuantityRemove.setVisible(false);
      dropDownMenuRemove.setVisible(false);
      removeButton.setVisible(false);
  }

  /**
   * @return Food item generated from input
   */
  @FXML
  private Food CreateFoodFromInput() {
      try {
          String food = textfieldFood.getText();
          String unit = unitchoice;
          int quantity = Integer.parseInt(textfieldQuantity.getText());
          LocalDate expiration = LocalDate.parse(textfieldExpiration.getText());
          String owner = textfieldOwner.getText();

          if (ValidateInput(food, quantity, expiration, owner) == true) {
              Food return_food = new Food(food, unit, quantity, expiration, owner);
              return return_food;
          }
          else {
              showErrorMessage("Invalid input!");
              return null;
          }
      } catch (Exception e) {
          showErrorMessage("Invalid input!");
          return null;
      }
  }

  /**
   * Handle a mouse click on an fridge element
   */
  @FXML
  private void handleMouseClickFridge(MouseEvent mouseevent) {
      to_be_removed = fridgeContent.getSelectionModel().getSelectedItem();
      infridge = true;
  }

  /**
   * Handle a mouse click on an freezer element
   */
  @FXML
  private void handleMouseClickFreezer(MouseEvent mouseevent) {
      to_be_removed = freezerContent.getSelectionModel().getSelectedItem();
      infreezer = true;
  }

  @FXML
  private void handleRemove() {
      if (infridge != null) {
          if (infridge == true) {
              fridgemanager.removeFridgeContent(to_be_removed);
              infridge = false;
          }
      }
      if (infreezer != null) {
          if (infreezer == true) {
              fridgemanager.removeFreezerContent(to_be_removed);
              infreezer = false;
          }
      }
          
      updateContent();

      this.to_be_removed = null;
      if (fridgeContent.getItems().size() == 0) {
          trashcanFridge1.setVisible(false);
      }
      if (freezerContent.getItems().size() == 0) {
          trachcanFreezer1.setVisible(false);
      }
      if (fridgeContent.getItems().size() == 0 && freezerContent.getItems().size() == 0) {
          HideRemovalMenu();
      }
      
      filehandler.saveObject(fridgemanager);
  }
  

  /**
   * Removes specific amount of food from either fridge or freezer depending on input given by user
   */
  @FXML
  private void handleRemoveSpecificAmount() {
    try {
      String foodname = textFieldFoodRemove.getText();
      Integer quantity = Integer.parseInt(textFieldQuantityRemove.getText());
      if (ValidateRemovalInput(foodname, quantity) == true) {
        if (choice == "fridge") {
          for (Food food : fridgemanager.getFridgeContents()) {
            if (food.getName().toLowerCase().equals(foodname.toLowerCase())) {
              if (food.getQuantity() >= quantity) {
                food.setQuantity(food.getQuantity() - quantity);
                quantity -= food.getQuantity();
                break;
                  
              } 
              else if (quantity > food.getQuantity()) {
                quantity -= food.getQuantity();
                food.setQuantity(0);                            
              } 
            }  
              }
          }
          else if (choice == "freezer") {
            for (Food food : fridgemanager.getFreezerContents()) {
              if (food.getName().toLowerCase().equals(foodname.toLowerCase())) {
                if (food.getQuantity() >= quantity) {
                  food.setQuantity(food.getQuantity() - quantity);
                  quantity -= food.getQuantity();
                  break;
                    
                }
                else if (quantity > food.getQuantity()) {
                  quantity -= food.getQuantity();
                  food.setQuantity(0);                            
                } 
              }  
            }
          }
          updateContent();
          filehandler.saveObject(this.fridgemanager);
          }
          else {
              showErrorMessage("Invalid input!");
          }
      }
      catch (Exception e) {
          e.printStackTrace();
          showErrorMessage("Invalid Input!");
      }
      
      textFieldFoodRemove.clear();
      textFieldQuantityRemove.clear();
  }

  /**
   * Refreshes content in fridge and freezer by retrieving content from fridgemanager
   */
  @FXML
  private void updateContent() {
      fridgeContent.getItems().clear();
      
      for (Food food : fridgemanager.getFridgeContents()) {
          if (food.getQuantity() == 0) {
              fridgemanager.getFridgeContents().remove(food);
          }
          else {
              fridgeContent.getItems().add(food);
          }
      }
      freezerContent.getItems().clear();
      for (Food food : fridgemanager.getFreezerContents()) {
          if (food.getQuantity() == 0) {
              fridgemanager.getFreezerContents().remove(food);
          }
          else {
              freezerContent.getItems().add(food);
          }
      }
  }  

  /**
   * Displays error message
   */
  @FXML
  private void showErrorMessage(String message) {
      errorText.setText(message);
  }

  /**
   * Hides/removes error message when users selects a textfield
   */
  @FXML
  private void hideErrorMessage() {
      errorText.setText("");
  }

  /**
   * @param food
   * @param quantity
   * @param expiration
   * @param owner
   * @return true if input is approved, false if not
   */
  private Boolean ValidateInput(String food, int quantity, LocalDate expiration, String owner) {

      Boolean approved = true;
      try {
          if (addchoice == null || food == null || quantity < 1 || unitchoice == null || owner == null) {
              approved = false;
          }
          for (Character letter : food.toCharArray()) {
              if (Character.isDigit(letter) == true) {
                  approved = false;
              }
          }

          // String[] exp = expiration.toString().split("-");
          // if (exp.length != 3 || exp[0].length() != 4 || exp[1].length() <= 2 || exp[2].length() <= 2) {
          //     approved = false;
          // } 
          for (Character letter : owner.toCharArray()) {
              if (Character.isDigit(letter) == true) {
                  approved = false;
              }
          }
      }
      catch (Exception e) {
          e.printStackTrace();
          approved = false;
      }
      return approved;
  }

  /**
   * @param food
   * @param quantity
   * @return true if input is approved, false if not
   */
  private Boolean ValidateRemovalInput(String food, int quantity) {
      Boolean approved = true;
      try {
          if (food == null || quantity < 1) {
              approved = false;
          }
  
          for (Character letter : food.toCharArray()) {
              if (Character.isDigit(letter) == true) {
                  approved = false;
              }
          }
      }
      catch (Exception e) {
          e.printStackTrace();
          showErrorMessage("Ugyldig input!");
      }
      return approved;
      
  }

  /**
   * Getter fridgemanager
   */
  public FridgeManager getFridgeManager() {
      return this.fridgemanager;
  }
}

