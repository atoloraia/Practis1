package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetDetailsPage {

    private final SelenideElement practisSetDetailsTitle =
            $("div[data-test='training-details-page-title']");
    private final SelenideElement practisSetDetailsSubtitle =
            $("div[data-test='training-details-page-subtitle']");
    private final SelenideElement backArrowButton = $("div[data-test='back-arrow-button']");

    // Assign Users button
    private final SelenideElement assignUserButton = $("button[data-test='assign-users-button']");
    private final SelenideElement assignUserIcon = $("div[data-test='assign-users-icon']");

    // Export Report button
    private final SelenideElement exportReportButton =
            $("button[data-test='export-report-button']");
    private final SelenideElement exportReportIcon = $("div[data-test='export-report-icon']");

    // View Practis Set button
    private final SelenideElement viewPractisSetButton =
            $("button[data-test='view-practis-set-button']");
    private final SelenideElement viewPractisSetIcon = $("div[data-test='view-practis-set-icon']");

    // Updated timestamp
    private final SelenideElement updatedTimestampText =
            $("span[data-test='training-timestamp-label']");
    private final SelenideElement updatedTimestampButton =
            $("button[data-test='training-timestamp-refresh']");

    // Search field
    private final SelenideElement searchFieldIcon = $("div[data-test='training-search-icon']");
    private final SelenideElement searchField = $("input[data-test='training-search']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='training-search-clear']");

    // Filter
    private final SelenideElement filtersButton = $("button[data-test='training-filters-button']");
    private final SelenideElement filtersBlueDot = $("div[data-test='training-filters-counter']");

    // Table elements
    private final SelenideElement itemsCounterText = $("div[data-test='training-paging-counter']");
    private final SelenideElement prevPageButton = $("button[data-test='training-paging-prev']");
    private final SelenideElement nextPageButton = $("button[data-test='training-paging-next']");
    private final ElementsCollection tableRow = $$("tr[data-test='training-item']");
    private final SelenideElement selectAllCheckbox =
            $("div[data-test='training-master-checkbox-input-view']");
    // Columns
    private final SelenideElement userColumn = $("th[data-test='training-user-name-column']");
    private final SelenideElement dueDateColumn = $("th[data-test='training-due-date-column']");
    private final SelenideElement progressColumn = $("th[data-test='training-progress-column']");
    private final SelenideElement accuracyColumn = $("th[data-test='training-accuracy-column']");
    private final SelenideElement trainingTimeColumn = $("th[data-test='training-time-column']");
    private final SelenideElement assignedColumn = $("th[data-test='assign-date-column']");
    private final SelenideElement startedColumn = $("th[data-test='start-date-column']");
    private final SelenideElement lastTrainingColumn = $("th[data-test='last-training-column']");

    private final ElementsCollection questionMarkButton = $$(".sc-hGNByQ.hudiqh");
    private final ElementsCollection threeDotButton =
            $$("div[data-test='training-item-menu-button']");

    // No Search elements
    private final SelenideElement noSearchIcon =
            $("div[data-test='training-no-found-entries-icon']");
    private final SelenideElement noSearchText =
            $("div[data-test='training-no-found-entries-label']");

    // No Filter elements
    private final SelenideElement noFilterIcon =
            $("div[data-test='training-no-filtered-entries-icon']");
    private final SelenideElement noFilterText =
            $("div[data-test='training-no-filtered-entries-label']");

    // Action Menu
    private final SelenideElement selectedItemsCounter =
            $("span[data-test='table-selected-counter']");
    private final SelenideElement selectAllButton = $("button[data-test='table-select-all']");
    private final SelenideElement selectAllCounter =
            $("span[data-test='table-select-all-counter']");
    private final SelenideElement clearSelectionButton =
            $("button[data-test='table-clear-selection']");
}
