package com.practis.web.selenide.page.admin.navigation;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyAccountsPage {

    private final SelenideElement companyHeaderText =
            $("div[data-test='company-accounts-page-page-subtitle']");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updateButton = $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement paginationCounterText =
            $("div[data-test='table-paging-counter']");
    private final SelenideElement paginationBackButton = $("button[data-test='table-paging-prev']");
    private final SelenideElement paginationNextButton = $("button[data-test='table-paging-next']");

    private final SelenideElement sortingArrow = $("button[data-test='table-column-sorting']");

    // 'Company Accounts' list columns
    private final SelenideElement companyColumn = $("div[data-test='company-title-column-text']");
    private final SelenideElement companyOwnerColumn =
            $("div[data-test='company-owner-column-text']");
    private final SelenideElement dateActivatedColumn = $("div[data-test='created-column-text']");
    private final SelenideElement ownerColumn = $("div[data-test='owner-column-text']");
    private final SelenideElement statusColumn = $("div[data-test='status-column-text']");
    private final SelenideElement limitColumn = $("div[data-test='limit-column-text']");
    private final SelenideElement usedSeatsColumn = $("div[data-test='used-seats-column-text']");

    // Company rows
    private final ElementsCollection companyRow = $$("tr[data-test='company-item']");
    private final ElementsCollection companyTitle = $$("tr[data-test='company-item-title']");
    private final ElementsCollection companyOwnerRow = $$("tr[data-test='company-item-creator']");
    private final ElementsCollection dateActivatedRow =
            $$("tr[data-test='date-activated-column-text']");
    private final ElementsCollection ownerRow = $$("tr[data-test='owner-column-text']");
    private final ElementsCollection statusRow = $$("tr[data-test='status-column-text']");
    private final ElementsCollection limitRow = $$("span[data-test='company-item-limit']");
    private final ElementsCollection usedSeatsRow = $$("span[data-test='company-item-used-seats']");

    // 3-dot menu
    private final ElementsCollection threeDotMenuEventRow =
            $$("div[data-test='company-item-menu-button']");
    private final SelenideElement settingsSingleAction =
            $("div[data-test='company-settings-action']");

    private final SelenideElement noCompanySearchIcon =
            $("div[data-test='no-found-companies-icon']");
    private final SelenideElement noCompanySearchText =
            $("div[data-test='no-found-companies-label']");

    // TODO update Filters
    private final SelenideElement filtersButton = $("button[data-test='company-filters-button']");
    private final SelenideElement filtersCounter = $("div[data-test='company-filters-counter']");
    private final SelenideElement filterTooltip = $("div[data-test='filters-tooltip']");
    ;
}
