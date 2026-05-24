package model;

public class Location {

    protected String name;
    protected String region;
    protected String category;

    protected String shortDescription;
    protected String fullHistory;
    protected String significance;
    protected String touristTips;

    public Location(String name,
                    String region,
                    String category,
                    String shortDescription,
                    String fullHistory,
                    String significance,
                    String touristTips) {

        this.name = name;
        this.region = region;
        this.category = category;
        this.shortDescription = shortDescription;
        this.fullHistory = fullHistory;
        this.significance = significance;
        this.touristTips = touristTips;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCategory() {
        return category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getFullHistory() {
        return fullHistory;
    }

    public String getSignificance() {
        return significance;
    }

    public String getTouristTips() {
        return touristTips;
    }

    public void displayInfo() {
        System.out.println("\nLOCATION DETAILS ");
        System.out.println("Name: " + name);
        System.out.println("Region: " + region);
        System.out.println("Category: " + category);
        System.out.println("Description: " + shortDescription);
        System.out.println("History: " + fullHistory);
        System.out.println("Significance: " + significance);
        System.out.println("Tourist Tips: " + touristTips);
        
    }
}