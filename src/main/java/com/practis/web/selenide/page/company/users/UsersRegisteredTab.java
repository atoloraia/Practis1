package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersRegisteredTab {

    private final SelenideElement noSearchResultIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noSearchResultText = $("div[data-test='no-found-results-label']");

    // Columns
    private final SelenideElement userColumn = $("th[data-test='name-column']");
    private final SelenideElement teamsColumn = $("th[data-test='teams-column']");
    private final SelenideElement practisSetColumn = $("th[data-test='practis-sets-column']");
    private final SelenideElement roleColumn = $("th[data-test='role-column']");
    private final SelenideElement registeredDateColumn =
            $("th[data-test='registration-date-column']");
    private final SelenideElement lastLoginColumn = $("th[data-test='login-date-column']");

    private final SelenideElement labelsIcon = $("div[data-test='table-labels']");

    private final ElementsCollection assignedLabelsCounter = $$(".sc-dbqXpx.bqxSxo");

    // Users - Registered, single actions
    private final SelenideElement viewProfileAction = $("div[data-test='view-profile-action']");
    private final SelenideElement userSettingsAction = $("div[data-test='user-settings-action']");
    private final SelenideElement assignPractisSetsAction =
            $("div[data-test='assign-practis-sets-action']");
    private final SelenideElement assignLabelsAction = $("div[data-test='assign-labels-action']");
    private final SelenideElement nudgeUsersAction = $("div[data-test='nudge-user-action']");
    private final SelenideElement exportReportAction = $("div[data-test='export-report-action']");
    private final SelenideElement deactivateUserAction = $("div[data-test='delete-user-action']");

    private final ElementsCollection userRow = $$("tr[data-test='user-item']");

    // Users - Registered, bulk actions
    private final SelenideElement assignPsBulkAction =
            $("div[data-test='assign-practis-sets-table-action']");
    private final SelenideElement assignLabelsBulkAction =
            $("div[data-test='assign-labels-table-action']");
    private final SelenideElement nudgeUsersBulkAction =
            $("div[data-test='nudge-users-table-action']");
    private final SelenideElement exportReportBulkAction =
            $("div[data-test='export-report-table-action']");
    private final SelenideElement deactivateUsersBulkAction =
            $("div[data-test='delete-table-action']");
}
