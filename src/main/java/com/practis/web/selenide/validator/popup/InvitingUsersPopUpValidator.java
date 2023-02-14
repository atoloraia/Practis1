package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.invitingUsersPopUpPopUp;

public class InvitingUsersPopUpValidator {

    /** Assert 'Inviting Users' pop up'. */
    public static void asserInvitingUsersPopUp() {
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(exactText("Inviting Users"));
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(matchText("Completed"));
        invitingUsersPopUpPopUp().getProgressbar().shouldBe(visible);
        invitingUsersPopUpPopUp().getWarningMessage().shouldBe(visible);
        invitingUsersPopUpPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        invitingUsersPopUpPopUp().getStopButton().shouldBe(visible);
        invitingUsersPopUpPopUp().getStopButton().shouldBe(exactText("Stop"));
    }

    /** Assert 'Inviting Users' pop up'. */
    public static void assertProcessingLabelsPopUp() {
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(exactText("Processing Labels"));
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(matchText("Completed"));
        invitingUsersPopUpPopUp().getProgressbar().shouldBe(visible);
        invitingUsersPopUpPopUp().getWarningMessage().shouldBe(visible);
        invitingUsersPopUpPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        invitingUsersPopUpPopUp().getStopButton().shouldBe(visible);
        invitingUsersPopUpPopUp().getStopButton().shouldBe(exactText("Stop"));
    }

    /** Assert 'Inviting Users' pop up'. */
    public static void assertProcessingDeleteUsersPopUp() {
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(exactText("Delete Users"));
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(matchText("Completed"));
        invitingUsersPopUpPopUp().getProgressbar().shouldBe(visible);
        invitingUsersPopUpPopUp().getWarningMessage().shouldBe(visible);
        invitingUsersPopUpPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        invitingUsersPopUpPopUp().getStopButton().shouldBe(visible);
        invitingUsersPopUpPopUp().getStopButton().shouldBe(exactText("Stop"));
    }

    public static void assertProcessingResendInvitationPopUp() {
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(exactText("Resend Invitations"));
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(matchText("Completed"));
        invitingUsersPopUpPopUp().getProgressbar().shouldBe(visible);
        invitingUsersPopUpPopUp().getWarningMessage().shouldBe(visible);
        invitingUsersPopUpPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        invitingUsersPopUpPopUp().getStopButton().shouldBe(visible);
        invitingUsersPopUpPopUp().getStopButton().shouldBe(exactText("Stop"));
    }

    public static void assertProcessingRevokePopUp() {
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getInvitingUsersTitle().shouldBe(exactText("Revoke Invitations"));
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(visible);
        invitingUsersPopUpPopUp().getProgressTitle().shouldBe(matchText("Completed"));
        invitingUsersPopUpPopUp().getProgressbar().shouldBe(visible);
        invitingUsersPopUpPopUp().getWarningMessage().shouldBe(visible);
        invitingUsersPopUpPopUp()
                .getWarningMessage()
                .shouldBe(exactText("Do not refresh or close the page to avoid losing data."));
        invitingUsersPopUpPopUp().getStopButton().shouldBe(visible);
        invitingUsersPopUpPopUp().getStopButton().shouldBe(exactText("Stop"));
    }
}
