package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deletePopUp;

public class WarningRemoveFromTeamPopUpValidator {

    /** Assert 'Warning' pop up'. */
    public static void assertWarningDeletePopUp() {
        deletePopUp().getWarningTitle().shouldBe(visible);
        deletePopUp().getWarningTitle().shouldBe(matchText("Warning"));
        deletePopUp().getDescription().shouldBe(visible);
        deletePopUp()
                .getDescription()
                .shouldHave(
                        text(
                                "You will remove the selected member(s) from the team. "
                                        + "This action cannot be undone. Are you sure?"));

        deletePopUp().getGoBackButton().shouldBe(visible);
        deletePopUp().getGoBackButton().shouldBe(matchText("Go Back"));
        deletePopUp().getProceedButton().shouldBe(visible);
        deletePopUp().getProceedButton().shouldBe(matchText("Proceed"));
    }
}
