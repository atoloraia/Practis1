package com.practis.selenide.company.create.team.manage;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createTeamsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamGridRow;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertChangesSavedText;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsEmptyManageTeam;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertPendingUserOnTeamMembers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertPendingUserOnTeamUsers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertQuantityOfAddedTeamMembers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertSavingChangesText;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMember;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMemberPending;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertCountersOnTeamPage;
import static com.practis.web.selenide.validator.popup.KeepTrackPopUpValidator.assertKeepTrackPopUp;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;

import com.codeborne.selenide.CollectionCondition;
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
    createTeamsService().createTeam(inputData);
    assertPendingUserOnTeamUsers(pending.get(0));
    manageTeamService().addSelectedUser(registered.get(0).getFirstName());
    awaitAjaxComplete(2);
    manageTeamService().addSelectedUser(pending.get(0).getFirstName());
    awaitAjaxComplete(2);
    assertChangesSavedText();

    //assert users on Team Page
    teamsPageService().openTeamPage();
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    assertTeamGridRow(inputData, "2", "—", "—");
    teamRow.click();
    assertKeepTrackPopUp();
    keepTrackPopUp().getGotItButton().click();
    assertCountersOnTeamPage("0", "2", "0");

    //assert users on Members Tab
    teamPage().getMembersTab().click();
    teamPage().getPaginationCounterText().shouldBe(exactText("1-2 of 2 Items"));
    membersTab().getMemberRow().shouldBe(CollectionCondition.size(3));
    assertTeamMember(registered.get(0), inputData.getName());
    assertTeamMemberPending(pending.get(0), inputData.getName());
    awaitAjaxComplete(2);
    membersTab().getMembersManageTeamButton().click();
    assertQuantityOfAddedTeamMembers(2);
    assertPendingUserOnTeamMembers(pending.get(0));
  }

  @AfterEach
  void cleanup() {
    teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
  }

}
