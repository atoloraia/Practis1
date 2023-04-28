package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class MembersTab {

    private final SelenideElement membersManageTeamButton =
            $("button[data-test='manage-team-button']");
    private final SelenideElement membersManageTeamIcon = $("div[data-test='manage-team-icon']");
    private final SelenideElement membersFiltersButton =
            $("button[data-test='team-filters-button']");
    private final SelenideElement membersSelectAllCheckbox =
            $("div[data-test='members-master-checkbox-input-view']");
    private final SelenideElement membersActionButton =
            $("div[data-test='assign-table-action-label']");

    // Members columns
    private final SelenideElement membersTeamMembersColumn =
            $("th[data-test='team-members-column']");
    private final SelenideElement membersOverdueColumn = $("th[data-test='overdue-column']");
    private final SelenideElement membersPractisSetStatusColumns =
            $("th[data-test='practis-set-status-column']");
    private final SelenideElement membersNotStartedColumn = $("th[data-test='not-started-column']");
    private final SelenideElement membersInProgressColumn = $("th[data-test='in-progress-column']");
    private final SelenideElement membersCompletedColumn = $("th[data-test='completed-column']");
    private final SelenideElement membersAccuracyColumn = $("th[data-test='accuracy-column']");
    private final SelenideElement membersTrainingTimeColumn =
            $("th[data-test='training-time-column']");
    private final SelenideElement membersLastTrainingColumn =
            $("th[data-test='last-training-column']");

    // Member row values
    private final ElementsCollection memberRow = $$("tr[data-test='member-item']");
    private final ElementsCollection memberLabelIcon = $$("div[data-test='table-labels']");

    // Members 3-dot menu
    private final SelenideElement membersThreeDotMenu = $(".action-button-element");
    private final SelenideElement membersViewProfileOption =
            $("div[data-test='view-profile-action-item']");
    private final SelenideElement membersAssignPractisSetOption =
            $("div[data-test='assign-practisset-action-item']");
    private final SelenideElement membersNudgeUserOption =
            $("div[data-test='nudge-user-action-item']");
    private final SelenideElement membersExportReportOption =
            $("div[data-test='export-report-action-item']");
    private final SelenideElement removeFromTeamOption =
            $("div[data-test='remove-from-team-action-item']");
    private final SelenideElement itemsCounterText = $("div[data-test='team-paging-counter']");

    // Members bulk Action
    private final SelenideElement assignLabelsBulkAction =
            $("div[data-test='assign-practis-sets-table-action']");
    private final SelenideElement nudgeBulkAction = $("div[data-test='nudge-users-table-action']");
    private final SelenideElement exportReportBulkAction =
            $("div[data-test='export-report-table-action']");
    private final SelenideElement removeFromTeamBulkAction =
            $("div[data-test='delete-table-action']");

    // Members empty values
    private final SelenideElement noMembersIcon = $("div[data-test='no-members-yet-icon']");
    private final SelenideElement noMembersText = $("div[data-test='no-members-yet-label']");
    private final SelenideElement membersNoSearchResultsIcon =
            $("div[data-test='no-members-found-icon']");
    private final SelenideElement membersNoSearchResultsText =
            $("div[data-test='no-members-found-label']");
    private final SelenideElement membersNoFilterResultsIcon =
            $("div[data-test='no-filtering-criteria-icon']");
    private final SelenideElement membersNoFilterResultsText =
            $("div[data-test='no-filtering-criteria-label']");

    // Search field
    private final SelenideElement membersSearchField = $("input[data-test='team-search']");
    private final SelenideElement membersSearchFieldIcon = $("div[data-test='team-search-icon']");
    private final SelenideElement membersSearchFieldCrossButton =
            $("div[data-test='team-search-clear']");

    // filters modal - buttons
    private final SelenideElement applyFiltersButton =
            $("button[data-test='apply-filters-button']");
    private final SelenideElement clearFiltersButton =
            $("button[data-test='clear-filters-button']");
    private final SelenideElement selectionCounter = $("span[data-test='total-filters-counter']");
    private final SelenideElement itemsCounter = $("div[data-test='team-paging-counter']");
}
