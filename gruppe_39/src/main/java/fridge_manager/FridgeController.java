package fridge_manager;

//FXML-imports
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


public class FridgeController {
    
    @FXML private TextField textfield_food;
    @FXML private TextField textfield_quantity;
    @FXML private TextField textfield_expiration;
    @FXML private TextField textfield_owner;
    @FXML private Button fridge_button;
    @FXML private Button freezer_button;
    @FXML private ListView<Food> fridgecontent;
    @FXML private ListView<Food> freezercontent;

    private FridgeManager fridgemanager;

    /**
     * Initializes Controller by creating a new fridgemanager-object
     */
    @FXML
    public void initialize() {
        this.fridgemanager = new FridgeManager(10, 10);
    }

    /**
     * Creates a new food item from input given by user and adds this to the fridge
     */
    @FXML
    private void addToFridge() {
        String food = textfield_food.getText();
        int quantity =  Integer.parseInt(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_fridge = new Food(food, quantity, expiration, owner);
        
        fridgemanager.getFridgeContents().add(food_to_fridge);
        fridgecontent.getItems().add(food_to_fridge);
    }    

    /**
     * Creates a new food item from input given by user and adds this to the freezer
     */
    @FXML
    private void addToFreezer() {
        String food = textfield_food.getText();
        int quantity = Integer.valueOf(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_freezer = new Food(food, quantity, expiration, owner);

        fridgemanager.getFreezerContents().add(food_to_freezer);
        freezercontent.getItems().add(food_to_freezer);
    }    

}
