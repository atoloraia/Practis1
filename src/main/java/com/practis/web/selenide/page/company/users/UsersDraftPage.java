package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersDraftPage {

    // Columns
    private final SelenideElement draftColumn = $("th[data-test='name-column']");
    private final SelenideElement usersColumn = $("th[data-test='users-count-column']");
    private final SelenideElement createdByColumn = $("th[data-test='creator-column']");
    private final SelenideElement createdOnColumn = $("th[data-test='creation-date-column']");
    private final SelenideElement editedByColumn = $("th[data-test='editor-column']");
    private final SelenideElement editedOnColumn = $("th[data-test='edit-date-column']");

    private final SelenideElement noDraftYetIcon = $("div[data-test='no-results-icon']");
    private final SelenideElement noDraftYetText = $("div[data-test='no-results-label']");
}
