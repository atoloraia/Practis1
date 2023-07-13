package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.youNeedMoreSeats;

public class YouNeedMoreSeatsValidator {

    /** Assert 'You Need More Seats' pop up. */
    public static void assertYouNeedMoreSeatsPopUp() {
        youNeedMoreSeats().getYouNeedMoreSeatsTitle().shouldBe(visible);
        youNeedMoreSeats().getYouNeedMoreSeatsTitle().shouldBe(matchText("You can’t invite new users"));
        youNeedMoreSeats().getCrossButton().shouldBe(visible);
        youNeedMoreSeats().getYouNeedMoreSeatsDescription().shouldBe(visible);
        youNeedMoreSeats()
                .getYouNeedMoreSeatsDescription()
                .shouldBe(
                        matchText(
                                "You can either modify the invitations here to a lower number, find unaccepted invitations and revoke them, which will free up some seats.\n"
                                    + "Alternatively, contact your Practis Support to request an increase."));
        youNeedMoreSeats().getQuickTipDescription().shouldBe(visible);
        youNeedMoreSeats().getQuickTipDescription().shouldBe(matchText("Quick tip: you can save all these invitations as a Draft for now until the limitation issue is resolved."));

        youNeedMoreSeats().getManageInvitationsButton().shouldBe(enabled);
        youNeedMoreSeats().getManageInvitationsButton().shouldBe(matchText("Manage Invitations"));

        youNeedMoreSeats().getRequestLimitChangeButton().shouldBe(visible);
        youNeedMoreSeats().getRequestLimitChangeButton().shouldBe(enabled);
        youNeedMoreSeats().getRequestLimitChangeButton().shouldBe(matchText("Request Limit Change"));
    }
}
