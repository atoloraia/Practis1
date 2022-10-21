package com.practis.selenide.company.create.user.screen;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelLabelButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertDisabledApplyLabelButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedLabel;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteScreenLabelDropdownTest {

  @BeforeEach
  void init() {
    newItemSelector().create("User");
  }

  /**
   * Invite User to the App: Check Label dropdown: Check WEB elements.
   */
  @TestRailTest(caseId = 14975)
  @DisplayName("InviteLabelDropdownTest: Check Label dropdown: Check WEB elements")
  @LabelExtension(count = 1)
  void checkElementsOnLabelDropdown() {
    Selenide.refresh();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getLabelsField().click();
    //assert WEB elements
    assertElementsOnLabelSection();
    assertDisabledApplyLabelButton();
    assertCancelLabelButton();
  }

  /**
   * Invite User to the App: Check Label dropdown: No Label state.
   */
  @TestRailTest(caseId = 9327)
  @DisplayName("InviteLabelDropdownTest: Check Labels dropdown: No Labels state")
  void checkEmptyLabelDropdown() {
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
  }

  /**
   * Invite User to the App: Check Label dropdown: Delete label.
   */
  @TestRailTest(caseId = 1101)
  @DisplayName("InviteLabelDropdownTest: Check Label dropdown: Delete label")
  @LabelExtension(count = 1)
  void checkDeletingLabel(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    assertAddedLabel(label.get(0).getName());
    practisApi().deleteLabel(label.get(0).getName());
    Selenide.refresh();
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
  }

  /**
   * Invite User to the App: Check Label dropdown: Search label.
   */
  @TestRailTest(caseId = 9326)
  @DisplayName("InviteLabelDropdownTest: Check Label dropdown: Search label")
  @LabelExtension(count = 1)
  void checkSearchLabel(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();
    //Check Label exists
    assertAddedLabel(label.get(0).getName());

    //Search by not existing label and check results
    labelModuleService().searchLabel("invalid search criteria");
    assertNoLabelSearchResult();

    //Search by existing label and check results
    labelModuleService().searchLabel(label.get(0).getName());
    assertLabelSearchResult(label.get(0).getName());
  }

  /**
   * Invite User to the App: Check Labels dropdown: Select All /Unselect All labels.
   */
  @TestRailTest(caseId = 9329)
  @DisplayName("InviteLabelDropdownTest: Check Labels dropdown: Select All/Unselect All labels")
  @LabelExtension(count = 1)
  void checkSelectUnselectAllLabels(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedLabel(label.get(0).getName());
    //Select all and assert
    labelModuleService().selectAllLabels();
    assertSelectedAllStateLabels();

    //Unselect all and assert
    labelModuleService().unSelectAllLabels();
    assertUnSelectAllStateLabels();
  }

}
