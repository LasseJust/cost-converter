package cmd;

import converter.CostConverter;
import converter.model.CostModel;
import converter.model.CustomCostValue;
import converter.repo.CostModelRepository;
import converter.repo.InMemoryCostModelStore;

import java.util.HashMap;
import java.util.Map;

public class CostConverterCommandLineApp {

    /**
     * Parses command line arguments and runs the {@link CostConverter} library.
     *
     * @param args command line arguments
     */
    public long run(String[] args) {
        // Parse arguments
        double timeInMinutes = Double.parseDouble(args[0]);
        double monetaryCost = Double.parseDouble(args[1]);
        String modelId = args[2].toLowerCase();
        Map<String, CustomCostValue> customCostsFromArgs = getCustomCostsFromArgs(args);

        // Setup test data
        CostModelRepository costModelRepository = setupTestData();

        // Call library
        CostConverter costConverter = new CostConverter(costModelRepository);
        return costConverter.calculateCost(timeInMinutes, monetaryCost, modelId, customCostsFromArgs);
    }

    private Map<String, CustomCostValue> getCustomCostsFromArgs(String[] args) {
        Map<String, CustomCostValue> customCosts = new HashMap<>();
        for (int i = 3; i < args.length; i++) {
            String customCostStringValue = args[i];
            String[] customCostSplit = customCostStringValue.split(":");
            String customCostName = customCostSplit[0].toUpperCase();
            String customCostValue = customCostSplit[1].toUpperCase();
            customCosts.put(customCostName, CustomCostValue.valueOf(customCostValue));
        }
        return customCosts;
    }

    private CostModelRepository setupTestData() {
        InMemoryCostModelStore modelStore = new InMemoryCostModelStore();
        CostModel global = createCostModel("global", 300, CustomCostValue.LOW, CustomCostValue.HIGH);
        CostModel test1234 = createCostModel("model1234", 500, CustomCostValue.HIGH, CustomCostValue.MEDIUM);
        modelStore.saveCostModel(global);
        modelStore.saveCostModel(test1234);
        return modelStore;
    }

    private CostModel createCostModel(String modelId,
                                 double timeFactorHourly,
                                 CustomCostValue riskValue,
                                 CustomCostValue inconvenienceValue) {
        CostModel costModel = new CostModel(modelId, timeFactorHourly);
        costModel.updateCustomCosts("risk", riskValue);
        costModel.updateCustomCosts("inconvenience", inconvenienceValue);
        return costModel;
    }
}
