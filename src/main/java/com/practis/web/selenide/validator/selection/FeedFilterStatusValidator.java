package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class FeedFilterStatusValidator {

    /** Assert Status model on Feed: Accuracy tab. */
    public static void assertFeedAccuracyStatusModule() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        feedStatusModule().getStatusTitle().shouldBe(visible);
        feedStatusModule().getStatusTitle().shouldBe(exactText("Status"));
        feedStatusModule().getStatusCheckbox().shouldBe(enabled);
        feedStatusModule().getArchivedStatus().shouldBe(exactText("Archived"));
    }
}
