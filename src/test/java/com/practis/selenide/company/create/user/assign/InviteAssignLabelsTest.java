package com.practis.selenide.company.create.user.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCleanLabelSearch;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSearchElementsOnLabelsModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectAllLabelButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnselectedLabel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedAllStateTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneLabelSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
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
public class InviteAssignLabelsTest {

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
   * Invite User to the App: Assign: Labels section: Search.
   */
  @TestRailTest(caseId = 13315)
  @DisplayName("AssignLabels: Search")
  @LabelExtension(count = 2)
  void assignLabelsSearch(final List<RestCreateLabelResponse> labels) {
    Selenide.refresh();
    userService().addRow(inputData, "Admin", labels.get(0));
    userService().assignFirstUser();

    //assert search team
    assertSearchElementsOnLabelsModal();
    //assert clean search
    assertCleanLabelSearch(2);
    //Search should be performed after entering 1 character
    assertLabelSearchAfter1Char(labels.get(0).getName());
    //assert empty state
    labelModuleService().searchLabel("no results");
    assertNoLabelSearchResult();
  }

  /**
   * Invite User to the App: Assign: Label section: Select All.
   */
  @TestRailTest(caseId = 13641)
  @DisplayName("AssignLabels: Select All")
  @LabelExtension(count = 2)
  void assignLabelsSelectAll(final List<RestCreateLabelResponse> labels) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    //assert unselected state
    assertUnSelectAllStateLabels();
    //select one Label
    labelModuleService().selectLabel(labels.get(0).getName());
    //assert modal if one Label is selected
    assertSelectedLabel(labels.get(0).getName());
    assertLabelCounter("1 Label selected");
    assertSelectAllLabelButton();
    //select all
    labelModuleService().selectAllLabels();
    assertSelectedAllStateTeam();
  }


  /**
   * Invite User to the App: Assign: Labels section: Cancel.
   */
  @TestRailTest(caseId = 13643)
  @DisplayName("AssignLabels: Cancel")
  @LabelExtension(count = 1)
  void assignTeamsCancel(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    //select one Label and click "Cancel"
    labelModuleService().selectLabel(label.get(0).getName());
    assignUserModuleService().cancel();
    //assert User row
    assertRequiredUserGridRow(inputData, "Admin", 0);
    inviteUsersPage().getLabel().get(0).shouldBe(hidden);
  }


  /**
   * Invite User to the App: Assign: Labels section: Apply.
   */
  @TestRailTest(caseId = 13642)
  @DisplayName("AssignLabels: Apply")
  @LabelExtension(count = 1)
  void assignLabelApply(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();

    //select one Label and click 'Assign' button
    labelModuleService().selectLabel(label.get(0).getName());
    assignUserModuleService().apply();
    //assert User row
    assertRequiredUserGridRow(inputData, "Admin", 0);
    assertOneLabelSelected(0);
  }

  /**
   * Invite User to the App: Assign: Labels section: Already Assigned Labels.
   */
  @TestRailTest(caseId = 13644)
  @DisplayName("AssignLabels: Already Assigned Labels")
  @LabelExtension(count = 2)
  void assignTeamsAlreadyAssigned(final List<RestCreateLabelResponse> labels) {
    Selenide.refresh();

    final var inputs = userService().generateUserInputs(2);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    userService().addRow(inputs.get(0), "Admin", labels.get(0));
    userService().addRow(inputs.get(1), "Admin");
    userService().assignAllUsers();
    //select one Team and click 'Assign' button
    assertSelectedLabel(labels.get(0).getName());
    assertUnselectedLabel(labels.get(1).getName());
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }
}
