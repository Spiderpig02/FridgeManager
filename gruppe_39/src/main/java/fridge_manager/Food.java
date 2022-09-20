package fridge_manager;

public class Food {
    private String name;
    private int quantity;
    private String owner;
    private String expiringDate;

    public Food(String name, int quantity, String owner, String expiringDate) throws IllegalArgumentException{
        this.name = name;
        this.owner = owner;
        this.expiringDate = expiringDate;
        if(quantity < 0){
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

    public String getExpiringDate() {
        return this.expiringDate;
    }

    @Override
    public String toString() {
        return "Food [expiringDate=" + expiringDate + ", name=" + name + ", owner=" + owner + ", quantity=" + quantity
                + "]";
    }

    

}
