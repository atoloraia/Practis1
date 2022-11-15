package com.practis.selenide.company.create.team.manage;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createTeamsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.manageTeamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.data.company.NewTeamInputData.getNewTeamInput;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertChangesSavedText;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertEditTeamName;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertElementsEmptyManageTeam;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertPendingUserOnTeamMembers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertPendingUserOnTeamUsers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertQuantityOfAddedTeamMembers;
import static com.practis.web.selenide.validator.company.team.ManageTeamValidator.assertSavingChangesText;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertElementsEmptyMembersTab;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertEmptyTeamMemberSection;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMember;
import static com.practis.web.selenide.validator.company.team.MembersTabValidator.assertTeamMemberPending;
import static com.practis.web.selenide.validator.company.team.TeamPageValidator.assertCountersOnTeamPage;
import static com.practis.web.selenide.validator.popup.KeepTrackPopUpValidator.assertKeepTrackPopUp;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static com.practis.web.util.AwaitUtils.awaitElementVisible;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.Qualifier;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    //assertSavingChangesText();
    assertChangesSavedText();

    teamsPageService().assertDataOnTeamsPage(inputData, "1", "—", "—", "0");
  }

  /**
   * Manage Team: Close.
   */
  @TestRailTest(caseId = 17128)
  @DisplayName("Manage Team: Close")
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void closeManageTeam(final List<NewUserInput> users) {
    createTeamsService().createTeam(inputData);
    manageTeamService().addSelectedUser(users.get(0).getFirstName());
    //assertSavingChangesText();
    assertChangesSavedText();
    manageTeamPage().getCloseButton().click();

    teamsPageService().assertDataOnTeamsPage(inputData, "1", "—", "—", "0");
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
    //Create Team
    createTeamsService().createTeam(inputData);
    assertPendingUserOnTeamUsers(pending.get(0));
    //add Registered User and Pending User
    manageTeamService().addSelectedUser(registered.get(0).getFirstName());
    manageTeamService().addSelectedUser(pending.get(0).getFirstName());
    assertChangesSavedText();

    //assert users on Teams Page
    teamsPageService().assertDataOnTeamsPage(inputData, "2", "—", "—", "0");

    assertKeepTrackPopUp(inputData.getName());
    keepTrackPopUp().getGotItButton().click();
    assertCountersOnTeamPage("0", "2", "0");

    //assert users on Members Tab
    teamPage().getMembersTab().click();
    teamPage().getPaginationCounterText().shouldBe(exactText("1-2 of 2 Items"));
    membersTab().getMemberRow().shouldBe(CollectionCondition.size(3));
    //assert Users Data
    assertTeamMember(registered.get(0), inputData.getName());
    assertTeamMemberPending(pending.get(0), inputData.getName());

    //asser Users on Manage Team page
    await().pollDelay(TWO_SECONDS).until(() -> true);
    membersTab().getMembersManageTeamButton().click();
    assertQuantityOfAddedTeamMembers(2);
    assertPendingUserOnTeamMembers(pending.get(0));
  }


  /**
   * Manage Team: Remove Registered and Pending Users.
   */
  @TestRailTest(caseId = 17127)
  @DisplayName("Manage Team: Remove Registered and Pending Users")
  @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
  void removeUsersFromTheTeam(
      @Qualifier("registered") final List<NewUserInput> registered,
      @Qualifier("pending") final List<NewUserInput> pending) {
    //add Registered User and Pending User
    createTeamsService().createTeam(inputData);
    assertPendingUserOnTeamUsers(pending.get(0));
    manageTeamService().addSelectedUser(registered.get(0).getFirstName());
    manageTeamService().addSelectedUser(pending.get(0).getFirstName());
    assertChangesSavedText();

    //remove Users
    manageTeamService().removeSelectedUser(registered.get(0).getFirstName());
    manageTeamService().removeSelectedUser(pending.get(0).getFirstName());
    assertChangesSavedText();

    //assert users on Teams Page
    teamsPageService().assertDataOnTeamsPage(inputData, "—", "—", "—", "0");

    assertKeepTrackPopUp(inputData.getName());
    keepTrackPopUp().getGotItButton().click();
    assertCountersOnTeamPage("0", "0", "0");

    //assert users on Members Tab
    teamPage().getMembersTab().click();
    membersTab().getMemberRow().shouldBe(CollectionCondition.size(1));
    assertElementsEmptyMembersTab();
    membersTab().getMembersManageTeamButton().click();
    assertQuantityOfAddedTeamMembers(0);
    assertEmptyTeamMemberSection();
  }

  /**
   * Manage Team: Add Label.
   */
  @TestRailTest(caseId = 17129)
  @DisplayName("Manage Team: Add Label")
  @LabelExtension(count = 1)
  void addLabelManageTeam(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();
    createTeamsService().createTeam(inputData);
    await().pollDelay(TWO_SECONDS).until(() -> true);
    manageTeamService().addLabelToTeam(label);
    assertSavingChangesText();
    assertChangesSavedText();
    manageTeamPage().getCloseButton().click();

    //assert users on Teams Page
    teamsPageService().assertDataOnTeamsPage(inputData, "—", "—", "—", "1");

    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();
    awaitAjaxComplete(5);
    membersTab().getMembersManageTeamButton().click();
    awaitAjaxComplete(5);
    manageTeamPage().getAssignLabelsButton().click();
    assertSelectedLabel(label.get(0).getName());
  }

  /**
   * Manage Team: Edit Name.
   */
  @TestRailTest(caseId = 18186)
  @DisplayName("Manage Team: Edit Name")
  @Test
  void editNameManageTeam() {
    createTeamsService().createTeam(inputData);

    //Edit Team name and Cancel changes
    awaitSoft(10, () -> manageTeamPage().getTitleField().getAttribute("value").length() > 0);
    manageTeamPage().getTitleField().append(" ").append("updated");
    assertEditTeamName();
    manageTeamPage().getTitleCancelButton().click();
    manageTeamPage().getCloseButton().click();
    //Check name hasn't been saved
    teamsPageService().openTeamsPage();
    var teamRow = teamsPageService().searchTeam(inputData.getName());
    teamRow.click();
    awaitElementVisible(10, () -> keepTrackPopUp().getGotItButton());
    keepTrackPopUp().getGotItButton().click();
    teamPage().getMembersTab().click();
    //SelenideJsUtils.jsClick(membersTab().getMembersManageTeamButton());
    awaitElementEnabled(10, () -> membersTab().getMembersManageTeamButton());
    membersTab().getMembersManageTeamButton().click();
    manageTeamPage().getTitleField().shouldBe(value(inputData.getName()));

    //Edit Team name and Apply changes
    manageTeamPage().getTitleField().append(" ").append("updated");
    assertEditTeamName();
    manageTeamPage().getTitleSaveButton().click();
    manageTeamPage().getCloseButton().click();
    //assert Team name has been changed
    teamsPageService().openTeamsPage();
    var teamRow1 = teamsPageService().searchTeam(inputData.getName() + " updated");
    teamRow1.click();
    teamPage().getMembersTab().click();
    awaitElementEnabled(10, () -> membersTab().getMembersManageTeamButton());
    membersTab().getMembersManageTeamButton().click();
    awaitSoft(10, () -> manageTeamPage().getTitleField().getAttribute("value").length() > 0);
    manageTeamPage().getTitleField().shouldBe(attribute("value", inputData.getName() + " updated"));
  }


  @AfterEach
  void cleanup() {
    teamsToRemove.forEach(name -> practisApi().deleteTeam(name));
  }

}
