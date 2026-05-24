package model;

public class HeritageSite extends Location {

    public HeritageSite(String name,
                        String region,
                        String shortDescription,
                        String fullHistory,
                        String significance,
                        String touristTips) {

        super(name,
              region,
              "Historical",
              shortDescription,
              fullHistory,
              significance,
              touristTips);
    }
}