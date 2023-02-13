package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

public class FeedFilterStatusValidator {

    /** Assert Status model on Feed: Accuracy tab. */
    public static void assertFeedAccuracyStatusModule() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        feedStatusModule().getStatusTitle().shouldBe(exactText("Status"));
        feedStatusModule().getStatusCheckbox().shouldBe(enabled);
        feedStatusModule().getArchivedStatusLabel().shouldBe(exactText("Archived"));
    }
}
