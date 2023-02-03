package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersRegisteredTab {

    // Columns
    private final SelenideElement userColumn = $("th[data-test='name-column']");
    private final SelenideElement teamsColumn = $("th[data-test='teams-column']");
    private final SelenideElement practisSetColumn = $("th[data-test='practis-sets-column']");
    private final SelenideElement roleColumn = $("th[data-test='role-column']");
    private final SelenideElement registeredDateColumn =
            $("th[data-test='registration-date-column']");
    private final SelenideElement lastLoginColumn = $("th[data-test='login-date-column']");

    private final SelenideElement labelsIcon = $("div[data-test='table-labels']");

    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-found-results-label']");

    private final SelenideElement noFilteredResultsIcon =
            $("div[data-test='no-filtered-results-icon']");
    private final SelenideElement noFilteredResultsText =
            $("div[data-test='no-filtered-results-label']");
}
