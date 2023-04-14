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
    public void clickOnTeamLeaderSummaryCard() {
        reportsPage().getTeamLeaderSummaryCard().click();
    }
}
