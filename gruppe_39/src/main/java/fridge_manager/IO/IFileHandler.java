package fridge_manager.IO;

import fridge_manager.FridgeManager;

public interface IFileHandler {

    void saveObject(FridgeManager fridge);

    FridgeManager loaFridgeManager();

}
