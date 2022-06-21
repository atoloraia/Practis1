package com.practis.web.selenide.service.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelChallenge;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.util.AwaitUtils.awaitElementCollectionSize;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.practis.dto.NewChallengeInput;
import com.practis.web.selenide.component.GridRow;
import lombok.SneakyThrows;

public class ChallengeService {

  private static final int GENERATE_ALL_TIMEOUT = 10;

  /**
   * Fill Title.
   */
  public void fillTitle(final NewChallengeInput inputData) {
    challengeCreatePage().getTitleField().append(inputData.getTitle());
  }

  /**
   * Fill Title and Customer Line.
   */
  public void fillTitleWithCustomerLine(final NewChallengeInput inputData) {
    challengeCreatePage().getTitleField().append(inputData.getTitle());
    setDivText(challengeCreatePage().getCustomerLine(), inputData.getCustomerLine());
    awaitElementEnabled(10, () -> challengeCreatePage().getGenerateForAllButton()).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, ()
        -> challengeCreatePage().getPlayButtons(), 1);
  }

  /**
   * Fill Customer Line.
   */
  public void fillCustomerLine(final NewChallengeInput inputData) {

    setDivText(challengeCreatePage().getCustomerLine(), inputData.getCustomerLine());
    awaitElementEnabled(10, ()
        -> challengeCreatePage().getGenerateForAllButton()).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, ()
        -> challengeCreatePage().getPlayButtons(), 1);
  }

  /**
   * Fill Add New Challenge form.
   */
  @SneakyThrows
  public void fillForm(final NewChallengeInput inputData, final String label) {
    fillTitle(inputData);
    challengeCreatePage().getDescriptionField().append(inputData.getDescription());

    challengeCreatePage().getAddLabels().click();
    labelChallenge().selectLabel(label).clickAddLabel();

    //Check snackbar message "labels have been assigned to Challenge"
    snackbar().getMessage().shouldBe(exactText("labels have been assigned to Challenge"));

    setDivText(challengeCreatePage().getCustomerLine(),
        inputData.getCustomerLine());
    awaitElementEnabled(10, () -> challengeCreatePage().getGenerateForAllButton()).click();
    awaitElementCollectionSize(GENERATE_ALL_TIMEOUT, ()
        -> challengeCreatePage().getPlayButtons(), 1);
  }

  public void createChallenge(final NewChallengeInput inputData, final String label) {
    fillForm(inputData, label);
    challengeCreatePage().getPublishButton().click();
  }

  public void saveAsDraftChallenge(final NewChallengeInput inputData, final String label) {
    fillForm(inputData, label);
    challengeCreatePage().getSaveAsDraftButton().click();
  }

  public void exitChallengeWithDiscard() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().discardChanges();
  }

  public void exitChallengeWithSave() {
    jsClick(navigationCompanies().getProgressNavigationItem());
    discardChangeForm().saveChanges();
  }

  public void deleteCustomerLine() {
    discardChangeForm().saveChanges();
  }


  /**
   * Search challenge on grid by Challenge Title.
   */
  public GridRow searchChallenge(final String name) {
    navigationCompanies().libraryNavigationItem.click();
    libraryTabs().goToTab("Challenges");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

}
