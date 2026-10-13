
public class FoodItem {

    private String name;
    private double weight;
    private String bestBefore;
    private String storedAt;

    // Constructor: creates a food item with its details
    public FoodItem(String name, double weight, String bestBefore, String storedAt) {
        this.name = name;
        this.weight = weight;
        this.bestBefore = bestBefore;
        this.storedAt = storedAt;
    }

    // Returns the name of the food item
    public String getName() {
        return name;
    }
    
    // Returns the food item's details as readable text
    @Override
    public String toString() {
        return "Name: " + name
                + ", Weight: " + weight
                + ", Best Before: " + bestBefore
                + ", Stored At: " + storedAt;
    }

}
