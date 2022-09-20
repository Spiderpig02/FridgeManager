package fridge_manager;

import java.util.ArrayList;
import java.util.List;

public class FridgeManager {
    private List<Food> frigdecontents = new ArrayList<Food>();
    private List<Food> freezercontents = new ArrayList<Food>();
    private int freezermaxsize;
    private int fridgemaxsize;

    public void Frigde(int fridgemaxsize,int freezermaxsize) throws IllegalArgumentException{
        if (fridgemaxsize<0){
            throw new IllegalArgumentException("Freezer max size must be over 0");
        }
        if (freezermaxsize<0){
            throw new IllegalArgumentException("Fridge max size must be over 0");
        }
        this.fridgemaxsize=fridgemaxsize;
        this.freezermaxsize=freezermaxsize;
    }

    public List<Food> getFridgeContents(){
        return new ArrayList<>(frigdecontents);
    }
    public List<Food> getFreezerContents(){
        return new ArrayList<>(freezercontents);
    }

    public int getFreezerMaxsize(){
        return freezermaxsize;
    }

    public int getFridgeMaxsize(){
        return fridgemaxsize;
    }

    public void addFreezerContent(Food content){
        freezercontents.add(content);
    }

    public void addFridgeContent(Food content){
        frigdecontents.add(content);
    }
}