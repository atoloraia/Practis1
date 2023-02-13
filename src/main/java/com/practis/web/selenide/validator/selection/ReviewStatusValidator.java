package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.reviewStatusModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class ReviewStatusValidator {

    /** Assert Review Status model on Feed: Challenge tab. */
    public static void assertReviewStatusModule() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        reviewStatusModule().getCheckedNeedsReviewCheckbox().shouldBe(enabled);
        reviewStatusModule().getNeedsReviewStatus().shouldBe(visible);
        reviewStatusModule().getNeedsReviewStatus().shouldBe(exactText("Needs Review"));
        reviewStatusModule().getCheckedReviewedCheckbox().shouldBe(enabled);
        reviewStatusModule().getReviewedStatus().shouldBe(visible);
        reviewStatusModule().getReviewedStatus().shouldBe(exactText("Reviewed"));
    }

    /** Assert Status model on Feed: Challenges tab. */
    public static void assertFeedChallengesStatusModule() {
        feedStatusModule().getStatusTitle().shouldBe(visible);
        feedStatusModule().getStatusTitle().shouldBe(exactText("Status"));

        feedStatusModule().getArchivedStatusLabel().shouldBe(visible);
        feedStatusModule().getArchivedStatusLabel().shouldBe(exactText("Needs Review"));

        feedStatusModule().getArchivedStatusLabel().shouldBe(visible);
        feedStatusModule().getArchivedStatusLabel().shouldBe(exactText("Reviewed"));

        feedStatusModule().getArchivedStatusLabel().shouldBe(visible);
        feedStatusModule().getArchivedStatusLabel().shouldBe(exactText("Archived"));
    }
}
