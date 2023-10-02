package com.practis.selenide.company.navigation.reports.Billing;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.billingReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertElementsOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertHiddenCurrentMonthOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.BillingReportValidator.assertSelectedMonthOnBillingReportPage;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertEnabledGenerateButtonReport;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class BillingReport {

    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
        reportsService().clickOnBillingCard();
    }

    @TestRailTest(caseId = 32064)
    @DisplayName("Reports: Billing Report: Check Elements")
    void checkElementsOnBillingReportPage() {

        assertElementsOnBillingReportPage();
    }

    @TestRailTest(caseId = 32065)
    @DisplayName("Reports: Billing Report: Check Calendar Picker")
    void checkCalendarPickerOnBillingReportPage() {

        billingReportService().clickOnPrevArrow();
        assertHiddenCurrentMonthOnBillingReportPage();
        billingReportService().clickOnNextArrow();
        assertElementsOnBillingReportPage();
    }

    @TestRailTest(caseId = 32066)
    @DisplayName("Reports: Billing Report: Generate")
    void generateBillingReportPage() {

        // Make Generate button enabled
        billingReportService().clickOnMonth();
        assertEnabledGenerateButtonReport();

        // Click on Clear button
        reportsService().clickOnClearButton();
        assertElementsOnBillingReportPage();

        // CLick on Generate button
        billingReportService().clickOnMonth();
        assertSelectedMonthOnBillingReportPage();
        reportsService().clickOnGenerateButton();
        assertEnabledGenerateButtonReport();

        snackbar()
                .getMessage()
                .shouldBe(
                        exactText(
                                "The report is being generated. Check your email in a few"
                                        + " minutes."));
    }
}
