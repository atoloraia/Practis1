package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.scenarioModule;

public class ScenarioSelectionValidator {

    /** Assert Scenarios model on Feed - Filters modal. */
    public static void assertEmptyScenarioModule() {
        scenarioModule().getSearchField().shouldBe(visible);
        scenarioModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        scenarioModule().getSearchField().shouldBe(attribute("disabled", "true"));
        scenarioModule().getSearchField().shouldBe(attribute("type", "text"));
        scenarioModule().getSearchFieldIcon().shouldBe(visible);
        scenarioModule().getNoScenarioYetText().shouldBe(visible);
        scenarioModule().getNoScenarioYetText().shouldBe(exactText(("No Scenarios yet")));
        scenarioModule().getNoScenarioYetIcon().shouldBe(visible);
    }
}
