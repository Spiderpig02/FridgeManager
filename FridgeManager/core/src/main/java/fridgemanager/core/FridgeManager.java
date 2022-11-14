package fridgemanager.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains food-items and handles adding and removing from fridge or freezer
*/
public class FridgeManager {
  private List<Food> frigdecontents = new ArrayList<Food>();
  private List<Food> freezercontents = new ArrayList<Food>();
  private int freezermaxsize;
  private int fridgemaxsize;

  /**
   * Creates a new FridgeManager-object with associated max-size 
   * of fridge and freezer. 
   * @param fridgemaxsize
   * @param freezermaxsize
   * @throws IllegalArgumentException if invalid input is given (sizes < 0)
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
   * Returns a list containing food-items in fridge
  */
  public List<Food> getFridgeContents() {
    return new ArrayList<>(frigdecontents);
  }

  /**
   * Returns a list containing food-items in freezer
  */
  public List<Food> getFreezerContents() {
    return new ArrayList<>(freezercontents);
  }

  /**
   * Getter freezermaxsize
  */
  public int getFreezerMaxsize() {
    return freezermaxsize;
  }

  /**
   * Getter fridgemaxsize
  */
  public int getFridgeMaxsize() {
    return fridgemaxsize;
  }

  /**
   * Adds a food-item to the fridge
   * @param content - food to be added
   * @throws IllegalArgumentException if fridge is full (number of items = max size)
   */
  public void addFridgeContent(Food content) throws IllegalArgumentException {
    if (frigdecontents.size() == fridgemaxsize) {
      throw new IllegalArgumentException();
    } else {
      frigdecontents.add(content);
    }
  }

  /**
   * Adds a food-item to the freezer
   * @param content - food to be added
   * @throws IllegalArgumentException if freezer is full 
   */
  public void addFreezerContent(Food content) throws IllegalArgumentException {
    if (freezercontents.size() == freezermaxsize) {
      throw new IllegalArgumentException();
    } else {
      freezercontents.add(content);
    }
  }

  /**
   * Removes a given food-item from the fridge
   * @param remove - food to be removed
   * @returns true if item exists in fridge and was removed succesfully, false if not
   */
  public boolean removeFridgeContent(Food remove) {
    if (frigdecontents.contains(remove)) {
      frigdecontents.remove(remove);
      return true;
    }
    return false;
  }

  /**
   * Removes a given food-item from the freezer
   * @param remove - food to be removed
   * @return true if item exists in fridge and was removed succesfully, false if not
   */
  public boolean removeFreezerContent(Food remove) {
    if (freezercontents.contains(remove)) {
      freezercontents.remove(remove);
      return true;
    }
    return false;
  }
}
