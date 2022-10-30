package com.practis.selenide.company.create.user.assign;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCleanLabelSearch;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertPartiallySelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSearchElementsOnLabelsModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectAllLabelButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnselectedLabel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneLabelSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
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
   * Invite User to the App: Assign: Check WEB elements on Label section.
   */
  @TestRailTest(caseId = 14977)
  @DisplayName("AssignLabels: Label section: Check WEB elements")
  @LabelExtension(count = 1)
  void checkElementsOnLabelSection() {
    Selenide.refresh();
    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();

    assertElementsOnLabelSection();
  }

  /**
   * Invite User to the App: Assign: Labels section: Search.
   */
  @TestRailTest(caseId = 13640)
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
    awaitAjaxComplete(5);
    assertUnSelectAllStateLabels();
    //select one Label
    labelModuleService().selectLabel(labels.get(0).getName());
    //assert modal if one Label is selected
    assertSelectedLabel(labels.get(0).getName());
    assertLabelCounter("1 Label selected");
    assertSelectAllLabelButton();
    //select all
    //TODO Update clicking on "Select All" when DEV-10367 will be done
    $(".sc-kGrCzQ.fOwgCv").click();
    assertSelectedAllStateLabels();
  }

  /**
   * Invite User to the App: Assign: Labels section: Cancel.
   */
  @TestRailTest(caseId = 13643)
  @DisplayName("AssignLabels: Cancel")
  @LabelExtension(count = 1)
  void assignLabelsCancel(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    awaitAjaxComplete(10);
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
    awaitAjaxComplete(10);
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
  void assignLabelAlreadyAssigned(final List<RestCreateLabelResponse> labels) {
    Selenide.refresh();

    final var inputs = userService().generateUserInputs(1);
    inputs.forEach(input -> usersToRemove.add(input.getEmail()));

    userService().addRow(inputs.get(0), "Admin", labels.get(0));
    userService().assignAllUsers();
    //select one Team and click 'Assign' button
    assertSelectedLabel(labels.get(0).getName());
    assertUnselectedLabel(labels.get(1).getName());
  }


  /**
   * Invite User to the App: Assign: Labels section: Empty state.
   */
  @TestRailTest(caseId = 13645)
  @DisplayName("AssignLabels: Empty state")
  void assignLabelEmptyState() {
    Selenide.refresh();

    userService().addRow(inputData, "Admin");
    userService().assignFirstUser();
    assertEmptyLabelModel();
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }
}
