package model;

public class NaturalSite extends Location {

    public NaturalSite(String name,
                       String region,
                       String shortDescription,
                       String fullHistory,
                       String significance,
                       String touristTips) {

        super(name,
              region,
              "Natural",
              shortDescription,
              fullHistory,
              significance,
              touristTips);
    }
    @Override
    public String getCategory() {
        return "Natural";
}
