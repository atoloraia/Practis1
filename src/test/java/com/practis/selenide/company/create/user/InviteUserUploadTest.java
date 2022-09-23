package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserDraftUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutEmail;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutFirstName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutLastName;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserGridRowWithoutRole;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUsers;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNotInvitedUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterSavingWithIssues;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUploadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
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

  @BeforeEach
  void init() {
    newItemSelector().create("User");
    usersToRemove = new ArrayList<>();
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
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    assertScreenAfterAddingRow();
    assertRequiredUserGridRow(input.get(0), "User", 0);
  }

  /**
   * Invite User to the App: Upload Template: Success upload: Save as Draft.
   */
  @TestRailTest(caseId = 11730)
  @DisplayName("InviteUserUploadTest: Upload Template: Success upload: Save as Draft")
  @GeneratedDraftNameExtension
  void uploadUsersSaveAsDraft(String draftName) throws FileNotFoundException {
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    //Save as Draft: Save
    userService().saveAsDraft(draftName);
    snackbar().getMessage().shouldBe(exactText("Invitation draft has been saved"));
    awaitElementNotExists(10, () -> snackbar().getMessage());

    //assert draft
    SelenideJsUtils.jsClick(inviteUsersPage().getOutsideTheForm());
    userService().openDraftUsersList();
    asserDraftUser(draftName, input.get(0), "User", 0);
  }

  /**
   * Invite User to the App: Upload Template: Success upload: Invite.
   */
  @TestRailTest(caseId = 11731)
  @DisplayName("InviteUserUploadTest: Upload Template: Success upload: Invite")
  @GeneratedDraftNameExtension
  void uploadUsersInvite(String draftName) throws FileNotFoundException {
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    //invite selected Users
    userService().inviteAllUser();

    //Check snackbar message
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 1 invitations. This might take a while."));

    //assert User 1
    assertInvitedUser(input.get(0));
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
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);
    input.get(0).setFirstName("");

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    assertScreenAfterAddingRow();

    asserGridRowWithoutFirstName(input.get(0), "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Last Name.
   */
  @TestRailTest(caseId = 1120)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Last Name")
  void uploadTemplateEmptyLastName() throws FileNotFoundException {
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);
    input.get(0).setLastName("");

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutLastName(input.get(0), "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Email.
   */
  @TestRailTest(caseId = 1121)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Email")
  void uploadTemplateEmptyEmail() throws FileNotFoundException {
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);
    input.get(0).setEmail("");

    //generate file and upload
    final var file = userService().getXml(input, "User");
    userService().uploadTemplate(file);

    assertScreenAfterAddingRow();
    asserGridRowWithoutEmail(input.get(0), "User");
  }

  /**
   * Invite User to the App: Upload Template: Empty Role.
   */
  @TestRailTest(caseId = 1122)
  @DisplayName("InviteUserUploadTest: Upload Template: Empty Role")
  void uploadTemplateEmptyRole() throws FileNotFoundException {
    //generate data for Users
    final var input = userService().generateUserData(1, usersToRemove);

    //generate file and upload
    final var file = userService().getXml(input, " ");
    userService().uploadTemplate(file);

    //assert
    assertScreenAfterAddingRow();
    asserGridRowWithoutRole(input.get(0), "User");
  }

  /**
   * Invite User to the App: Upload Template: User counter.
   */
  @TestRailTest(caseId = 1114)
  @DisplayName("InviteUserUploadTest: Upload Template: User counter")
  void uploadTemplateUserCounter() throws FileNotFoundException {
    //generate data for Users
    final var inputs = userService().generateUserData(2, usersToRemove);

    //generate file and upload
    final var file = userService().getXml(inputs, "Admin");
    userService().uploadTemplate(file);

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
    final var inputs = userService().generateUserData(3, usersToRemove);
    ;

    //generate file and upload
    final var file = userService().getXml(inputs, "User");
    userService().uploadTemplate(file);
    ;

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message
    snackbar().getMessage()
        .shouldBe(exactText("We’re sending 3 invitations. This might take a while."));

    //search and view invited users in 'Pending User' list, view User Profile
    assertInvitedUsers(inputs);
  }

  /**
   * Invite User to the App: Upload Template: Invite Not All users.
   */
  @Test
  @TestRailTest(caseId = 11673)
  @DisplayName("InviteUserUploadTest: Upload Template: Invite Not All users")
  void uploadInviteNotAllUsers() throws FileNotFoundException {
    //generate data for Users
    final var inputs = userService().generateUserData(3, usersToRemove);
    final var role = "User";

    //generate file and upload
    final var file = userService().getXml(inputs, role);
    userService().uploadTemplate(file);
    ;

    //select first user and click "Invite Selected Users" button
    userService().inviteFirstUser();

    //Check snackbar message "We’re sending 3 invitations. This might take a while."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenOneFromManyInvitation();
    userService().exitWithoutSaving();
    userService().openPendingUsersList();

    //search and view invited user in 'Pending User' list, view User Profile
    assertInvitedUser(inputs.get(0));
  }

  /**
   * Invite User to the App: Upload Template: Not All users successfully invited.
   */
  @Test
  @TestRailTest(caseId = 1117)
  @DisplayName("InviteUserUploadTest: Upload Template: Not All users successfully invited")
  void uploadNotAllSuccessfullyInvited() throws FileNotFoundException {
    //generate data for Users
    final var inputs = userService().generateUserData(2, usersToRemove);
    inputs.get(1).setFirstName("");
    inputs.get(1).setLastName("");
    final var role = "User";

    //generate file and upload
    final var file = userService().getXml(inputs, role);
    userService().uploadTemplate(file);

    //select all user and click "Invite Selected Users" button
    userService().inviteAllUser();

    //Check snackbar message "1 User has been invited."
    snackbar().getMessage()
        .shouldBe(exactText("1 User has been invited"));

    //assert screen after invitation
    awaitElementNotExists(10, () -> snackbar().getMessage());
    assertScreenAfterSavingWithIssues();
    userService().exitWithoutSaving();
    userService().openPendingUsersList();

    //search and view invited user in 'Pending User' list, view User Profile
    assertInvitedUser(inputs.get(0));

    //assert the second user hasn't been invited
    assertNotInvitedUser(inputs.get(1));
  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(email -> practisApi().revokeUser(email));
  }


}
