package converter;

import converter.model.CustomCostValue;
import converter.repo.InMemoryCostModelStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostConverterTest {

    private CostConverter costConverter;

    @BeforeEach
    void setup() {
        InMemoryCostModelStore costModelRepository = new InMemoryCostModelStore();
        costModelRepository.saveCostModel(TestUtil.createCostModel("global", 300, CustomCostValue.LOW, CustomCostValue.HIGH));
        costModelRepository.saveCostModel(TestUtil.createCostModel("model1234", 500, CustomCostValue.HIGH, CustomCostValue.MEDIUM));
        costConverter = new CostConverter(costModelRepository);
    }

    @Test
    void calculateCost() {
        Map<String, CustomCostValue> customCosts = new HashMap<>();
        customCosts.put("inconvenience", CustomCostValue.NONE);

        long cost = costConverter.calculateCost(0.5, 275.50, "model1234", customCosts);

        assertEquals(559, cost);
    }

    @Test
    void calculateCost_customCostsUsed() {
        Map<String, CustomCostValue> customCosts = new HashMap<>();
        customCosts.put("risk", CustomCostValue.NONE);
        customCosts.put("inconvenience", CustomCostValue.NONE);

        long cost = costConverter.calculateCost(0.5, 275.50, "model1234", customCosts);

        assertEquals(280, cost);
    }

    @Test
    void calculateCost_usingGlobalModelIfLocalNotFound() {
        long cost = costConverter.calculateCost(60, 100, "unknown", null);

        assertEquals(840, cost);
    }
}