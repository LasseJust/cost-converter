package converter.util;

import converter.TestUtil;
import converter.model.CostModel;
import converter.model.CustomCostValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CostModelCombinerTest {

    @Test
    void combineCostModel_localOverwritesGlobal() {
        CostModel global = TestUtil.createCostModel(300, CustomCostValue.LOW, CustomCostValue.HIGH);
        CostModel local = TestUtil.createCostModel(500, CustomCostValue.NONE, CustomCostValue.MEDIUM);

        CostModel combined = CostModelCombiner.combineCostModel(global, local);

        assertEquals(500, combined.getTimeFactorHourly());
        assertEquals(CustomCostValue.NONE, combined.getCustomCostValue("risk"));
        assertEquals(CustomCostValue.MEDIUM, combined.getCustomCostValue("inconvenience"));
    }

    @Test
    void combineCostModel_globalUsedIfLocalValuesMissing() {
        CostModel global = TestUtil.createCostModel(300, CustomCostValue.LOW, CustomCostValue.HIGH);
        CostModel local = TestUtil.createCostModel(null, null, null);

        CostModel combined = CostModelCombiner.combineCostModel(global, local);

        assertEquals(300, combined.getTimeFactorHourly());
        assertEquals(CustomCostValue.LOW, combined.getCustomCostValue("risk"));
        assertEquals(CustomCostValue.HIGH, combined.getCustomCostValue("inconvenience"));
    }
}