package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.billingReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userActivityReportPage;

public class BillingReportService {

  /** Select Month. */
  public void clickOnMonth() {
    billingReportPage().getMonthsTitles().get(0).click();
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
