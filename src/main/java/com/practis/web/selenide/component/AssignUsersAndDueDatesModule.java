package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AssignUsersAndDueDatesModule {

    private final SelenideElement assignUsersAndDueDatesTitle =
            $("div[data-test='assign-users-title']");
    private final SelenideElement practisSetSubtitle = $("div[data-test='practis-set-name']");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updatedTimestampButton =
            $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchField = $("input[data-test='table-search-input']");
    private final ElementsCollection searchFields = $$("input[data-test='table-search-input']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='practisset-searchbox-field-clear']");
    private final SelenideElement searchFieldXButton =
            $("div[data-test='assign-users-search-clear']");
    private final SelenideElement filtersButton =
            $("button[data-test='assign-users-filters-button']");

    // Users table
    // columns
    private final SelenideElement itemsCounterText = $("p[data-test='table-selected-item']");
    private final SelenideElement selectAllCheckbox =
            $("div[data-test='assign-users-master-checkbox-input-view']");
    private final SelenideElement userColumn = $("th[data-test='users-column']");
    private final SelenideElement dueDateColumn = $("th[data-test='due-date-column']");
    private final SelenideElement registeredOnColumn =
            $("th[data-test='registration-date-column-text']");
    // rows
    private final ElementsCollection userRow = $$("tr[data-test='table-row']");
    private final ElementsCollection userCheckbox = $$("div[data-test='user-item-checkbox-view']");
    private final ElementsCollection userLabelRow = $$("div[data-test='table-labels']");
    private final ElementsCollection userNameRow = $$("div[data-test='user-item-name']");
    private final ElementsCollection dueDateRow = $$("span[data-test='user-item-due-date']");
    private final ElementsCollection registeredOnRow =
            $$("span[data-test='user-item-registration-date']");
    private final ElementsCollection threeDotButton =
            $$("div[data-test='due-date-actions-button']");

    private final SelenideElement noUserIcon = $(".sc-jlObCs.heRKBi");
    private final SelenideElement noUserText = $(".sc-NqzHV.heoJmO");

    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-users-found-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-users-found-label']");

    private final SelenideElement assignSelectedUsersButton =
            $(".sc-caiKgP.hPyIrA.undefined.primary");
}
