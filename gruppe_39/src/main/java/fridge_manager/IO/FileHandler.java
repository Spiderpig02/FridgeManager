package fridge_manager.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fridge_manager.FridgeManager;

public class FileHandler implements IFileHandler {

    /**
     * The file name for the saving and loading
     */
    public static String FileName = "FridgeSave.txt";

    /**
     * Method for saving a FidgeManager object to the specified location,
     * writes the object
     */
    public void saveObject(FridgeManager fridge) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileName));
            oos.writeObject(fridge);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the saved FridgeManager object from system to app
     * Returns the saved object if there was any, or a null object in any other case
     */
    public FridgeManager loadFridgeManager() {
        FridgeManager fridge = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FileName));
            fridge = (FridgeManager) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fridge;
    }
}
