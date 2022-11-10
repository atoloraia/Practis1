package com.practis.selenide.company.navigation.teams;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersEmptyManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersManageTeamScreen;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ManageTeamTest {

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




}
