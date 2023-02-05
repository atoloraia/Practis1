package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;

public class FilterValidator {

    /** Assert filter elements. */
    public static void assertFiltersElementsDefaultState() {
        filter().getSelectedFilterCriteriaCounter().shouldBe(exactText("0 Selected"));
        filter().getClearButton().shouldBe(visible);
        filter().getClearButton().shouldBe(disabled);
        filter().getApplyFilterButton().shouldBe(visible);
        filter().getApplyFilterButton().shouldBe(enabled);
    }

    /** Assert filter elements. */
    public static void assertFiltersElementsSelectedState(String counter) {
        filter().getSelectedFilterCriteriaCounter().shouldBe(exactText(counter));
        filter().getClearButton().shouldBe(visible);
        filter().getClearButton().shouldBe(enabled);
        filter().getApplyFilterButton().shouldBe(visible);
        filter().getApplyFilterButton().shouldBe(enabled);
    }
}
