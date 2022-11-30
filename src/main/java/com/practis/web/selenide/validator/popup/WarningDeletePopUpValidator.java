package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deleteTeamPopUp;

public class WarningDeletePopUpValidator {

  /**
   * Assert 'Warning' pop up'.
   */
  public static void assertWarningDeletePopUp() {
    deleteTeamPopUp().getWarningTitle().shouldBe(visible);
    deleteTeamPopUp().getWarningTitle().shouldBe(matchText("Warning"));
    deleteTeamPopUp().getDescription().shouldBe(visible);
    //deleteTeamPopUp().getDescription()
    //.shouldBe(matchText("You will delete the selected team(s)"));
    deleteTeamPopUp().getGoBackButton().shouldBe(visible);
    deleteTeamPopUp().getGoBackButton().shouldBe(matchText("Go Back"));
    deleteTeamPopUp().getProceedButton().shouldBe(visible);
    deleteTeamPopUp().getProceedButton().shouldBe(matchText("Proceed"));
  }
}
