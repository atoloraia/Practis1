package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.component.SaveAsDraftValidator.assertSaveAsDraftPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteScreenCancelDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSaving;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserSaveAsDraftTest {

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
   * Invite User to the App: Save As Draft: View pop-up.
   */
  @Test
  @TestRailTest(caseId = 1125)
  @DisplayName("Invite User: Save As Draft: View pop-up")
  void checkSaveAsDraftPopUp() {
    userService().addRow(inputData, "User");
    inviteUsersPage().getSaveAsDraftButton().click();
    assertSaveAsDraftPopUp();
  }

  /**
   * Invite User to the App: Save As Draft: Cancel.
   */
  @Test
  @TestRailTest(caseId = 1133)
  @DisplayName("Invite User: Save As Draft: Cancel")
  @GeneratedDraftNameExtension
  void saveAsDraftPopUpCancel(String draftName) {

    userService().addRow(inputData, "User");

    //Save as Draft: Cancel
    userService().cancelSaveAsDraft();
    assertInviteScreenCancelDraft();

    //assert grid row data
    userService().exitWithoutSaving();
    userService().openDraftUsersList();
    userService().searchUser(inputData.getEmail());
    assertNoSearchResults(draftName);
  }

  /**
   * Invite User to the App: Save As Draft: Save.
   */
  @Test
  @TestRailTest(caseId = 9330)
  @DisplayName("Invite User: Save As Draft: Save")
  @GeneratedDraftNameExtension
  void saveAsDraftPopUpSave(String draftName) {

    userService().fillText(inputData).selectRole("User");
    userService().addRow();

    //Save as Draft: Save
    userService().saveAsDraft(draftName);

    //Check snackbar message "Invitation draft has been saved"
    snackbar().getMessage().shouldBe(exactText("Invitation draft has been saved"));

    //assert screen after saving
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenAfterSaving();

    //assert grid row data
    userService().openDraftUsersList();
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);

    //open and assert draft
    userGridRow.click();
    assertRequiredUserGridRow(inputData, "User", 0);
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }
}
