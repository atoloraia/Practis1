package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.userActivityReportPage;

public class TeamLeaderEngagementReportService {

    /** Select Team. */
    public void clickOnTeamCheckbox() {
        userActivityReportPage().getTeamsTitle().get(0).click();
    }
}
