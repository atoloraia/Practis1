package com.practis.web.selenide.service.admin.company;

import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdmin;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

public class CompanyAccoutsService {

    /** Search company on grid by email. */
    public GridRow searchCompany(final String name) {
        navigationAdmin().companyNavigationItem.click();
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Find status for first company Row. */
    public SelenideElement findStatusFirstCompanyRow() {
        final var companyRow = companyAccountsPage().getCompanyRow().get(0);
        return companyRow.$("[data-test='company-item-status']");
    }

    // TODO update

    /** Click 3-dot menu for the Company. */
    public void clickSingleAction(final String name) {
        final var companyRow =
                companyAccountsPage().getCompanyRow().find(Condition.matchText(name));
        companyRow.$("div[data-test='company-item-menu-button']").click();
    }

    /** Click outside the Filter module. */
    public void clickOutsideTheFilter() {
        jsClick($(".root"));
    }
}
