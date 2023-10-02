package com.practis.selenide.company.navigation.reports;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertElementsOnReportsPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class ReportsTest {
    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
    }

    @TestRailTest(caseId = 32051)
    @DisplayName("Reports: Reporting Screen: Check Elements for Admins")
    void checkElementsOnReportsPage() {

        assertElementsOnReportsPage();
    }
}
