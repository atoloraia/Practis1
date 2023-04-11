package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPage {

    // tabs
    private final SelenideElement registeredTab = $("a[data-test='users-tab-registered']");
    private final SelenideElement pendingTab = $("a[data-test='users-tab-pending']");
    private final SelenideElement draftTab = $("a[data-test='users-tab-drafts']");

    private final ElementsCollection userRowValue = $$(".sc-gJvIFg.lirGUH");
    private final ElementsCollection userTeamValue = $$("div[data-test='teams-count']");
    private final ElementsCollection userRow = $$("tr[data-test='user-item']");

    private final SelenideElement usersHeader = $("div[data-test='users-page-subtitle']");
    private final SelenideElement updatedTimestampText =
            $("span[data-test='users-timestamp-label']");
    private final SelenideElement updateTimestampButton =
            $("button[data-test='users-timestamp-refresh']");

    private final SelenideElement searchField = $("div[data-test='users-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='users-search-icon']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement filtersButton = $("button[data-test='users-filters-button']");
    private final SelenideElement itemsCounterText = $("div[data-test='users-paging-counter']");
    private final SelenideElement previousPageArrow = $("button[data-test='users-paging-prev']");
    private final SelenideElement nextPageArrow = $("button[data-test='users-paging-next']");

    private final SelenideElement selectAllCheckbox = $("input[data-test*='master-checkbox-']");
    private final SelenideElement selectAllCheckboxClick =
            $("div[data-test='master-checkbox-input-view']");
    private final ElementsCollection listValues = $$("td[data-test='table-cell']");
    private final ElementsCollection labelsIcon = $$("div[data-test='user-labels']");
    private final ElementsCollection assignedLabelsCounter = $$(".sc-cLqpJY.fnUanC");
    private final ElementsCollection threeDotMenu = $$("div[data-test='list-item-menu-button']");

    // Bulk action
    private final SelenideElement assignButton = $("div[data-test='assign-table-action-label']");

    // Empty state
    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-found-results-label']");

    private final SelenideElement noUsersIcon = $("div[data-test='no-results-icon']");
    private final SelenideElement noUsersText = $("div[data-test='no-results-label']");

    private final SelenideElement noFilteredResultsIcon =
            $("div[data-test='no-filtered-results-icon']");
    private final SelenideElement noFilteredResultsText =
            $("div[data-test='no-filtered-results-label']");
}
