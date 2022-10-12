package fridge_manager.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains food and handles adding and removing
 */
public class FridgeManager implements Serializable {
    private List<Food> frigdecontents = new ArrayList<Food>();
    private List<Food> freezercontents = new ArrayList<Food>();
    private int freezermaxsize;
    private int fridgemaxsize;

    /**
     * Constructor that validates that sizes are not below zero
     */
    public FridgeManager(int fridgemaxsize, int freezermaxsize) throws IllegalArgumentException {
        if (fridgemaxsize < 0) {
            throw new IllegalArgumentException("Freezer max size must be over 0");
        }
        if (freezermaxsize < 0) {
            throw new IllegalArgumentException("Fridge max size must be over 0");
        }
        this.fridgemaxsize = fridgemaxsize;
        this.freezermaxsize = freezermaxsize;
    }

    /**
     * return a copy of the contents
     */
    public List<Food> getFridgeContents() {
        return new ArrayList<>(frigdecontents);
    }

    /**
     * return a copy of the contents
     */
    public List<Food> getFreezerContents() {
        return new ArrayList<>(freezercontents);
    }

    /**
     * return FreezerMaxSize
     */
    public int getFreezerMaxsize() {
        return freezermaxsize;
    }

    /**
     * return fridgemaxsize
     */
    public int getFridgeMaxsize() {
        return fridgemaxsize;
    }

    /**
     * Method for adding freezercontent
     */
    public void addFreezerContent(Food content) throws IllegalArgumentException {
        if (freezercontents.size() == freezermaxsize) {
            throw new IllegalArgumentException();
        } else {
            freezercontents.add(content);
        }

    }

    /**
     * Method for adding fridgecontent
     */
    public void addFridgeContent(Food content) throws IllegalArgumentException {
        if (frigdecontents.size() == fridgemaxsize) {
            throw new IllegalArgumentException();
        } else {
            frigdecontents.add(content);
        }

    }

    /**
     * Method for removing fridge content
     * Returns true if something is removed, false if nothing is removed
     * Input remove is what is to be removed
     */
    public boolean removeFridgeContent(Food remove) {

        if (frigdecontents.contains(remove)) {
            frigdecontents.remove(remove);
            return true;
        }
        return false;

    }

    /**
     * Method for removing fridge content
     * Returns true if something is removed, false if nothing is removed
     * Input remove is what is to be removed
     */
    public boolean removeFreezerContent(Food remove) {
        if (freezercontents.contains(remove)) {
            freezercontents.remove(remove);
            return true;
        }
        return false;
    }
}