package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deletePopUp;

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
        deletePopUp().getWarningTitle().shouldBe(visible);
        deletePopUp().getWarningTitle().shouldBe(matchText("Warning"));
        deletePopUp().getDescription().shouldBe(visible);
        deletePopUp()
                .getDescription()
                .shouldHave(
                        text(
                                "You will erase the selected profile(s) and all their activity"
                                        + " from the system. This action cannot be undone. Are you"
                                        + " sure?"));
        deletePopUp().getGoBackButton().shouldBe(visible);
        deletePopUp().getGoBackButton().shouldBe(matchText("Go Back"));
        deletePopUp().getProceedButton().shouldBe(visible);
        deletePopUp().getProceedButton().shouldBe(matchText("Proceed"));
    }

    /** Assert 'Warning' pop up after clicking on Revoke User. */
    public static void assertWarningRevokeUserPopUp() {
        deletePopUp().getWarningTitle().shouldBe(visible);
        deletePopUp().getWarningTitle().shouldBe(matchText("Revoke Invitation"));
        deletePopUp().getDescription().shouldBe(visible);
        deletePopUp()
                .getDescription()
                .shouldBe(
                        matchText("Are you sure you want to revoke the invitation for this user?"));
        deletePopUp().getGoBackButton().shouldBe(visible);
        deletePopUp().getGoBackButton().shouldBe(matchText("Go Back"));
        deletePopUp().getProceedButton().shouldBe(visible);
        deletePopUp().getProceedButton().shouldBe(matchText("Yes, Revoke"));
    }
}
