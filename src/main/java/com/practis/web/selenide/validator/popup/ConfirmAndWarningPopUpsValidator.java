package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;

public class ConfirmAndWarningPopUpsValidator {

    /** Assert 'Warning' pop up'. */
    public static void assertWarningDeletePopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Warning"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        // confirmationAndWarningPopUp()
        // .getConfirmDescription()
        // .shouldBe(
        // matchText(
        // "You will delete the selected team(s) from the system. This action"
        //  + " cannot be undone. Are you sure?"));
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Go Back"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Proceed"));
    }

    /** Assert 'Warning' pop up after clicking on Delete User. */
    public static void assertWarningDeleteUsersPopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Warning"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp()
                .getConfirmDescription()
                .shouldHave(
                        text(
                                "You will erase the selected profile(s) and all their activity"
                                        + " from the system. This action cannot be undone. Are you"
                                        + " sure?"));
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Go Back"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Proceed"));
    }

    /** Assert 'Warning' pop up after clicking on Revoke User. */
    public static void assertWarningRevokeUserPopUp() {
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmTitle().shouldBe(matchText("Revoke Invitation"));
        confirmationAndWarningPopUp().getConfirmDescription().shouldBe(visible);
        confirmationAndWarningPopUp()
                .getConfirmDescription()
                .shouldBe(
                        matchText("Are you sure you want to revoke the invitation for this user?"));
        confirmationAndWarningPopUp().getCancelButton().shouldBe(visible);
        confirmationAndWarningPopUp().getCancelButton().shouldBe(matchText("Go Back"));
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(visible);
        confirmationAndWarningPopUp().getConfirmButton().shouldBe(matchText("Yes, Revoke"));
    }
}
