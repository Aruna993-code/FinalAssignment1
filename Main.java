//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        CourierManager manager = new CourierManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===============================");
            System.out.println(" Welcome to Delivery Dilemmas!");
            System.out.println("===============================");
            System.out.println("1. Add a new package");
            System.out.println("2. Display all packages and shipping costs");
            System.out.println("3. Sort packages by weight");
            System.out.println("4. Search for a package by Tracking ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter package type (Standard/Express): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter tracking ID: ");
                    String trackingID = scanner.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = scanner.nextLine();
                    System.out.print("Enter weight: ");
                    double weight = scanner.nextDouble();

                    try {
                        if (type.equalsIgnoreCase("Standard")) {
                            manager.addPackage(new StandardPackage(trackingID, destination, weight));
                        } else if (type.equalsIgnoreCase("Express")) {
                            manager.addPackage(new ExpressPackage(trackingID, destination, weight));
                        } else {
                            System.out.println("Invalid package type. Please choose Standard or Express.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    manager.displayPackages();
                    break;
                case 3:
                    manager.bubbleSortByWeight();
                    manager.displayPackages();
                    break;
                case 4:
                    System.out.print("Enter Tracking ID to search: ");
                    String searchID = scanner.nextLine();
                    Package foundPackage = manager.binarySearchByTrackingID(searchID);
                    if (foundPackage != null) {
                        System.out.println("Package Found: " + foundPackage);
                    } else {
                        System.out.println("No package found with Tracking ID: " + searchID);
                    }
                    break;
                case 5:
                    running = false;
                    System.out.println("Thank you for using Delivery Dilemmas!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
