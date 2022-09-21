package fridge_manager;

public class Food {
    private String name;
    private int quantity;
    private String owner;
    private String expirationDate;

    public Food(String name, int quantity, String expirationDate, String owner) throws IllegalArgumentException{
        this.name = name;
        this.expirationDate = expirationDate;
        this.owner = owner;
        if (quantity < 0){
            throw new IllegalArgumentException("Quantity needs to have a positive value");
        }
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getexpirationDate() {
        return this.expirationDate;
    }

    @Override
    public String toString() {
        return "Food [expirationDate=" + expirationDate + ", name=" + name + ", owner=" + owner + ", quantity=" + quantity
                + "]";
    }

    

}
