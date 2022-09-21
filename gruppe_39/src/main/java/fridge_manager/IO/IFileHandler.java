package fridge_manager.IO;

import fridge_manager.FridgeManager;

public interface IFileHandler {

    /**
     * Saves a FrigdeManager object to disk
     */
    void saveObject(FridgeManager fridge);

    /**
     * Loads the saved FridgeManager object from disk
     */
    FridgeManager loadFridgeManager();

}
