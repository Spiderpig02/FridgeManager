package fridge_manager.ui;

//FXML-imports
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;
import fridge_manager.json.FileHandler;
import javafx.fxml.FXML;


public class FridgeController {
    
    @FXML private TextField textfield_food;
    @FXML private TextField textfield_quantity;
    @FXML private TextField textfield_expiration;
    @FXML private TextField textfield_owner;
    @FXML private Text removetext;
    @FXML private Button fridge_button;
    @FXML private Button freezer_button;
    @FXML private Button removebutton;
    @FXML private ListView<Food> fridgecontent;
    @FXML private ListView<Food> freezercontent;
    
    

    private FridgeManager fridgemanager;
    private Food to_be_removed;
    private FileHandler filehandler;
    private Boolean infridge;
    private Boolean infreezer;

    /**
     * Initializes Controller by creating a new fridgemanager-object
     */
    @FXML
    public void initialize() {
        this.fridgemanager = new FridgeManager(10, 10);
        this.filehandler = new FileHandler();

        removetext.setVisible(false);
        removebutton.setVisible(false);
        removebutton.setDisable(true);
    }

    /**
     * Creates a new food item from input given by user and adds this to the fridge
     */
    @FXML
    private void addToFridge() {
        removetext.setVisible(true);
        removebutton.setVisible(true);
        removebutton.setDisable(false);

        String food = textfield_food.getText();
        int quantity =  Integer.parseInt(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_fridge = new Food(food, quantity, expiration, owner);
        
        fridgemanager.getFridgeContents().add(food_to_fridge);
        fridgecontent.getItems().add(food_to_fridge);
        filehandler.saveObject(fridgemanager);
    }    

    /**
     * Creates a new food item from input given by user and adds this to the freezer
     */
    @FXML
    private void addToFreezer() {
        removetext.setVisible(true);
        removebutton.setVisible(true);
        removebutton.setDisable(false);

        String food = textfield_food.getText();
        int quantity = Integer.valueOf(textfield_quantity.getText());
        String expiration = textfield_expiration.getText();
        String owner = textfield_owner.getText();
        Food food_to_freezer = new Food(food, quantity, expiration, owner);

        fridgemanager.getFreezerContents().add(food_to_freezer);
        freezercontent.getItems().add(food_to_freezer);
        filehandler.saveObject(fridgemanager);
    }    

    @FXML
    private void handleMouseClickFridge(MouseEvent mouseevent) {
        this.to_be_removed = fridgecontent.getSelectionModel().getSelectedItem();
        infridge = true;
    }

    @FXML
    private void handleMouseClickFreezer(MouseEvent mouseevent) {
        this.to_be_removed = freezercontent.getSelectionModel().getSelectedItem();
        infreezer = true;
    }

    @FXML
    private void handleRemove() {
        if (infridge == true) {
            fridgecontent.getItems().remove(to_be_removed);
            infridge = false;
        }
        else if (infreezer == true) {
            freezercontent.getItems().remove(to_be_removed);
            infreezer = false;
        }
        this.to_be_removed = null;
        if (fridgecontent.getItems().size() == 0 && freezercontent.getItems().size() == 0) {
            removebutton.setDisable(true);
        }
        filehandler.saveObject(fridgemanager);
    }

}
