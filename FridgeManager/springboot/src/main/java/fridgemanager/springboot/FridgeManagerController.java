package fridgemanager.springboot;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The service implementation.
 */

@RestController
@RequestMapping(FridgeManagerController.FridgeManagerServicePath)
public class FridgeManagerController {

  public static final String FridgeManagerServicePath = "springboot/fridgemanager";
  
  @Autowired
  private FridgeManagerService fridgeManagerService;
  
  @GetMapping
  public FridgeManager getFridgeManager() {
    return this.fridgeManagerService.getFridgeManager();
  }
  
  private void autoSave() {
    this.fridgeManagerService.autoSave();
  }
  
  @GetMapping(path = "/getFridgeContent")
  public List<Food> getFridgeContent() {
    return this.fridgeManagerService.getFridgeManager().getFridgeContents();
  }
  
  @GetMapping(path = "/getFreezerContent")
  public List<Food> getFreezerContent() {
    return this.fridgeManagerService.getFridgeManager().getFreezerContents();
  }
  
  @GetMapping(path = "/getFreezerSize")
  public int getFreezerSize() {
    return this.fridgeManagerService.getFridgeManager().getFreezerMaxsize();
  }
  
  @GetMapping(path = "/getFridgeSize")
  public int getFridgeSize() {
    return this.fridgeManagerService.getFridgeManager().getFridgeMaxsize();
  }
  
  /**
   * Remove object in fridge.
   */
  @DeleteMapping(path = "/removeFridgeContent")
  public boolean removeFridgeContent(@RequestBody Food food) {
    boolean removed = this.fridgeManagerService.getFridgeManager().removeFridgeContent(food);
    this.autoSave();
    return removed;
  }

  /**
   * Remove object in freezer.
   */
  @DeleteMapping(path = "/removeFreezerContent")
  public boolean removeFreezerContent(@RequestBody Food food) {
    boolean removed = this.fridgeManagerService.getFridgeManager().removeFreezerContent(food);
    this.autoSave();
    return removed;
  }

  /**
   * Replaces or adds a object in fridge.
   */
  @PutMapping(path = "/addFridgeContent")
  public boolean addFridgeContent(@RequestBody Food food) throws IllegalArgumentException {
    boolean tmp = false;
    try {
      this.fridgeManagerService.getFridgeManager().addFridgeContent(food);
      tmp = true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Food named \"" + food.getName() + "\" already exists");
    }
    this.autoSave();
    return tmp;
  }
  
  /**
   * Replaces or adds a object in freezer.
   */
  @PutMapping(path = "/addFreezerContent")
  public boolean addFreezerContent(@RequestBody Food food) throws IllegalArgumentException {
    boolean tmp = false;
    try {
      this.fridgeManagerService.getFridgeManager().addFreezerContent(food);
      tmp = true;
    } catch (Exception e) {
      throw new IllegalArgumentException("Food named \"" + food.getName() + "\" already exists");
    }
    this.autoSave();
    return tmp;
  }
}
