package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class OverdueLearnersTab {
    private final SelenideElement overdueTitle = $("div[data-test='team-overdue-learners']");

    // Overdue Tab
    private final SelenideElement overdueTimestamp =
            $("span[data-test='overdue-learners-timestamp-label']");
    private final SelenideElement overdueTimestampRefresh =
            $("button[data-test='overdue-learners-timestamp-refresh']");
    private final SelenideElement overdueSearchField =
            $("input[data-test='overdue-learners-search']");
    private final SelenideElement overdueSearchFieldIcon =
            $("div[data-test='overdue-learners-search-icon']");
    private final SelenideElement overdueSearchFieldCrossButton =
            $("div[data-test='overdue-learners-search-clear']");
    private final SelenideElement noOverdueFoundIcon = $("div[data-test='no-found-users-icon']");
    private final SelenideElement noOverdueFoundText = $("div[data-test='no-found-users-label']");
    private final SelenideElement overdueFilterButton =
            $("button[data-test='overdue-learners-filters-button']");
    private final SelenideElement overdueItemsCounter =
            $("div[data-test='overdue-learners-paging-counter']");
    private final SelenideElement overduePrevButton =
            $("button[data-test='overdue-learners-paging-prev']");
    private final SelenideElement overdueNextButton =
            $("button[data-test='overdue-learners-paging-next']");
    private final SelenideElement selectAllCheckbox =
            $("input[data-test='members-master-checkbox-input-partially-checked']");

    private final ElementsCollection overdueRow = $$("tr[data-test='table-row']");
    private final SelenideElement overdueNameColumn = $("th[data-test='name-column']");
    private final SelenideElement overdueTeamsColumn = $("th[data-test='teams-column']");
    private final SelenideElement overdueColumnItem =
            $("div[data-test='overdue-users-user-item-title']");
    private final SelenideElement overdueNameColumnItem = $("div[data-test='teams-item-members']");
    private final SelenideElement overdueTeamsSetsColumnItem =
            $("div[data-test='overdue-users-teams-item']");

    // 3-dot menu
    private final SelenideElement singleActionOnTeams = $("div[data-test='list-item-menu-button']");
    private final SelenideElement viewProfileSingleAction =
            $("div[data-test='view-profile-action']");
    private final SelenideElement nudgeSingleAction = $("div[data-test='nudge-user-action']");

    // Selection modal - Action
    private final SelenideElement actionButton = $("div[data-test='assign-table-action-label']");
    private final SelenideElement nudgeActionButton =
            $("div[data-test='nudge-users-table-action']");
    private final SelenideElement selectedItemCounterText =
            $("span[data-test='table-selected-counter']");
    private final SelenideElement selectAllButton = $("button[data-test='table-select-all']");
    private final SelenideElement clearSelectionButton =
            $("button[data-test='table-clear-selection']");
}
