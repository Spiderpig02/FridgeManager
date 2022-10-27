package fridgemanager.json;

import fridgemanager.core.FridgeManager;

/**
 * Interface for FileHandler.
*/
public interface InterfaceFileHandler {

  /**
   * Saves a FrigdeManager object to disc.
  */
  void saveObject(FridgeManager fridge);

  /**
   * Loads the saved FridgeManager object from disc.
  */
  FridgeManager loadFridgeManager();
}
