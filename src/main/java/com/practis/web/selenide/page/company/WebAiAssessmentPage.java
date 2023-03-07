package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class WebAiAssessmentPage {
    private final SelenideElement aiAssessmentTitle = $(".sc-dMOKBa.cZHFqd");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updateButton = $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchField = $("input[data-test='table-search-input']");
    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchCleanIcon = $("div[data-test='table-search-input-clear']");

    private final SelenideElement filtersButton = $("button[data-test='filters-selector']");
    private final SelenideElement calendarField = $("div[data-test='calendar-selector']");
    private final SelenideElement paginationCounterText =
            $("div[data-test='table-paging-counter']");
    private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
    private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");
    private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

    // AI Assessment list columns
    private final ElementsCollection columns = $$(".sc-cQUzBi.deRqJe");

    // AI Assessment Empty state
    private final SelenideElement noAssessmentFilterIcon =
            $("div[data-test='no-ai-assessment-icon']");
    private final SelenideElement noAssessmentFilterText =
            $("div[data-test='no-ai-assessment-label']");
}
