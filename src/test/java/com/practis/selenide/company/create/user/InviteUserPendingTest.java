package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserEditGridRowWithoutEmail;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserPendingUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserSelectionPanel_ExistingUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;

import com.codeborne.selenide.CollectionCondition;
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
import com.practis.web.util.PractisUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserPendingTest {

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
   * Invite User to the App: Invite Selected Users with User Role.
   */
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert

    Selenide.refresh();
    userService().addRow(inputData, "User", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "User", 0);
    //TODO Add asserts that are commented below when the ticket DEV-9739 will be ready
    //assertTeamUserGridRow(0);
    //assertLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert User data
    asserPendingUser(inputData);
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
  }

  /**
   * Invite User to the App: Invite Selected Users with Admin Role.
   */
  @TestRailTest(caseId = 8844)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert

    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "Admin", 0);
    //TODO Add asserts that are commented below when the ticket DEV-9739 will be ready
    //assertTeamUserGridRow(0);
    //assertLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //assert snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert User data
    asserPendingUser(inputData);
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
  }

  /**
   * Invite User to the App: Edit User row.
   */
  @TestRailTest(caseId = 8845)
  @DisplayName("Invite User: Edit User row")
  @LabelExtension
  @TeamExtension
  void editUserRow(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add edit Team, Label and Practis Set
    Selenide.refresh();

    final var inputs = userService().generateUserInputs(3);

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //Add User row, First name is empty
    Selenide.refresh();
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    asserEditGridRowWithoutEmail();

    //Edit User row and cancel Edit changes
    userService().clickEdit(0).editText(inputs.get(1)).editRole("User").cancelEditChanges(0);
    assertRequiredUserGridRow(inputs.get(0), "Admin", 0);

    //Edit User row and apply changes
    userService().clickEdit(0).editText(inputs.get(2)).editRole("User").applyEditChanges(0);

    assertRequiredUserGridRow(inputs.get(2), "User", 0);

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //assert user
    asserPendingUser(inputs.get(2));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
  }

  /**
   * Invite User to the App: Invite not all users.
   */
  @TestRailTest(caseId = 1127)
  @DisplayName("Invite User: Invite not all users")
  @LabelExtension
  @TeamExtension
  void inviteNotAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(3);
    final var role = "User";

    //add users
    range(0, inputs.size()).forEach(idx ->
        userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //assert counter
    assertUserCounter("3 items");

    //select the first user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "1 User has been invited."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();
    inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(2));

    //assert User
    userService().exitWithoutSaving();
    userService().openPendingUsersList();
    asserPendingUser(inputs.get(2));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
  }

  /**
   * Invite User to the App: Invite All users.
   */
  @TestRailTest(caseId = 1162)
  @DisplayName("Invite User: Invite all users")
  @LabelExtension
  @TeamExtension
  void inviteAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(3);
    final var role = "User";

    //add users
    range(0, inputs.size()).forEach(idx ->
        userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //assert User 1
    asserPendingUser(inputs.get(0));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
    PractisUtils.clickOutOfTheForm();

    //assert User 2
    userService().openPendingUsersList();
    asserPendingUser(inputs.get(1));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
    PractisUtils.clickOutOfTheForm();

    //assert User 3
    userService().openPendingUsersList();
    asserPendingUser(inputs.get(2));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
  }


  /**
   * Invite User to the App: Not All users successfully invited.
   */
  @TestRailTest(caseId = 1135)
  @DisplayName("Invite User: Not All users successfully invited")
  @LabelExtension
  @TeamExtension
  void notAllSuccessfullyInvited(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(2);
    final var role = "Admin";

    //preconditions: invite the user
    userService().addRow(inputs.get(0), role, label.getName(), team.getName());
    userService().inviteAllUser();

    //Add some Users with already existing emails
    newItemSelector().create("User");
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(inputs.get(1), "Admin", label.getName(), team.getName());
    userService().inviteAllUser();

    //Check snackbar message "1 User has been invited but 1 user already exist in our system."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited but 1 user already exist in our system"));
    asserSelectionPanel_ExistingUser();

    //assert User 1
    userService().openPendingUsersListWithoutSaving();
    asserPendingUser(inputs.get(1));
    //TODO add one method for checking whole user data
    userProfilePage().getAssignButton().click();
    assertSelectedTeam(team.getName());
    assertSelectedLabel(label.getName());
    PractisUtils.clickOutOfTheForm();
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }

}
