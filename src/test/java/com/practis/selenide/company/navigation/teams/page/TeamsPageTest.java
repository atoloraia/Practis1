package com.practis.selenide.company.navigation.teams.page;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSingleActionAllMembers;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSingleActionTeam;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsManageTeamPage;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertEmptyTeamPage;

import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 1321)
  @DisplayName("Check WEB Elements 'Teams' screen")
  void checkElementsTeamsPage() {
    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

  @TestRailTest(caseId = 1837)
  @DisplayName("Teams: All Members: Check elements on single action menu for All Members team")
  void checkElementsSingleActionAllMembers() {
    teamsPageService().singleActionAllMembers();

    //Assert Training Page
    assertSingleActionAllMembers();
  }

  @TestRailTest(caseId = 18190)
  @DisplayName("Teams: All Members: Single Action: View Team")
  void viewTeamSingleActionAllMembers() {
    teamsPageService().singleActionAllMembers();
    teamsPageService().clickViewTeamSingleAction();

    //Assert 'Team' page for All Members
    assertEmptyTeamPage("All Members");
  }

  @TestRailTest(caseId = 18191)
  @DisplayName("Teams: All Members: Single Action: Manage Team")
  void manageTeamSingleActionAllMembers() {
    teamsPageService().singleActionAllMembers();
    teamsPageService().clickManageTeamSingleAction();

    //Assert 'Team' page for All Members
    assertAllMembersEmptyManageTeamScreen();
  }

  @TestRailTest(caseId = 18192)
  @DisplayName("Teams: Check elements on single action menu for the team")
  @TeamExtension(count = 1)
  void checkElementsSingleActionTeam(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().singleActionTeam(team.get(0).getName());

    //Assert 'Team' page for All Members
    assertSingleActionTeam();
  }

  @TestRailTest(caseId = 18193)
  @DisplayName("Teams: Single Action: View Team")
  @TeamExtension(count = 1)
  void viewTeamSingleAction(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().singleActionTeam(team.get(0).getName());
    teamsPageService().clickViewTeamSingleAction();

    //Assert 'Team' page for All Members
    assertEmptyTeamPage(team.get(0).getName());
  }

  @TestRailTest(caseId = 18194)
  @DisplayName("Teams: Single Action: Manage Team")
  @TeamExtension(count = 1)
  void manageTeamSingleAction(final List<RestTeamResponse> team) {
    Selenide.refresh();
    teamsPageService().singleActionTeam(team.get(0).getName());
    teamsPageService().clickManageTeamSingleAction();

    //Assert 'Team' page for All Members
    assertElementsManageTeamPage(team.get(0).getName());
  }


}