package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;

import com.practis.dto.NewScenarioInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.ScenarioEditPage;

public class ScenarioValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertScenarioGridRow(final NewScenarioInput inputData,
      final GridRow gridRow) {
    gridRow.get("Scenarios").shouldBe(matchText(inputData.getTitle()));
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

  /**
   * Assert elements on Add New Scenario page.
   */
  public static void assertElementsNewScenario() {
    scenarioCreatePage().getAddNewScenarioTitle().shouldBe(exactText("Add New Scenario"));
    scenarioCreatePage().getAddNewScenarioTitle().shouldBe(visible);
    scenarioCreatePage().getNotPublishTitle().shouldBe(exactText("Not Published Yet"));
    scenarioCreatePage().getNotPublishTitle().shouldBe(visible);

    scenarioCreatePage().getTitleField().shouldBe(visible);
    scenarioCreatePage().getTitleField().shouldBe(attribute("placeholder", "Scenario Title"));

    scenarioCreatePage().getDescriptionField().shouldBe(visible);
    scenarioCreatePage().getDescriptionField().shouldBe(attribute("placeholder", "Description"));

    scenarioCreatePage().getLabelsButton().shouldBe(visible);
    scenarioCreatePage().getLabelsButton().shouldBe(exactText("Labels"));

    scenarioCreatePage().getDownloadAsPdfButton().shouldBe(visible);

    scenarioCreatePage().getCustomerLinesTitle().shouldBe(visible);
    scenarioCreatePage().getCustomerLinesTitle().shouldBe(exactText("Customer Lines"));
    scenarioCreatePage().getCustomerLinesValue().shouldBe(visible);
    scenarioCreatePage().getCustomerLinesValue().shouldBe(exactText("0"));

    scenarioCreatePage().getRepLinesTitle().shouldBe(visible);
    scenarioCreatePage().getRepLinesTitle().shouldBe(exactText("Rep Lines"));
    scenarioCreatePage().getRepLinesValue().shouldBe(visible);
    scenarioCreatePage().getRepLinesValue().shouldBe(exactText("0"));

    scenarioCreatePage().getTotalDurationTitle().shouldBe(visible);
    scenarioCreatePage().getTotalDurationTitle().shouldBe(exactText("Total Duration"));
    scenarioCreatePage().getTotalDurationValue().shouldBe(visible);
    scenarioCreatePage().getTotalDurationValue().shouldBe(exactText("0m 0s"));

    scenarioCreatePage().getCustomerRepTitles().get(0).shouldBe(visible);
    scenarioCreatePage().getCustomerRepTitles().get(0).shouldBe(exactText("Customer"));
    scenarioCreatePage().getCustomerRepTitles().get(1).shouldBe(visible);
    scenarioCreatePage().getCustomerRepTitles().get(1).shouldBe(exactText("Rep"));

    scenarioCreatePage().getAddCustomerLine().shouldBe(visible);
    scenarioCreatePage().getAddCustomerLine().shouldBe(exactText("+ Add a customer line"));

    scenarioCreatePage().getAddARepLine().shouldBe(visible);
    scenarioCreatePage().getAddARepLine().shouldBe(exactText("Add a rep line +"));

    scenarioCreatePage().getPublishButton().shouldBe(visible);
    scenarioCreatePage().getPublishButton().shouldBe(exactText("Publish"));

    scenarioCreatePage().getSaveAsDraftButton().shouldBe(visible);
    scenarioCreatePage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
  }

}
