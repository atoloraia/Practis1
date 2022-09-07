package com.practis.selenide.company.create.team;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsService;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;
import static com.practis.web.selenide.validator.company.TeamsValidator.assertElementsEmptyCreateNewTeam;
import static com.practis.web.selenide.validator.company.TeamsValidator.assertElementsEmptyManageTeam;
import static java.lang.String.format;

import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class NewTeamTest {

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
   * Team: Check WEB Elements 'Create New Team' and 'Manage Team' page.
   */
  @TestRailTest(caseId = 1353)
  @DisplayName("Check WEB Elements 'Create New Team' and 'Manage Team' page")
  void checkElementsNewTeam() {
    assertElementsEmptyCreateNewTeam();
    teamCreatePage().getTitleField().append(inputData.getName());
    teamCreatePage().getCreateButton().click();

    snackbar().getMessage().shouldBe(exactText("New team has been created"));
    assertElementsEmptyManageTeam();
  }

  @AfterEach
  void cleanup() {
    teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
  }
}
