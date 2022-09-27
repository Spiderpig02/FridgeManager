package fridge_manager;

//FXML-imports
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import fridge_manager.IO.FileHandler;
import javafx.fxml.FXML;


public class FridgeController {
    
    @FXML private TextField textfield_food;
    @FXML private TextField textfield_quantity;
    @FXML private TextField textfield_expiration;
    @FXML private TextField textfield_owner;
    @FXML private Text removetext;
    @FXML private Button fridge_button;
    @FXML private Button freezer_button;
    @FXML private Button button_removefridge;
    @FXML private Button button_removefreezer;
    @FXML private ListView<Food> fridgecontent;
    @FXML private ListView<Food> freezercontent;
    
    

    private FridgeManager fridgemanager;
    private Food to_be_removed;
    private FileHandler filehandler;

    /**
     * Initializes Controller by creating a new fridgemanager-object
     */
    @FXML
    public void initialize() {
        this.fridgemanager = new FridgeManager(10, 10);
        this.filehandler = new FileHandler();

        removetext.setVisible(false);
        button_removefridge.setVisible(false);
        button_removefreezer.setVisible(false);

        button_removefridge.setDisable(true);
        button_removefreezer.setDisable(true);
    }

    /**
     * Creates a new food item from input given by user and adds this to the fridge
     */
    @FXML
    private void addToFridge() {
        removetext.setVisible(true);
        button_removefridge.setVisible(true);
        button_removefreezer.setVisible(true);
        button_removefridge.setDisable(false);

        String food = textfield_food.getText();
        int quantity =  Integer.parseInt(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_fridge = new Food(food, quantity, expiration, owner);
        
        fridgemanager.getFridgeContents().add(food_to_fridge);
        fridgecontent.getItems().add(food_to_fridge);
        filehandler.saveObject(this);
    }    

    /**
     * Creates a new food item from input given by user and adds this to the freezer
     */
    @FXML
    private void addToFreezer() {
        removetext.setVisible(true);
        button_removefridge.setVisible(true);
        button_removefreezer.setVisible(true);
        button_removefreezer.setDisable(false);

        String food = textfield_food.getText();
        int quantity = Integer.valueOf(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_freezer = new Food(food, quantity, expiration, owner);

        fridgemanager.getFreezerContents().add(food_to_freezer);
        freezercontent.getItems().add(food_to_freezer);
        filehandler.saveObject(this);
    }    

    @FXML
    private void handleMouseClickFridge(MouseEvent mouseevent) {
        this.to_be_removed = fridgecontent.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleMouseClickFreezer(MouseEvent mouseevent) {
        this.to_be_removed = freezercontent.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleRemoveFromFridge() {
        fridgecontent.getItems().remove(to_be_removed);
        this.to_be_removed = null;
        if (fridgecontent.getItems().size() == 0) {
            button_removefridge.setDisable(true);
        }
        filehandler.saveObject(this);
    }

    @FXML
    private void handleRemoveFromFreezer() {
        freezercontent.getItems().remove(to_be_removed);
        this.to_be_removed = null;
        if (freezercontent.getItems().size() == 0) {
            button_removefreezer.setDisable(true);
        }
        filehandler.saveObject(this);
    }

}
