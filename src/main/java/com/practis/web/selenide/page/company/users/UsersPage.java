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

    private final ElementsCollection userRowValue = $$(".sc-ddCvFA.gikQKi");

    // Registered list
    private final SelenideElement usersHeader = $("div[data-test='users-page-subtitle']");
    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updateTimestampButton =
            $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchField = $("div[data-test='table-search-input']");
    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement filtersButton = $("button[data-test='users-filters-button']");
    private final SelenideElement itemsCounterText = $("div[data-test='table-paging-counter']");
    private final SelenideElement previousPageArrow = $("button[data-test='table-paging-prev']");
    private final SelenideElement nextPageArrow = $("button[data-test='table-paging-next']");

    private final SelenideElement selectAllCheckbox = $("input[data-test*='master-checkbox-']");
    private final ElementsCollection listValues = $$("td[data-test='table-cell']");
    private final ElementsCollection labelsIcon = $$("div[data-test='table-labels']");
    private final ElementsCollection threeDotMenu = $$("div[data-test='list-item-menu-button']");

    // Users - Registered, single actions
    private final SelenideElement viewProfileAction = $("div[data-test='view-profile-action']");
    private final SelenideElement userSettingsAction = $("div[data-test='user-settings-action']");
    private final SelenideElement assignPractisSetsAction =
            $("div[data-test='assign-practis-sets-action']");
    private final SelenideElement assignLabelsAction = $("div[data-test='assign-labels-action']");
    private final SelenideElement nudgeUsersAction = $("div[data-test='nudge-user-action']");
    private final SelenideElement exportReportAction = $("div[data-test='export-report-action']");
    private final SelenideElement deleteUserAction = $("div[data-test='delete-user-action']");

    private final SelenideElement userRow = $("tr[data-test='table-row']");

    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-found-results-label']");
}
