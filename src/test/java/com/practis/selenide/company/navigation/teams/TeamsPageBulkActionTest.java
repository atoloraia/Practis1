package com.practis.selenide.company.navigation.teams;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.deleteTeamPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsRows;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.selenide.validator.popup.WarningDeletePopUpValidator.assertWarningDeletePopUp;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
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
public class TeamsPageBulkActionTest {

  @BeforeEach
  void init() {
    navigationCompanies().getTeamsNavigationItem().click();
  }

  @TestRailTest(caseId = 19364)
  @DisplayName("Teams: Bulk Action: Delete")
  @TeamExtension(count = 3)
  void viewTeamSingleActionAllMembers(final List<NewTeamInput> team) {
    Selenide.refresh();
    teamsPageService().selectAllTeams();
    teamsPage().getActionButton().click();
    teamsPage().getDeleteTeamsActionButton().click();

    //assert warning message
    assertConfirmBulkActionPopUp();
    deleteTeamPopUp().getProceedButton().click();

    //check snackbar
    awaitElementExists(10, () -> snackbar().getMessage())
        .shouldBe(exactText("Teams have been deleted."));

    //assert team has been deleted
    assertTeamsRows(0);
    assertElementsEmptyTeamsPage();

  }



}