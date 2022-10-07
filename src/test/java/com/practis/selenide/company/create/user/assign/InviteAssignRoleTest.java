package com.practis.selenide.company.create.user.assign;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.roleModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertAdminRadioButton;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertPartiallySelection;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertUserRadioButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteAssignRoleTest {

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
   * Invite User to the App: Assign: Role section: User.
   */
  @TestRailTest(caseId = 14122)
  @DisplayName("AssignRole: User")
  void assignRolesUser() {
    Selenide.refresh();
    userService().addRow(inputData, "User");
    userService().assignFirstUser();

    //assert 'User' radio button is selected
    assertUserRadioButton();
  }

  /**
   * Invite User to the App: Assign: Role section: Admin.
   */
  @TestRailTest(caseId = 14130)
  @DisplayName("AssignRole: Admin")
  void assignRolesAdmin() {
    Selenide.refresh();
    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();

    //assert 'User' radio button is selected
    assertAdminRadioButton();
  }

  /**
   * Invite User to the App: Assign: Role section: Partially selection.
   */
  @TestRailTest(caseId = 14131)
  @DisplayName("AssignRole: Partially selection")
  void assignRolesPartiallySection() {
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    //add users
    userService().addRow(inputs.get(0), "User");
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();

    //assert 'User' radio button is selected
    assertPartiallySelection();
  }

  /**
   * Invite User to the App: Assign: Role section: User: Apply.
   */
  @TestRailTest(caseId = 14123)
  @DisplayName("AssignRole: User: Apply")
  void assignRolesUserApply() {
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    //add users
    userService().addRow(inputs.get(0), "User");
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();

    assertPartiallySelection();
    //select "User" radio button
    roleModuleService().selectRole("User");
    //check "User" and "Admin" radi buttons
    assertUserRadioButton();
    //click "Apply" button
    assignUserModuleService().apply();

    //check that the changes have been applied
    assertRequiredUserGridRow(inputs.get(1), "User", 0);
    assertRequiredUserGridRow(inputs.get(0), "User", 1);
  }


  /**
   * Invite User to the App: Assign: Role section: Admin: Apply.
   */
  @TestRailTest(caseId = 14124)
  @DisplayName("AssignRole: Admin: Apply")
  void assignRolesAdminApply() {
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    //add users
    userService().addRow(inputs.get(0), "User");
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();

    assertPartiallySelection();
    //select "User" radio button
    roleModuleService().selectRole("Admin");
    //check "User" and "Admin" radi buttons
    assertUserRadioButton();
    //click "Apply" button
    assignUserModuleService().apply();

    //check that the changes have been applied
    assertRequiredUserGridRow(inputs.get(1), "Admin", 0);
    assertRequiredUserGridRow(inputs.get(0), "Admin", 1);
  }



  /**
   * Invite User to the App: Assign: Role section: User: Cancel.
   */
  @TestRailTest(caseId = 14125)
  @DisplayName("AssignRole: User: Cancel")
  void assignRolesUserCancel() {
    Selenide.refresh();

    //generate data for Users
    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    //add users
    userService().addRow(inputs.get(0), "User");
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();

    assertPartiallySelection();
    //select "User" radio button
    roleModuleService().selectRole("User");
    //check "User" and "Admin" radi buttons
    assertUserRadioButton();
    //click "Apply" button
    assignUserModuleService().cancel();

    //check that the changes have NOT been applied
    assertRequiredUserGridRow(inputs.get(1), "Admin", 0);
    assertRequiredUserGridRow(inputs.get(0), "User", 1);
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }

}
