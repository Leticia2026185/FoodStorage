
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
    
    // Adds a food item to the circular queue
    public void enqueue(FoodItem food) {

        // Check if the storage is full
        if (size == capacity) {
            System.out.println("Food storage is full.");
            return;
        }

        // Move rear to the next available position
        rear = (rear + 1) % capacity;

        // Store the food item and update the count
        queue[rear] = food;
        size++;

        System.out.println("Food added to storage.");
    }

    // Removes and returns the first food item
    public FoodItem dequeue() {

        // Check if the storage is empty
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }

        // Get the first food item
        FoodItem food = queue[front];

        // Clear the position that was removed
        queue[front] = null;

        // Move front to the next position
        front = (front + 1) % capacity;

        // Decrease the number of food items
        size--;

        return food;
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

    // Displays all food items currently in storage
    public void display() {

        // Check if the storage is empty
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return;
        }

        // Display the food items in queue order
        System.out.println("Food items in storage:");

        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;
            System.out.println(queue[index]);
        }
    }


    // Searches for a food item by name
    public FoodItem search(String searchName) {

        // Check if the storage is empty
        if (isEmpty()) {
            System.out.println("Food storage is empty.");
            return null;
        }

        // Check each food item in the circular queue
        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;

            // Compare the food item's name with the search name
            if (queue[index].getName().equalsIgnoreCase(searchName)) {
                return queue[index];
            }
        }

        // Return null if the food item was not found
        return null;
    }

}
