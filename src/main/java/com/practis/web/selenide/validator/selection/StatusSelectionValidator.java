package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.statusModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;

public class StatusSelectionValidator {

  /**
   * Assert Status model on Library - Filters modal.
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

  /**
   * Assert Status model on Feed - Filters modal.
   */
  public static void assertFeedAccuracyStatusModule() {
    statusModule().getModuleTitle().get(0).shouldBe(visible);
    statusModule().getModuleTitle().get(0).shouldBe(exactText("Status"));
    statusModule().getModuleTitle().get(1).shouldBe(visible);
    statusModule().getModuleTitle().get(1).shouldBe(exactText("Scenarios"));
    statusModule().getModuleTitle().get(2).shouldBe(visible);
    statusModule().getModuleTitle().get(2).shouldBe(exactText("Teams"));
    statusModule().getModuleTitle().get(3).shouldBe(visible);
    statusModule().getModuleTitle().get(3).shouldBe(exactText("Labels"));
    statusModule().getCheckboxButtonArchived().shouldBe(visible);
    statusModule().getFeedStatusText().get(0).shouldBe(visible);
    statusModule().getFeedStatusText().get(0).shouldBe(exactText("Archived"));
    feedPage().getFiltersSelectionText().shouldBe(visible);
    feedPage().getFiltersSelectionText().shouldBe((matchText("Selected")));
    feedPage().getFiltersClearButton().shouldBe(visible);
    feedPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    feedPage().getFiltersApplyFilterButton().shouldBe(visible);
    feedPage().getFiltersApplyFilterButton().shouldBe((exactText("Apply Filter")));
  }

  /**
   * Assert Status model on Feed - Filters modal.
   */
  public static void assertChallengesAccuracyStatusModule() {
    statusModule().getModuleTitle().get(0).shouldBe(visible);
    statusModule().getModuleTitle().get(0).shouldBe(exactText("Status"));
    statusModule().getModuleTitle().get(1).shouldBe(visible);
    statusModule().getModuleTitle().get(1).shouldBe(exactText("Teams"));
    statusModule().getModuleTitle().get(2).shouldBe(visible);
    statusModule().getModuleTitle().get(2).shouldBe(exactText("Labels"));
    statusModule().getCheckboxButton().get(0).shouldBe(visible);
    statusModule().getCheckboxButton().get(1).shouldBe(visible);
    statusModule().getCheckboxButtonArchived().shouldBe(visible);
    statusModule().getFeedStatusText().get(0).shouldBe(visible);
    statusModule().getFeedStatusText().get(0).shouldBe(exactText("Needs Review"));
    statusModule().getFeedStatusText().get(1).shouldBe(visible);
    statusModule().getFeedStatusText().get(1).shouldBe(exactText("Reviewed"));
    statusModule().getFeedStatusText().get(2).shouldBe(visible);
    statusModule().getFeedStatusText().get(2).shouldBe(exactText("Archived"));
    feedPage().getChallengeFiltersSelectionText().shouldBe(visible);
    feedPage().getChallengeFiltersSelectionText().shouldBe((matchText("Selected")));
    feedPage().getFiltersClearButton().shouldBe(visible);
    feedPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    feedPage().getFiltersApplyFilterButton().shouldBe(visible);
    feedPage().getFiltersApplyFilterButton().shouldBe((exactText("Apply Filter")));
  }
}