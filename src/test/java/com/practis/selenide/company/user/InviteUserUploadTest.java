package com.practis.selenide.company.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInput;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutEmail;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutFirstName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutLastName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutRole;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSaving;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUploadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.utils.XmlService;
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
  private NewUserInput inputData;
  private NewUserInput templateData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    templateData = getUploadTemplateInput();
    templateData.setEmail(format(templateData.getEmail(), timestamp()));
    templateData.setFirstName(format(templateData.getFirstName(), timestamp()));

    inputData = getNewUserInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }


  /**
   * Invite User to the App: Upload Template button.
   */
  @Test
  @TestRailTest(caseId = 1110)
  @DisplayName("Invite User to the App: Upload Template button")
  void checkUploadTemplate() {
    assertUploadButton();
  }

  /**
   * Invite User to the App: Upload Template: Success upload.
   */
  @Test
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
  @Test
  @TestRailTest(caseId = 1116)
  @DisplayName("Invite User to the App: Upload Template: Invalid format")
  void uploadInvalidTemplate() throws FileNotFoundException {
    final File file = Optional.of("/configuration/web/input/template/upload.docx")
        .map(InviteUserScreenTest.class::getResource).map(URL::getPath).map(File::new).orElseThrow();
    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    snackbar().getMessage().shouldBe(exactText("No valid scheme found"));
  }

  /**
   * Invite User to the App: Upload Template: Empty First Name.
   */
  @Test
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
    assertScreenAfterSaving();

    //assert grid row data
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
  @Test
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
  @Test
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
  @Test
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

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().deleteUser(email));
  }

}
