package model;

public class CulturalSite extends Location {

    public CulturalSite(String name,
                        String region,
                        String shortDescription,
                        String fullHistory,
                        String significance,
                        String touristTips) {

        super(name,
              region,
              "Cultural",
              shortDescription,
              fullHistory,
              significance,
              touristTips);
    }
 @Override
    public String getCategory() {
        return "Cultural";
}
