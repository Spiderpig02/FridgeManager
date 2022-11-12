package fridgemanager.core;

import java.time.LocalDate;

/**
 * Food class.
*/
public class Food {
  private String name;
  private int quantity;
  private String owner;
  private LocalDate expirationDate;
  private String unit;

  /**
   * Constructor. 
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
  }

  /**
   * Getter name.
  */
  public String getName() {
    return this.name;
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
   * Setter quantity.
  */
  public void setQuantity(int newQuantity) {
    if (newQuantity >= 0) {
      this.quantity = newQuantity;
    } else {
      throw new IllegalArgumentException("Cannot set amount of food to a negative number!");
    }
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * Change quantity, given that quantity >= 0.
  */
  public void changeQuantity(int newQuantity) throws IllegalArgumentException {
    if (newQuantity < 0) {
      throw new IllegalArgumentException("Quantity needs to have a positive value");
    }
    quantity = newQuantity;
  }

  /**
   * toString method for Food object.
  */
  @Override
  public String toString() {
    String expDate = expirationDate.toString();
    return quantity + " " + unit + " " + name + ", " + owner + " sin, går ut: " + expDate;
  }
}
