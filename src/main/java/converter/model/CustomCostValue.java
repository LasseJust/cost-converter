package converter.model;

/**
 * Enum representing the possible values of the customer specific factors.
 */
public enum CustomCostValue {
    LOW("LOW", 10),
    MEDIUM("MEDIUM", 30),
    HIGH("HIGH", 100),
    NONE("NONE", 0);

    private final String label;
    private final double adjustmentPercentage;

    CustomCostValue(String label, double adjustmentPercentage) {
        this.label = label.toUpperCase();
        this.adjustmentPercentage = adjustmentPercentage;
    }

    public String getLabel() {
        return label;
    }

    public double getAdjustmentPercentage() {
        return adjustmentPercentage;
    }
}
