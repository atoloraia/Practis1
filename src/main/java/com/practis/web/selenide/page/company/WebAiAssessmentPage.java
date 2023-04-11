package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WebAiAssessmentPage {

    private final SelenideElement aiAssessmentTitle =
            $("div[data-test='assessment-page-subtitle']");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='assessment-timestamp-label']");
    private final SelenideElement updateButton =
            $("button[data-test='assessment-timestamp-refresh']");

    private final SelenideElement searchField = $("input[data-test='assessment-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='assessment-search-icon']");
    private final SelenideElement searchCleanIcon = $("div[data-test='assessment-search-clear']");

    private final SelenideElement filtersButton = $("button[data-test='assessment-filters']");
    private final SelenideElement calendarField = $("div[data-test='assessment-calendar']");
    private final SelenideElement paginationCounterText =
            $("div[data-test='table-paging-counter']");
    private final SelenideElement paginationBackButton =
            $("button[data-test='assessment-paging-prev']");
    private final SelenideElement paginationNextButton =
            $("button[data-test='assessment-paging-next']");
    private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

    // AI Assessment list columns
    private final SelenideElement idColumn = $("th[data-test='id-column']");
    private final SelenideElement usersColumn = $("th[data-test='users-column']");
    private final SelenideElement scenarioColumn = $("th[data-test='scenario-column']");
    private final SelenideElement dateColumn = $("th[data-test='date-column']");
    private final SelenideElement modeColumn = $("th[data-test='mode-column']");
    private final SelenideElement accuracyColumn = $("th[data-test='accuracy-column']");
    private final SelenideElement flagColumn = $("th[data-test='flag-column']");

    // AI Assessment Empty state
    private final SelenideElement noAssessmentFilterIcon =
            $("div[data-test='no-ai-assessment-icon']");
    private final SelenideElement noAssessmentFilterText =
            $("div[data-test='no-ai-assessment-label']");
}
