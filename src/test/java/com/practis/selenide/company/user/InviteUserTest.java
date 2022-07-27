package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedLabel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyLabelList;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyState;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyTeamList;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyTopRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.InviteUserValidator.getEmailValidationMessage;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertSelectAll;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamUserProfile;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertUnSelectAll;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserTest {

  private List<String> usersToRemove;
  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }

  /**
   * Invite User to the App: Check WEB Elements.
   */
  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }

  /**
   * Invite User to the App: Invite Selected Users with User Role.
   */
  @Test
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().fillText(inputData).selectRole("User").selectLabel(label.getName())
        .selectTeam(team.getName());
    userService().addRow();

    //assert User row
    assertUserGridRow(inputData, "User", label.getName(), team.getName());
    assertEmptyTopRow();
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    userService().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = userService().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();

    assertUserData(inputData, userProfilePage());
    assertTeamUserProfile(team.getName());
  }

  /**
   * Invite User to the App: Invite Selected Users with Admin Role.
   */
  @Test
  @TestRailTest(caseId = 8736)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().fillText(inputData).selectRole("Admin").selectLabel(label.getName())
        .selectTeam(team.getName());
    userService().addRow();

    //assert User row
    assertUserGridRow(inputData, "Admin", label.getName(), team.getName());
    assertEmptyTopRow();
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    userService().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = userService().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    assertUserData(inputData, userProfilePage());
  }

  /**
   * Invite User to the App: Delete User row.
   */
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Delete User row")
  @LabelExtension
  @TeamExtension
  void deleteUserRow(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().fillText(inputData).selectRole("Admin").selectLabel(label.getName())
        .selectTeam(team.getName());
    userService().addRow();
    assertNoPrompt();

    //Remove User row, assert empty state
    userService().deleteRow(0);
    snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
    assertEmptyState();
  }

  /**
   * Invite User to the App: Edit User row.
   */
  @Test
  @TestRailTest(caseId = 8845)
  @DisplayName("Invite User: Edit User row")
  @LabelExtension
  @TeamExtension
  void editUserRow(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    final var inputs = getNewUserInputs().stream().limit(3)
        .peek(inputData -> inputData.setEmail(format(inputData.getEmail(), timestamp())))
        .peek(inputData -> inputData.setFirstName(format(inputData.getFirstName(), timestamp())))
        .collect(toList());

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().fillText(inputs.get(0)).selectRole("Admin").selectLabel(label.getName())
        .selectTeam(team.getName());
    userService().addRow();
    assertNoPrompt();

    //Edit User row and cancel Edit changes
    userService().clickEdit(0).editText(inputs.get(1)).editRole("User").cancelEditChanges(0);
    assertUserGridRow(inputs.get(0), "Admin", label.getName(), team.getName());

    //Edit User row and apply changes
    userService().clickEdit(0).editText(inputs.get(2)).editRole("User").applyEditChanges(0);

    assertUserGridRow(inputs.get(2), "User", label.getName(), team.getName());

    //select user and click "Invite Selected Users" button

    userService().clickInviteSelectedUserButton();

    //assert grid row data
    final var userGridRow = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow);
  }

  /**
   * Invite User to the App: Validation: Email.
   */
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Validation: Email")
  @LabelExtension
  @TeamExtension
  void inviteUser_wrongEmailFormat() {
    userService().wrongEmailFormatFillRow(inputData);
    userService().addRow();

    //assert message
    getEmailValidationMessage().shouldBe(visible);
    getEmailValidationMessage().shouldBe(exactText("Enter a valid Email Address."));

    //add correct email
    inviteUsersPage().getEmailField().append((format("test%s@test.com", timestamp())));
    userService().addRow();

    //assert message
    getEmailValidationMessage().shouldNotBe(visible);
  }

  /**
   * Invite User to the App: Check required fields.
   */
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Check required fields")
  void checkRequiredFields() {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModule().getUserRoleRadioButton().click();
    inviteUsersPage().getAddRowButton().shouldBe(enabled);

    //Click '+' button
    userService().addRow();

    //assert User row
    assertRequiredUserGridRow(inputData, "User");
    assertNoPrompt();
  }

  /**
   * Invite User to the App: Check Teams dropdown: No teams state.
   */
  @Test
  @TestRailTest(caseId = 1079)
  @DisplayName("Invite User: Check Teams dropdown: No teams state")
  void checkTeamsDropdown() {
    assertEmptyTeamList();
  }

  /**
   * Invite User to the App: Check Teams dropdown: Delete team.
   */
  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Invite User: Check Teams dropdown: Delete team")
  @TeamExtension
  void checkDeletingTeam(final RestTeamResponse team) {
    Selenide.refresh();

    assertAddedTeam(team.getName());
    practisApi().deleteTeam(team.getName());
    Selenide.refresh();
    assertEmptyTeamList();
  }

  /**
   * Invite User to the App: Check Teams dropdown: Search team.
   */
  @Test
  @TestRailTest(caseId = 1083)
  @DisplayName("Invite User: Check Teams dropdown: Search team")
  @TeamExtension
  void checkSearchTeam(final RestTeamResponse team) {
    Selenide.refresh();
    //Check Team exists
    assertAddedTeam(team.getName());

    //Search by not existing team and check results
    teamService().searchTeam("qwer");
    assertNoTeamSearchResult();

    //Search by existing team and check results
    teamService().searchTeam(team.getName());
    assertTeamSearchResult(team.getName());
  }

  /**
   * Invite User to the App: Check Teams dropdown: Select All /Unselect All team.
   */
  @Test
  @TestRailTest(caseId = 1084)
  @DisplayName("Invite User: Check Teams dropdown: Select All/Unselect All team")
  @TeamExtension
  void checkSelectUnselectAllTeam(final RestTeamResponse team) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getTeamsField().click();
    //Select all and assert
    teamService().selectAllTeam();
    assertSelectAll();

    //Unselect all and assert
    teamService().unSelectAllTeam();
    assertUnSelectAll();
  }

  /**
   * Invite User to the App: Check Label dropdown: Delete label.
   */
  @Test
  @TestRailTest(caseId = 1101)
  @DisplayName("Invite User: Check Label dropdown: Delete label")
  @LabelExtension
  void checkDeletingLabel(final RestCreateLabelResponse label) {
    Selenide.refresh();

    assertAddedLabel(label.getName());
    practisApi().deleteLabel(label.getName());
    Selenide.refresh();
    assertEmptyLabelList();
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
