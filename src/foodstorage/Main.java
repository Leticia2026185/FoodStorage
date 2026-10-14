package foodstorage;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //create the CIRCULAR QUEUE (max 8)
        CircularQueue storage = new CircularQueue(8);

        int choice;

        do {
            System.out.println("\n=== FOOD STORAGE MENU ===");
            System.out.println("1. Add food");
            System.out.println("2. Remove food");
            System.out.println("3. Display food");
            System.out.println("4. Peek first food");
            System.out.println("5. Search food");
            System.out.println("6. Check if storage is empty");
            System.out.println("7. Check if storage is full");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            //menu choice must be a number
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); //clear buffer
            } else {
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine();
                choice = 0;
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter food name (Burger, Pizza, Fries, Sandwich, Hotdog): ");
                    String name = sc.nextLine();

                    //only the 5 food types are allowed
                    if (!name.equalsIgnoreCase("Burger") && !name.equalsIgnoreCase("Pizza")
                            && !name.equalsIgnoreCase("Fries") && !name.equalsIgnoreCase("Sandwich")
                            && !name.equalsIgnoreCase("Hotdog")) {
                        System.out.println("Invalid food. Choose Burger, Pizza, Fries, Sandwich or Hotdog.");
                        break;
                    }

                    System.out.print("Enter food weight: ");

                    //weight must be a number
                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid weight. Numbers only.");
                        sc.nextLine();
                        break;
                    }

                    double weight = sc.nextDouble();
                    sc.nextLine(); //clear buffer

                    //weight must be more than 0
                    if (weight <= 0) {
                        System.out.println("Invalid weight. Must be greater than 0.");
                        break;
                    }

                    System.out.print("Enter best-before date (YYYY-MM-DD): ");
                    String bestBefore = sc.nextLine();

                    try {
                        LocalDate expiryDate = LocalDate.parse(bestBefore);
                        LocalDate today = LocalDate.now();
                        LocalDate maxDate = today.plusWeeks(2);

                        //date cannot be in the past
                        if (expiryDate.isBefore(today)) {
                            System.out.println("Best-before date cannot be in the past.");
                            break;
                        }

                        //max 2 weeks from today
                        if (expiryDate.isAfter(maxDate)) {
                            System.out.println("Best-before date cannot be more than two weeks from today.");
                            break;
                        }

                    } catch (java.time.format.DateTimeParseException e) {
                        //wrong date format
                        System.out.println("Invalid date. Use YYYY-MM-DD.");
                        break;
                    }

                    //time added automatically
                    String storedAt = LocalDateTime.now().toString();

                    FoodItem food = new FoodItem(name, weight, bestBefore, storedAt);
                    //ENQUEUE: add at rear
                    storage.enqueue(food);
                    break;

                case 2:
                    //DEQUEUE: remove from front (FIFO)
                    FoodItem removedFood = storage.dequeue();
                    if (removedFood != null) System.out.println("Food removed: " + removedFood);
                    break;

                case 3:
                    //DISPLAY: front to rear
                    storage.display();
                    break;

                case 4:
                    //PEEK: first food in the queue
                    FoodItem top = storage.peek();
                    if (top != null) System.out.println("Top food: " + top);
                    break;

                case 5:
                    //check before asking the name
                    if (storage.isEmpty()) {
                        System.out.println("Food storage is empty.");
                        break;
                    }
                    System.out.print("Enter food name to search: ");
                    String searchName = sc.nextLine();
                    FoodItem foundFood = storage.search(searchName);
                    if (foundFood != null) System.out.println("Food found: " + foundFood);
                    else System.out.println("Food item not found.");
                    break;

                case 6:
                    if (storage.isEmpty()) System.out.println("Food storage is empty.");
                    else System.out.println("Food storage is not empty.");
                    break;

                case 7:
                    if (storage.isFull()) System.out.println("Food storage is full.");
                    else System.out.println("Food storage is not full.");
                    break;

                case 8:
                    System.out.println("Exiting Food Storage.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);

        sc.close();
    }
}