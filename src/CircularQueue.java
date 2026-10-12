
public class CircularQueue {

    // Array used to store the food items
    private FoodItem[] queue;

    // Position of the first food item in the queue
    private int front;

    // Position of the last food item added
    private int rear;

    // Number of food items currently in storage
    private int size;

    // Maximum number of food items the storage can hold
    private int capacity;

    // Constructor: creates an empty queue with a fixed capacity
    public CircularQueue(int capacity) {
        queue = new FoodItem[capacity];
        front = 0;
        rear = -1;
        size = 0;
        this.capacity = capacity;
    }
    
    
    // Returns the first food item without removing it
    public FoodItem peek() {

        // Check if the storage is empty
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }

        // Return the food item at the front of the queue
        return queue[front];
    }
 
    // Checks whether the food storage is empty
    public boolean isEmpty() {

        // The storage is empty when there are no food items
        return size == 0;
    }
    
    // Checks whether the food storage is full
    public boolean isFull() {

        // The storage is full when size reaches capacity
        return size == capacity;
    }


}
