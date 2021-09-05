package converter.repo;

import converter.exception.CostModelNotFoundException;
import converter.model.CostModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * I assume the models would be fetched from a DB in a real-world implementation.
 * For this test app I use a simple HashMap to store values in-memory.
 */
public class InMemoryCostModelStore implements CostModelRepository {

    private static final String GLOBAL_COST_MODEL_NAME = "global";

    private static final Map<String, CostModel> COST_MODEL_STORE = new HashMap<>();

    @Override
    public CostModel getGlobalCostModel() {
        return getCostModel(GLOBAL_COST_MODEL_NAME)
                .orElseThrow(() -> new CostModelNotFoundException("Global cost model not found"));
    }

    @Override
    public Optional<CostModel> getCostModel(String modelId) {
        return Optional.ofNullable(COST_MODEL_STORE.get(modelId.toLowerCase()));
    }

    @Override
    public void saveCostModel(CostModel costModel) {
        COST_MODEL_STORE.put(costModel.getModelId(), costModel);
    }
}
