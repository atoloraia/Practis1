package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.statusModule;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;

public class StatusSelectionValidator {

  /**
   * Assert elements on Library: Filters modal: Status.
   */
  public static void assertLibraryFilterStatusModule() {
    statusModule().getStatusTitle().shouldBe(visible, Duration.of(10, SECONDS));
    statusModule().getStatusTitle().shouldBe(exactText("Status"));

    statusModule().getActiveStatusLabel().shouldBe(visible);
    statusModule().getActiveStatusCheckbox().shouldBe(visible);
    statusModule().getActiveStatusLabel().shouldBe(exactText("Active"));

    statusModule().getDraftStatusLabel().shouldBe(visible);
    statusModule().getDraftStatusCheckbox().shouldBe(visible);
    statusModule().getDraftStatusLabel().shouldBe(exactText("Draft"));

    statusModule().getDraftStatusLabel().shouldBe(visible);
    statusModule().getDraftStatusCheckbox().shouldBe(visible);
    statusModule().getDraftStatusLabel().shouldBe(exactText("Archived"));
  }

  /**
   * Assert Status model on Feed: Accuracy tab.
   */
  public static void assertFeedAccuracyStatusModule() {
    statusModule().getArchivedStatusCheckbox().shouldBe(visible);
    statusModule().getArchivedStatusLabel().shouldBe(exactText("Archived"));
  }

  /**
   * Assert Status model on Feed: Challenges tab.
   */
  public static void assertFeedChallengesStatusModule() {
    statusModule().getStatusTitle().shouldBe(visible);
    statusModule().getStatusTitle().shouldBe(exactText("Status"));

    statusModule().getActiveStatusLabel().shouldBe(visible);
    statusModule().getDraftStatusLabel().shouldBe(visible);
    statusModule().getArchivedStatusLabel().shouldBe(visible);

    statusModule().getStatus().get(0).shouldBe(visible);
    statusModule().getStatus().get(0).shouldBe(exactText("Needs Review"));

    statusModule().getStatus().get(1).shouldBe(visible);
    statusModule().getStatus().get(1).shouldBe(exactText("Reviewed"));

    statusModule().getStatus().get(2).shouldBe(visible);
    statusModule().getStatus().get(2).shouldBe(exactText("Archived"));
  }
}