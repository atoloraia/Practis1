package com.practis.web.selenide.service.company;

import static com.practis.utils.AwaitUtils.awaitGridRowExists;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;

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
    navigationCompanies().goTo("Progress");
    discardChangeForm().discardChanges();
  }

  public void exitChallengeWithSave() {
    navigationCompanies().goTo("Progress");
    discardChangeForm().saveChanges();
  }

  public void deleteCustomerLine() {
    discardChangeForm().saveChanges();
  }


  /**
   * Search challenge on grid by Challenge Title.
   */
  public GridRow searchChallenge(final String name) {
    navigationCompanies().goTo("Library");
    libraryTabs().goToTab("Challenges");
    search().search(name);

    return awaitGridRowExists(5, () -> grid().getRow(name));
  }

}
