package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;

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
    scenarioCreatePage().getLabelsButtonName().shouldBe(exactText("Labels"));
    scenarioCreatePage().getLabelsButtonName().shouldBe(visible);

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

  /**
   * Assert elements on Edit Scenario page.
   */
  public static void assertElementsEditScenario() {
    scenarioEditPage().getArchiveButton().shouldBe(visible);
    scenarioEditPage().getArchiveButton().shouldBe(exactText("Archive"));

    scenarioEditPage().getEditButton().shouldBe(visible);
    scenarioEditPage().getEditButton().shouldBe(exactText("Edit"));

    scenarioEditPage().getCreatedField().shouldBe(visible);

    scenarioEditPage().getDescriptionField().shouldBe(visible);

    scenarioEditPage().getSymbolCount().shouldBe(visible);

    scenarioEditPage().getPublishedField().shouldBe(visible);
    scenarioEditPage().getPublishedField().shouldBe(matchText("Published"));

    scenarioEditPage().getLabels().shouldBe(visible);
    scenarioEditPage().getLabels().shouldBe(exactText("Labels"));

    scenarioEditPage().getGetPdf().shouldBe(visible);

    scenarioEditPage().getTitleField().shouldBe(visible);

    scenarioEditPage().getRepTitle().shouldBe(visible);

    scenarioEditPage().getCustomerTitle().shouldBe(visible);

    scenarioEditPage().getGenerateForAll().shouldBe(visible);
    scenarioEditPage().getGenerateForAll().shouldBe(exactText("Generate for All"));

    scenarioEditPage().getPlayAll().shouldBe(visible);
    scenarioEditPage().getPlayAll().shouldBe(exactText("Play All"));

    scenarioEditPage().getScenarioPreview().shouldBe(visible);
    scenarioEditPage().getScenarioPreview().shouldBe(exactText("Preview"));

    scenarioEditPage().getCustomerLine().shouldBe(visible);
    scenarioEditPage().getCustomerLine().shouldBe(exactText("Customer Lines"));

    scenarioEditPage().getRepLine().shouldBe(visible);
    scenarioEditPage().getRepLine().shouldBe(exactText("Rep Lines"));

    scenarioEditPage().getTotalDuration().shouldBe(visible);
    scenarioEditPage().getTotalDuration().shouldBe(exactText("Total Duration"));

    scenarioEditPage().getScenarioPlayCustomer().shouldBe(visible);
    scenarioEditPage().getScenarioPlayCustomer().shouldBe(exactText("Play"));

    scenarioEditPage().getScenarioPlayRep().shouldBe(visible);
    scenarioEditPage().getScenarioPlayRep().shouldBe(exactText("Play"));


  }

}
