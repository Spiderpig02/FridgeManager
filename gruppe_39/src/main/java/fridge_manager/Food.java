package fridge_manager;

/**
    * Class that represent food objects
    */
public class Food {
    private String name;
    private int quantity;
    private String owner;
    private String expirationDate;
    
    /**
    * The constructor for the Food class
    */
    public Food(String name, int quantity, String expirationDate, String owner) throws IllegalArgumentException{
        this.name = name;
        this.expirationDate = expirationDate;
        this.owner = owner;
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity needs to have a positive value");
        }
        this.quantity = quantity;
    }

    /**
     * return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * return quantity
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * return owner
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * return expirationDate
     */
    public String getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * returning the toString method
     */
    @Override
    public String toString() {
        return "Food [expirationDate=" + expirationDate + ", name=" + name + ", owner=" + owner + ", quantity=" + quantity
                + "]";
    }
}
