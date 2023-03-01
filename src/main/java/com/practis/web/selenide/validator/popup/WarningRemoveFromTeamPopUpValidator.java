package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;

public class WarningRemoveFromTeamPopUpValidator {

    /** Assert 'Warning' pop up'. */
    public static void assertWarningDeletePopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Warning"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp()
                .getConfirmDescription()
                .shouldHave(
                        text(
                                "You will remove the selected member(s) from the team. "
                                        + "This action cannot be undone. Are you sure?"));

        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Go Back"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Proceed"));
    }
}
