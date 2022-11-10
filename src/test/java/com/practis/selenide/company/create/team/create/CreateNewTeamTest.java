package com.practis.selenide.company.create.team.create;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createTeamsService;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;
import static com.practis.web.selenide.validator.company.team.CreateNewTeamValidator.assertClosedCreateNewTeam;
import static com.practis.web.selenide.validator.company.team.CreateNewTeamValidator.assertElementsCreateNewTeamWithWarning;
import static com.practis.web.selenide.validator.company.team.CreateNewTeamValidator.assertElementsEmptyCreateNewTeam;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsEmptyManageTeam;

import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class CreateNewTeamTest {

  private NewTeamInput inputData;
  private List<String> teamsToRemove;

  @BeforeEach
  void init() {
    newItemSelector().create("Team");

    inputData = getNewTeamInput();
    inputData.setName(String.format(inputData.getName(), timestamp()));

    teamsToRemove = new ArrayList<>();
    teamsToRemove.add(inputData.getName());
  }

  /**
   * Create Team: Check WEB Elements on 'Create New Team' page.
   */
  @TestRailTest(caseId = 1353)
  @DisplayName("Create Team: Check WEB Elements on 'Create New Team' page")
  void checkElementsCreateNewTeam() {
    assertElementsEmptyCreateNewTeam();
    createTeamsService().createTeam(inputData);

    snackbar().getMessage().shouldBe(exactText("New team has been created"));
  }

  /**
   * Create Team: Create.
   */
  @TestRailTest(caseId = 64)
  @DisplayName("Create Team: Create")
  void createNewTeam() {
    assertElementsEmptyCreateNewTeam();
    createTeamsService().createTeam(inputData);

    snackbar().getMessage().shouldBe(exactText("New team has been created"));
    assertElementsEmptyManageTeam();
  }

  /**
   * Create Team: Cancel.
   */
  @TestRailTest(caseId = 18187)
  @DisplayName("Create Team: Cancel")
  void cancelCreateNewTeam() {
    teamCreatePage().getTitleField().append(inputData.getName());
    teamCreatePage().getCancelButton().click();
    assertClosedCreateNewTeam();

  }

  /**
   * Create Team: Already exists Name.
   */
  @TestRailTest(caseId = 12708)
  @DisplayName("Create Team: Already exists Name")
  @TeamExtension(count = 1)
  void createNewTeamAlreadyExistsName(final List<RestTeamResponse> team) {
    teamCreatePage().getTitleField().append(team.get(0).getName());
    teamCreatePage().getCreateButton().click();

    snackbar().getMessage().shouldBe(exactText("Entered name already exists."));
    assertElementsCreateNewTeamWithWarning();
  }

  @AfterEach
  void cleanup() {
    teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
  }
}
