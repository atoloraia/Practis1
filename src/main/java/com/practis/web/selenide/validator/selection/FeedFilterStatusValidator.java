package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.feedStatusModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryStatusModule;

public class FeedFilterStatusValidator {

  /**
   * Assert Status model on Feed: Accuracy tab.
   */
  public static void assertFeedAccuracyStatusModule() {
    feedStatusModule().getStatusCheckbox().get(0).shouldBe(visible);
    feedStatusModule().getStatus().get(0).shouldBe(exactText("Archived"));
  }

  /**
   * Assert Status model on Feed: Challenges tab.
   */
  public static void assertFeedChallengesStatusModule() {
    feedStatusModule().getStatusTitle().shouldBe(visible);
    feedStatusModule().getStatusTitle().shouldBe(exactText("Status"));

    feedStatusModule().getStatus().get(0).shouldBe(visible);
    feedStatusModule().getStatus().get(0).shouldBe(exactText("Needs Review"));

    feedStatusModule().getStatus().get(1).shouldBe(visible);
    feedStatusModule().getStatus().get(1).shouldBe(exactText("Reviewed"));

    feedStatusModule().getStatus().get(2).shouldBe(visible);
    feedStatusModule().getStatus().get(2).shouldBe(exactText("Archived"));
  }
}