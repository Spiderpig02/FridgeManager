package fridge_manager;

//FXML-imports
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;


public class FridgeController {
    
    @FXML private TextField textfield_matvare;
    @FXML private TextField textfield_antall;
    @FXML private TextField textfield_utlopsdato;
    @FXML private TextField textfield_eier;
    @FXML private Button fridge_button;
    @FXML private Button freezer_button;
    @FXML private ListView fridgecontent;

    private FridgeManager fridgemanager;

    @FXML
    private void addToFridge() {
        
    }    

    @FXML
    public void initialize() {
        this.fridgemanager = new FridgeManager();
        
    }

}
