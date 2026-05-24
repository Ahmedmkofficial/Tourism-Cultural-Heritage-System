package service;

import java.util.ArrayList;
import model.Location;

public class TourismManager {

    private ArrayList<Location> locations;

    public TourismManager() {
        locations = new ArrayList<>();
    }

    // THIS METHOD MUST EXIST
    public void addLocation(Location location) {
        locations.add(location);
    }

    public void displayAllLocations() {
        for (Location l : locations) {
            System.out.println("- " + l.getName());
        }
    }

    public Location searchLocationByName(String name) {
        for (Location l : locations) {
            if (l.getName().equalsIgnoreCase(name)) {
                return l;
            }
        }
        return null;
    }
}