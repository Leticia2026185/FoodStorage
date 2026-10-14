package foodstorage;

public class FoodItem {

    //details of one food item
    private String name;
    private double weight;
    private String bestBefore;
    private String storedAt;

    public FoodItem(String name, double weight, String bestBefore, String storedAt) {
        this.name = name;
        this.weight = weight;
        this.bestBefore = bestBefore;
        this.storedAt = storedAt;
    }

    //used by search
    public String getName() {
        return name;
    }

    //used to print the item
    @Override
    public String toString() {
        return "Name: " + name
                + ", Weight: " + weight
                + ", Best Before: " + bestBefore
                + ", Stored At: " + storedAt;
    }
}