package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deletePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.warningDeleteUserPopUp;

public class WarningDeletePopUpValidator {

    /** Assert 'Warning' pop up'. */
    public static void assertWarningDeletePopUp() {
        deletePopUp().getWarningTitle().shouldBe(visible);
        deletePopUp().getWarningTitle().shouldBe(matchText("Warning"));
        deletePopUp().getDescription().shouldBe(visible);
        // deletePopUp().getDescription()
        // .shouldBe(matchText("You will delete the selected team(s)"));
        deletePopUp().getGoBackButton().shouldBe(visible);
        deletePopUp().getGoBackButton().shouldBe(matchText("Go Back"));
        deletePopUp().getProceedButton().shouldBe(visible);
        deletePopUp().getProceedButton().shouldBe(matchText("Proceed"));
    }

    /** Assert 'Warning' pop up after clicking on Delete User. */
    public static void assertWarningDeleteUsersPopUp() {
        warningDeleteUserPopUp().getConfirmActionTitle().shouldBe(visible);
        warningDeleteUserPopUp().getConfirmActionTitle().shouldBe(matchText("Warning"));
        warningDeleteUserPopUp().getDescriptionText().shouldBe(visible);
        // warningDeleteUserPopUp()
        //         .getDescriptionText()
        //         .shouldBe(
        //                 matchText(
        //                         "You will erase the selected profile(s) and all their activity"
        //                                 + " from the system. This action cannot be undone. Are
        // you"
        //                                  + " sure?"));
        warningDeleteUserPopUp().getGoBackButton().shouldBe(visible);
        warningDeleteUserPopUp().getGoBackButton().shouldBe(matchText("Go Back"));
        warningDeleteUserPopUp().getProceedButton().shouldBe(visible);
        warningDeleteUserPopUp().getProceedButton().shouldBe(matchText("Proceed"));
    }
}
