/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lleti
 */
    
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        // Create the scanner to read user input
        Scanner sc = new Scanner(System.in);

        // Create the food storage with a maximum capacity of 8 items
        CircularQueue storage = new CircularQueue(8);

        int choice;

        // Keep showing the menu until the user chooses to exit
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

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine();
                choice = 0;
                continue;
}

            // Execute the operation selected by the user
            switch (choice) {

                
                case 1:
                    // Ask the user for the food details
                    System.out.print("Enter food name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter food weight: ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid weight. Numbers only.");
                        sc.nextLine();
                        break;
                    }

                    double weight = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter best-before date (YYYY-MM-DD): ");
                    String bestBefore = sc.nextLine();

                    try {
                        LocalDate expiryDate = LocalDate.parse(bestBefore);
                        LocalDate today = LocalDate.now();
                        LocalDate maxDate = today.plusWeeks(2);

                        if (expiryDate.isBefore(today)) {
                            System.out.println("Best-before date cannot be in the past.");
                            break;
                        }

                        if (expiryDate.isAfter(maxDate)) {
                            System.out.println("Best-before date cannot be more than two weeks from today.");
                            break;
                        }

                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Invalid date. Use YYYY-MM-DD.");
                        break;
                    }

                    String storedAt = LocalDateTime.now().toString();

                    // Create a new food item
                    FoodItem food = new FoodItem(
                            name, weight, bestBefore, storedAt);

                    // Add the food item to the circular queue
                    storage.enqueue(food);
                    break;

                    
                case 2:
                      FoodItem removedFood = storage.dequeue();

                    if (removedFood != null) {
                        System.out.println("Food removed: " + removedFood);
                    }
                    break;

                case 3:
                    storage.display();
                    break;

                case 4:
                    System.out.println(storage.peek());
                    break;

                case 5:
                    System.out.print("Enter food name to search: ");
                    String searchName = sc.nextLine();

                    FoodItem foundFood = storage.search(searchName);

                    if (foundFood != null) {
                        System.out.println(foundFood);
                    } else {
                        System.out.println("Food item not found.");
                    }
                    break;

                case 6:
                    
                    if (storage.isEmpty()) {
                        System.out.println("Food storage is empty.");
                    } else {
                        System.out.println("Food storage is not empty.");
                    }
                    break;

                case 7:
                   
                    if (storage.isFull()) {
                        System.out.println("Food storage is full.");
                    } else {
                        System.out.println("Food storage is not full.");
                    }
                    break;

                case 8:
                    System.out.println("Exiting Food Storage.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);

        // Close the scanner when the program finishes
        sc.close();
    }
}


