package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.Condition;

public class FeedFilterStatusValidator {

    /** Assert Status model on Feed: Accuracy tab. */
    public static void assertFeedAccuracyStatusModule() {
        awaitSoft(15, () -> feedStatusModule().getStatusTitle().text().contains("Status"));
        feedStatusModule().getStatusTitle().shouldHave(Condition.text("Status"));
        feedStatusModule().getStatusCheckbox().shouldBe(enabled);
        feedStatusModule().getArchivedStatusLabel().shouldBe(exactText("Archived"));
    }
}
