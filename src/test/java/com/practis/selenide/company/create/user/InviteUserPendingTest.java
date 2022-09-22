package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserProblemGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResultsOnPendingTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneLabelUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertPendingUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
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
import com.practis.support.extension.practis.InviteUserExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.web.util.PractisUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
  @DisplayName("InviteUserPendingTest: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert

    Selenide.refresh();
    userService().addRow(inputData, "User", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "User", 0);
    assertOneTeamSelected(0);
    assertOneLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputData);
    assertUserGridRowPending(inputData, userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputData, team.getName(), label.getName());
    //TODO add one method for checking whole user data
  }

  /**
   * Invite User to the App: Invite Selected Users with Admin Role.
   */
  @TestRailTest(caseId = 8844)
  @DisplayName("InviteUserPendingTest: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert

    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(inputData, "Admin", 0);
    assertOneTeamSelected(0);
    assertOneLabelUserGridRow(0);
    assertUserCounter("1 item");

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //assert snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputData);
    assertUserGridRowPending(inputData, userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputData, team.getName(), label.getName());
    //TODO add one method for checking whole user data
  }


  /**
   * Invite User to the App: Invite not all users.
   */
  @TestRailTest(caseId = 1127)
  @DisplayName("InviteUserPendingTest: Invite not all users")
  @LabelExtension
  @TeamExtension
  void inviteNotAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(3);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));
    final var role = "User";

    //add users
    range(0, inputs.size()).forEach(
        idx -> userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //assert counter
    assertUserCounter("3 items");

    //select the first user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "1 User has been invited."
    snackbar().getMessage().shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();
    inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(2));

    //open Pending page
    userService().exitWithoutSaving();
    userService().openPendingUsersList();

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputs.get(2));
    assertUserGridRowPending(inputs.get(2), userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputs.get(2), team.getName(), label.getName());
  }

  /**
   * Invite User to the App: Invite All users.
   */
  @TestRailTest(caseId = 1162)
  @DisplayName("InviteUserPendingTest: Invite all users")
  @LabelExtension
  @TeamExtension
  void inviteAllUsers(final RestCreateLabelResponse label, final RestTeamResponse team) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(3);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));
    final var role = "User";

    //add users
    range(0, inputs.size()).forEach(
        idx -> userService().addRow(inputs.get(idx), role, label.getName(), team.getName()));

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //Asserts invited users
    IntStream.range(0, 2).forEach(idx -> {
      var userRow = searchPendingUser(inputs.get(idx));
      assertUserGridRowPending(inputs.get(idx), userRow);
      //view User Profile
      userRow.click();
      assertPendingUser(inputs.get(idx), team.getName(), label.getName());

      PractisUtils.clickOutOfTheForm();
      userService().openPendingUsersList();
    });
  }

  /**
   * Invite User to the App: User with already existing email: All selection .
   */
  @TestRailTest(caseId = 11674)
  @DisplayName("InviteUserPendingTest: User with existing email: All selection ")
  @InviteUserExtension(limit = 1, company = "CompanyAuto", role = 4)
  @LabelExtension
  @TeamExtension
  void oneUserExistsInviteAllSelection(final RestCreateLabelResponse label,
      final RestTeamResponse team, final List<NewUserInput> users) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //Invite 2 Users. One of them has already existing email
    userService().addRow(users.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(inputData, "Admin", label.getName(), team.getName());
    userService().inviteAllUser();

    //Check snackbar messages
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited but 1 user already exist in our system"));
    asserSelectionPanel();
    asserProblemGridRow(0, "User’s email exists in our system");

    //TODO Move this assert to separate tests
    //Delete existing users
    inviteUsersPage().getDeleteExistingUsersButton().click();
    Assertions.assertEquals("1 Existing user has been removed", snackbar().getMessage().text());
    snackbar().getMessage().shouldBe(exactText("1 Existing user has been removed"));
    PractisUtils.clickOutOfTheForm();
    userService().openPendingUsersList();

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputData);
    assertUserGridRowPending(inputData, userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputData, team.getName(), label.getName());
  }

  @TestRailTest(caseId = 1129)
  @DisplayName("InviteUserPendingTest: Users with existing emails: All selection")
  @InviteUserExtension(limit = 2, company = "CompanyAuto", role = 4)
  @LabelExtension
  @TeamExtension
  void severalUsersExistInviteAllSelection(final RestCreateLabelResponse label,
      final RestTeamResponse team, final List<NewUserInput> users) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    //Add some Users with already existing emails
    userService().addRow(users.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(users.get(1), "Admin", label.getName(), team.getName());
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(inputs.get(1), "Admin", label.getName(), team.getName());
    userService().inviteAllUser();

    //Check snackbar messages
    snackbar().getMessage()
        .shouldBe(exactText("2 Users have been invited but 2 users already exist in our system"));

    //assert selection panel
    asserSelectionPanel();

    userService().openPendingUsersListWithoutSaving();

    //Asserts invited users
    IntStream.range(0, 1).forEach(idx -> {
      var userRow = searchPendingUser(inputs.get(idx));
      assertUserGridRowPending(inputs.get(idx), userRow);
      //view User Profile
      userRow.click();
      assertPendingUser(inputs.get(idx), team.getName(), label.getName());

      PractisUtils.clickOutOfTheForm();
      userService().openPendingUsersList();
    });
  }

  @TestRailTest(caseId = 1135)
  @DisplayName("InviteUserPendingTest: Invite users with existing emails: Partially selection")
  @InviteUserExtension(limit = 2, company = "CompanyAuto", role = 4)
  @LabelExtension
  @TeamExtension
  void someUsersExistInvitePartiallySelection(final RestCreateLabelResponse label,
      final RestTeamResponse team, final List<NewUserInput> users) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));
    final var role = "Admin";

    //Add some Users with already existing emails
    userService().addRow(users.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(users.get(1), "Admin", label.getName(), team.getName());
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    userService().addRow(inputs.get(1), "Admin", label.getName(), team.getName());
    userService().inviteSomeUser(1, 2);

    //Check snackbar messages
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited but 1  user already exist in our system"));

    asserProblemGridRow(1, "User’s email exists in our system");

    //open Pending page
    userService().openPendingUsersListWithoutSaving();

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputs.get(1));
    assertUserGridRowPending(inputs.get(1), userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputs.get(2), team.getName(), label.getName());
  }

  @TestRailTest(caseId = 1140)
  @DisplayName("InviteUserPendingTest: Invite with existing emails: Select All-Unselect some.")
  @InviteUserExtension(limit = 2, company = "CompanyAuto", role = 4)
  @LabelExtension
  @TeamExtension
  void someUsersExistInviteAllUnselectSome(final RestCreateLabelResponse label,
      final RestTeamResponse team, final List<NewUserInput> users) {
    //TODO Add Practis Set and assert
    Selenide.refresh();

    //generate input data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));
    final var role = "Admin";

    //Add some Users with already existing emails
    userService().addRow(users.get(0), role, label.getName(), team.getName());
    userService().addRow(users.get(1), role, label.getName(), team.getName());
    userService().addRow(inputs.get(0), "User", label.getName(), team.getName());
    userService().addRow(inputs.get(1), "User", label.getName(), team.getName());
    //Select all users
    inviteUsersPage().getSelectAllCheckbox().click();
    //Unselect some Users and click "Invite Selected Users" button
    userService().inviteSomeUser(1, 2);

    //Check snackbar notifications
    awaitFullPageLoad(10);
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited but 1 user already exist in our system"));

    //Check the list contains 3 User rows and check one 'problem' User row
    inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(3));
    asserProblemGridRow(2, "User’s email exists in our system");

    //open Pending page
    userService().openPendingUsersListWithoutSaving();

    //search and view user in 'Pending User' list
    var userGridRow = searchPendingUser(inputs.get(1));
    assertUserGridRowPending(inputs.get(1), userGridRow);

    //view User Profile
    userGridRow.click();
    assertPendingUser(inputs.get(1), team.getName(), label.getName());

    //Check that the others Users were not invited
    PractisUtils.clickOutOfTheForm();
    userService().openPendingUsersList();
    userService().searchUser(inputs.get(0).getEmail());
    assertNoSearchResultsOnPendingTab();
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }

}
