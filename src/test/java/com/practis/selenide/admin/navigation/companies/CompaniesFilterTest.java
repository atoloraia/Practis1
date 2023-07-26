package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyAccoutsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyStatusService;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertFilterButtonCompanies;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertNoResultsCompanyAccounts;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertRowColumn;
import static com.practis.web.selenide.validator.admin.CompanyAccountsValidator.assertRowCompanyAccounts;
import static com.practis.web.selenide.validator.selection.CompaniesFilterStatusValidator.assertCompaniesStatusModule;

import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.CompanyExtension;
import com.practis.web.util.SelenideJsUtils;
import com.practis.web.util.SelenidePageLoadAwait;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
public class CompaniesFilterTest {

    @Disabled
    //@TestRailTest(caseId = 23855)
    @DisplayName("Companies: Filter: Check Elements")
    @CompanyExtension(count = 1)
    void checkFiltersCompanies(List<RestCompanyResponse> companies) {
        practisApi().deactivateCompany(companies.get(0).getName());

        // assert elements on filter
        assertFilterButtonCompanies();
        companyAccountsPage().getFiltersButton().click();
        assertCompaniesStatusModule();

        // select All statuses and click outside the filter
        companyStatusService().selectInactiveStatus();
        SelenideJsUtils.jsClick(companyAccountsPage().getFiltersButton());

        // assert changes for filter haven't been applied: there is no inactive companies
        SelenidePageLoadAwait.awaitAjaxComplete(10);
        grid().getRows().forEach(row -> assertRowColumn(row, "Status", "Active"));
        companyAccoutsService().searchCompany(companies.get(0).getName());
        assertNoResultsCompanyAccounts();
    }

    @Disabled
    //@TestRailTest(caseId = 23854)
    @DisplayName("Companies: Filter: Inactive")
    @CompanyExtension(count = 1)
    void checkInactiveFiltersCompanies(List<RestCompanyResponse> companies) {
        companyAccountsPage().getFiltersButton().click();

        // select Inactive and Active statuses and click outside the filter
        companyStatusService().selectOnlyInactiveStatusApply();

        // assert changes for the filter have been applied: there are no active companies
        SelenidePageLoadAwait.awaitAjaxComplete(10);
        grid().getRows().forEach(row -> assertRowColumn(row, "Status", "Inactive"));
        companyAccoutsService().searchCompany(companies.get(0).getName());
        assertNoResultsCompanyAccounts();
    }

    @Disabled
    //@TestRailTest(caseId = 23858)
    @DisplayName("Companies: Filter: Inactive + Active")
    @CompanyExtension(count = 2)
    void checkInactiveActiveFiltersCompanies(List<RestCompanyResponse> companies) {
        practisApi().deactivateCompany(companies.get(0).getName());

        companyAccountsPage().getFiltersButton().click();

        // select Inactive and Active statuses and Apply filter
        companyStatusService().selectActiveInactiveStatusApply();

        // assert Active and Inactive companies are shown in the list
        var inactiveCompanyRow = companyAccoutsService().searchCompany(companies.get(0).getName());
        assertRowCompanyAccounts(companies.get(0), inactiveCompanyRow, "Inactive");

        var activeCompanyRow = companyAccoutsService().searchCompany(companies.get(1).getName());
        assertRowCompanyAccounts(companies.get(1), activeCompanyRow, "Active");
    }
}
