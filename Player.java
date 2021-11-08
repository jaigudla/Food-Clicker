import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {

    private static final String[] allUpgrades = {"CHEF", "FOOD_TRUCK", "STORE", "MARKET", "SUPERMARKET", "FACTORY", "INDUSTRY"}; //Array of all the upgrades
    private final HashMap<String, Double> upgradesCPS; //Hashmap of the cps for each upgrade
    private final HashMap<String, Double> upgradeBaseCost; //Hashmap of the base cost for each upgrade
    private HashMap<String, Integer> numUpgrades; //Hashmap of the number upgrades the player has bought
    private HashMap<String, Double> currentPrices; //Hashmap of the current price for each upgrade

    private List<String> upgrades; //ArrayList of all the upgrades bought by the player
    private double points; //Amount of points the player has
    private double cps; //The cps that the player has from their upgrades
    private String currentFood; //The current food that the player is on

    public Player() {
        upgrades = new ArrayList<String>();
        points = 0;
        cps = 0;
        currentFood = "BURGER";

        upgradesCPS = new HashMap<String, Double>();
        upgradeBaseCost = new HashMap<String, Double>();
        numUpgrades = new HashMap<String, Integer>();
        currentPrices = new HashMap<String, Double>();

        //setting the cps for each upgrade

        upgradesCPS.put("CHEF", 1/200.0 * 2);
        upgradesCPS.put("FOOD_TRUCK", 20/200.0 * 2);
        upgradesCPS.put("STORE", 100/200.0 * 2);
        upgradesCPS.put("MARKET", 200/200.0 * 2);
        upgradesCPS.put("SUPERMARKET", 400/200.0 * 2);
        upgradesCPS.put("FACTORY", 1000/200.0 * 2);
        upgradesCPS.put("INDUSTRY", 3000/200.0 * 2);

        //setting the base cost for each upgrade

        upgradeBaseCost.put("CHEF", 15.0);
        upgradeBaseCost.put("FOOD_TRUCK", 100.0);
        upgradeBaseCost.put("STORE", 1100.0);
        upgradeBaseCost.put("MARKET", 12000.0);
        upgradeBaseCost.put("SUPERMARKET", 130000.0);
        upgradeBaseCost.put("FACTORY", 1400000.0);
        upgradeBaseCost.put("INDUSTRY", 2000000.0);

        //setting all of the upgrade counts to 0

        for (String tier : allUpgrades) {
            numUpgrades.put(tier, 0);
        }

    }

    //getters and setters for the fields --------------------------------------------------

    public HashMap<String, Double> getCurrentPrices() {
        return currentPrices;
    }

    public HashMap<String, Integer> getNumUpgrades() {
        return numUpgrades;
    }

    public HashMap<String, Double> getUpgradesCPS() {
        return upgradesCPS;
    }

    public HashMap<String, Double> getUpgradeBaseCost() {
        return upgradeBaseCost;
    }

    public List<String> getUpgrades() {
        return this.upgrades;
    }

    public double getPoints() {
        return this.points;
    }

    public double getCPS(String tier) {
        return upgradesCPS.get(tier);
    }

    public String getFood() {
        return this.currentFood;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setCPS(double cps) {
        this.cps = cps;
    }

    public double getCPS() {
        return this.cps;
    }

    //--------------------------------------------------------------------------------------

    //method that lets the player buy upgrades

    public void buyUpgrade(String tier) {
        if (this.points >= currentPrices.get(tier)) {
            this.setPoints(this.getPoints() - currentPrices.get(tier));
            this.upgrades.add(tier);
            this.numUpgrades.put(tier, numUpgrades.get(tier) + 1);
        }
    }

    //method that updates all the current prices of the upgrades

    public void updateCurrentPrices() {
        for (String tier : allUpgrades) {
            currentPrices.put(tier, upgradeBaseCost.get(tier) * Math.pow(1.15, numUpgrades.get(tier)));
            //The function used to determine the current price is: base_cost * 1.15 ^ number_of_upgrades
        }
    }

    //method to reset all of the stats for the player
    //method is used only in the prestige method

    private void reset() {
        this.setPoints(0);
        this.upgrades.clear();
        this.setCPS(0);
        for (String tier : allUpgrades) {
            this.getNumUpgrades().put(tier, 0);
        }
    }
    
    //method that prestiges the player
    //prestiging resets all of the player's stats and upgrades and displays a new image icon

    public void prestige() {
        if (this.currentFood.equals("BURGER") && this.points >= 2000000.0 * 2) {
            this.currentFood = "PIZZA";
            this.reset();
        } else if (this.currentFood.equals("PIZZA") && this.points >= 2000000.0 * 3) {
            this.currentFood = "TACO";
            this.reset();
        }
    }

}
