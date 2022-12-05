package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.failedInvitingUsersPopUp;

public class FailedInvitingUsersPopUpValidator {

    /** Assert 'Failed: Inviting Users' pop up'. */
    public static void asserFailedInvitingUsersPopUp() {
        failedInvitingUsersPopUp().getFailedInvitingUsersTitle().shouldBe(visible);
        failedInvitingUsersPopUp()
                .getFailedInvitingUsersTitle()
                .shouldBe(exactText("Failed: Inviting Users"));
        failedInvitingUsersPopUp().getDescriptionMessage().shouldBe(visible);
        failedInvitingUsersPopUp()
                .getDescriptionMessage()
                .shouldBe(
                        matchText(
                                "Oops! Something went wrong,"
                                        + " please try again or contact our support team."));
        failedInvitingUsersPopUp().getGotItButton().shouldBe(visible);
        failedInvitingUsersPopUp().getGotItButton().shouldBe(exactText("Got it"));
    }
}
