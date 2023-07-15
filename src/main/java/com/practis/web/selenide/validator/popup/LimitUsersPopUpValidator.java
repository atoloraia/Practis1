package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.limitUsersPopUp;

public class LimitUsersPopUpValidator {

    /** Assert 'You Need More Seats' pop up. */
    public static void assertYouNeedMoreSeatsPopUp() {
        limitUsersPopUp().getLimitUserPopUpTitle().shouldBe(visible);
        limitUsersPopUp()
                .getLimitUserPopUpTitle()
                .shouldBe(matchText("You need more seats to invite these users"));
        limitUsersPopUp().getCrossButton().shouldBe(visible);
        limitUsersPopUp().getLimitUserPopUpDescription().shouldBe(visible);
        limitUsersPopUp()
                .getLimitUserPopUpDescription()
                .shouldBe(
                        matchText(
                                "You're trying to invite more users than is allowed under your"
                                        + " existing license. Your company's account on Practis is"
                                        + " limited to"));
        limitUsersPopUp().getQuickTipIcon().shouldBe(visible);
        limitUsersPopUp().getQuickTipTitle().shouldBe(visible);
        limitUsersPopUp().getQuickTipTitle().shouldBe(matchText("Quick tip:"));
        limitUsersPopUp().getQuickTipDescription().shouldBe(visible);
        limitUsersPopUp()
                .getQuickTipDescription()
                .shouldBe(
                        matchText(
                                "you can save all these invitations as a Draft for now"
                                        + " until the limitation issue is resolved."));

        limitUsersPopUp().getManageInvitationsButton().shouldBe(enabled);
        limitUsersPopUp().getManageInvitationsButton().shouldBe(matchText("Manage Invitations"));

        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(visible);
        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(enabled);
        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(matchText("Request Limit Change"));
    }

    /** Assert 'You Need More Seats' pop up. */
    public static void youCantInviteNewUsersPopUp() {
        limitUsersPopUp().getLimitUserPopUpTitle().shouldBe(visible);
        limitUsersPopUp()
                .getLimitUserPopUpTitle()
                .shouldBe(matchText("You can't invite new users"));
        limitUsersPopUp().getCrossButton().shouldBe(visible);
        limitUsersPopUp().getLimitUserPopUpDescription().shouldBe(visible);
        limitUsersPopUp()
                .getLimitUserPopUpDescription()
                .shouldBe(
                        matchText(
                                ". You may be able to find unaccepted invitations and revoke them,"
                                        + " which will free up some seats."));
        limitUsersPopUp().getQuickTipIcon().shouldBe(visible);
        limitUsersPopUp().getQuickTipTitle().shouldBe(visible);
        limitUsersPopUp().getQuickTipTitle().shouldBe(matchText("Quick tip:"));
        limitUsersPopUp().getQuickTipDescription().shouldBe(visible);
        limitUsersPopUp()
                .getQuickTipDescription()
                .shouldBe(
                        matchText(
                                "you can save all these invitations as a Draft for now until the"
                                        + " limitation issue is resolved."));

        limitUsersPopUp().getManageInvitationsButton().shouldBe(enabled);
        limitUsersPopUp().getManageInvitationsButton().shouldBe(matchText("Manage Invitations"));

        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(visible);
        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(enabled);
        limitUsersPopUp().getRequestLimitChangeButton().shouldBe(matchText("Request Limit Change"));
    }
}
