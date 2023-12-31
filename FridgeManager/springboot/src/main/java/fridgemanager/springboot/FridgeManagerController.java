package fridgemanager.springboot;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for communication with springboot-server.
 * Logs every command. 
 */
@RestController
@RequestMapping(FridgeManagerController.FridgeManagerServicePath)
public class FridgeManagerController {

  public static final String FridgeManagerServicePath = "fridgemanager";
  private static final Logger logger = LoggerFactory.getLogger(FridgeManagerController.class);
  
  @Autowired
  private FridgeManagerService fridgeManagerService;
  
  /**
   * Returns a fridgemanager-object.

   * @return FridgeManager
   */
  @GetMapping
  public FridgeManager getFridgeManager() {
    logger.debug("getFridgeManager");
    return this.fridgeManagerService.getFridgeManager();
  } 
  
  /**
   * Saves state of fridgemanager to file. 
   */
  private void autoSave() {
    logger.debug("autoSaved");
    this.fridgeManagerService.autoSave();
  }
  
  /**
   * Retrieve and return content in fridge. 

   * @return fridgecontent
   */
  @GetMapping(path = "/getFridgeContent")
  public List<Food> getFridgeContent() {
    logger.debug("getFridgeContent");
    return this.fridgeManagerService.getFridgeManager().getFridgeContents();
  }
  
  /**
   * Retrieve and return content in freezer.

   * @return ListzFood> freezercontent
   */
  @GetMapping(path = "/getFreezerContent")
  public List<Food> getFreezerContent() {
    logger.debug("getFreezerContent");
    return this.fridgeManagerService.getFridgeManager().getFreezerContents();
  }
  
  /**
   * Retrieves and returns max number of items allowed in fridge.

   * @return int fridgemaxsize
   */
  @GetMapping(path = "/getFrigdeMaxsize")
  public int getFrigdeMaxsize() {
    logger.debug("getFrigdeMaxsize");
    return this.fridgeManagerService.getFridgeManager().getFridgeMaxsize();
  }

  /**
   * Retrieves and returns max number of items allowed in freezer.

   * @return int freezermaxsize
   */
  @GetMapping(path = "/getFreezerMaxsize")
  public int getFreezerMaxsize() {
    logger.debug("getFreezerMaxsize");
    return this.fridgeManagerService.getFridgeManager().getFreezerMaxsize();
  }
  
  /**
   * Removes Food-item from Fridge.

   * @param id id of the Food to be removed.
   * @return Food that has been removed.
   */
  @DeleteMapping(path = "/removeFridgeContent/{id}")
  public boolean removeFridgeContent(@PathVariable("id") String id) {
    logger.debug("removeFridgeContent: " + id);
    boolean removed = this.fridgeManagerService.getFridgeManager().removeFridgeContent(id);
    this.autoSave();
    return removed;
  }
  
  /**
   * Removes Food-item from Freezer.

   * @param id id of the Food to be removed.
   * @return Food that has been removed.
   */
  @DeleteMapping(path = "/removeFreezerContent/{id}")
  public boolean removeFreezerContent(@PathVariable("id") String id) {
    logger.debug("removeFreezerContent: " + id);
    boolean removed = this.fridgeManagerService.getFridgeManager().removeFreezerContent(id);
    this.autoSave();
    return removed;
  }
  
  /**
   * Sets new quantity for Food.

   * @param quantity new quantity.
   * @param food the Food object.
   * @return true if it was added, false if it is replaced.
   */
  @PutMapping(path = "/setQuantity/{quantity}")
  public boolean setQuantity(@PathVariable("quantity") int quantity, @RequestBody Food food) {
    logger.debug(food.toString());
    System.out.println(this.fridgeManagerService.getFridgeManager()
        .setQuantity(quantity, food.getId()));
    this.autoSave();
    return true;
  }
  
  /**
   * Add new Food-item to Fridge.

   * @param food the Food object.
   * @return true if it was added, false otherwise.
   */
  @PostMapping(path = "/addFridgeContent")
  public boolean addFridgeContent(@RequestBody Food food) {
    boolean tmp = false;
    try {
      this.fridgeManagerService.getFridgeManager().addFridgeContent(food);
      tmp = true;
      logger.debug(food.toString());
    } catch (Exception e) {
      System.out.println("Could not add Food to Fridge");
    }
    this.autoSave();
    return tmp;
  }
  
  /**
   * Add new Food-item to Freezer.

   * @param food the Food object.
   * @return true if it was added, false otherwise.
   */
  @PostMapping(path = "/addFreezerContent")
  public boolean addFreezerContent(@RequestBody Food food) {
    boolean tmp = false;
    try {
      this.fridgeManagerService.getFridgeManager().addFreezerContent(food);
      tmp = true;
      logger.debug(food.toString());
    } catch (Exception e) {
      System.out.println("Could not add Food to Freezer");
    }
    this.autoSave();
    return tmp;
  }
}
