package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.company.PractisSetReportValidator.assertEmptyNudgePopUp;
import static com.practis.web.selenide.validator.company.PractisSetReportValidator.assertPendingPractisSetReportPage;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Condition;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetReportTest {

    @BeforeEach
    void init(final TeamWithChildren teamWithChildren) {
        teamsPageService().searchTeam(teamWithChildren.getTeam().getName());
        teamPage().getTeamRowTitle().get(0).click();
        jsClick(keepTrackPopUp().getGotItButton());
        teamPage().getMembersTab().click();
        userService().searchUser(teamWithChildren.getUsers().get(0).getFirstName());
        membersTab().getMemberRow().get(0).click();
        userProfilePage().getPsRow().get(0).click();
    }

    @TestRailTest(caseId = 31812)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Practis Set Report: Pending: Check Elements")
    void assertElementsPendingPractisSetReportPage() {

        // Assert Pending Practis Set Report page
        assertPendingPractisSetReportPage();
    }

    @TestRailTest(caseId = 31813)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Practis Set Report: Nudge")
    void assertNudgePractisSetReportPage() {

        // Assert Nudge User modal
        practisSetReportService().psReportClickOnNudge();
        assertEmptyNudgePopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(Condition.exactText("Nudge has been sent"));
    }
}
