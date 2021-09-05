package converter.util;

import converter.model.CostModel;

/**
 * Util for combining cost models, as cost models are treated as immutable.
 */
public class CostModelCombiner {

    /**
     * Combines the global cost model with the local cost model.
     * The global timefactor and custom costs will be used if not overwritten by the local model.
     *
     * @param globalCostModel global cost model
     * @param localCostModel local cost model
     * @return combined cost model
     */
    public static CostModel combineCostModel(CostModel globalCostModel, CostModel localCostModel) {
        if (localCostModel == null) {
            return globalCostModel;
        }
        if (globalCostModel == null) {
            return localCostModel;
        }
        Double timeFactor = localCostModel.getTimeFactorHourly() != null ? localCostModel.getTimeFactorHourly() : globalCostModel.getTimeFactorHourly();

        CostModel combinedModel = new CostModel(timeFactor, globalCostModel.getCustomCosts());
        combinedModel.updateCustomCosts(localCostModel.getCustomCosts());
        return combinedModel;
    }
}
