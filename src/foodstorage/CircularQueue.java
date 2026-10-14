package foodstorage;
// CIRCULAR QUEUE (FIFO)
// add at REAR, remove from FRONT
// first food in = first food out

public class CircularQueue {

    private FoodItem[] queue;
    private int front;    //FRONT: next food to be removed
    private int rear;     //REAR: last food added
    private int size;
    private int capacity; //max 8

    public CircularQueue(int capacity) {
        queue = new FoodItem[capacity];
        front = 0;
        rear = -1;
        size = 0;
        this.capacity = capacity;
    }

    //ENQUEUE: insert at rear - O(1)
    public void enqueue(FoodItem food) {
        if (size == capacity) {
            System.out.println("Food storage is full.");
            return;
        }
        //[CIRCULAR] capacity=8
        //rear (7+1)%8 ===> 0
        rear = (rear + 1) % capacity;
        queue[rear] = food;
        size++;
        System.out.println("Food added to storage.");
    }

    //DEQUEUE: delete from front - O(1)
    //[FIFO] the oldest food leaves first
    public FoodItem dequeue() {
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }
        FoodItem food = queue[front];
        queue[front] = null;
        //[CIRCULAR] front goes back to 0 after 7
        front = (front + 1) % capacity;
        size--;
        return food;
    }

    //PEEK: first food without removing - O(1)
    public FoodItem peek() {
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }
        return queue[front];
    }

    //check if empty - O(1)
    public boolean isEmpty() {
        return size == 0;
    }

    //check if full - O(1)
    public boolean isFull() {
        return size == capacity;
    }

    //DISPLAY: from front to rear - O(n)
    public void display() {
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return;
        }
        System.out.println("Food items in storage:");
        for (int i = 0; i < size; i++) {
            //[CIRCULAR] index wraps around the array
            System.out.println(queue[(front + i) % capacity]);
        }
    }

    //SEARCH: checks one by one from front - O(n)
    public FoodItem search(String searchName) {
        if (size == 0) {
            System.out.println("Food storage is empty.");
            return null;
        }
        for (int i = 0; i < size; i++) {
            //[CIRCULAR] index wraps around the array
            int index = (front + i) % capacity;
            if (queue[index].getName().equalsIgnoreCase(searchName)) {
                return queue[index];
            }
        }
        return null;
    }
}