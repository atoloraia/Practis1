package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
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

    statusModule().getStatusTitles().get(0).shouldBe(visible);
    statusModule().getStatusTitles().get(0).shouldBe(exactText("Active"));
    statusModule().getStatusTitles().get(0).shouldBe(attribute("color", "#212121"));
    statusModule().getStatusTitles().get(0).shouldBe(attribute("size", "13"));
    statusModule().getCheckboxButton().get(0).shouldBe(visible);
    statusModule().getCheckboxButton().get(0).shouldBe(attribute("size", "12"));

    statusModule().getStatusTitles().get(1).shouldBe(visible);
    statusModule().getStatusTitles().get(1).shouldBe(exactText("Draft"));
    statusModule().getStatusTitles().get(1).shouldBe(attribute("color", "#212121"));
    statusModule().getStatusTitles().get(1).shouldBe(attribute("size", "13"));
    statusModule().getCheckboxButton().get(1).shouldBe(visible);
    statusModule().getCheckboxButton().get(1).shouldBe(attribute("size", "12"));

    statusModule().getStatusTitles().get(2).shouldBe(visible);
    statusModule().getStatusTitles().get(2).shouldBe(exactText("Archived"));
    statusModule().getStatusTitles().get(2).shouldBe(attribute("color", "#212121"));
    statusModule().getStatusTitles().get(2).shouldBe(attribute("size", "13"));
    statusModule().getCheckboxButtonArchived().shouldBe(visible);
    statusModule().getCheckboxButtonArchived().shouldBe(attribute("size", "12"));

    statusModule().getCheckboxButton().get(0).click();
    statusModule().getCheckboxButtonArchived().click();

    libraryPage().getFiltersSelectedCounterText().shouldBe(matchText("2 Selected"));
    libraryPage().getFiltersClearButton().shouldBe(enabled);
    libraryPage().getFiltersClearButton().click();

    libraryPage().getFiltersSelectedCounterText().shouldBe(visible);
    libraryPage().getFiltersSelectedCounterText().shouldBe(matchText("0 Selected"));

    libraryPage().getFiltersClearButton().shouldBe(visible);
    libraryPage().getFiltersClearButton().shouldBe(exactText("Clear"));

    libraryPage().getFiltersApplyButton().shouldBe(visible);
    libraryPage().getFiltersApplyButton().shouldBe(enabled);
    libraryPage().getFiltersApplyButton().shouldBe(exactText("Apply Filter"));
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
    statusModule().getFeedStatusText().get(0).shouldBe(attribute("color", "#212121"));
    statusModule().getCheckboxButtonArchived().shouldBe(attribute("size", "12"));
    feedPage().getFiltersSelectionText().shouldBe(visible);
    feedPage().getFiltersSelectionText().shouldBe(matchText("0 Selected"));
    statusModule().getCheckboxButtonArchived().click();
    feedPage().getFiltersSelectionText().shouldBe(matchText("1 Selected"));
    feedPage().getFiltersClearButton().shouldBe(enabled);

    feedPage().getFiltersClearButton().click();
    feedPage().getFiltersSelectionText().shouldBe((matchText("0 Selected")));


    feedPage().getFiltersClearButton().shouldBe(visible);
    feedPage().getFiltersClearButton().shouldBe(attribute("disabled", ""));
    feedPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    feedPage().getFiltersClearButton().shouldBe(attribute("color", "default"));
    feedPage().getFiltersClearButton().shouldBe(attribute("width", "122px"));

    feedPage().getFiltersApplyFilterButton().shouldBe(visible);
    feedPage().getFiltersApplyFilterButton().shouldBe(enabled);
    feedPage().getFiltersApplyFilterButton().shouldBe((exactText("Apply Filter")));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("color", "default"));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("width", "122px"));
    feedPage().getFiltersApplyFilterButton().shouldBe(attribute("type", "submit"));
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
    statusModule().getCheckboxButton().get(0).shouldBe(attribute("size", "12"));
    statusModule().getFeedStatusText().get(0).shouldBe(exactText("Needs Review"));
    statusModule().getFeedStatusText().get(0).shouldBe(attribute("color", "#212121"));
    statusModule().getFeedStatusText().get(0).shouldBe(attribute("type", "checkbox"));

    statusModule().getFeedStatusText().get(1).shouldBe(visible);
    statusModule().getCheckboxButton().get(1).shouldBe(attribute("size", "12"));
    statusModule().getFeedStatusText().get(1).shouldBe(exactText("Reviewed"));
    statusModule().getFeedStatusText().get(1).shouldBe(attribute("color", "#212121"));
    statusModule().getFeedStatusText().get(1).shouldBe(attribute("type", "checkbox"));

    statusModule().getFeedStatusText().get(2).shouldBe(visible);
    statusModule().getCheckboxButtonArchived().shouldBe(attribute("size", "12"));
    statusModule().getFeedStatusText().get(2).shouldBe(exactText("Archived"));
    statusModule().getFeedStatusText().get(2).shouldBe(attribute("color", "#212121"));
    statusModule().getFeedStatusText().get(2).shouldBe(attribute("type", "checkbox"));

    statusModule().getCheckboxButton().get(1).click();
    statusModule().getCheckboxButtonArchived().click();
    feedPage().getFiltersClearButton().click();

    feedPage().getChallengeFiltersSelectionText().shouldBe(visible);
    feedPage().getChallengeFiltersSelectionText().shouldBe(matchText("0 Selected"));

    feedPage().getFiltersClearButton().shouldBe(visible);
    feedPage().getFiltersClearButton().shouldBe((exactText("Clear")));
    feedPage().getFiltersApplyFilterButton().shouldBe(visible);
    feedPage().getFiltersApplyFilterButton().shouldBe((exactText("Apply Filter")));
  }
}