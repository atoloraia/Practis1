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
    private final SelenideElement generateReportButton =
            $("button[data-test='export-report-button']");
    private final SelenideElement generateReportIcon = $("div[data-test='export-report-icon']");

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

    private final SelenideElement progressQuestionMarkButton =
            $("div[data-test='training-progress-column-tooltip-icon']");
    private final SelenideElement accuracyQuestionMarkButton =
            $("div[data-test='training-accuracy-column-tooltip-icon']");
    private final SelenideElement trainingTimeQuestionMarkButton =
            $("div[data-test='training-time-column-tooltip-icon']");
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

    // Filters - Registration Status
    private final SelenideElement registrationStatusTitle =
            $("span[data-test='registration-status-section-title-title']");
    private final ElementsCollection registrationStatusItems =
            $$("div[data-test='registration-status-item-container']");
    private final SelenideElement pendingText = $("div[data-test='pending-status-checkbox-label']");
    private final SelenideElement pendingCheckbox =
            $("div[data-test='pending-status-checkbox-view']");
    private final SelenideElement activeText = $("div[data-test='active-status-checkbox-label']");
    private final SelenideElement activeCheckbox =
            $("div[data-test='active-status-checkbox-view']");

    // Filters - Progress Status
    private final SelenideElement progressStatusTitle =
            $("span[data-test='progress-status-section-title-title']");
    private final ElementsCollection progressStatusItems =
            $$("div[data-test='progress-status-item-container']");
    private final SelenideElement notStartedText =
            $("div[data-test='not-started-status-checkbox-label']");
    private final SelenideElement notStartedCheckbox =
            $("div[data-test='not-started-status-checkbox-view']");
    private final SelenideElement inProgressText =
            $("div[data-test='in-progress-status-checkbox-label']");
    private final SelenideElement inProgressCheckbox =
            $("div[data-test='in-progress-status-checkbox-view']");
    private final SelenideElement completedText =
            $("div[data-test='completed-status-checkbox-label']");
    private final SelenideElement completedCheckbox =
            $("div[data-test='completed-status-checkbox-view']");

    // Filters - Due Date
    private final SelenideElement dueDateTitle =
            $("span[data-test='due-date-section-title-title']");
    private final SelenideElement assignedTitle = $("div[data-test='assigned-checkbox-label']");
    private final SelenideElement assignedCheckbox = $("div[data-test='assigned-checkbox-view']");
    private final SelenideElement noDueDateText = $("div[data-test='no-due-date-checkbox-label']");
    private final SelenideElement noDueDateCheckbox =
            $("div[data-test='no-due-date-checkbox-view']");
    private final SelenideElement overdueText = $("div[data-test='overdue-checkbox-label']");
    private final SelenideElement overdueCheckbox = $("div[data-test='overdue-checkbox-view']");
    private final SelenideElement overdueIcon = $("span[data-test='overdue-checkbox-icon']");
}
