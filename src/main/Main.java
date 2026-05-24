package main;

import model.*;
import service.TourismManager;

public class Main {

    public static void main(String[] args) {

        TourismManager manager = new TourismManager();

        
        // SAMPLE DATA (TEST DATA ONLY)
        

        manager.addLocation(new HeritageSite(
                "Lalibela",
                "Amhara",
                "Rock-hewn churches carved into volcanic rock",
                "Built in the 12th century during the Zagwe dynasty as a spiritual New Jerusalem",
                "UNESCO World Heritage Site and major religious center",
                "Visit early morning for ceremonies"
        ));

        manager.addLocation(new HeritageSite(
                "Axum Obelisks",
                "Tigray",
                "Ancient stone obelisks and ruins of an empire",
                "Capital of the Aksumite Empire, one of the greatest ancient civilizations in Africa",
                "Symbol of early Ethiopian civilization and Christianity",
                "Hire a guide for full explanation"
        ));

        manager.addLocation(new NaturalSite(
                "Simien Mountains",
                "Amhara",
                "Dramatic mountain ranges and wildlife",
                "Formed through volcanic activity and erosion over millions of years",
                "UNESCO Natural Heritage Site with unique biodiversity",
                "Bring warm clothes"
        ));

        manager.addLocation(new NaturalSite(
                "Sof Omar Caves",
                "Oromia",
                "Large underground cave system with river passages",
                "Carved by the Web River over thousands of years",
                "One of Africa’s most extensive cave systems",
                "Visit with a guide for safety"
        ));

        manager.addLocation(new CulturalSite(
                "Harar Jugol",
                "Harar",
                "Walled historic city with unique Islamic architecture",
                "Developed between 7th–16th century as a trading and religious hub",
                "Important cultural heritage center in Ethiopia",
                "Explore on foot to experience the old city"
        ));

        
        // SIMPLE TEST
        

        System.out.println("ETHIOPIAN TOURISM SYSTEM \n");

        System.out.println("Available Locations:");
        manager.displayAllLocations();

        System.out.println("\nSearch Example (type name exactly):");

        String searchName = "Harar Jugol"; // you can later replace with Scanner input

        Location result = manager.searchLocationByName(searchName);

        if (result != null) {
            result.displayInfo();
        } else {
            System.out.println("Location not found.");
        }
    }
}