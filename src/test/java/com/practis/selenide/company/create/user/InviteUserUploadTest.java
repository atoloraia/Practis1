package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInput;
import static com.practis.web.selenide.configuration.data.company.UploadTemplateInputData.getUploadTemplateInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserDraftUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutEmail;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutFirstName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutLastName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutRole;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserPendingUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResultsOnPendingTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSavingWithIssues;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUploadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
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
  @DisplayName("InviteUserUploadTest: Upload Template button")
  void checkUploadTemplate() {
    assertUploadButton();
  }

  /**
   * Invite User to the App: Upload Template: Success upload.
   */
  @TestRailTest(caseId = 1111)
  @DisplayName("InviteUserUploadTest: Upload Template: Success upload")
  @GeneratedDraftNameExtension
  void successUploadTemplate(String draftName) throws FileNotFoundException {
    File file = userService()
        .generateTemplate(templateData.getFirstName(), templateData
            .getLastName(), templateData.getEmail(), "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(templateData, "User", 0);
  }

  /**
   * Invite User to the App: Upload Template: Success upload: Save as Draft.
   */
  @TestRailTest(caseId = 11730)
  @DisplayName("InviteUserUploadTest: Upload Template: Success upload: Save as Draft")
  @GeneratedDraftNameExtension
  void uploadUsersSaveAsDraft(String draftName) throws FileNotFoundException {
    File file = userService()
        .generateTemplate(templateData.getFirstName(), templateData
            .getLastName(), templateData.getEmail(), "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //Save as Draft: Save
    userService().saveAsDraft(draftName);
    snackbar().getMessage().shouldBe(exactText("Invitation draft has been saved"));
    awaitElementNotExists(10, () -> snackbar().getMessage());

    //assert draft
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    userService().openDraftUsersList();
    asserDraftUser(draftName, templateData, "User", 0);
  }

  /**
   * Invite User to the App: Upload Template: Success upload: Invite.
   */
  @TestRailTest(caseId = 11731)
  @DisplayName("InviteUserUploadTest: Upload Template: Success upload: Invite")
  @GeneratedDraftNameExtension
  void uploadUsersInvite(String draftName) throws FileNotFoundException {
    File file = userService()
        .generateTemplate(templateData.getFirstName(), templateData
            .getLastName(), templateData.getEmail(), "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //invite selected Users
    userService().inviteAllUser();

    //Check snackbar message
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 1 invitations. This might take a while."));

    //assert User 1
    asserPendingUser(templateData);
    //TODO check role
  }

  /**
   * Invite User to the App: Upload Template: Invalid format.
   */
  @TestRailTest(caseId = 1116)
  @DisplayName("InviteUserUploadTest: Upload Template: Invalid format")
  void uploadInvalidTemplate() throws FileNotFoundException {
    final File file = Optional.of("/configuration/web/input/template/upload.docx")
        .map(InviteUserScreenTest.class::getResource).map(URL::getPath).map(File::new)
        .orElseThrow();

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //assert snackbar
    snackbar().getMessage().shouldBe(exactText("No valid scheme found"));
  }

  /**
   * Invite User to the App: Upload Template: Empty First Name.
   */
  @TestRailTest(caseId = 1119)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty First Name")
  @GeneratedDraftNameExtension
  void uploadTemplateEmptyFirstName(String draftName) throws FileNotFoundException {
    File file = userService()
        .generateTemplate("", templateData
            .getLastName(), templateData.getEmail(), "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutFirstName(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Last Name.
   */
  @TestRailTest(caseId = 1120)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Last Name")
  void uploadTemplateEmptyLastName() throws FileNotFoundException {
    File file = userService()
        .generateTemplate(templateData.getFirstName(), "", templateData.getEmail(), "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutLastName(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Email.
   */
  @TestRailTest(caseId = 1121)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Email")
  void uploadTemplateEmptyEmail() throws FileNotFoundException {
    File file = userService()
        .generateTemplate(templateData.getFirstName(), templateData.getLastName(), "", "User");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutEmail(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Role.
   */
  @TestRailTest(caseId = 1122)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Role")
  void uploadTemplateEmptyRole() throws FileNotFoundException {

    //generate template
    File file = userService()
        .generateTemplate(templateData.getFirstName(), templateData
            .getLastName(), templateData.getEmail(), "");

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutRole(templateData, "User");
  }

  /**
   * Invite User to the App: Upload Template: User counter.
   */
  @TestRailTest(caseId = 1114)
  @DisplayName("InviteUserUploadTest: Upload Template: User counter")
  void uploadTemplateUserCounter() throws FileNotFoundException {
    //generate data for Users
    final var inputs = generateUserTemplateInputs(2);

    //generate template
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .setUserRow(inputs.get(0).getFirstName(), inputs.get(0).getLastName(), inputs.get(0)
            .getEmail(), " ")
        .setUserRow(inputs.get(1).getFirstName(), inputs.get(1).getLastName(), inputs.get(0)
            .getEmail(), " ")
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
  @DisplayName("InviteUserUploadTest: Upload Template: Invite All users")
  void uploadInviteAllUsers() throws FileNotFoundException {
    //generate data for Users
    final var inputs = generateUserTemplateInputs(3);
    final var role = "User";

    //generate template
    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .setUserRow(inputs.get(0).getFirstName(), inputs.get(0).getLastName(), inputs.get(0)
            .getEmail(), role)
        .setUserRow(inputs.get(1).getFirstName(), inputs.get(1).getLastName(), inputs.get(1)
            .getEmail(), role)
        .setUserRow(inputs.get(2).getFirstName(), inputs.get(2).getLastName(), inputs.get(2)
            .getEmail(), role)
        .write(file);

    inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //assert User 1
    asserPendingUser(inputs.get(0));

    userService().openPendingUsersList();

    //assert User 2
    asserPendingUser(inputs.get(1));

    userService().openPendingUsersList();

    //assert User 3
    asserPendingUser(inputs.get(2));
  }


  /**
   * Invite User to the App: Upload Template: Invite Not All users.
   */
  @Test
  @TestRailTest(caseId = 11673)
  @DisplayName("InviteUserUploadTest: Upload Template: Invite Not All users")
  void uploadInviteNotAllUsers() throws FileNotFoundException {
    //generate data for Users
    final var inputs = generateUserTemplateInputs(3);
    final var role = "User";

    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .setUserRow(inputs.get(0).getFirstName(), inputs.get(0).getLastName(), inputs.get(0)
            .getEmail(), role)
        .setUserRow(inputs.get(1).getFirstName(), inputs.get(1).getLastName(), inputs.get(1)
            .getEmail(), role)
        .setUserRow(inputs.get(2).getFirstName(), inputs.get(2).getLastName(), inputs.get(2)
            .getEmail(), role)
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
    asserPendingUser(inputs.get(0));
  }

  /**
   * Invite User to the App: Upload Template: Not All users successfully invited.
   */
  @Test
  @TestRailTest(caseId = 1117)
  @DisplayName("InviteUserUploadTest: Upload Template: Not All users successfully invited")
  void uploadNotAllSuccessfullyInvited() throws FileNotFoundException {
    //generate data for Users
    final var inputs = generateUserTemplateInputs(2);
    final var role = "User";

    final var file = new File("test.xls");
    new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
        .setUserRow(inputs.get(0).getFirstName(), inputs.get(0).getLastName(), inputs.get(0)
            .getEmail(), role)
        .setUserRow("", "", inputs.get(1).getEmail(), role)
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
    asserPendingUser(inputs.get(0));

    //assert User hasn't been invited
    userService().openPendingUsersList();
    userService().searchUser(inputs.get(1).getEmail());
    assertNoSearchResultsOnPendingTab();
  }


  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }

  /**
   * Generate template inputs.
   */
  private List<NewUserInput> generateUserTemplateInputs(int limit) {
    final var inputs = getUploadTemplateInputs().stream().limit(limit)
        .peek(input -> {
          input.setEmail(format(input.getEmail(), timestamp()));
          input.setFirstName(format(input.getFirstName(), timestamp()));
          usersToRemove.add(input.getEmail());
        })
        .collect(toList());
    return inputs;
  }
}
