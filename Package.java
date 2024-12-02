import java.util.regex.*;
import java.util.ArrayList;
import java.util.Collections;

abstract class Package {
    protected String trackingID;
    protected String destination;
    protected double weight;

    public Package(String trackingID, String destination, double weight) {
        if (!validateTrackingID(trackingID)) {
            throw new IllegalArgumentException("Invalid tracking ID. Must match pattern: PKG12345.");
        }
        if (!validateDestination(destination)) {
            throw new IllegalArgumentException("Invalid destination. Must include street name and number (e.g., 123 Elm Street).");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive number.");
        }
        this.trackingID = trackingID;
        this.destination = destination;
        this.weight = weight;
    }

    public abstract double calculateShippingCost();

    public static boolean validateTrackingID(String trackingID) {
        return trackingID.matches("PKG\\d{5}");
    }

    public static boolean validateDestination(String destination) {
        return destination.matches("\\d+\\s+\\w+(\\s+\\w+)*\\s+Street");
    }

    @Override
    public String toString() {
        return "Tracking ID: " + trackingID + " | Destination: " + destination +
                " | Weight: " + String.format("%.2f", weight) +
                " | Cost: $" + String.format("%.2f", calculateShippingCost());
    }
}
class StandardPackage extends Package {
    public StandardPackage(String trackingID, String destination, double weight) {
        super(trackingID, destination, weight);
    }

    @Override
    public double calculateShippingCost() {
        return weight * 2.5;
    }
}

class ExpressPackage extends Package {
    public ExpressPackage(String trackingID, String destination, double weight) {
        super(trackingID, destination, weight);
    }

    @Override
    public double calculateShippingCost() {
        return weight * 4.0;
    }
}


class CourierManager {
    private ArrayList<Package> packages = new ArrayList<>();

    public void addPackage(Package pkg) {
        packages.add(pkg);
        System.out.println("Package added successfully!");
    }

    public void displayPackages() {
        if (packages.isEmpty()) {
            System.out.println("No packages to display.");
        } else {
            System.out.println("Package List:");
            for (Package pkg : packages) {
                System.out.println(pkg);
            }
        }
    }

    public void bubbleSortByWeight() {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = 0; j < packages.size() - i - 1; j++) {
                if (packages.get(j).weight > packages.get(j + 1).weight) {
                    Collections.swap(packages, j, j + 1);
                }
            }
        }
        System.out.println("Packages sorted by weight!");
    }

    public Package binarySearchByTrackingID(String trackingID) {
        packages.sort((a, b) -> a.trackingID.compareTo(b.trackingID));
        int left = 0, right = packages.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int comparison = packages.get(mid).trackingID.compareTo(trackingID);
            if (comparison == 0) {
                return packages.get(mid);
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}

