package com.practis.selenide.company.create.user.screen;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertAddedPs;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertDisabledApplyPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertElementsOnPsSection;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertEnabledCancelPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertNoPsAddedYet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertNoPsSearchResult;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPsSearchResult;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedAllStatePs;
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
  @DisplayName("InvitePsDropdownTest: Check PS dropdown: Check WEB elements")
  @PractisSetExtension(count = 1)
  void checkElementsOnPsDropdown() {
    Selenide.refresh();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    inviteUsersPage().getPractisSetsField().click();

    //assert WEB elements
    assertElementsOnPsSection();
    assertDisabledApplyPractisSetButton();
    assertEnabledCancelPractisSetButton();
  }

  /**
   * Invite User to the App: Check PS dropdown: No PS state.
   */
  @TestRailTest(caseId = 1086)
  @DisplayName("InvitePsDropdownTest: Check PS dropdown: No PS state")
  void checkEmptyPsDropdown() {
    inviteUsersPage().getPractisSetsField().click();
    assertNoPsAddedYet();
  }

  /**
   * Invite User to the App: Check PS dropdown: Delete PS.
   */
  @TestRailTest(caseId = 1089)
  @DisplayName("InvitePsDropdownTest: Check PS dropdown: Delete PS")
  @PractisSetExtension(count = 1)
  void checkDeletingPractisSet(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();

    assertAddedPs(practisSets.get(0).getName());
    practisApi().deletePractisSet(practisSets.get(0).getName());
    Selenide.refresh();
    inviteUsersPage().getPractisSetsField().click();
    assertNoPsAddedYet();
  }

  /**
   * Invite User to the App: Check PS dropdown: Search PS.
   */
  @TestRailTest(caseId = 1090)
  @DisplayName("InvitePsDropdownTest: Check PS dropdown: Search PS")
  @PractisSetExtension(count = 1)
  void checkSearchPractisSet(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();
    //Check Practis Set exists
    assertAddedPs(practisSets.get(0).getName());

    //Search by not existing Practis Set and check results
    psModuleService().searchPs("invalid search criteria");
    assertNoPsSearchResult();

    //Search by existing Practis Set and check results
    psModuleService().searchPs(practisSets.get(0).getName());
    assertPsSearchResult(practisSets.get(0).getName());
  }

  /**
   * Invite User to the App: Check PS dropdown: Select All /Unselect All PS.
   */
  @TestRailTest(caseId = 1091)
  @DisplayName("InvitePsDropdownTest: Check PS dropdown: Select All/Unselect All PS")
  @PractisSetExtension(count = 1)
  void checkSelectUnselectAllPs(final List<NewPractisSetInput> practisSets) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedPs(practisSets.get(0).getName());
    //Select all and assert
    inviteUserPsModule().getSelectedAllButton().get(0).click();
    assertSelectedAllStatePs();

    //Unselect all and assert
    psModuleService().unSelectAllPs();
    assertUnSelectedAllStatePs();
  }
}
