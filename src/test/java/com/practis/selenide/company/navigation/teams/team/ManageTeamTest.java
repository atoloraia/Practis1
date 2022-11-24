package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.service.company.team.ManageTeamService.clickCloseManageTeam;
import static com.practis.web.selenide.service.company.team.ManageTeamService.openAllMembersManageTeamScreen;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertDataOnTeamsPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMember;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMemberPending;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertCountersOnTeamPage;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.Qualifier;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ManageTeamTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 18184)
  @DisplayName("Team: All Members: Check Elements on 'Manage Team' Page")
  void checkElementsEmptyAllMembersTeamsPage() {

    //Open 'All Members' Manage Team screen
    openAllMembersManageTeamScreen();

    //Assert Empty All Members Manage Team screen
    assertAllMembersEmptyManageTeamScreen();
  }

  @TestRailTest(caseId = 18185)
  @DisplayName("Team: All Members: Adding Pending and Registered Users")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
  void checkElementsAllMembersTeamsPage() {
    Selenide.refresh();

    //Open 'All Members' Manage Team screen
    openAllMembersManageTeamScreen();

    //Assert All Members Manage Team screen
    assertAllMembersManageTeamScreen();
  }

  @TestRailTest(caseId = 19365)
  @DisplayName("Teams: Manage Team: Adding Pending and Registered Users")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
  @TeamExtension(count = 1)
  void addUsersToTeamsPage(final List<NewTeamInput> team,
      @Qualifier("registered") final List<NewUserInput> registered,
      @Qualifier("pending") final List<NewUserInput> pending) {
    Selenide.refresh();

    //Open 'All Members' Manage Team screen
    teamsPageService().openManageTeamFromTeamsPage(team.get(0).getName());

    //Add Users to the team
    manageTeamService().addSelectedUser(registered.get(0).getFirstName());
    manageTeamService().addSelectedUser(pending.get(0).getFirstName());
    clickCloseManageTeam();

    //assert users have been added
    assertTeamMemberPending(pending.get(0), team.get(0).getName());
    assertTeamMember(registered.get(0), team.get(0).getName());
    assertCountersOnTeamPage("0", "2", "0");

    //assert users on Teams Page
    teamsPageService().openTeamsPage();
    assertDataOnTeamsPage(team.get(0), "2", "—", "—", "0");

  }

}
