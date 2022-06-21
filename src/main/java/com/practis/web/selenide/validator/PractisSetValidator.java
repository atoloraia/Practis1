package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;

import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.PractisSetEditPage;

public class PractisSetValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertPractisSetGridRow(final NewPractisSetInput inputData,
      final GridRow gridRow) {
    gridRow.get("Practis Sets").shouldBe(matchText(".*" + inputData.getTitle()));
  }

  /**
   * Assert input on edit page.
   */
  public static void assertPracrisSetInput(final NewPractisSetInput inputData,
      final PractisSetEditPage practisSetEditPage) {
    practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    practisSetEditPage.getDescriptionField()
        .shouldBe(attribute("value", inputData.getDescription()));
  }

  /**
   * Assert Title on edit page.
   */
  public static void assertPracrisSetTitle(final NewPractisSetInput inputData,
      final PractisSetEditPage practisSetEditPage) {
    practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
  }

  /**
   * Assert elements on New Practis Set page.
   */
  public static void assertElementsNewPractisSet() {

    practisSetCreatePage().getAddNewPractisSetTitle().shouldBe(visible);

    practisSetCreatePage().getNotPublishedYetText().shouldBe(visible);
    practisSetCreatePage().getNotPublishedYetText().shouldBe(exactText("Not Published Yet"));

    practisSetCreatePage().getTitleField().shouldBe(visible);
    practisSetCreatePage().getTitleField().shouldBe(attribute("placeholder", "Practis Set Title"));

    practisSetCreatePage().getAddLabels().shouldBe(visible);
    practisSetCreatePage().getAddLabels().shouldBe(exactText("Labels"));

    practisSetCreatePage().getPacingDropdown().shouldBe(visible);
    practisSetCreatePage().getPacingDropdown().shouldBe(exactText("Free-Form"));

    practisSetCreatePage().getDescriptionField().shouldBe(visible);
    practisSetCreatePage().getDescriptionField().shouldBe(attribute("placeholder", "Description"));

    practisSetCreatePage().getTotalDurationText().shouldBe(visible);
    practisSetCreatePage().getTotalDurationText().shouldBe(exactText("Total Duration"));
    practisSetCreatePage().getTotalDuration().shouldBe(visible);
    practisSetCreatePage().getTotalDuration().shouldBe(exactText("0m 0s"));

    practisSetCreatePage().getTotalRepsReqDText().shouldBe(visible);
    practisSetCreatePage().getTotalRepsReqDText().shouldBe(exactText("Total Reps Req’d"));
    practisSetCreatePage().getTotalReps().shouldBe(visible);
    practisSetCreatePage().getTotalReps().shouldBe(exactText("0"));

    practisSetCreatePage().getMinAccuracyText().shouldBe(visible);
    practisSetCreatePage().getMinAccuracyText().shouldBe(exactText("Minimum Accuracy"));
    practisSetCreatePage().getMinAccuracy().shouldBe(visible);
    practisSetCreatePage().getMinAccuracy().shouldBe(exactText("65%"));


    practisSetCreatePage().getScenarioTab().shouldBe(visible);
    practisSetCreatePage().getSearchField().shouldBe(visible);
    practisSetCreatePage().getFilterButton().shouldBe(visible);
    practisSetCreatePage().getFirstColumnScenario().shouldBe(visible);
    practisSetCreatePage().getFirstColumnScenario().shouldBe(exactText("Scenarios"));
    practisSetCreatePage().getSecondColumnScenario().shouldBe(visible);
    practisSetCreatePage().getSecondColumnScenario().shouldBe(exactText("Duration"));

    practisSetCreatePage().getChallengeTab().shouldBe(visible);
    practisSetCreatePage().getChallengeTab().click();

    practisSetCreatePage().getSearchField().shouldBe(visible);
    practisSetCreatePage().getFilterButton().shouldBe(visible);
    practisSetCreatePage().getFirstColumnChallenge().shouldBe(visible);
    practisSetCreatePage().getFirstColumnChallenge().shouldBe(exactText("Challenges"));
    practisSetCreatePage().getSecondColumnChallenge().shouldBe(visible);
    practisSetCreatePage().getSecondColumnChallenge().shouldBe(exactText("Duration"));

    practisSetCreatePage().getNoContentImage().shouldBe(visible);
    practisSetCreatePage().getNoContentText().shouldBe(visible);
    practisSetCreatePage().getNoContentText().shouldBe(exactText("No Content"));
    practisSetCreatePage().getNoContentText().shouldBe(visible);
    practisSetCreatePage().getNoContentDescriptionText()
        .shouldBe(exactText(
            "Add Scenarios & Challenges from the left in order to build this practis set."));

  }

}
