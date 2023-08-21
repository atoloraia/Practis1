package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AdministratorsPage {

    private final SelenideElement adminHeaderText =
            $("div[data-test='administrators-page-page-subtitle']");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updateButton = $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement paginationCounterText =
            $("div[data-test='table-paging-counter']");
    private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
    private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");

    private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

    // 'Administrators' list columns
    private final SelenideElement administratorColumn =
            $("div[data-test='administrator-title-column-text']");
    private final SelenideElement emailAddressColumn =
            $("div[data-test='email-address-column-text']");
    private final SelenideElement dateCreatedColumn =
            $("div[data-test='date-created-column-text']");
    private final SelenideElement ownerColumn = $("div[data-test='owner-column-text']");

    // Administrator rows
    private final ElementsCollection administratorItemRow =
            $$("tr[data-test='administrator-item']");
    private final ElementsCollection administratorRow = $$("tr[data-test='administrator-item']");
    private final ElementsCollection emailAddressRow =
            $$("tr[data-test='administrator-item-email']");
    private final ElementsCollection dateCreatedRow =
            $$("tr[data-test='administrator-item-creation-date']");
    private final ElementsCollection ownerRow = $$("tr[data-test='administrator-item-creator']");

    private final ElementsCollection threeDotMenuEventRow =
            $$("div[data-test='administrator-item-menu-button']");
    private final SelenideElement deactivateButton =
            $("div[data-test='delete-administrator-action']");

    private final SelenideElement noAdministratorsSearchIcon =
            $("div[data-test='no-found-administrators-icon']");
    private final SelenideElement noAdministratorsSearchText =
            $("div[data-test='no-found-administrators-label']");
}
