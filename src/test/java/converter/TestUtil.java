package converter;

import converter.model.CostModel;
import converter.model.CustomCostValue;

import java.util.HashMap;
import java.util.UUID;

public class TestUtil {

    public static CostModel createCostModel(String modelId, Double timeFactor, CustomCostValue riskValue, CustomCostValue inconvenienceValue) {
        CostModel costModel = new CostModel(modelId, timeFactor, new HashMap<>());
        costModel.updateCustomCosts("risk", riskValue);
        costModel.updateCustomCosts("inconvenience", inconvenienceValue);
        return costModel;
    }

    public static CostModel createCostModel(String modelId, int timeFactor, CustomCostValue riskValue, CustomCostValue inconvenienceValue) {
        return createCostModel(modelId, (double)timeFactor, riskValue, inconvenienceValue);
    }

    public static CostModel createCostModel(Double timeFactor, CustomCostValue riskValue, CustomCostValue inconvenienceValue) {
        return createCostModel(UUID.randomUUID().toString(), timeFactor, riskValue, inconvenienceValue);
    }

    public static CostModel createCostModel(int timeFactor, CustomCostValue riskValue, CustomCostValue inconvenienceValue) {
        return createCostModel((double)timeFactor, riskValue, inconvenienceValue);
    }
}
