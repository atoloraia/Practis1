package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.publishPractisSetPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;

import com.codeborne.selenide.Condition;
import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;

public class PractisSetService {

  /**
   * Publish Practis Set.
   */
  public void createPractisSet(final NewPractisSetInput inputData, final String label,
      final String scenarioTitle, final String challengeTitle) {
    practisSetCreatePage().createPractisSet(inputData, label, scenarioTitle, challengeTitle);
    practisSetCreatePage().getPublishButton().click();
  }

  /**
   * Publish Practis Set.
   */
  public void saveAsDraftPractisSet(final NewPractisSetInput inputData, final String label,
      final String scenarioTitle, final String challengeTitle) {
    practisSetCreatePage().createPractisSet(inputData, label, scenarioTitle, challengeTitle);
  }

  /**
   * Click Publish on 'Publish Practis Set' pop-up .
   */
  public void publish() {
    publishPractisSetPopUp().publish();
  }

  /**
   * Click go back on 'Publish Practis Set' pop-up .
   */
  public void goBack() {
    publishPractisSetPopUp().goBack();
  }

  /**
   * Search PS on grid by PS Title.
   */
  public GridRow searchPS(final String name) {
    navigationCompanies().goTo("Library");
    libraryTabs().goToTab("Practis Set");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click outside the Practis Set form and discard changes.
   */
  public void exitPractisSetWithDiscard() {
    navigationCompanies().goTo("Progress");
    discardChangeForm().discardChanges();
  }

  /**
   * Click outside the Practis Set form and save changes.
   */
  public void exitPractisSetWithSave() {
    navigationCompanies().goTo("Progress");
    discardChangeForm().saveChanges();
  }


  /**
   * To be added.
   */
  public void assertNumbers(final String totalDuration, final String totalReps,
      final String minAccuracy) {
    practisSetCreatePage().getTotalDuration().shouldBe(Condition.exactText(totalDuration));
    practisSetCreatePage().getTotalReps().shouldBe(Condition.exactText(totalReps));
    practisSetCreatePage().getMinAccuracy().shouldBe(Condition.exactText(minAccuracy));
  }

  /**
   * To be added.
   */
  public void assertNumbersNot(final String totalDuration, final String totalReps,
      final String minAccuracy) {
    practisSetCreatePage().getTotalDuration().shouldBe(Condition.exactText(totalDuration));
    practisSetCreatePage().getTotalReps().shouldBe(Condition.exactText(totalReps));
    practisSetCreatePage().getMinAccuracy().shouldBe(Condition.exactText(minAccuracy));
  }
}
