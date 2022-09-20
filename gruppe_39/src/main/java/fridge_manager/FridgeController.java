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
    @FXML private ListView fridgecontent;
    @FXML private ListView freezercontent;

    private FridgeManager fridgemanager;

    @FXML
    public void initialize() {
        this.fridgemanager = new FridgeManager();
    }


    @FXML
    private void addToFridge() {
        String food = textfield_food.getText();
        Integer quantity = Integer.parseInt(textfield_food.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        fridgecontent.getItems().add(new Food(food, quantity, expiration, owner));
    }    

    @FXML
    private void addToFreezer() {
        String food = textfield_food.getText();
        Integer quantity = Integer.parseInt(textfield_food.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        freezercontent.getItems().add(new Food(food, quantity, expiration, owner));
    }    

}
