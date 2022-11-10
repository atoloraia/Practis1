package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsTeamsPage;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertAllMembersManageTeamScreen;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertManageTeamScreen;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import org.junit.jupiter.api.DisplayName;

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



}
