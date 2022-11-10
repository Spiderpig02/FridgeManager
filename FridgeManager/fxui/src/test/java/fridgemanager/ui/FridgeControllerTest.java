package fridgemanager.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import org.assertj.core.internal.bytebuddy.asm.Advice.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import fridge_manager.core.Food;
import fridge_manager.core.FridgeManager;
import fridge_manager.ui.FridgeController;



/**
 * Unit test for simple App.
 */
public class FridgeControllerTest extends ApplicationTest {
    
  private FridgeController controller;
  private FridgeManager fridgemanager;

  Food eple;

  /**
  * Initialize the JavaFX App used for testing
  */
  public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("FridgeApp.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      this.fridgemanager = this.controller.getFridgeManager();
      stage.setScene(new Scene(root));
      stage.show();
  }

  /**
  * Initialize a food element
  */
  @BeforeEach
  public void init() {
      eple=new Food("Eple", 3, LocalDate.of(2022,02,15), "Halvor");
      eple.setUnit("stk");
  }
  
  

  /**
  * Compare if two elements are equal
  */
  public void checkItem(Food food1, Food food2){
      assertEquals(food1.getName(), food2.getName());
      assertEquals(food1.getQuantity(), food2.getQuantity());
      assertEquals(food1.getExpirationDate(), food2.getExpirationDate());
      assertEquals(food1.getOwner(), food2.getOwner());
  }

  /**
  * Add Food-items into the textfields
  */
  public void addFoodItem(String name, String quantity, LocalDate expirationdate, String owner) {
      clickOn("#textfieldFood").write(name);
      clickOn("#textfieldQuantity").write(quantity);
      clickOn("#textfieldExpiration").write(expirationdate.toString());
      clickOn("#textfieldOwner").write(owner);
  }
}


