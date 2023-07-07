package com.practis.web.selenide.validator.popup;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.youNeedMoreSeats;

public class YouNeedMoreSeatsValidator {

    /** Assert 'You Need More Seats' pop up. */
    public static void assertYouNeedMoreSeatsPopUp() {
        youNeedMoreSeats().getYouNeedMoreTitle().shouldBe(visible);
        youNeedMoreSeats().getYouNeedMoreTitle().shouldBe(matchText("You can’t invite new users"));
        youNeedMoreSeats().getCrossButton().shouldBe(visible);
        youNeedMoreSeats().getExclamationMark().shouldBe(visible);
        youNeedMoreSeats().getYouNeedMoreDescription().shouldBe(visible);
        youNeedMoreSeats()
                .getYouNeedMoreDescription()
                .shouldBe(
                        matchText(
                                "You have reached your User Limit. To handle this situation, you"
                                    + " can revoke some invitations, request a new user limit. "
                                    + " Also, check if you can revoke some invitations to free"
                                    + " some users. You can save it as a draft, fix the issue and"
                                    + " invite them from the draft."));
        youNeedMoreSeats().getManageUsersButton().shouldBe(visible);
        youNeedMoreSeats().getManageUsersButton().shouldBe(enabled);
        youNeedMoreSeats().getManageUsersButton().shouldBe(matchText("Manage Users"));

        youNeedMoreSeats().getSetALimitButton().shouldBe(visible);
        youNeedMoreSeats().getSetALimitButton().shouldBe(enabled);
        youNeedMoreSeats().getSetALimitButton().shouldBe(matchText("Set A Limit"));
    }
}
