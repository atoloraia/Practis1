package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertFullUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertLabelUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertTeamUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamUserProfile;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

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
  @Test
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().addRow(inputData, "User", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "User", 0);
    //assertTeamUserGridRow(0);
    //assertLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select user and click "Invite Selected Users" button
    userService().inviteFirstUser();

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
  @TestRailTest(caseId = 8844)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "Admin", 0);
    //assertTeamUserGridRow(0);
    //assertLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select user and click "Invite Selected Users" button
    userService().inviteFirstUser();

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
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //Edit User row and cancel Edit changes
    userService().clickEdit(0).editText(inputs.get(1)).editRole("User").cancelEditChanges(0);
    assertRequiredUserGridRow(inputs.get(0), "Admin", 0);

    //Edit User row and apply changes
    userService().clickEdit(0).editText(inputs.get(2)).editRole("User").applyEditChanges(0);

    assertRequiredUserGridRow(inputs.get(2), "User", 0);

    //select user and click "Invite Selected Users" button

    userService().inviteFirstUser();

    //assert grid row data
    final var userGridRow = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow);
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

    //Check snackbar message "We’re sending 3 invitations. This might take a while."
    snackbar().getMessage()
        .shouldBe(exactText("3 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();

    //assert grid row data
    userService().exitWithoutSaving();
    userService().openPendingUsersList();
    final var userGridRow = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();

    assertUserData(inputs.get(2), userProfilePage());
  }

  /**
   * Invite User to the App: Invite All users.
   */
  @Test
  @TestRailTest(caseId = 1162)
  @DisplayName("Invite User: Invite all users")
  @LabelExtension
  @TeamExtension
  void inviteAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    //generate input data for Users
    final var inputs = getNewUserInputs().stream().limit(3)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    //add users
    range(0, inputs.size()).forEach(idx ->
        userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //assert User rows
    assertScreenAfterAddingRow();
    range(0, inputs.size())
        .forEach(idx ->
            assertFullUserGridRow(
                inputs.get(inputs.size() - 1 - idx), role, label.getName(), team.getName(), idx));

    //select user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //assert User 1
    final var userGridRow1 = userService().searchUser(inputs.get(0).getEmail());
    assertUserGridRowPending(inputs.get(0), userGridRow1);
    userGridRow1.click();
    assertUserData(inputs.get(0), userProfilePage());

    userService().openPendingUsersList();

    //assert User 2
    final var userGridRow2 = userService().searchUser(inputs.get(1).getEmail());
    assertUserGridRowPending(inputs.get(1), userGridRow2);
    userGridRow2.click();
    assertUserData(inputs.get(1), userProfilePage());

    userService().openPendingUsersList();

    //assert User 3
    final var userGridRow3 = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow3);
    userGridRow3.click();
    assertUserData(inputs.get(2), userProfilePage());
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
