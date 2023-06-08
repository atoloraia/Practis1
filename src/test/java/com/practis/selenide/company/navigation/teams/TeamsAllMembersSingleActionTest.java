package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertEmptyTeamPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsAllMembersSingleActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31755)
    @DisplayName("Teams: All Members: Single Action: View Team")
    void viewTeamSingleActionAllMembers() {
        teamsPageService().clickSingleActionAllMembers();
        teamsPageService().clickViewTeamSingleAction();

        // Assert 'Team' page for All Members
        assertEmptyTeamPage("All Members");
    }

    @TestRailTest(caseId = 31756)
    @DisplayName("Teams: All Members: Single Action: Manage Team")
    void manageTeamSingleActionAllMembers() {
        teamsPageService().clickSingleActionAllMembers();
        teamsPageService().clickManageTeamSingleAction();

        // Assert 'Team' page for All Members
        assertAllMembersEmptyManageTeamScreen();
    }

    @TestRailTest(caseId = 31754)
    @DisplayName("Teams: All Members: Single action: Check elements on single action")
    void viewSingleActionMenuAllMembers() {

        teamsPageService().clickSingleActionAllMembers();

        // Assert Training Page
        assertElementsEmptyTeamsPage();
    }
}
