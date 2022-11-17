package com.practis.selenide.company.navigation.teams.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertElementsTrainingTab;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertTrainingFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;



@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTabTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();

  }

  @TestRailTest(caseId = 9521)
  @DisplayName("Check WEB Elements 'Training' screen")
  void assertElementsTrainingPage() {
    //Open 'Training' page
    teamsPage().getTeamsAllMembersRow().click();

    //Assert Training Page
    assertElementsTrainingTab();
  }

  @TestRailTest(caseId = 17125)
  @DisplayName("Check WEB Elements 'Training' Filters modal")
  @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
  void assertTrainingsFiltersModal() {
    Selenide.refresh();

    //Open 'Training' page
    teamsPage().getTeamsAllMembersRow().click();
    keepTrackPopUp().getGotItButton().click();

    //Open Filters
    teamPage().getFiltersButton().click();

    //Assert Filters Modal
    assertTrainingFiltersModal();

    //Assert Labels
    assertEmptyLabelModel();
  }

}