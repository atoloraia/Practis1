package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInput;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutEmail;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutFirstName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutLastName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutRole;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResultsOnPendingTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSaving;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSavingWithIssues;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUploadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.utils.XmlService;
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
public class InviteUserUploadTest {

  private List<String> usersToRemove;
  private NewUserInput templateData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    templateData = getUploadTemplateInput();
    templateData.setEmail(format(templateData.getEmail(), timestamp()));
    templateData.setFirstName(format(templateData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(templateData.getEmail());
  }


  /**
   * Invite User to the App: Upload Template button.
   */
  @TestRailTest(caseId = 1110)
  @DisplayName("Invite User to the App: Upload Template button")
  void checkUploadTemplate() {
    assertUploadButton();
  }

  /**
   * Invite User to the App: Upload Template: Success upload.
   */
  @TestRailTest(caseId = 1111)
  @DisplayName("Invite User to the App: Upload Template: Success upload")
  @GeneratedDraftNameExtension
  void successUploadTemplate(String draftName) throws FileNotFoundException {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users").set(
            "First Name", templateData.getFirstName()).set("Last Name", templateData.getLastName())
        .set("Email", templateData.getEmail()).set("Role", "User").write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(templateData, "User", 0);

    //Save as Draft: Save
    userService().saveAsDraft(draftName);
    assertScreenAfterSaving();

    //assert grid row data
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    userService().openDraftUsersList();
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);

