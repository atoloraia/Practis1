package com.practis.selenide.company.create.team.manage;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createTeamsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;
import static com.practis.web.selenide.validator.company.ManageTeamValidator.assertChangesSavedText;
import static com.practis.web.selenide.validator.company.ManageTeamValidator.assertElementsEmptyManageTeam;
import static com.practis.web.selenide.validator.company.ManageTeamValidator.assertSavingChangesText;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamGridRow;

import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.Qualifier;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ManageTeamTests {

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
   * Manage Team: Check Elements on 'Manage Team' page.
   */
  @TestRailTest(caseId = 17123)
  @DisplayName("Manage Team: Check Elements on 'Manage Team' page")
  void checkElementsManageTeam() {
    createTeamsService().createTeam(inputData);
    assertElementsEmptyManageTeam();
  }

  /**
   * Manage Team: Autosave.
   */
  @TestRailTest(caseId = 12710)
  @DisplayName("Manage Team: Autosave")
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void autoSaveManageTeam(final List<NewUserInput> users) {
    createTeamsService().createTeam(inputData);
    manageTeamService().addSelectedUser(users.get(0).getFirstName());
    assertSavingChangesText();
    assertChangesSavedText();

    teamsPageService().openTeamPage();
    teamsPageService().searchTeam(inputData.getName());
    assertTeamGridRow(inputData, "1", "—", "—");
  }

  /**
   * Manage Team: Add Registered and Pending Users.
   */
  @TestRailTest(caseId = 17124)
  @DisplayName("Manage Team: Add Registered and Pending Users")
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void addUsersToTheTeam(
      @Qualifier("registered") final List<NewUserInput> registered,
      @Qualifier("pending") final List<NewUserInput> pending) {
    System.out.println(1);
    //    createTeamsService().createTeam(inputData);
    //    manageTeamService().addSelectedUser(users.get(0).getFirstName());
    //    assertSavingChangesText();
    //    assertChangesSavedText();
    //
    //    teamsPageService().openTeamPage();
    //    teamsPageService().searchTeam(inputData.getName());
    //    assertTeamGridRow(inputData, "1", "—", "—");
  }

  @AfterEach
  void cleanup() {
    teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
  }

}
