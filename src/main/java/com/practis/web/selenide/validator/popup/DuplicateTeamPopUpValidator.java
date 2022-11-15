package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.duplicatePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.invitingUsersPopUpPopUp;

public class DuplicateTeamPopUpValidator {

  /**
   * Assert 'Duplicate' pop up'.
   */
  public static void asserDuplicateUsersPopUp() {
    duplicatePopUp().getDuplicatesTitle().shouldBe(visible);
    duplicatePopUp().getDuplicatesTitle()
        .shouldBe(exactText("Duplicate Team"));
    duplicatePopUp().getProgressTitle().shouldBe(visible);
    duplicatePopUp().getProgressTitle().shouldBe(matchText("Completed"));
    duplicatePopUp().getProgressbar().shouldBe(visible);
    duplicatePopUp().getWarningMessage().shouldBe(visible);
    duplicatePopUp().getWarningMessage()
        .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
    duplicatePopUp().getStopButton().shouldBe(visible);
    duplicatePopUp().getStopButton().shouldBe(exactText("Stop"));
  }
}