    //open and assert draft
    userGridRow.click();
    assertRequiredUserGridRow(templateData, "User", 0);

  }

  /**
   * Invite User to the App: Upload Template: Invalid format.
   */
  @TestRailTest(caseId = 1116)
  @DisplayName("Invite User to the App: Upload Template: Invalid format")
  void uploadInvalidTemplate() throws FileNotFoundException {
    final File file = Optional.of("/configuration/web/input/template/upload.docx")
        .map(InviteUserScreenTest.class::getResource).map(URL::getPath).map(File::new)
        .orElseThrow();
    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    snackbar().getMessage().shouldBe(exactText("No valid scheme found"));
  }

  /**
   * Invite User to the App: Upload Template: Empty First Name.
   */
  @TestRailTest(caseId = 1119)
  @DisplayName("Invite User to the App: Upload Template: Empty First Name")
  @GeneratedDraftNameExtension
  void uploadTemplateEmptyFirstName(String draftName) throws FileNotFoundException {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users").set(
            "First Name", " ").set("Last Name", templateData.getLastName())
        .set("Email", templateData.getEmail()).set("Role", "User").write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutFirstName(templateData, "User");

    //Save as Draft: Save
    userService().saveAsDraft(draftName);
    assertScreenAfterSavingWithIssues();
    assertScreenAfterSaving();

    //assert grid row data
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    userService().openDraftUsersList();
    final var userGridRow = userService().searchUser(draftName);
    assertUserGridRowDraft(draftName, userGridRow);

    //open and assert draft
    userGridRow.click();
    asserGridRowWithoutFirstName(templateData, "User");

  }

  /**
   * Invite User to the App: Upload Template: Empty Last Name.
   */
  @TestRailTest(caseId = 1120)
  @DisplayName("Invite User to the App: Upload Template: Empty Last Name")
  void uploadTemplateEmptyLastName() throws FileNotFoundException {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users").set(
            "First Name", templateData.getFirstName()).set("Last Name", " ")
        .set("Email", templateData.getEmail()).set("Role", "User").write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutLastName(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Email.
   */
  @TestRailTest(caseId = 1121)
  @DisplayName("Invite User to the App: Upload Template: Empty Email")
  void uploadTemplateEmptyEmail() throws FileNotFoundException {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users").set(
            "First Name", templateData.getFirstName()).set("Last Name", templateData.getLastName())
        .set("Email", " ").set("Role", "User").write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutEmail(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Role.
   */
  @TestRailTest(caseId = 1122)
  @DisplayName("Invite User to the App: Upload Template: Empty Role")
  void uploadTemplateEmptyRole() throws FileNotFoundException {
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users").set(
            "First Name", templateData.getFirstName()).set("Last Name", templateData.getLastName())
        .set("Email", templateData.getEmail()).set("Role", " ").write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutRole(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: User counter.
   */
  @TestRailTest(caseId = 1114)
  @DisplayName("Invite User to the App: Upload Template: User counter")
  void uploadTemplateUserCounter() throws FileNotFoundException {
    final var file = new File("test.xls");
    //given
    final var inputs = getUploadTemplateInputs().stream().limit(2)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .set("First Name", inputs.get(0).getFirstName())
        .set("Last Name", inputs.get(0).getLastName())
        .set("Email", inputs.get(0).getEmail()).set("Role", " ")

        .set("First Name", inputs.get(1).getFirstName())
        .set("Last Name", inputs.get(1).getLastName())
        .set("Email", inputs.get(1).getEmail()).set("Role", " ")
        .write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    assertUserCounter("2 items");
  }


  /**
   * Invite User to the App: Upload Template: Invite All users.
   */
  @Test
  @TestRailTest(caseId = 11672)
  @DisplayName("Invite User to the App: Upload Template: Invite All users")
  void uploadInviteAllUsers() throws FileNotFoundException {
    final var file = new File("test.xls");
    //given
    final var inputs = getUploadTemplateInputs().stream().limit(3)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .set("First Name", inputs.get(0).getFirstName())
        .set("Last Name", inputs.get(0).getLastName())
        .set("Email", inputs.get(0).getEmail())
        .set("Role", role)

        .set("First Name", inputs.get(1).getFirstName())
        .set("Last Name", inputs.get(1).getLastName())
        .set("Email", inputs.get(1).getEmail())
        .set("Role", role)

        .set("First Name", inputs.get(2).getFirstName())
        .set("Last Name", inputs.get(2).getLastName())
        .set("Email", inputs.get(2).getEmail())
        .set("Role", role)
        .write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //assert User 1
    final var userGridRow1 = userService().searchUser(inputs.get(0).getEmail());
    assertUserGridRowPending(inputs.get(0), userGridRow1);
    userGridRow1.click();
    assertUserData(inputs.get(0), userProfilePage());

    userService().openPendingUsersList();

    //assert User 2
    final var userGridRow2 = userService().searchUser(inputs.get(1).getEmail());
    assertUserGridRowPending(inputs.get(1), userGridRow2);
    userGridRow2.click();
    assertUserData(inputs.get(1), userProfilePage());

    userService().openPendingUsersList();

    //assert User 3
    final var userGridRow3 = userService().searchUser(inputs.get(2).getEmail());
    assertUserGridRowPending(inputs.get(2), userGridRow3);
    userGridRow3.click();
    assertUserData(inputs.get(2), userProfilePage());
  }


  /**
   * Invite User to the App: Upload Template: Invite Not All users.
   */
  @Test
  @TestRailTest(caseId = 11673)
  @DisplayName("Invite User to the App: Upload Template: Invite Not All users")
  void uploadInviteNotAllUsers() throws FileNotFoundException {
    final var file = new File("test.xls");
    //given
    final var inputs = getUploadTemplateInputs().stream().limit(3)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .set("First Name", inputs.get(0).getFirstName())
        .set("Last Name", inputs.get(0).getLastName())
        .set("Email", inputs.get(0).getEmail())
        .set("Role", role)

        .set("First Name", inputs.get(1).getFirstName())
        .set("Last Name", inputs.get(1).getLastName())
        .set("Email", inputs.get(1).getEmail())
        .set("Role", role)

        .set("First Name", inputs.get(2).getFirstName())
        .set("Last Name", inputs.get(2).getLastName())
        .set("Email", inputs.get(2).getEmail())
        .set("Role", role)
        .write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //select first user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "We’re sending 3 invitations. This might take a while."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();

    //assert grid row data
    userService().exitWithoutSaving();
    userService().openPendingUsersList();
    final var userGridRow = userService().searchUser(inputs.get(0).getEmail());
    assertUserGridRowPending(inputs.get(0), userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();

    assertUserData(inputs.get(0), userProfilePage());
  }

  /**
   * Invite User to the App: Upload Template: Not All users successfully invited.
   */
  @Test
  @TestRailTest(caseId = 1117)
  @DisplayName("Invite User to the App: Upload Template: Not All users successfully invited")
  void uploadNotAllSuccessfullyInvited() throws FileNotFoundException {
    final var file = new File("test.xls");
    //given
    final var inputs = getUploadTemplateInputs().stream().limit(2)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
        })
        .collect(toList());
    final var role = "User";

    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .set("First Name", inputs.get(0).getFirstName())
        .set("Last Name", inputs.get(0).getLastName())
        .set("Email", inputs.get(0).getEmail())
        .set("Role", role)

        .set("First Name", "")
        .set("Last Name", "")
        .set("Email", inputs.get(1).getEmail())
        .set("Role", role)
        .write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message "1 User has been invited."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenAfterSavingWithIssues();

    //assert successfully uploaded user
    userService().exitWithoutSaving();
    userService().openPendingUsersList();
    final var userGridRow = userService().searchUser(inputs.get(0).getEmail());
    assertUserGridRowPending(inputs.get(0), userGridRow);

    //assert data on 'User Settings' page for successfully uploaded user
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    assertUserData(inputs.get(0), userProfilePage());

    //assert User hasn't been invited
    userService().openPendingUsersList();
    userService().searchUser(inputs.get(1).getEmail());
    assertNoSearchResultsOnPendingTab();
    System.out.println("gfvb");

  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
