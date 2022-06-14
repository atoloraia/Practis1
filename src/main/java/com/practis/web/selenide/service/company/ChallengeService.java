package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.practis.dto.NewChallengeInput;
import com.practis.web.selenide.component.GridRow;

public class ChallengeService {

  public void createChallenge(final NewChallengeInput inputData, final String label) {
    challengeCreatePage().fillForm(inputData, label);
    challengeCreatePage().getPublishButton().click();
  }

  public void saveAsDraftChallenge(final NewChallengeInput inputData, final String label) {
    challengeCreatePage().fillForm(inputData, label);
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
