package com.practis.web.selenide.service.company.reports;

import static com.practis.web.selenide.configuration.PageObjectFactory.userActivityReportPage;

public class UserActivityReportService {

    /** Click on Search icon. */
    public void clickOnTeamCheckbox() {
        userActivityReportPage().getTeamsTitle().get(0).click();
    }
}
