package fridge_manager.json;

import fridge_manager.core.FridgeManager;

public interface IFileHandler {

  /** Saves a FrigdeManager object to disc */
  void saveObject(FridgeManager fridge);

  /** Loads the saved FridgeManager object from disc */
  FridgeManager loadFridgeManager();
}
