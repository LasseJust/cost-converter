package converter.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Model used for calculating the cost of a corrective action.
 */
public class CostModel {

    private final String modelId;
    private final Double timeFactorHourly;
    private final Map<String, CustomCostValue> customCosts;

    public CostModel(String modelId, Double timeFactorHourly) {
        this(modelId, timeFactorHourly, new HashMap<>());
    }

    public CostModel(Double timeFactorHourly, Map<String, CustomCostValue> customCosts) {
        this(UUID.randomUUID().toString(), timeFactorHourly, customCosts);
    }

    public CostModel(String modelId, Double timeFactorHourly, Map<String, CustomCostValue> customCosts) {
        this.modelId = modelId;
        this.timeFactorHourly = timeFactorHourly;
        this.customCosts = customCosts != null ? customCosts : new HashMap<>();
    }

    public String getModelId() {
        return modelId;
    }

    public Double getTimeFactorHourly() {
        return timeFactorHourly;
    }

    public CustomCostValue getCustomCostValue(String name) {
        return customCosts.get(name.toUpperCase());
    }

    public Map<String, CustomCostValue> getCustomCosts() {
        return customCosts;
    }

    /**
     * Updates the custom costs of the model.
     * If a custom cost with the same name already exists, it will be overwritten.
     *
     * @param name name of the custom cost
     * @param value value of the custom cost
     */
    public void updateCustomCosts(String name, CustomCostValue value) {
        if (value == null) {
            return;
        }
        this.customCosts.put(name.toUpperCase(), value);
    }

    /**
     * Updates the custom costs of the model.
     * If a custom cost with the same name already exists, it will be overwritten.
     *
     * @param customCosts custom costs to be added/updated
     */
    public void updateCustomCosts(Map<String, CustomCostValue> customCosts) {
        if (customCosts == null) {
            return;
        }
        customCosts.forEach(this::updateCustomCosts);
    }
}
