package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryStatusModule;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.Duration;

public class LibraryFilterStatusValidator {

  /**
   * Assert elements on Library: Filters modal: Status.
   */
  public static void assertLibraryFilterStatusModule() {
    libraryStatusModule().getStatusTitle().shouldBe(visible, Duration.of(10, SECONDS));
    libraryStatusModule().getStatusTitle().shouldBe(exactText("Status"));

    libraryStatusModule().getActiveStatusLabel().shouldBe(visible);
    libraryStatusModule().getActiveStatusCheckbox().shouldBe(checked);
    libraryStatusModule().getActiveStatusLabel().shouldBe(exactText("Active"));

    libraryStatusModule().getDraftStatusLabel().shouldBe(visible);
    libraryStatusModule().getDraftStatusCheckbox().shouldBe(checked);
    libraryStatusModule().getDraftStatusLabel().shouldBe(exactText("Draft"));

    libraryStatusModule().getArchivedStatusLabel().shouldBe(visible);
    libraryStatusModule().getArchivedStatusCheckbox().shouldNotBe(checked);
    libraryStatusModule().getArchivedStatusLabel().shouldBe(exactText("Archived"));
  }

}