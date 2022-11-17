package fridgemanager.springboot;

import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;
import org.springframework.stereotype.Service;

/**
 * Configures the FridgeManager-service.
 */
@Service
public class FridgeManagerService {

  private FileHandler filhander;
  private FridgeManager fridgeManager;
  
  /**
   * Constructor FridgeManagerService.
   * @param fridgeManager
   */
  public FridgeManagerService(FridgeManager fridgeManager) {
    this.fridgeManager = fridgeManager;
    this.filhander = new FileHandler("RestServerSave.json");
  }
  
  /**
   * Constructor FridgeManagerService.
   */
  public FridgeManagerService() {
    this.filhander = new FileHandler("RestServerSave.json");
    this.fridgeManager = initializeFridgeManager();
  }

  /**
   * Getter FridgeManager
   * @return fridgemanager
   */
  public FridgeManager getFridgeManager() {
    return this.fridgeManager;
  }

  /**
   * Sets new FridgeManager and saves.
   * @param fridgeManager
   */
  public void setFridgeManager(FridgeManager fridgeManager) {
    this.fridgeManager = fridgeManager;
    this.autoSave();
  }
  
  /**
   * Initializes a fridgemanager-object.
   * @return fridgemanager
   */
  private FridgeManager initializeFridgeManager() {
    FridgeManager tmp = this.filhander.loadFridgeManager();
    if (tmp == null) {
      tmp = new FridgeManager(25, 25);
    }
    return tmp;
  }
  
  /**
   * Saves state of program/fridgemanager. 
   */
  public void autoSave() {
    this.filhander.saveObject(this.fridgeManager);
  }
}
