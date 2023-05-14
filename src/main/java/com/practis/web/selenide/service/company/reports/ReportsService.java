package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.reportsPage;

public class ReportsService {

    /** Click on Practis Set Summary Report. */
    public void clickOnPractisSetSummaryCard() {
        reportsPage().getPractisSetSummaryCard().click();
    }

    /** Click on User Activity Report. */
    public void clickOnUserActivityCard() {
        reportsPage().getUserActivityCard().click();
    }

    /** Click on Team Leader Summary Report. */
    public void clickOnTeamLeaderEngagementCard() {
        reportsPage().getTeamLeaderEngagementCard().click();
    }

    /** Click on Billing Report. */
    public void clickOnBillingCard() {
        reportsPage().getBillingCard().click();
    }

    /** Click on Search icon. */
    public void clickOnFirstModalSearchIcon() {
        reportsPage().getFilterSearchIcon().get(0).click();
    }

    /** Click on clear search. */
    public void clickOnClearSearch() {
        reportsPage().getFilterSearchClear().get(1).click();
    }

    /** Click on Search icon on PS modal. */
    public void clickOnSecondModalSearchIcon() {
        reportsPage().getFilterSearchIcon().get(1).click();
    }

    /** Click on Clear button. */
    public void clickOnClearButton() {
        reportsPage().getClearReportButton().click();
    }

    /** Click on Generate button. */
    public void clickOnGenerateButton() {
        reportsPage().getGenerateReportButton().click();
    }
}
