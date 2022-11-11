package fridgemanager.springboot;

import java.util.List;

import org.springframework.stereotype.Service;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;
import fridgemanager.json.FileHandler;

@Service
public class FridgeManagerService {

    private FileHandler filhander;
    private FridgeManager fridgeManager;

    public FridgeManagerService(FridgeManager fridgeManager) {
        this.fridgeManager = fridgeManager;
        this.filhander = new FileHandler("RestServerSave.txt");
    }

    public FridgeManagerService() {
        this.filhander = new FileHandler("RestServerSave.txt");
        this.fridgeManager = inizializeFridgeManager();
    }

    public FridgeManager getFridgeManager() {
        return this.fridgeManager;
    }

    public void setFridgeManager(FridgeManager fridgeManager) {
        this.fridgeManager = fridgeManager;
        this.autoSave();
    }

    private FridgeManager inizializeFridgeManager() {
        FridgeManager tmp = this.filhander.loadFridgeManager();
        if (tmp == null) {
            tmp = new FridgeManager(25, 25);
        }
        return tmp;
    }

    public void autoSave() {
        this.filhander.saveObject(this.fridgeManager);
    }

    public void setFridgeContent(List<Food> list) {
        this.fridgeManager.setFridgeContents(list);
    }

    public void setFreezerContent(List<Food> list) {
        this.fridgeManager.setFreezerContents(list);
    }

}
