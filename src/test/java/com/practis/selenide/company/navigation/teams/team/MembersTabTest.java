package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertMembersFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static java.lang.String.format;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTabTest {
  @TestRailTest(caseId = 15692)
  @DisplayName("Check WEB Elements 'Members' screen")
  @TeamExtension(count = 1)
  void assertElementMembersPage() {
    //Open 'Members' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamsPage().getTeamRow().get(0).click();
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Assert Training Page
    assertElementsEmptyMembersTab();

  }


  @TestRailTest(caseId = 17126)
  @DisplayName("Check WEB Elements 'Members' Filters modal")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void assertMemberFiltersModal() {
    //Open 'Training' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamPage().getTeamRowTitle().get(0).click();

    //Open Members tab
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();

    //Open Filters
    membersTab().getMembersFiltersIcon().click();

    //Assert Filters Modal
    assertMembersFiltersModal();

    //Assert Labels
    assertEmptyLabelModel();
  }

  @Test
  @TeamExtensionWithUsersAndPractisSets
  void testTest(final TeamWithChildren input) {
    System.out.println(1);
  }

}
