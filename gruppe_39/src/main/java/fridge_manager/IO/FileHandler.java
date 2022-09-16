package fridge_manager.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fridge_manager.FridgeManager;

public class FileHandler implements IFileHandler {

    public void saveObject(FridgeManager fridge) {
        try {
            FileOutputStream fileOStream = new FileOutputStream(
                    new File(FridgeManager.class.getResourceAsStream("IO/Fridg.ser").toString()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOStream);

            objectOutputStream.writeObject(fridge);

            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FridgeManager loaFridgeManager() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(new File(FridgeManager.class.getResourceAsStream("IO/Fridg.ser").toString())));
            FridgeManager fridgeManager = (FridgeManager) objectInputStream.readObject();
            return fridgeManager;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
