package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.publishPractisSetPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetPage;

import com.practis.dto.NewPractisSetInput;

public class PractisSetService {

  /**
   * Publish Practis Set.
   */
  public void createPractsSet(final NewPractisSetInput inputData,
      final String label, final String scenarioTitle, final String challengeTitle) {
    practisSetPage().createPractisSet(inputData, label, scenarioTitle, challengeTitle);
    practisSetPage().getPublishButton().click();
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

}
