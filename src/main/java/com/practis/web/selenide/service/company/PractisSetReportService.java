package com.practis.web.selenide.service.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetDetailsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetReportPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

public class PractisSetReportService {

    /** Open Practis Set report from User Profile page. */
    public void openPsReportFromUserProfile() {
        usersPage().getUserRow().get(0).click();
        userProfilePage().getPsRow().get(0).click();
    }

    /** Open Practis Set report from Practis Set Details page. */
    public void openPsReportFromPsDetailsPage(String teamName) {
        teamsPageService().searchTeam(teamName);
        teamsPage().getTeamRow().get(0).click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        jsClick(keepTrackPopUp().getGotItButton());
        trainingTab().getTeamRow().get(0).click();
        practisSetDetailsPage().getTableRow().get(0).click();
    }

    /** Click on Nudge button. */
    public void psReportClickOnNudge() {
        practisSetReportPage().getNudgeButton().click();
    }

    /** Click on Scenario Card. */
    public void psReportClickOnScenarioCard() {
        practisSetReportPage().getScenarioCard().get(0).click();
    }

    /** Click on Challenge card. */
    public void psReportClickOnChallengeCard() {
        practisSetReportPage().getChallengeCard().get(0).click();
    }

    /** Click on Review button. */
    public void psReportClickOnReviewButton() {
        practisSetReportPage().getReviewButton().click();
    }
}
