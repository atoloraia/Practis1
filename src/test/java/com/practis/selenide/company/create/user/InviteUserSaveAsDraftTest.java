package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.popup.SaveAsDraftPopUpValidator.assertSaveAsDraftErrorPopUp;
import static com.practis.web.selenide.validator.popup.SaveAsDraftPopUpValidator.assertSaveAsDraftPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserDraftUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteScreenCancelDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResultsOnDraftTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSaving;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.web.util.SelenideJsUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserSaveAsDraftTest {

  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));
  }

  /**
   * Invite User to the App: Save As Draft: View pop-up.
   */
  @TestRailTest(caseId = 1125)
  @DisplayName("InviteUserSaveAsDraftTest: Save As Draft: View pop-up")
  void checkSaveAsDraftPopUp() {
    userService().addRow(inputData, "User");
    inviteUsersPage().getSaveAsDraftButton().click();
    assertSaveAsDraftPopUp();
  }

  /**
   * Invite User to the App: Save As Draft: Cancel.
   */
  @TestRailTest(caseId = 1133)
  @DisplayName("InviteUserSaveAsDraftTest: Save As Draft: Cancel")
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
    assertNoSearchResultsOnDraftTab(draftName);
  }

  /**
   * Invite User to the App: Save As Draft: Save.
   */
  @TestRailTest(caseId = 9330)
  @DisplayName("InviteUserSaveAsDraftTest: Save As Draft: Save")
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

    //assert draft User
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    userService().openDraftUsersList();
    asserDraftUser(draftName, inputData, "User", 0);
  }

  @TestRailTest(caseId = 11740)
  @DisplayName("InviteUserSaveAsDraftTest: Save As Draft: Name Already Exists")
  @GeneratedDraftNameExtension
  void saveAsDraftPopNameAlreadyExists() {

    userService().fillText(inputData).selectRole("User");
    userService().addRow();

    //Save as Draft: Save
    userService().saveAsDraft("existingName");
    assertSaveAsDraftErrorPopUp();
  }

  // User 6 or 7
  //Admin 4
  @DisplayName("Invite User: Save As Draft: Save")
  @PendingUserExtension(limit = 2, company = "CompanyAnastasiya", role = 64)
  void testMultipleInvite(final List<NewUserInput> users) {
    System.out.println(1);
  }
}
