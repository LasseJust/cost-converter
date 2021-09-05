package converter.calculation;

import converter.model.CostModel;
import converter.model.CustomCostValue;

import java.util.Collection;

public class CostCalculator {

    private static final int MINUTES_PER_HOUR = 60;

    /**
     * Calculates the total cost of the action, including the custom cost adjustments.
     *
     * @param timeInMinutes time in minutes for the action
     * @param monetaryCost monetary cost of the action (e.g. cost of spare parts)
     * @param costModel model used for the calculation, including the relevant timefactor and custom costs
     * @return the total cost of the actions, rounded to the nearest whole number
     */
    public static long calculateTotalCost(double timeInMinutes, double monetaryCost, CostModel costModel) {
        double baseCost = calculateBaseCost(timeInMinutes, costModel.getTimeFactorHourly(), monetaryCost);
        double adjustedCost = calculateAdjustedCost(baseCost, costModel.getCustomCosts().values());
        return Math.round(adjustedCost);
    }

    /**
     * Performs basic cost calculation.
     *
     * @param timeInMinutes time in minutes for the action
     * @param timeFactor hourly cost
     * @param monetaryCost monetary cost of the action (e.g. cost of spare parts)
     * @return calculated cost
     */
    public static double calculateBaseCost(double timeInMinutes, double timeFactor, double monetaryCost) {
        double timeInHours = timeInMinutes / MINUTES_PER_HOUR;
        return timeInHours * timeFactor + monetaryCost;
    }

    /**
     * Calculates the total adjusted cost.
     *
     * @param calculatedCost calculated cost before any adjustments
     * @param customCosts list of custom adjustments to apply
     * @return the total cost after applying all the adjustments
     */
    public static double calculateAdjustedCost(double calculatedCost, Collection<CustomCostValue> customCosts) {
        if (customCosts == null) {
            return calculatedCost;
        }
        Double totalCostAdjustment = customCosts.stream()
                .map(CustomCostValue::getAdjustmentPercentage)
                .map(percentAdjustment -> getMonetaryAdjustment(calculatedCost, percentAdjustment))
                .reduce((double) 0, Double::sum);

        return calculatedCost + totalCostAdjustment;
    }

    /**
     * Calculates the monetary value of the percentage adjustment.
     *
     * @param nonAdjustedCost calculated cost before adjustment is made
     * @param percentageAdjustment percentage cost increase
     * @return monetary amount that should be added to the original cost
     */
    public static double getMonetaryAdjustment(double nonAdjustedCost, double percentageAdjustment) {
        return nonAdjustedCost / 100 * percentageAdjustment;
    }
}
