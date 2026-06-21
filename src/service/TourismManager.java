package service;

import java.util.ArrayList;
import model.Location;

public class TourismManager {

    private ArrayList<Location> locations;

    public TourismManager() {
        locations = new ArrayList<>();
    }

    // Add a new location
    public void addLocation(Location location) {
        locations.add(location);
    }

    // Display all location names
    public void displayAllLocations() {

        if (locations.isEmpty()) {
            System.out.println("No tourism locations available.");
            return;
        }

        for (Location location : locations) {
            System.out.println("- " + location.getName());
        }
    }

    // Search location by name
    public Location searchLocationByName(String name) {

        for (Location location : locations) {

            if (location.getName().equalsIgnoreCase(name)) {
                return location;
            }
        }

        return null;
    }

    // Filter locations by category
    public ArrayList<Location> filterByCategory(String category) {

        ArrayList<Location> filteredLocations = new ArrayList<>();

        for (Location location : locations) {

            if (location.getCategory().equalsIgnoreCase(category)) {
                filteredLocations.add(location);
            }
        }

        return filteredLocations;
    }

    // Return all locations
    public ArrayList<Location> getAllLocations() {
        return locations;
    }
    


public boolean deleteLocationByName(String name) {

    for (int i = 0; i < locations.size(); i++) {

        if (locations.get(i).getName().equalsIgnoreCase(name)) {
            locations.remove(i);
            return true;
        }
    }

    return false;
}
}