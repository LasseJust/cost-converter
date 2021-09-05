package converter.repo;

import converter.model.CostModel;

import java.util.Optional;

/**
 * Repository for fetching model details.
 */
public interface CostModelRepository {

    /**
     * Gets the global model, containing values that will be used if not overwritten.
     * @return global cost model
     */
    CostModel getGlobalCostModel();

    /**
     * Fetches the model with the given id.
     * @param modelId the id of the model used for lookup
     * @return an optional containing the cost model, if one was found. Otherwise an empty optional
     */
    Optional<CostModel> getCostModel(String modelId);

    /**
     * Save a model.
     * @param costModel the model to be saved
     */
    void saveCostModel(CostModel costModel);
}
