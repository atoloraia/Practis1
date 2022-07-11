package com.practis.selenide.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.user;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.UserValidator.asserUserData;
import static com.practis.web.selenide.validator.UserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.UserValidator.assertNoPrompt;
import static com.practis.web.selenide.validator.UserValidator.assertUserGridRow;
import static com.practis.web.selenide.validator.UserValidator.assertUserGridRowPending;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

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
import java.util.ArrayList;
import java.util.List;
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
    inputData.setEmail(String.format(inputData.getEmail(), timestamp()));
    inputData.setFirstName(String.format(inputData.getFirstName(), timestamp()));

    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }

  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }

  @Test
  @TestRailTest(caseId = 8735)
  @DisplayName("Invite User: User Role")
  @LabelExtension
  @TeamExtension
  void inviteUser(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    user().userRoleFillRow(inputData, label.getName(), team.getName());
    user().addRow();

    //assert User row
    assertUserGridRow(inputData, "User", label.getName(), team.getName());
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    user().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = user().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    asserUserData(inputData, userProfilePage());

  }

  @Test
  @TestRailTest(caseId = 8736)
  @DisplayName("Invite User: Admin Role")
  @LabelExtension
  @TeamExtension
  void inviteAdmin(final RestCreateLabelResponse label, final RestTeamResponse team) {

    Selenide.refresh();
    user().adminRoleFillRow(inputData, label.getName(), team.getName());
    user().addRow();

    //assert User row
    assertUserGridRow(inputData, "Admin", label.getName(), team.getName());
    assertNoPrompt();

    //select user and click "Invite Selected Users" button
    user().clickInviteSelectedUserButton();

    //Check snackbar message "All Users have been invited"
    snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

    //assert grid row data
    final var userGridRow = user().searchUser(inputData.getEmail());
    assertUserGridRowPending(inputData, userGridRow);

    //assert data on 'User Settings' page
    awaitElementNotExists(10, () -> snackbar().getMessage());
    userGridRow.click();
    asserUserData(inputData, userProfilePage());

  }

  @AfterEach
  void cleanup() {
    usersToRemove.forEach(name -> practisApi().deleteUser(name));
  }
}
