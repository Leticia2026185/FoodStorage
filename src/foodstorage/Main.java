package foodstorage;


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

        // Create a Scanner to read input entered by the user
        Scanner sc = new Scanner(System.in);

        // Create a circular queue with a maximum capacity of 8 food items
        CircularQueue storage = new CircularQueue(8);

        // Store the menu option selected by the user
        int choice;

        // Repeat the menu until the user chooses to exit
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

            // Check whether the menu choice is an integer
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // Clear the input buffer
            } else {
                // Show an error if the user enters something other than a number
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine(); // Remove the invalid input
                choice = 0;
                continue; // Return to the beginning of the menu
            }

            // Execute the operation selected by the user
            switch (choice) {

                case 1:
                    // Ask for the food item's name
                    System.out.print("Enter food name (Burger, Pizza, Fries, Sandwich, Hotdog): ");
                    String name = sc.nextLine();

                    //only the 5 food types are allowed
                    if (!name.equalsIgnoreCase("Burger") && !name.equalsIgnoreCase("Pizza")
                            && !name.equalsIgnoreCase("Fries") && !name.equalsIgnoreCase("Sandwich")
                            && !name.equalsIgnoreCase("Hotdog")) {
                        System.out.println("Invalid food. Choose Burger, Pizza, Fries, Sandwich or Hotdog.");
                        break;
                    }

                    // Ask for the food item's weight
                    System.out.print("Enter food weight: ");

                    // Check that the weight is a valid number
                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid weight. Numbers only.");
                        sc.nextLine(); // Remove the invalid input
                        break; // Cancel this operation
                    }

                    double weight = sc.nextDouble();
                    sc.nextLine();

                   //weight must be more than 0
                    if (weight <= 0) {
                        System.out.println("Invalid weight. Must be greater than 0.");
                        break;
                    }

                    System.out.print("Enter best-before date (YYYY-MM-DD): ");
                    String bestBefore = sc.nextLine();

                    try {
                        // Convert the entered text into a date
                        LocalDate expiryDate = LocalDate.parse(bestBefore);

                        // Get today's date and calculate the maximum allowed date
                        LocalDate today = LocalDate.now();
                        LocalDate maxDate = today.plusWeeks(2);

                        // Reject dates that are earlier than today
                        if (expiryDate.isBefore(today)) {
                            System.out.println("Best-before date cannot be in the past.");
                            break;
                        }

                        // Reject dates more than two weeks from today
                        if (expiryDate.isAfter(maxDate)) {
                            System.out.println("Best-before date cannot be more than two weeks from today.");
                            break;
                        }

                    } catch (java.time.format.DateTimeParseException e) {
                        // Handle dates that are invalid or incorrectly formatted
                        System.out.println("Invalid date. Use YYYY-MM-DD.");
                        break;
                    }

                    // Record the current date and time automatically
                    String storedAt = LocalDateTime.now().toString();

                    // Create a FoodItem object containing all the food details
                    FoodItem food = new FoodItem(
                            name, weight, bestBefore, storedAt);

                    // Add the new item to the rear of the circular queue
                    storage.enqueue(food);
                    break;

                case 2:
                    // Remove the first item from the queue (FIFO)
                    FoodItem removedFood = storage.dequeue();

                    // Display the removed item if one was available
                    if (removedFood != null) {
                        System.out.println("Food removed: " + removedFood);
                    }
                    break;

                case 3:
                    // Display all food items currently in storage
                    storage.display();
                    break;

                 case 4:
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
                    // Check whether the queue contains any food items
                    if (storage.isEmpty()) {
                        System.out.println("Food storage is empty.");
                    } else {
                        System.out.println("Food storage is not empty.");
                    }
                    break;

                case 7:
                    // Check whether the queue has reached its capacity
                    if (storage.isFull()) {
                        System.out.println("Food storage is full.");
                    } else {
                        System.out.println("Food storage is not full.");
                    }
                    break;

                case 8:
                    // Display a message before ending the application
                    System.out.println("Exiting Food Storage.");
                    break;

                default:
                    // Handle menu numbers that are not valid options
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);

        // Close the Scanner when the application finishes
        sc.close();
    }
}