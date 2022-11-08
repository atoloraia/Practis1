package com.practis.selenide.company.navigation.teams;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.LabelPanelValidator.assertElementsLabelPanel;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertMembersFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static java.lang.String.format;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class MembersTest {
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

}
