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
     * Sets quantity
     * Throws IllegalArgumentException if value of quantity is negative.
     */
    public void setQuantity(int new_quantity) {
        if (new_quantity >= 0) {
            this.quantity = new_quantity;
        }
        else {
            throw new IllegalArgumentException("Cannot set amount of food to a negative number!");
        }
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
    /*
     * Method for updating the quantity of a food item
     */
    public void changeQuantity(int newquantity) throws IllegalArgumentException{
        if (newquantity < 0){
            throw new IllegalArgumentException("Quantity needs to have a positive value");
        }
        quantity=newquantity;
    }
    
    /**
     * returning the toString method
     */
    @Override
    public String toString() {
        return quantity+" "+name+", "+owner+" sin, går ut: "+expirationDate;
    }
}
