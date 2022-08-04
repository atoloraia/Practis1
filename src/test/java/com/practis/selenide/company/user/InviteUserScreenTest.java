package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllLabels;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedLabel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyState;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyTeamList;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertFullUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.InviteUserValidator.getEmailValidationMessage;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertSelectAllTeam;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamUserProfile;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertUnSelectAllTeam;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserScreenTest {

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
    userService().addRow(inputData, "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //Remove User row, assert empty state
    userService().deleteRow(0);
    snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
    assertEmptyState();
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
    assertRequiredInputs(inputData);

    //Click '+' button
    userService().addRow();

    //assert User row
    assertRequiredUserGridRow(inputData, "User", 0);
    assertNoPrompt();
  }

  /**
   * Invite User to the App: Check Teams dropdown: No teams state.
   */
  @Test
  @TestRailTest(caseId = 1079)
  @DisplayName("Invite User: Check Teams dropdown: No teams state")
  void checkEmptyTeamsDropdown() {
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
    teamService().searchTeam("invalid search criteria");
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
    assertAddedTeam(team.getName());
    //Select all and assert
    teamService().selectAllTeam();
    assertSelectAllTeam();

    //Unselect all and assert
    teamService().unSelectAllTeam();
    assertUnSelectAllTeam();
  }

  /**
   * Invite User to the App: Check Label dropdown: No Label state.
   */
  @Test
  @TestRailTest(caseId = 9327)
  @DisplayName("Invite User: Check Labels dropdown: No Labels state")
  void checkEmptyLabelDropdown() {
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
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
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
  }

  /**
   * Invite User to the App: Check Label dropdown: Search label.
   */
  @Test
  @TestRailTest(caseId = 9326)
  @DisplayName("Invite User: Check Label dropdown: Search label")
  @LabelExtension
  void checkSearchLabel(final RestCreateLabelResponse label) {
    Selenide.refresh();
    //Check Team exists
    assertAddedLabel(label.getName());
    //Search by not existing label and check results
    labelService().searchLabel("invalid search criteria");
    assertNoLabelSearchResult();

    //Search by existing label and check results
    labelService().searchLabel(label.getName());
    assertLabelSearchResult(label.getName());
  }

  /**
   * Invite User to the App: Check Labels dropdown: Select All /Unselect All labels.
   */
  @Test
  @TestRailTest(caseId = 9329)
  @DisplayName("Invite User: Check Teams dropdown: Select All/Unselect All labels")
  @LabelExtension
  void checkSelectUnselectAllLabels(final RestCreateLabelResponse label) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedLabel(label.getName());
    //Select all and assert
    labelService().selectAllLabels();
    assertSelectedAllLabels();

    //Unselect all and assert
    labelService().unSelectAllLabels();
    assertUnSelectAllLabels();
  }

  /**
   * Invite User to the App: Download Template button.
   */
  @Test
  @TestRailTest(caseId = 1109)
  @DisplayName("Invite User: Download Template button")
  void checkDownloadTemplate() throws FileNotFoundException {
    assertDownloadButton();
    inviteUsersPage().getDownloadTemplateButton().download();
    assertDownloadedFile("upload.xlsx");
  }


  /**
   * Invite User to the App: Invite not all users.
   */
  @Test
  @TestRailTest(caseId = 1127)
  @DisplayName("Invite User: Invite not all users")
  @LabelExtension
  @TeamExtension
  void inviteNotAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    //given
    final var inputs = getNewUserInputs().stream().limit(3)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    //when
    range(0, inputs.size()).forEach(idx ->
        userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //then
    //assert User row
    assertScreenAfterAddingRow();
    range(0, inputs.size())
        .forEach(idx ->
            assertFullUserGridRow(
                inputs.get(inputs.size() - 1 - idx), role, label.getName(), team.getName(), idx));

    //select user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();

    //assert grid row data
    userService().openPendingUsersListWithoutSaving();
    final var userGridRow = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();

    assertUserData(inputs.get(2), userProfilePage());
    assertTeamUserProfile(team.getName());
  }


  /**
   * Invite User to the App: User counter.
   */
  @Test
  @TestRailTest(caseId = 9525)
  @DisplayName("Invite User to the App: User counter")
  @LabelExtension
  @TeamExtension
  void userCounter(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    //create input data
    final var inputs = getNewUserInputs().stream().limit(3)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    //assert User counter
    userService().addRow(inputs.get(0), role, label.getName(), team.getName());
    assertUserCounter("1 item");
    userService().addRow(inputs.get(1), role, label.getName(), team.getName());
    assertUserCounter("2 items");
    userService().addRow(inputs.get(2), role, label.getName(), team.getName());
    assertUserCounter("3 items");
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
