package fridgemanager.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fridgemanager.core.Food;
import fridgemanager.core.FridgeManager;

@RestController
@RequestMapping(FridgeManagerController.FridgeManagerServicePath)
public class FridgeManagerController {

    public static final String FridgeManagerServicePath = "fridgemanager";

    @Autowired
    private FridgeManagerService fridgeManagerService;

    @GetMapping
    public FridgeManager getFridgeManager() {
        return this.fridgeManagerService.getFridgeManager();
    }

    private void autoSave() {
        this.fridgeManagerService.autoSave();
    }

    @GetMapping(path = "/getFridgeContent")
    public List<Food> getFridgeContent() {
        return this.fridgeManagerService.getFridgeManager().getFridgeContents();
    }

    @GetMapping(path = "/getFreezerContent")
    public List<Food> getFreezerContent() {
        return this.fridgeManagerService.getFridgeManager().getFreezerContents();
    }

    @GetMapping(path = "/getFreezerSize")
    public int getFreezerSize() {
        return this.fridgeManagerService.getFridgeManager().getFreezerMaxsize();
    }

    @GetMapping(path = "/getFridgeSize")
    public int getFridgeSize() {
        return this.fridgeManagerService.getFridgeManager().getFridgeMaxsize();
    }

    @DeleteMapping(path = "/removeFridgeContent")
    public boolean removeFridgeContent(@RequestBody Food food) {
        boolean removed = this.fridgeManagerService.getFridgeManager().removeFridgeContent(food);
        this.autoSave();
        return removed;
    }

    @DeleteMapping(path = "/removeFreezerContent")
    public boolean removeFreezerContent(@RequestBody Food food) {
        boolean removed = this.fridgeManagerService.getFridgeManager().removeFreezerContent(food);
        this.autoSave();
        return removed;
    }

    @PutMapping(path = "/addFridgeContent")
    public boolean addFridgeContent(@RequestBody Food food) {
        boolean tmp = false;
        try {
            this.fridgeManagerService.getFridgeManager().addFridgeContent(food);
            tmp = true;
        } catch (Exception e) {
        }
        this.autoSave();
        return tmp;
    }

    @PutMapping(path = "/addFreezerContent")
    public boolean addFreezerContent(@RequestBody Food food) {
        boolean tmp = false;
        try {
            this.fridgeManagerService.getFridgeManager().addFreezerContent(food);
            tmp = true;
        } catch (Exception e) {
        }
        this.autoSave();
        return tmp;
    }
}
