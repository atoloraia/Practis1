package com.practis.web.selenide.service.admin;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companiesPage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;

import com.codeborne.selenide.Condition;
import com.practis.web.selenide.component.GridRow;

public class CompaniesService {

    /** Search admin on grid by email. */
    public GridRow searchCompany(final String name) {
        navigationAdmin().companyNavigationItem.click();
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    // TODO update
    /** Click 3-dot menu for the Company. */
    public void clickSingleAction(final String name) {
        final var companyRow = companiesPage().getCompanyRow().find(Condition.matchText(name));
        companyRow.$("div[data-test='company-item-menu-button']").click();
    }

    /** Click outside the Filter module. */
    public void clickOutsideTheFilter() {
        companiesPage().getUpdateButton().click();
    }
}
