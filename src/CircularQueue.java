
public class CircularQueue {

    // This array stores all the food items in the queue.
    // Each position of the array can hold one FoodItem object.
    private FoodItem[] queue;

    // front - tells us where the first food item is.
    // This is the item that will be removed first.
    private int front;

    // rear - tells us the position where the last food item was added.
    private int rear;

    // 'size' counts how many food items are currently stored.
    // For example, if there are 3 food items, size will be 3.
    private int size;

    // capacity - is the maximum number of food items allowed.
    // In our application, the capacity is 8.
    private int capacity;

    // The constructor creates an empty circular queue.
    // It receives the maximum capacity when the queue is created.
    public CircularQueue(int capacity) {

        // Create an array that can hold the chosen number of food items.
        queue = new FoodItem[capacity];

        // Start at index 0 because the queue has no items yet.
        front = 0;

        // Start at -1 because no item has been added yet.
        rear = -1;

        // The queue starts empty, so the current size is 0.
        size = 0;

        // Save the maximum capacity received by the constructor.
        this.capacity = capacity;
    }

    // Adds a new food item to the rear of the circular queue.
    public void enqueue(FoodItem food) {

        // Check whether the queue has reached its maximum capacity.
        if (size == capacity) {
            System.out.println("Food storage is full.");
            return; // Stop here because there is no space for another item.
        }

        // Move rear to the next position.
        // The % operator makes rear return to index 0
        // when it reaches the end of the array.
        rear = (rear + 1) % capacity;

        // Place the new food item at the calculated position.
        queue[rear] = food;

        // Increase size because one item has been added.
        size++;

        System.out.println("Food added to storage.");
    }

    // Removes and returns the first food item in the queue.
    // This follows FIFO: First In, First Out.
    public FoodItem dequeue() {

        // Check whether there are any food items to remove.
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null; // Return null because there is no item to remove.
        }

        // Save the first food item before removing it.
        FoodItem food = queue[front];

        // Clear the position so the removed item is no longer stored there.
        queue[front] = null;

        // Move front to the next position.
        // If front reaches the end of the array, it returns to index 0.
        front = (front + 1) % capacity;

        // Decrease size because one item has been removed.
        size--;

        // Return the item that was removed.
        return food;
    }

    // Returns the first food item without removing it from the queue.
    public FoodItem peek() {

        // Check whether there is an item available to view.
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }

        // Return the item at front.
        // The item remains in the queue.
        return queue[front];
    }

    // Checks whether the queue has no food items.
    public boolean isEmpty() {

        // If size is 0, the queue is empty.
        // Otherwise, it contains at least one item.
        return size == 0;
    }

    // Checks whether the queue has reached its maximum capacity.
    public boolean isFull() {

        // The queue is full when size is equal to capacity.
        return size == capacity;
    }

    // Displays all food items currently stored in the queue.
    public void display() {

        // If size is 0, there are no items to display.
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return; // Stop because there are no items to display.
        }

        System.out.println("Food items in storage:");

        // Start at front and display each stored item.
        // The loop runs 'size' times, not through every array position.
        for (int i = 0; i < size; i++) {

            // Calculate the position of the next item.
            // The % operator allows the calculation to wrap around
            // to the beginning of the array when necessary.
            int index = (front + i) % capacity;

            // Print the food item at the calculated position.
            System.out.println(queue[index]);
        }
    }

    // Searches for a food item using its name.
    public FoodItem search(String searchName) {

        // Check whether there are any items to search.
        if (isEmpty()) {
            System.out.println("Food storage is empty.");
            return null;
        }

        // Check each stored item, starting from front.
        for (int i = 0; i < size; i++) {

            // Calculate the position of the item being checked.
            // This also works when the queue wraps around the array.
            int index = (front + i) % capacity;

            // Compare the stored name with the name entered by the user.
            // equalsIgnoreCase allows uppercase and lowercase letters
            // to match, so "Burger" and "burger" are treated equally.
            if (queue[index].getName().equalsIgnoreCase(searchName)) {

                // Return the matching food item when it is found.
                return queue[index];
            }
        }

        // Return null when no food item has the requested name.
        return null;
    }
}
