package fridge_manager.ui;

//imports
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;

import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;
import fridge_manager.json.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class FridgeController {
    
    @FXML private TextField textfieldFood;
    @FXML private TextField textfieldQuantity;
    @FXML private TextField textfieldExpiration;
    @FXML private TextField textfieldOwner;
    @FXML private TextField textfieldFood_remove;
    @FXML private TextField textfieldQuantity_remove;
    @FXML private Text removetext;
    @FXML private Text removeSpecificAmount;
    @FXML private Text foodtext;
    @FXML private Text quantitytext;
    @FXML private Text remove_from_text;
    @FXML private Text errortext;
    // @FXML private Button fridge_button;
    // @FXML private Button freezer_button;
    @FXML private Button removebutton2;
    @FXML private ListView<Food> fridgecontent;
    @FXML private ListView<Food> freezercontent;
    @FXML private ImageView trashcan_fridge1;
    @FXML private ImageView trashcan_freezer1;
    @FXML private ChoiceBox<String> DropDownMenu_add;
    @FXML private ChoiceBox<String> DropDownMenu_quantity;
    @FXML private ChoiceBox<String> DropDownMenu_remove;
    
    
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
        UpdateContent();
        if(fridgemanager.getFridgeContents().size() > 0|| fridgemanager.getFreezerContents().size() > 0) {
            ShowRemovalMenu();
        }

    }
    
    /**
     * Setting FXML-elements to correct state upon startup
     */
    @FXML
    private void startup() {
        removetext.setVisible(false);
        trashcan_fridge1.setVisible(false);
        trashcan_freezer1.setVisible(false);

        removeSpecificAmount.setVisible(false);
        foodtext.setVisible(false);
        quantitytext.setVisible(false);
        remove_from_text.setVisible(false);
        textfieldFood_remove.setVisible(false);
        textfieldQuantity_remove.setVisible(false);
        DropDownMenu_remove.setVisible(false);
        removebutton2.setVisible(false);

        DropDownMenu_add.getItems().addAll(options);
        DropDownMenu_quantity.getItems().addAll(units);
        DropDownMenu_remove.getItems().addAll(options);
        
        DropDownMenu_add.setOnAction(this::getAddChoice);
        DropDownMenu_quantity.setOnAction(this::getUnitChoice);
        DropDownMenu_remove.setOnAction(this::getRemovalChoice);
    }

    @FXML
    private void openTrashCanFridge() {
        // Image image = new Image(getClass().getResourceAsStream("/pictures_fxui/trashcan-closed.jpg"));
        // System.out.println(image.toString());
        
        // trashcan_fridge1.setImage(image);
        // trashcan_fridge1.setVisible(true);
    }

    @FXML
    private void openTrashCanFreezer() {
        // Image image = new Image(getClass().getResourceAsStream("/pictures_fxui/trashcan-closed.jpg"));
        // System.out.println(image.toString());
        // trashcan_freezer1.setImage(image);
        // trashcan_freezer1.setVisible(true);
    }
    
    public void getAddChoice(ActionEvent event) {
        this.addchoice = DropDownMenu_add.getValue();
    }

    public void getUnitChoice(ActionEvent event) {
        this.unitchoice = DropDownMenu_quantity.getValue();
    }

    /**
     * Registers what the user has selected in the dropdown-menu
     * @param ActionEvent event (mouse click)
     */
    public void getRemovalChoice(ActionEvent event) {
        this.choice = DropDownMenu_remove.getValue();
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
                fridgemanager.addFridgeContent(CreateFoodFromInput());
            }
            else if (addchoice == "freezer") {
                fridgemanager.addFreezerContent(CreateFoodFromInput());
            }
            ClearInput();
        }
        UpdateContent();
        filehandler.saveObject(this.fridgemanager);
    }

    /**
     * Creates a new food item from input given by user and adds this to the fridge
     */
    // @FXML
    // private void addToFridge() {
    //     ShowRemovalMenu();
    //     if (CreateFoodFromInput() != null) {
    //         fridgemanager.addFridgeContent(CreateFoodFromInput());
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
    //         fridgemanager.addFreezerContent(CreateFoodFromInput());
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
        removetext.setVisible(true);
        trashcan_fridge1.setVisible(true);
        trashcan_freezer1.setVisible(true);

        removeSpecificAmount.setVisible(true);
        foodtext.setVisible(true);
        quantitytext.setVisible(true);
        remove_from_text.setVisible(true);
        textfieldFood_remove.setVisible(true);
        textfieldQuantity_remove.setVisible(true);
        DropDownMenu_remove.setVisible(true);
        removebutton2.setVisible(true);
    }

    @FXML
    private void HideRemovalMenu() {
        removetext.setVisible(false);
        trashcan_fridge1.setVisible(false);
        trashcan_freezer1.setVisible(false);

        removeSpecificAmount.setVisible(false);
        foodtext.setVisible(false);
        quantitytext.setVisible(false);
        remove_from_text.setVisible(false);
        textfieldFood_remove.setVisible(false);
        textfieldQuantity_remove.setVisible(false);
        DropDownMenu_remove.setVisible(false);
        removebutton2.setVisible(false);
    }

    /**
     * @return Food item generated from input
     */
    @FXML
    private Food CreateFoodFromInput() {
        try {
            String food = textfieldFood.getText();
            int quantity = Integer.parseInt(textfieldQuantity.getText());
            LocalDate expiration = LocalDate.parse(textfieldExpiration.getText());
            String owner = textfieldOwner.getText();

            if (ValidateInput(food, quantity, expiration, owner) == true) {
                Food return_food = new Food(food, quantity, expiration, owner);
                return_food.setUnit(unitchoice);
                return return_food;
            }
            else {
                ShowErrorMessage("Invalid input!");
                return null;
            }
        } catch (Exception e) {
            ShowErrorMessage("Invalid input!");
            return null;
        }
    }

    /**
     * Handle a mouse click on an fridge element
     */
    @FXML
    private void handleMouseClickFridge(MouseEvent mouseevent) {
        to_be_removed = fridgecontent.getSelectionModel().getSelectedItem();
        infridge = true;
    }

    /**
     * Handle a mouse click on an freezer element
     */
    @FXML
    private void handleMouseClickFreezer(MouseEvent mouseevent) {
        to_be_removed = freezercontent.getSelectionModel().getSelectedItem();
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
            
        UpdateContent();

        this.to_be_removed = null;
        if (fridgecontent.getItems().size() == 0) {
            trashcan_fridge1.setVisible(false);
        }
        if (freezercontent.getItems().size() == 0) {
            trashcan_freezer1.setVisible(false);
        }
        if (fridgecontent.getItems().size() == 0 && freezercontent.getItems().size() == 0) {
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
            String foodname = textfieldFood_remove.getText();
        Integer quantity = Integer.parseInt(textfieldQuantity_remove.getText());
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
            UpdateContent();
            filehandler.saveObject(this.fridgemanager);
            }
            else {
                ShowErrorMessage("Invalid input!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            ShowErrorMessage("Invalid Input!");
        }
        
        textfieldFood_remove.clear();
        textfieldQuantity_remove.clear();
    }

    /**
     * Refreshes content in fridge and freezer by retrieving content from fridgemanager
     */
    @FXML
    private void UpdateContent() {
        fridgecontent.getItems().clear();
        
        for (Food food : fridgemanager.getFridgeContents()) {
            if (food.getQuantity() == 0) {
                fridgemanager.getFridgeContents().remove(food);
            }
            else {
                fridgecontent.getItems().add(food);
            }
        }
        freezercontent.getItems().clear();
        for (Food food : fridgemanager.getFreezerContents()) {
            if (food.getQuantity() == 0) {
                fridgemanager.getFreezerContents().remove(food);
            }
            else {
                freezercontent.getItems().add(food);
            }
        }
    }  

    /**
     * Displays error message
     */
    @FXML
    private void ShowErrorMessage(String message) {
        errortext.setText(message);
    }

    /**
     * Hides/removes error message when users selects a textfield
     */
    @FXML
    private void HideErrorMessage() {
        errortext.setText("");
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
            if (addchoice == null || food == null || quantity < 1 || unitchoice == null || expiration == null || owner == null) {
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
            ShowErrorMessage("Ugyldig input!");
        }
        return approved;
        
    }

    /**
     * Getter fridgemanager
     */
    public FridgeManager getFridgeManager() {
        return this.fridgemanager;
    }

    public static void main(String[] args) {
        FridgeController controller = new FridgeController();
        controller.ValidateInput("Daddy", 3, LocalDate.parse("2000-11-12"), "JENSSSSS");
    }
}
