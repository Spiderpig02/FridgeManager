package fridgemanager.core;

import java.util.UUID;

/**
 * Food class.
 */
public class Food {
  private String name;
  private int quantity;
  private String owner;
  private String expirationDate;
  private String uniqueID;

  /**
   * Constructor.
   */
  public Food(String name, int quantity, String expirationDate, String owner)
      throws IllegalArgumentException {
    this.name = name;
    this.expirationDate = expirationDate;
    this.owner = owner;
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity needs to have a positive value");
    }
    this.quantity = quantity;

    this.uniqueID = UUID.randomUUID().toString();
  }

  /**
   * Constructor.
   */
  public Food(String name, int quantity, String expirationDate, String owner, String uuid)
      throws IllegalArgumentException {
    this.name = name;
    this.expirationDate = expirationDate;
    this.owner = owner;
    if (quantity < 0) {
      throw new IllegalArgumentException("Quantity needs to have a positive value");
    }
    this.quantity = quantity;

    this.uniqueID = uuid;
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
  public String id() {
    return this.uniqueID;
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
  public String getExpirationDate() {
    return this.expirationDate;
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
    return quantity + " " + name + ", " + owner + " sin, går ut: " + expirationDate;
  }
}
