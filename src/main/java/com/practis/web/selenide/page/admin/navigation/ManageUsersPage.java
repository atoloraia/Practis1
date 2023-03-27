package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ManageUsersPage {

    private final SelenideElement manageUsersTitle =
            $("div[data-test='manage-users-page-page-subtitle']");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='manage-users-timestamp-label']");
    private final SelenideElement updateButton =
            $("button[data-test='manage-users-timestamp-refresh']");

    private final SelenideElement searchField = $("input[data-test='manage-users-search']");
    private final SelenideElement searchFieldIcon = $("div[data-test='manage-users-search-icon']");
    private final SelenideElement searchCleanIcon = $("div[data-test='manage-users-search-clear']");
    private final SelenideElement paginationCounterText =
            $("div[data-test='manage-users-paging-counter']");
    private final SelenideElement paginationBackButton =
            $("button[data-test='manage-users-paging-prev']");
    private final SelenideElement paginationNextButton =
            $("button[data-test='manage-users-paging-next']");

    // Manage Users list columns
    private final SelenideElement nameColumn = $("th[data-test='name-column']");
    private final SelenideElement emailColumn = $("th[data-test='email-column']");
    private final SelenideElement companyColumn = $("th[data-test='company-column']");
    private final SelenideElement roleColumn = $("th[data-test='role-column']");
    private final SelenideElement statusColumn = $("th[data-test='status-column']");

    // Manage Users row values
    private final ElementsCollection manageUsersRow = $$("tr[data-test='table-row']");
    private final ElementsCollection usersRow = $$("div[data-test='manage-users-user-item-title']");
    private final ElementsCollection emailRow = $$("span[data-test='manage-users-email-item']");
    private final ElementsCollection companyRow =
            $$("div[data-test='manage-users-company-item-title']");
    private final ElementsCollection companyLogo =
            $$("img[data-test='manage-users-company-item-avatar']");
    private final ElementsCollection statusRow = $$("div[data-test='manage-users-status-item']");

    // No results
    private final SelenideElement noUsersIcon = $("div[data-test='no-results-icon']");
    private final SelenideElement noUsersText = $("div[data-test='no-results-label']");
    private final SelenideElement noUsersSearchIcon = $("div[data-test='no-users-found-icon']");
    private final SelenideElement noUserSearchText = $("div[data-test='no-users-found-label']");
}
