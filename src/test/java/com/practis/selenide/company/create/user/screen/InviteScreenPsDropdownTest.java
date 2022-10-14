package com.practis.selenide.company.create.user.screen;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetModuleService;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertAddedPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertDisabledApplyPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertElementsOnPsDropdown;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertEnabledCancelPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertNoPractisSetAddedYet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertNoPractisSetSearchResult;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPractisSetSearchResult;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedAllStatePractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertUnSelectedAllStatePs;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteScreenPsDropdownTest {

  @BeforeEach
  void init() {
    newItemSelector().create("User");
  }

  /**
   * Invite User to the App: Check PS dropdown: Check WEB elements.
   */
  @TestRailTest(caseId = 14964)
  @DisplayName("InviteScreenPsDropdownTest: Check PS dropdown: Check WEB elements")
  @PractisSetExtension(count = 1)
  void checkElementsOnPsDropdown() {
    Selenide.refresh();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getPractisSetsField().click();
    //assert WEB elements
    assertElementsOnPsDropdown();
    assertDisabledApplyPractisSetButton();
    assertEnabledCancelPractisSetButton();
  }

  /**
   * Invite User to the App: Check PS dropdown: No PS state.
   */
  @TestRailTest(caseId = 1086)
  @DisplayName("InviteScreenPsDropdownTest: Check PS dropdown: No PS state")
  void checkEmptyPsDropdown() {
    inviteUsersPage().getPractisSetsField().click();
    assertNoPractisSetAddedYet();
  }

  /**
   * Invite User to the App: Check PS dropdown: Delete PS.
   */
  @TestRailTest(caseId = 1089)
  @DisplayName("InviteScreenPsDropdownTest: Check PS dropdown: Delete PS")
  @PractisSetExtension(count = 1)
  void checkDeletingPractisSet(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();
    //check there is one PS
    assertAddedPractisSet(practisSets.get(0).getName());
    //delete PS
    practisApi().deletePractisSet(practisSets.get(0).getName());
    //assert there is no PS
    Selenide.refresh();
    inviteUsersPage().getPractisSetsField().click();
    assertNoPractisSetAddedYet();
  }

  /**
   * Invite User to the App: Check PS dropdown: Search PS.
   */
  @TestRailTest(caseId = 1090)
  @DisplayName("InviteScreenPsDropdownTest: Check PS dropdown: Search PS")
  @PractisSetExtension(count = 1)
  void checkSearchPractisSet(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();
    //Check Practis Set exists
    assertAddedPractisSet(practisSets.get(0).getName());
    //Search by not existing Practis Set and check results
    practisSetModuleService().searchPractisSet("invalid search criteria");
    assertNoPractisSetSearchResult();

    //Search by existing Practis Set and check results
    practisSetModuleService().searchPractisSet(practisSets.get(0).getName());
    assertPractisSetSearchResult(practisSets.get(0).getName());
  }

  /**
   * Invite User to the App: Check PS dropdown: Select All /Unselect All PS.
   */
  @TestRailTest(caseId = 1091)
  @DisplayName("InviteScreenPsDropdownTest: Check PS dropdown: Select All/Unselect All PS")
  @PractisSetExtension(count = 1)
  void checkSelectUnselectAllPs(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedPractisSet(practisSets.get(0).getName());
    //Select all and assert
    inviteUserPsModule().getSelectedAllButton().get(0).click();
    assertSelectedAllStatePractisSet();

    //Unselect all and assert
    practisSetModuleService().unSelectAllPractisSets();
    assertUnSelectedAllStatePs();
  }


}
