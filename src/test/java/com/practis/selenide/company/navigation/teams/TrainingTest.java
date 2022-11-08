package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.validator.LabelPanelValidator.assertElementsLabelPanel;
import static com.practis.web.selenide.validator.company.team.TrainingTabValidator.assertTrainingFiltersModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTest {

  @TestRailTest(caseId = 9521)
  @DisplayName("Check WEB Elements 'Training' screen")
  void assertElementsTrainingPage() {
    //Open 'Training' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamPage().getTeamRowTitle().get(0).click();

    //Assert Training Page
    assertElementsTrainingPage();
  }

  @TestRailTest(caseId = 17125)
  @DisplayName("Check WEB Elements 'Training' Filters modal")
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  // ToDo: add extension for User with Practis Set
  void assertTrainingsFiltersModal() {
    //Open 'Training' page
    navigationCompanies().getTeamsNavigationItem().click();
    teamPage().getTeamRowTitle().get(0).click();

    //Open Filters
    teamPage().getFiltersButton().click();

    //Assert Filters Modal
    assertTrainingFiltersModal();

    //Assert Labels
    assertEmptyLabelModel();
  }

}