package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewScenarioInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.ChallengeEditPage;
import com.practis.web.selenide.page.company.ScenarioEditPage;

public class ScenarioValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertScenarioGridRow(final NewScenarioInput inputData,
      final GridRow gridRow) {
    gridRow.get("Scenarios").shouldBe(matchText(".*" + inputData.getTitle()));
  }

  /**
   * Assert data on edit page with input.
   */
  public static void assertScenarioData(final NewScenarioInput inputData,
      final ScenarioEditPage scenarioEditPage) {
    scenarioEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    scenarioEditPage.getDescriptionField().shouldBe(text(inputData.getDescription()));
  }

  /**
   * Assert Title on edit page with input.
   */
  public static void assertScenarioTitle(final NewScenarioInput inputData,
      final ScenarioEditPage scenarioEditPage) {
    scenarioEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));

  }

}
