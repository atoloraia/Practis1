package com.practis.selenide.admin.navigation.companies;

import static com.practis.web.selenide.configuration.PageObjectFactory.companiesPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companiesService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyStatusService;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertActiveStatusForCompanyRow;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertFilterButtonCompanies;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertInactiveActiveStatusForCompanyRow;
import static com.practis.web.selenide.validator.admin.CompaniesValidator.assertInactiveStatusForCompanyRow;
import static com.practis.web.selenide.validator.selection.CompaniesFilterStatusValidator.assertCompaniesStatusModule;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
public class CompaniesFilterTest {

    // TODO update
    // @TestRailTest(caseId = 23855)
    @DisplayName("Companies: Filter: Check Elements")
    void checkFiltersCompanies() {
        assertFilterButtonCompanies();

        // assert elements on filter
        companiesPage().getFiltersButton().click();
        assertCompaniesStatusModule();

        // select Inactive and Active statuses and click outside the filter
        companyStatusService().selectInactiveStatus();
        companiesService().clickOutsideTheFilter();

        // assert All Companies in the list have 'Active' status
        assertActiveStatusForCompanyRow();
    }

    // TODO update
    // @TestRailTest(caseId = 23854)
    @DisplayName("Companies: Filter: Inactive")
    void checkInactiveFiltersCompanies() {

        // select Inactive and Active statuses and click outside the filter
        companyStatusService().selectOnlyInactiveStatusApply();

        // assert All Companies in the list have 'Active' status
        assertInactiveStatusForCompanyRow();
    }

    // TODO update
    // @TestRailTest(caseId = 23858)
    @DisplayName("Companies: Filter: Inactive + Active")
    void checkInactiveActiveFiltersCompanies() {

        // select Inactive and Active statuses and click outside the filter
        companyStatusService().selectActiveInactiveStatusApply();

        // assert All Companies in the list have 'Active' status
        assertInactiveActiveStatusForCompanyRow();
    }
}
