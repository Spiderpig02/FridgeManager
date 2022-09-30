package fridge_manager.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fridge_manager.FridgeManager;

public class FileHandler implements IFileHandler {

    /**
     * The file name for saving and loading
     */
    public static String FileName = "FridgeSave.txt";

    /**
     * Method for saving a FridgeManager object to the specified location
     */
    public void saveObject(FridgeManager fridgemanager) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FileName));
            oos.writeObject(fridgemanager);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    /**
     * Loads the saved FridgeManager object from system to app
     * Returns the saved object if it exists, null otherwise
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
