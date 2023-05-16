package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.billingReportPage;

public class BillingReportService {

    /** Select Month. */
    public void clickOnMonth() {
        billingReportPage().getCurrentMonthView().click();
    }

    /** Click on Next arrow. */
    public void clickOnNextArrow() {
        billingReportPage().getYearNextButton().click();
    }

    /** Click on Prev arrow. */
    public void clickOnPrevArrow() {
        billingReportPage().getYearPrevButton().click();
    }
}
