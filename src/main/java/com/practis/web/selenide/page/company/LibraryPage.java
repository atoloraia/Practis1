package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class LibraryPage {

    private final SelenideElement libraryTitle = $("div[data-test='library-page-subtitle']");
    private final SelenideElement practisSetsTab = $("a[data-test='library-nav-practis-sets']");
    private final SelenideElement scenariosTab = $("a[data-test='library-nav-scenarios']");
    private final SelenideElement challengesTab = $("a[data-test='library-nav-challenges']");
    private final SelenideElement timestampText = $("span[data-test='library-timestamp-label']");
    private final SelenideElement timestampRefreshButton =
            $("button[data-test='library-timestamp-refresh']");

    private final SelenideElement searchField = $("input[data-test='library-search-input']");
    private final SelenideElement searchFieldIcon = $("div[data-test='library-search-input-icon']");
    private final SelenideElement filtersButton = $("button[data-test='library-filters-button']");
    private final SelenideElement filtersCounter = $("div[data-test='library-filters-counter']");
    private final SelenideElement paginationNextButton =
            $("button[data-test='library-paging-next']");
    private final SelenideElement paginationPrevButton =
            $("button[data-test='library-paging-prev']");

    private final SelenideElement emptyIconScenarioTab =
            $("div[data-test='library-no-filtered-scenarios-icon']");
    private final SelenideElement emptyTextScenarioTab =
            $("div[data-test='library-no-filtered-scenarios-label']");
    private final SelenideElement emptyIconChallengeTab =
            $("div[data-test='library-no-filtered-challenges-icon']");
    private final SelenideElement emptyTextChallengeTab =
            $("div[data-test='library-no-filtered-challenges-label']");

    private final SelenideElement challengesSelectAllCheckbox =
            $("td[data-test-custom-name='library-challenges-master-checkbox-column']");
    private final SelenideElement challengesColumn =
            $("th[data-test='library-challenges-title-column']");
    private final SelenideElement challengesStatusColumn =
            $("th[data-test='library-challenges-status-column']");
    private final SelenideElement challengesLastUpdatedColumn =
            $("th[data-test='library-challenges-date-column']");

    private final SelenideElement filtersSelectedCounterText =
            $("span[data-test='library-filters-selected-counter']");
    private final SelenideElement filtersClearButton =
            $("button[data-test='library-filters-clear']");
    private final SelenideElement filtersApplyButton =
            $("button[data-test='library-filters-apply']");
}
