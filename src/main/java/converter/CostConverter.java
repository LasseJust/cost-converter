package converter;

import converter.calculation.CostCalculator;
import converter.model.CostModel;
import converter.model.CustomCostValue;
import converter.repo.CostModelRepository;
import converter.util.CostModelCombiner;

import java.util.Map;

/**
 * Cost converter
 */
public class CostConverter {

    private final CostModel globalCostModel;
    private final CostModelRepository costModelRepository;

    /**
     * Initialized a CostConverter.
     * @param costModelRepository repository used to lookup cost models (including the global model)
     */
    public CostConverter(CostModelRepository costModelRepository) {
        this.costModelRepository = costModelRepository;
        this.globalCostModel = costModelRepository.getGlobalCostModel();
    }

    /**
     * Calculates the cost of performing a corrective action.
     *
     * @param timeInMinutes time in minutes the task will take
     * @param monetaryCost monetary cost of the task, e.g. cost of spare parts
     * @param modelId id of a model that should be used for performing the calculation
     * @param argsCustomCosts additional custom factors used to adjust the calculated cost
     * @return the total calculated cost, rounded to the nearest whole number
     */
    public long calculateCost(double timeInMinutes, double monetaryCost, String modelId, Map<String, CustomCostValue> argsCustomCosts) {
        CostModel activeCostModel = costModelRepository.getCostModel(modelId)
                .map(localCostModel -> CostModelCombiner.combineCostModel(globalCostModel, localCostModel))
                .orElse(globalCostModel);
        if (argsCustomCosts != null) {
            activeCostModel.updateCustomCosts(argsCustomCosts);
        }
        return calculateCost(timeInMinutes, monetaryCost, activeCostModel);
    }

    public long calculateCost(double timeInMinutes, double monetaryCost, String modelId) {
        return calculateCost(timeInMinutes, monetaryCost, modelId, null);
    }

    private long calculateCost(double timeInMinutes, double monetaryCost, CostModel costModel) {
        return CostCalculator.calculateTotalCost(timeInMinutes, monetaryCost, costModel);
    }
}
