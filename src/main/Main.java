package main;

import model.HeritageSite;
import model.NaturalSite;
import model.Location;
import service.TourismManager;

public class Main {

    public static void main(String[] args) {

        TourismManager manager = new TourismManager();

        // -------------------------
        // HISTORICAL / CULTURAL SITES
        // -------------------------
        manager.addLocation(new HeritageSite(
                "Lalibela",
                "Amhara",
                "Rock-hewn churches of Lalibela",
                "Built in the 12th century under King Lalibela",
                "One of Ethiopia's most important religious sites",
                "Visit during religious festivals for best experience"
        ));

        manager.addLocation(new HeritageSite(
                "Axum Obelisks",
                "Tigray",
                "Ancient stone obelisks",
                "Remains of the Axumite Empire over 2000 years old",
                "Symbol of ancient Ethiopian civilization",
                "Visit archaeological museum nearby"
        ));

        manager.addLocation(new HeritageSite(
                "Harar Jugol",
                "Harar",
                "Walled historic city",
                "Developed between 7th–16th century as trade hub",
                "Important Islamic cultural heritage site",
                "Walk inside the old city to explore fully"
        ));

        // -------------------------
        // NATURAL SITES
        // -------------------------
        manager.addLocation(new NaturalSite(
                "Simien Mountains",
                "Amhara",
                "Mountain range with dramatic landscapes",
                "Formed by volcanic activity millions of years ago",
                "Home to rare wildlife like Gelada baboons",
                "Best for hiking and trekking"
        ));

        manager.addLocation(new NaturalSite(
                "Sof Omar Caves",
                "Oromia",
                "Huge limestone cave system",
                "Formed by Weyib River over thousands of years",
                "One of Africa's largest cave systems",
                "Bring guide for safe exploration"
        ));

        // -------------------------
        // OUTPUT SECTION
        // -------------------------

        System.out.println("====================================");
        System.out.println(" ETHIOPIAN TOURISM SYSTEM");
        System.out.println("====================================");

        System.out.println("\nALL LOCATIONS:");
        manager.displayAllLocations();

        System.out.println("\nSEARCH TEST (Lalibela):");
        Location result = manager.searchLocationByName("Lalibela");

        if (result != null) {
            System.out.println(result);
        } else {
            System.out.println("Location not found.");
        }

        System.out.println("\nNATURAL SITES:");
        for (Location l : manager.filterByCategory("Natural")) {
            System.out.println("- " + l.getName());
        }

        System.out.println("\nHISTORICAL SITES:");
        for (Location l : manager.filterByCategory("Historical")) {
            System.out.println("- " + l.getName());
        }

        System.out.println("\nSYSTEM RUN SUCCESSFUL");
    }
}