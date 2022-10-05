package com.practis.selenide.company.create.user.assign;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCleanLabelSearch;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSearchElementsOnLabelsModal;
import static com.practis.web.selenide.validator.selection.RoleSelectionValidator.assertUserRadioButton;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
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
  @TestRailTest(caseId = 10266)
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
  @TestRailTest(caseId = 10266)
  @DisplayName("AssignRole: Admin")
  void assignRolesAdmin() {
    Selenide.refresh();
    userService().addRow(inputData, "User");
    userService().assignFirstUser();

    //assert 'User' radio button is selected
    assertUserRadioButton();
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }

}
