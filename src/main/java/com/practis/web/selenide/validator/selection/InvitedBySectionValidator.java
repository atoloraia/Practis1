package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.invitedByModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class InvitedBySectionValidator {

    /** Assert elements on Role model. */
    public static void assertElementsOnInvitedBySection() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        invitedByModule().getInvitedByTitle().shouldBe(visible);
        invitedByModule().getInvitedByTitle().shouldBe(exactText("Invited by"));
        invitedByModule().getSearchField().shouldBe(visible);
        invitedByModule().getSearchField().shouldBe(enabled);
        invitedByModule().getSearchFieldIcon().shouldBe(visible);
        invitedByModule().getCleanSearchIcon().shouldBe(hidden);
        invitedByModule().getSelectedText().shouldBe(visible);
        invitedByModule().getSelectedText().shouldBe(exactText("No Inviters selected"));
        invitedByModule().getSelectedAllButton().shouldBe(visible);
        invitedByModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        invitedByModule().getUserCheckbox().get(0).shouldBe(enabled);
        invitedByModule().getUserName().get(0).shouldBe(visible);
    }
}
