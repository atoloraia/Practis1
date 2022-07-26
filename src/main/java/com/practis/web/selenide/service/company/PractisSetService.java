package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.psConfirmationPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.publishPractisSetPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Condition;
import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;

public class PractisSetService {

  /**
   * Fill Practis Set Title.
   */
  public void fillTitle(final NewPractisSetInput inputData) {
    practisSetCreatePage().getTitleField().append(inputData.getTitle());
  }

  /**
   * Fill Practis Set Title and Description.
   */
  public void fillForm(final NewPractisSetInput inputData, final String label) {
    fillTitle(inputData);
    practisSetCreatePage().getDescriptionField().append(inputData.getDescription());
  }

  /**
   * Fill Title, Description. Add Challenge and Scenario.
   */
  public void createPractisSet(final NewPractisSetInput inputData, final String label,
      final String scenarioTitle, final String challengeTitle) {
    fillForm(inputData, label);
    addScenario(scenarioTitle);
    addChallenge(challengeTitle);
  }

  /**
   * Adds Scenario to PractisSet.
   */
  public void addScenario(final String scenarioTitle) {
    practisSetCreatePage().getScenarioItems().find(Condition.matchText(scenarioTitle))
        .doubleClick();
  }

  /**
   * Adds Challenge to PractisSet.
   */
  public void addChallenge(final String challengeTitle) {
    practisSetCreatePage().getChallengeTab().click();
    practisSetCreatePage().getChallengeItems().find(Condition.matchText(challengeTitle))
        .doubleClick();
  }

  /**
   * Click Publish button.
   */
  public void publishPractisSet() {
    practisSetCreatePage().getPublishButton().click();
  }

  /**
   * Click Publish on 'Publish Practis Set' pop-up .
   */
  public void confirmPublish() {
    publishPractisSetPopUp().publish();
  }

  /**
   * Save Practis Set as Draft.
   */
  public void saveAsDraftPractisSet() {
    practisSetCreatePage().getSaveAsDraftButton().click();
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
    navigationCompanies().libraryNavigationItem.click();
    libraryTabs().practisSetLibraryTab.click();
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

  /**
   * Click outside the Practis Set form and discard changes.
   */
  public void exitPractisSetWithDiscard() {
    jsClick(navigationCompanies().getTeamsNavigationItem());
    areYouSurePopUp().discardChanges();
  }

  /**
   * Click outside the Practis Set form and save changes.
   */
  public void exitPractisSetWithSave() {
    jsClick(navigationCompanies().getTeamsNavigationItem());
    areYouSurePopUp().saveChanges();
  }

  /**
   * Assert Total Duration, Total Reps and Min Accuracy.
   */
  public void assertNumbers(final String totalDuration, final String totalReps,
      final String minAccuracy) {
    practisSetCreatePage().getTotalDuration().shouldBe(Condition.exactText(totalDuration));
    practisSetCreatePage().getTotalReps().shouldBe(Condition.exactText(totalReps));
    practisSetCreatePage().getMinAccuracy().shouldBe(Condition.exactText(minAccuracy));
  }

}
