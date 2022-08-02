package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.saveAsDraftService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.unsavedProgressPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.component.SaveAsDraftValidator.assertSaveAsDraftPopUp;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllLabels;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedLabel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedTeam;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyState;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertEmptyTeamList;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteScreenCancelDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteScreenSaveDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUploadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.InviteUserValidator.getEmailValidationMessage;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertNoTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertSelectAllTeam;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamSearchResult;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertTeamUserProfile;
import static com.practis.web.selenide.validator.user.UserTeamValidator.assertUnSelectAllTeam;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

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
import com.practis.web.util.SelenideJsUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserTest {

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
  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }

  /**
   * Invite User to the App: Invite Selected Users with User Role.
   */
  @Test
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().addRow(inputData, "User", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow(inputData, "User");

    //select user and click "Invite Selected Users" button
    userService().inviteUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = userService().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();

    assertUserData(inputData, userProfilePage());
    assertTeamUserProfile(team.getName());
  }

  /**
   * Invite User to the App: Invite Selected Users with Admin Role.
   */
  @Test
  @TestRailTest(caseId = 8736)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.getName(), team.getName());

    //assert User row
    assertScreenAfterAddingRow(inputData, "Admin");

    //select user and click "Invite Selected Users" button
    userService().inviteUser();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = userService().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    assertUserData(inputData, userProfilePage());
  }

  /**
   * Invite User to the App: Delete User row.
   */
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Delete User row")
  @LabelExtension
  @TeamExtension
  void deleteUserRow(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().addRow(inputData, "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //Remove User row, assert empty state
    userService().deleteRow(0);
    snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
    assertEmptyState();
  }

  /**
   * Invite User to the App: Edit User row.
   */
  @Test
  @TestRailTest(caseId = 8845)
  @DisplayName("Invite User: Edit User row")
  @LabelExtension
  @TeamExtension
  void editUserRow(final RestCreateLabelResponse label, final RestTeamResponse team) {
    Selenide.refresh();

    final var inputs = getNewUserInputs().stream().limit(3)
        .peek(inputData -> inputData.setEmail(format(inputData.getEmail(), timestamp())))
        .peek(inputData -> inputData.setFirstName(format(inputData.getFirstName(), timestamp())))
        .collect(toList());

    //Add User row, assert not empty state
    Selenide.refresh();
    userService().addRow(inputs.get(0), "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //Edit User row and cancel Edit changes
    userService().clickEdit(0).editText(inputs.get(1)).editRole("User").cancelEditChanges(0);
    assertRequiredUserGridRow(inputs.get(0), "Admin");

    //Edit User row and apply changes
    userService().clickEdit(0).editText(inputs.get(2)).editRole("User").applyEditChanges(0);

    assertRequiredUserGridRow(inputs.get(2), "User");

    //select user and click "Invite Selected Users" button

    userService().inviteUser();

    //assert grid row data
    final var userGridRow = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow);
  }

  /**
   * Invite User to the App: Validation: Email.
   */
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Validation: Email")
  @LabelExtension
  @TeamExtension
  void inviteUser_wrongEmailFormat() {
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
  @Test
  @TestRailTest(caseId = 1065)
  @DisplayName("Invite User: Check required fields")
  void checkRequiredFields() {
    assertRequiredInputs(inputData);

    //Click '+' button
    userService().addRow();

    //assert User row
    assertRequiredUserGridRow(inputData, "User");
    assertNoPrompt();
  }

  /**
   * Invite User to the App: Check Teams dropdown: No teams state.
   */
  @Test
  @TestRailTest(caseId = 1079)
  @DisplayName("Invite User: Check Teams dropdown: No teams state")
  void checkEmptyTeamsDropdown() {
    assertEmptyTeamList();
  }

  /**
   * Invite User to the App: Check Teams dropdown: Delete team.
   */
  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Invite User: Check Teams dropdown: Delete team")
  @TeamExtension
  void checkDeletingTeam(final RestTeamResponse team) {
    Selenide.refresh();

    assertAddedTeam(team.getName());
    practisApi().deleteTeam(team.getName());
    Selenide.refresh();
    assertEmptyTeamList();
  }

  /**
   * Invite User to the App: Check Teams dropdown: Search team.
   */
  @Test
  @TestRailTest(caseId = 1083)
  @DisplayName("Invite User: Check Teams dropdown: Search team")
  @TeamExtension
  void checkSearchTeam(final RestTeamResponse team) {
    Selenide.refresh();
    //Check Team exists
    assertAddedTeam(team.getName());

    //Search by not existing team and check results
    teamService().searchTeam("invalid search criteria");
    assertNoTeamSearchResult();

    //Search by existing team and check results
    teamService().searchTeam(team.getName());
    assertTeamSearchResult(team.getName());
  }

  /**
   * Invite User to the App: Check Teams dropdown: Select All /Unselect All team.
   */
  @Test
  @TestRailTest(caseId = 1084)
  @DisplayName("Invite User: Check Teams dropdown: Select All/Unselect All team")
  @TeamExtension
  void checkSelectUnselectAllTeam(final RestTeamResponse team) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedTeam(team.getName());
    //Select all and assert
    teamService().selectAllTeam();
    assertSelectAllTeam();

    //Unselect all and assert
    teamService().unSelectAllTeam();
    assertUnSelectAllTeam();
  }

  /**
   * Invite User to the App: Check Label dropdown: No Label state.
   */
  @Test
  @TestRailTest(caseId = 9327)
  @DisplayName("Invite User: Check Labels dropdown: No Labels state")
  void checkEmptyLabelDropdown() {
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
  }


  /**
   * Invite User to the App: Check Label dropdown: Delete label.
   */
  @Test
  @TestRailTest(caseId = 1101)
  @DisplayName("Invite User: Check Label dropdown: Delete label")
  @LabelExtension
  void checkDeletingLabel(final RestCreateLabelResponse label) {
    Selenide.refresh();

    assertAddedLabel(label.getName());
    practisApi().deleteLabel(label.getName());
    Selenide.refresh();
    inviteUsersPage().getLabelsField().click();
    assertNoLabelsYet();
  }

  /**
   * Invite User to the App: Check Label dropdown: Search label.
   */
  @Test
  @TestRailTest(caseId = 9326)
  @DisplayName("Invite User: Check Label dropdown: Search label")
  @LabelExtension
  void checkSearchLabel(final RestCreateLabelResponse label) {
    Selenide.refresh();
    //Check Team exists
    assertAddedLabel(label.getName());
    //Search by not existing label and check results
    labelService().searchLabel("invalid search criteria");
    assertNoLabelSearchResult();

    //Search by existing label and check results
    labelService().searchLabel(label.getName());
    assertLabelSearchResult(label.getName());
  }

  /**
   * Invite User to the App: Check Labels dropdown: Select All /Unselect All labels.
   */
  @Test
  @TestRailTest(caseId = 9329)
  @DisplayName("Invite User: Check Teams dropdown: Select All/Unselect All labels")
  @LabelExtension
  void checkSelectUnselectAllLabels(final RestCreateLabelResponse label) {
    Selenide.refresh();

    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertAddedLabel(label.getName());
    //Select all and assert
    labelService().selectAllLabels();
    assertSelectedAllLabels();

    //Unselect all and assert
    labelService().unSelectAllLabels();
    assertUnSelectAllLabels();
  }

  /**
   * Invite User to the App: Download Template button.
   */
  @Test
  @TestRailTest(caseId = 1109)
  @DisplayName("Invite User: Download Template button")
  void checkDownloadTemplate() throws FileNotFoundException {
    assertDownloadButton();
    inviteUsersPage().getDownloadTemplateButton().download();
    assertDownloadedFile("upload.xlsx");
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
  void saveAsDraftPopUpCancel() {
    final var draftName = String.format("Draft %s", timestamp());

    userService().addRow(inputData, "User");

    //Save as Draft: Cancel
    userService().clickSaveAsDraftButton();
    saveAsDraftService().clickCancel();
    assertInviteScreenCancelDraft();

    //Exit form  Without Saving and go to Users page: Draft
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    unsavedProgressPopUpService().clickExitWithoutSavingButton();
    navigationCompanies().getUsersNavigationItem().click();
    usersPage().getTabs().get(2).click();

    //assert grid row data
    userService().searchUser(inputData.getEmail());
    assertNoSearchResults(draftName);
  }

  /**
   * Invite User to the App: Save As Draft: Save.
   */
  @Test
  @TestRailTest(caseId = 9330)
  @DisplayName("Invite User: Save As Draft: Save")
  void saveAsDraftPopUpSave() {
    final var draftName = String.format("Draft %s", timestamp());

    userService().fillText(inputData).selectRole("User");
    userService().addRow();

    //Save as Draft: Save
    userService().clickSaveAsDraftButton();
    saveAsDraftService().saveAsDraft(draftName);

    //Check snackbar message "Invitation draft has been saved"
    snackbar().getMessage().shouldBe(exactText("Invitation draft has been saved"));

    //assert screen after saving
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertInviteScreenSaveDraft();

    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    navigationCompanies().getUsersNavigationItem().click();
    usersPage().getTabs().get(2).click();

    //assert grid row data
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);
  }

  /**
   * Invite User to the App: Upload Template button.
   */
  @Test
  @TestRailTest(caseId = 1110)
  @DisplayName("Invite User to the App: Upload Template button")
  void checkUploadTemplate()  {
    assertUploadButton();
  }

  /**
   * Invite User to the App: Upload Template: Success upload.
   */
  @Test
  @TestRailTest(caseId = 1111)
  @DisplayName("Invite User to the App: Upload Template: Success upload")
  void successUpload() throws FileNotFoundException {
    final File file = Optional.of("/configuration/web/input/template/upload.xlsx")
        .map(InviteUserTest.class::getResource)
        .map(URL::getPath)
        .map(File::new)
        .orElseThrow();
    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    System.out.println(1);
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
