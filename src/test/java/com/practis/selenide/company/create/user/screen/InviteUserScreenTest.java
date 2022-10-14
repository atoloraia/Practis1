package com.practis.selenide.company.create.user.screen;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserNormalGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserProblemGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyState;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.getEmailValidationMessage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.getWarningCheckbox;
import static java.lang.String.format;

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
  @TestRailTest(caseId = 8687)
  @DisplayName("InviteUserScreenTest: Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }


  /**
   * Invite User to the App: Edit User row.
   */
  @TestRailTest(caseId = 8845)
  @DisplayName("InviteUserScreenTest: Edit User row")
  @LabelExtension(count = 1)
  @TeamExtension(count = 1)
  void editUserRow(final List<RestCreateLabelResponse> label, final List<RestTeamResponse> team) {
    //TODO Add edit Team, Label and Practis Set
    Selenide.refresh();

    final var inputs = userService().generateUserInputs(3);

    //Add User row
    Selenide.refresh();
    userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0));

    //Edit User row and cancel Edit changes
    userService().clickEdit(0).editText(inputs.get(1)).editRole("User")
        .cancelEditChanges(0);
    assertNoPrompt();
    assertRequiredUserGridRow(inputs.get(0), "Admin", 0);

    //Edit User row and apply changes
    userService().clickEdit(0).editText(inputs.get(2)).editRole("User").applyEditChanges(0);
    assertRequiredUserGridRow(inputs.get(2), "User", 0);

    //select the user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //assert user
    assertInvitedUser(inputs.get(2), label.get(0), team.get(0));
  }

  /**
   * Invite User to the App: Delete User row.
   */
  @TestRailTest(caseId = 1065)
  @DisplayName("InviteUserScreenTest: Delete User row")
  @LabelExtension(count = 1)
  @TeamExtension(count = 1)
  void deleteUserRow(final List<RestCreateLabelResponse> label,
      final List<RestTeamResponse> teams) {

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.get(0), teams.get(0));
    assertNoPrompt();

    //Remove User row, assert empty state
    userService().deleteRow(0);
    snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
    assertEmptyState();
  }

  /**
   * Invite User to the App: Validation: Email.
   */
  @TestRailTest(caseId = 1072)
  @DisplayName("InviteUserScreenTest: Validation: Email")
  void inviteUserWrongEmailFormat() {
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
  @TestRailTest(caseId = 1068)
  @DisplayName("InviteUserScreenTest: Check required fields")
  void checkRequiredFields() {
    assertRequiredInputs(inputData);
    //Click '+' button
    userService().addRow();

    //assert User row
    assertRequiredUserGridRow(inputData, "User", 0);
    assertNoPrompt();
  }


  @TestRailTest(caseId = 1109)
  @DisplayName("InviteUserScreenTest: Download Template button.")
  void checkDownloadTemplate() throws FileNotFoundException {
    assertDownloadButton();
    inviteUsersPage().getDownloadTemplateButton().download();
    assertDownloadedFile("upload.xlsx");
  }


  /**
   * Invite User to the App: Uniqueness Email.
   */
  @TestRailTest(caseId = 11764)
  @DisplayName("InviteUserScreenTest: Uniqueness Email")
  void inviteUserDuplicatedEmailRow() {

    //generate data for Users
    final var inputs = userService().generateUserInputs(2);

    userService().addRow(inputs.get(0), "User");

    userService().addRow(inputs.get(0), "User");

    //assert error
    asserProblemGridRow(0, "Please edit before selecting");
    asserNormalGridRow(1);

    //change email
    userService().clickEdit(0);
    inviteUsersPage().getEditEmailField().clear();
    inviteUsersPage().getEditEmailField().append(inputs.get(1).getEmail());
    userService().applyEditChanges(0);

    //assert message
    getWarningCheckbox().shouldNotBe(visible);
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
