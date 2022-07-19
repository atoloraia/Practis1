package com.practis.selenide.company;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.user;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.UserValidator.asserUserData;
import static com.practis.web.selenide.validator.UserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.UserValidator.assertEmptyState;
import static com.practis.web.selenide.validator.UserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.UserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.UserValidator.assertUserGridRow;
import static com.practis.web.selenide.validator.UserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.UserValidator.getEmailValidationMessage;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

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
  private List<String> teamsToRemove;
  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

    teamsToRemove = new ArrayList<>();
    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }

  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }

  @Test
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {
    teamsToRemove.add(team.getName());

    Selenide.refresh();
    user().userRoleFillRow(inputData, label.getName(), team.getName());
    user().addRow();

    //assert User row
    assertUserGridRow(inputData, "User", label.getName(), team.getName());
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    user().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = user().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    asserUserData(inputData, userProfilePage());
  }

  @Test
  @TestRailTest(caseId = 8736)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {
    teamsToRemove.add(team.getName());
    Selenide.refresh();

    user().adminRoleFillRow(inputData, label.getName(), team.getName());
    user().addRow();

    //assert User row
    assertUserGridRow(inputData, "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    user().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = user().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    asserUserData(inputData, userProfilePage());
  }

  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Delete User row")
  @LabelExtension
  @TeamExtension
  void deleteUserRow(
      final RestCreateLabelResponse label, final RestTeamResponse team) {
    teamsToRemove.add(team.getName());
    Selenide.refresh();

    //Add User row, assert not empty state
    user().adminRoleFillRow(inputData, label.getName(), team.getName());
    user().addRow();
    assertNoPrompt();

    //Remove User row, assert empty state
    user().deleteRow(0);
    snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
    assertEmptyState();
  }

  @Test
  @TestRailTest(caseId = 8845)
  @DisplayName("Invite User: Edit User row")
  @LabelExtension
  @TeamExtension
  void editUserRow(
      final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    final var inputs = getNewUserInputs().stream()
        .limit(3)
        .peek(inputData -> inputData.setEmail(format(inputData.getEmail(), timestamp())))
        .peek(inputData -> inputData.setFirstName(format(inputData.getFirstName(), timestamp())))
        .collect(toList());

    //Add User row, assert not empty state
    user().adminRoleFillRow(inputs.get(0), label.getName(), team.getName());
    user().addRow();
    assertNoPrompt();

    //Edit User row and cancel Edit changes
    user().editRow(0, inputs.get(1), label.getName(), team.getName());
    user().cancelEditChanges(0);
    assertUserGridRow(inputs.get(0), "Admin", label.getName(), team.getName());

    //Edit User row and apply changes
    user().editRow(0, inputs.get(1), label.getName(), team.getName());
    user().applyEditChanges(0);
    assertUserGridRow(inputs.get(1), "User", label.getName(), team.getName());

    //select user and click "Invite Selected Users" button
    user().clickInviteSelectedUserButton();

  }

  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Validation: Email")
  @LabelExtension
  @TeamExtension
  void inviteUser_wrongEmailFormat() {
    user().wrongEmailFormatFillRow(inputData);
    user().addRow();

    //assert message
    getEmailValidationMessage().shouldBe(visible);
    getEmailValidationMessage().shouldBe(exactText("Enter a valid Email Address."));

    //add correct email
    inviteUsersPage().getEmailField().append((format("test%s@test.com", timestamp())));
    user().addRow();

    //assert message
    getEmailValidationMessage().shouldNotBe(visible);
  }

  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Check required fields")
  @LabelExtension
  @TeamExtension
  void checkRequiredFields(final RestCreateLabelResponse label, final RestTeamResponse team) {
    inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getLastNameField().append(inputData.getLastName());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getEmailField().append(inputData.getEmail());
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModel().getUserRoleRadioButton().click();
    inviteUsersPage().getAddRowButton().shouldBe(enabled);

    //Click '+' button
    user().addRow();

    //assert User row
    assertRequiredUserGridRow(inputData, "User");
    assertNoPrompt();
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
