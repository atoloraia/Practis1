package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.statusModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;

public class StatusSelectionValidator {

  /**
   * Assert Status model.
   */
  public static void assertStatusModule() {
    statusModule().getModuleTitle().get(0).shouldBe(visible);
    statusModule().getModuleTitle().get(0).shouldBe(exactText("Status"));
    statusModule().getModuleTitle().get(1).shouldBe(visible);
    statusModule().getModuleTitle().get(1).shouldBe(exactText("Labels"));
    statusModule().getCheckboxButton().get(0).shouldBe(visible);
    statusModule().getCheckboxButton().get(1).shouldBe(visible);
    statusModule().getCheckboxButtonArchived().shouldBe(visible);
    statusModule().getStatusTitles().get(0).shouldBe(visible);
    statusModule().getStatusTitles().get(0).shouldBe(exactText("Active"));
    statusModule().getStatusTitles().get(1).shouldBe(visible);
    statusModule().getStatusTitles().get(1).shouldBe(exactText("Draft"));
    statusModule().getStatusTitles().get(2).shouldBe(visible);
    statusModule().getStatusTitles().get(2).shouldBe(exactText("Archived"));
    libraryPage().getFiltersSelectedCounterText().shouldBe(visible);
    libraryPage().getFiltersSelectedCounterText().shouldBe((matchText("Selected")));
    libraryPage().getFiltersClearButton().shouldBe(visible);
    libraryPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    libraryPage().getFiltersApplyButton().shouldBe(visible);
    libraryPage().getFiltersApplyButton().shouldBe((exactText("Apply Filter")));
  }
}