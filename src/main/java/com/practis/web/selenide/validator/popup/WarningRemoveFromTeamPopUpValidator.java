package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.removeFromTeamPopup;

public class WarningRemoveFromTeamPopUpValidator {

  /**
   * Assert 'Warning' pop up'.
   */
  public static void assertWarningDeletePopUp() {
    removeFromTeamPopup().getWarningTitle().shouldBe(visible);
    removeFromTeamPopup().getWarningTitle().shouldBe(matchText("Warning"));
    removeFromTeamPopup().getDescription().shouldBe(visible);
    //removeFromTeamPopup().getDescription()
    //.shouldBe(matchText("You will remove the selected member(s) from the team.
    //This action cannot be undone. Are you sure?"));

    removeFromTeamPopup().getGoBackButton().shouldBe(visible);
    removeFromTeamPopup().getGoBackButton().shouldBe(matchText("Go Back"));
    removeFromTeamPopup().getProceedButton().shouldBe(visible);
    removeFromTeamPopup().getProceedButton().shouldBe(matchText("Proceed"));
  }

}
