package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.youCantInviteNewUsers;

public class YouCantInviteNewUsersValidator {

    /** Assert 'You Need More Seats' pop up. */
    public static void assertYouCantInviteNewUsersPopUp() {
        youCantInviteNewUsers().getYouCantInviteNewUsersTitle().shouldBe(visible);
        youCantInviteNewUsers()
                .getYouCantInviteNewUsersTitle()
                .shouldBe(matchText("You can't invite new users"));
        youCantInviteNewUsers().getCrossButton().shouldBe(visible);
        youCantInviteNewUsers().getYouCantInviteNewUsersDescription().shouldBe(visible);
        youCantInviteNewUsers()
                .getYouCantInviteNewUsersDescription()
                .shouldBe(
                        matchText(
                                ". You may be able to find unaccepted invitations and revoke them,"
                                    + " which will free up some seats. Alternatively, contact your"
                                    + " Practis Support to request an increase."));
        youCantInviteNewUsers().getQuickTipDescription().shouldBe(visible);
        youCantInviteNewUsers()
                .getQuickTipDescription()
                .shouldBe(
                        matchText(
                                "you can save all these invitations as a Draft for now until the"
                                        + " limitation issue is resolved."));

        youCantInviteNewUsers().getManageInvitationsButton().shouldBe(visible);
        youCantInviteNewUsers()
                .getManageInvitationsButton()
                .shouldBe(matchText("Manage Invitations"));

        youCantInviteNewUsers().getRequestLimitChangeButton().shouldBe(visible);
        youCantInviteNewUsers()
                .getRequestLimitChangeButton()
                .shouldBe(matchText("Request Limit Change"));
    }
}
