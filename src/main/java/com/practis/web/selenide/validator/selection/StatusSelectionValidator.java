package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.statusModule;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class StatusSelectionValidator {

  /**
   * Assert elements on Library: Filters modal: Status.
   */
  public static void assertLibraryFilterStatusModule() {
    statusModule().getModuleTitle().get(0).shouldBe(visible, Duration.of(10, SECONDS));
    statusModule().getModuleTitle().get(0).shouldBe(exactText("Status"));

    statusModule().getStatus().get(0).shouldBe(visible);
    statusModule().getStatus().get(0).shouldBe(exactText("Active"));

    statusModule().getStatus().get(1).shouldBe(visible);
    statusModule().getStatus().get(1).shouldBe(exactText("Draft"));

    statusModule().getStatus().get(2).shouldBe(visible);
    statusModule().getStatus().get(2).shouldBe(exactText("Archived"));
  }

  /**
   * Assert Status model on Feed: Accuracy tab.
   */
  public static void assertFeedAccuracyTestTabStatusModule() {
    statusModule().getStatus().get(0).shouldBe(visible);
    statusModule().getStatus().get(0).shouldBe(exactText("Archived"));
  }

  /**
   * Assert Status model on Feed: Challenges tab.
   */
  public static void assertFeedChallengesTabStatusModule() {
    statusModule().getModuleTitle().get(0).shouldBe(visible);
    statusModule().getModuleTitle().get(0).shouldBe(exactText("Status"));

    statusModule().getStatus().get(0).shouldBe(visible);
    statusModule().getStatus().get(1).shouldBe(visible);
    statusModule().getStatus().get(2).shouldBe(visible);

    statusModule().getStatus().get(0).shouldBe(visible);
    statusModule().getStatus().get(0).shouldBe(exactText("Needs Review"));

    statusModule().getStatus().get(1).shouldBe(visible);
    statusModule().getStatus().get(1).shouldBe(exactText("Reviewed"));

    statusModule().getStatus().get(2).shouldBe(visible);
    statusModule().getStatus().get(2).shouldBe(exactText("Archived"));
  }
}