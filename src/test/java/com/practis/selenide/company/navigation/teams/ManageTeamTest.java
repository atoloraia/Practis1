package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsTeamsPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertManageTeamScreen;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ManageTeamTest {

  @TestRailTest(caseId = 15693)
  @DisplayName("Check WEB Elements 'Manage All Members Team' screen")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @TeamExtension(count = 1)
  void checkElementsTeamsPage() {
    //Open 'Teams' page
    navigationCompanies().getTeamsNavigationItem().click();

    //Assert All Members Manage Team
    assertAllMembersManageTeamScreen();
    Selenide.refresh();

    //Assert Manage Team (Not All Members Team)
    assertManageTeamScreen();

  }

  @TestRailTest(caseId = 18184)
  @DisplayName("Team: All Members: Check Elements on 'Manage Team' Page")
  void checkElementsEmptyAllMembersTeamsPage() {
    //Open 'Teams' page
    navigationCompanies().getTeamsNavigationItem().click();

    //Open 'All Members' Manage Team screen
    manageTeamService().openAllMembersManageTeamScreen();

    //Assert Empty All Members Manage Team screen
    assertAllMembersEmptyManageTeamScreen();
  }

  @TestRailTest(caseId = 18185)
  @DisplayName("Team: All Members: Adding Pending and Registered Users")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 4)
  void checkElementsAllMembersTeamsPage() {
    //Open 'Teams' page
    Selenide.refresh();
    navigationCompanies().getTeamsNavigationItem().click();

    //Open 'All Members' Manage Team screen
    manageTeamService().openAllMembersManageTeamScreen();

    //Assert All Members Manage Team screen
    assertAllMembersManageTeamScreen();
  }

  @Test
  @TeamExtensionWithUsersAndPractisSets
  void testTest(final TeamWithChildren input) {
    System.out.println(1);
  }


}
