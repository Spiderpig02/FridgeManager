package fridgemanager.core;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Food-class.
*/
public class Food {
  private String name;
  private int quantity;
  private String owner;
  private LocalDate expirationDate;
  private String unit;
  private String uniqueId;

  /**
   * Creates a new Food-object from input.
   * 
   * @param name name of Food
   * @param unit unit of Food
   * @param quantity quantity of Food
   * @param expirationDate date of expiration to Food
   * @param owner owner of Food
   * @throws IllegalArgumentException if invalid quantity (<0) is given as argument.
   */
  public Food(String name, String unit, int quantity, LocalDate expirationDate, String owner)
      throws IllegalArgumentException {
    this.name = name;
    this.unit = unit;
    this.expirationDate = expirationDate;
    this.owner = owner;
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity needs to have a positive value");
    }
    this.quantity = quantity;

    this.uniqueId = UUID.randomUUID().toString();
  }

  /**
   * Creates a new Food-object from input.
   * 
   * @param name name of Food.
   * @param unit unit of Food.
   * @param quantity quantity of Food.
   * @param expirationDate date of expiration to Food.
   * @param owner owner of Food.
   * @param uuid unique id to the Food object
   * @throws IllegalArgumentException if invalid quantity (<0) is given as argument.
   */
  public Food(String name, String unit, int quantity, LocalDate expirationDate,
      String owner, String uuid) throws IllegalArgumentException {
    
    this.name = name;
    this.unit = unit;
    this.expirationDate = expirationDate;
    this.owner = owner;
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity needs to have a positive value");
    }
    this.quantity = quantity;
    this.uniqueId = uuid;
  }

  /**
   * Getter name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter ID.
   */
  public String getId() {
    return this.uniqueId;
  }

  /**
   * Getter quantity.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Getter owner.
   */
  public String getOwner() {
    return this.owner;
  }

  /**
   * Getter expirationDate.
  */
  public LocalDate getExpirationDate() {
    return this.expirationDate;
  }

  /**
   * Getter unit.
  */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets new quantity for Food-item.
   * 
   * @param newQuantity.
   * @throws IllegalArgumentException if newQuantity < 0.
   */
  public void setQuantity(int newQuantity) {
    if (newQuantity >= 0) {
      this.quantity = newQuantity;
    } else {
      throw new IllegalArgumentException("Cannot set amount of food to a negative number!");
    }
  }

  /**
   * Sets new unit for Food-item.
   * 
   * @param unit
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * toString method for Food-object which shows all relevant data.
  */
  @Override
  public String toString() {
    String expDate = expirationDate.toString();
    return quantity + " " + unit + " " + name + ", " + owner + " sin, går ut: " + expDate;
  }
}
