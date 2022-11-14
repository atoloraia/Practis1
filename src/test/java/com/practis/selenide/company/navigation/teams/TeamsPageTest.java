package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
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
  void viewSingleActionMenuAllMembers() {

    teamsPageService().singleActionAllMembers();

    //Assert Training Page
    assertElementsEmptyTeamsPage();
  }

}