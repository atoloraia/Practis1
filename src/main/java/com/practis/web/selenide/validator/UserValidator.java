package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserLabelModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserRoleModel;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserTeamModal;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.UserProfilePage;

public class UserValidator {

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnInviteUsersPage() {
    inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersPage().getInviteUsersToTheAppTitle()
        .shouldBe(exactText("Invite Users to the App"));

    inviteUsersPage().getSearchField().shouldBe(visible);
    inviteUsersPage().getFiltersButton().shouldBe(visible);
    inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
    inviteUsersPage().getUploadTemplateButton().shouldBe(visible);

    //Table header
    inviteUsersPage().getFirstNameColumn().shouldBe(visible);
    inviteUsersPage().getFirstNameColumn().shouldBe(exactText("First Name"));
    inviteUsersPage().getLastNameColumn().shouldBe(visible);
    inviteUsersPage().getLastNameColumn().shouldBe(exactText("Last Name"));
    inviteUsersPage().getUserEmailColumn().shouldBe(visible);
    inviteUsersPage().getUserEmailColumn().shouldBe(exactText("User Email"));
    inviteUsersPage().getRoleColumn().shouldBe(visible);
    inviteUsersPage().getRoleColumn().shouldBe(exactText("Role"));
    inviteUsersPage().getTeamsColumn().shouldBe(visible);
    inviteUsersPage().getTeamsColumn().shouldBe(exactText("Teams"));
    inviteUsersPage().getPractisSetColumn().shouldBe(visible);
    inviteUsersPage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getLabelsColumn().shouldBe(visible);
    inviteUsersPage().getLabelsColumn().shouldBe(exactText("Labels"));

    //User row
    inviteUsersPage().getFirstNameField().sibling(0).shouldBe(visible);
    inviteUsersPage().getFirstNameField().sibling(0).shouldBe(exactText("First Name*"));
    inviteUsersPage().getLastNameField().sibling(0).shouldBe(visible);
    inviteUsersPage().getLastNameField().sibling(0).shouldBe(exactText("Last Name*"));
    inviteUsersPage().getEmailField().sibling(0).shouldBe(visible);
    inviteUsersPage().getEmailField().sibling(0).shouldBe(exactText("Email*"));
    //Role modal
    inviteUsersPage().getRoleField().shouldBe(visible);
    inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
    inviteUsersPage().getRoleField().click();
    inviteUserRoleModel().getUserRoleRadioButton().shouldBe(visible);
    inviteUserRoleModel().getUserRoleRadioButton().shouldBe(exactText("User"));
    inviteUserRoleModel().getAdminRoleRadioButton().shouldBe(visible);
    inviteUserRoleModel().getAdminRoleRadioButton().shouldBe(exactText("Admin"));
    //Teams Modal
    inviteUsersPage().getTeamsField().shouldBe(visible);
    inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
    inviteUsersPage().getTeamsField().click();
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    inviteUserTeamModal().getSearchField().shouldBe(visible);
    inviteUserTeamModal().getNoSelectedText().shouldBe(visible);
    inviteUserTeamModal().getNoSelectedText().shouldBe(exactText("No Teams selected"));
    inviteUserTeamModal().getSelectedAllButton().shouldBe(visible);
    inviteUserTeamModal().getSelectedAllButton().shouldBe(exactText("Select All"));
    inviteUserTeamModal().getAllMembersTeam().shouldBe(visible);
    inviteUserTeamModal().getApplyButton().shouldBe(visible);
    inviteUserTeamModal().getCancelButton().shouldBe(visible);
    inviteUserTeamModal().getCancelButton().click();
    //Practis Set modal
    inviteUsersPage().getPractisSetsField().shouldBe(visible);
    inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getPractisSetsField().click();
    inviteUserPsModel().getSearchField().shouldBe(visible);
    inviteUserPsModel().getNoSelectedText().shouldBe(visible);
    inviteUserPsModel().getNoSelectedText().shouldBe(exactText("No Practis Sets selected"));
    inviteUserPsModel().getSelectedAllButton().shouldBe(visible);
    inviteUserPsModel().getSelectedAllButton().shouldBe(exactText("Select All"));
    inviteUserPsModel().getDueDatesColumnTitle().shouldBe(visible);
    inviteUserPsModel().getDueDatesColumnTitle().shouldBe(exactText("Due Dates"));
    inviteUserPsModel().getCancelButton().shouldBe(visible);
    inviteUserPsModel().getApplyButton().shouldBe(visible);
    inviteUserPsModel().getCancelButton().click();
    //Label Modal
    inviteUsersPage().getLabelsField().shouldBe(visible);
    inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
    inviteUsersPage().getLabelsField().click();
    inviteUserLabelModel().getSearchField().shouldBe(visible);
    inviteUserLabelModel().getNoSelectedText().shouldBe(visible);
    inviteUserLabelModel().getNoSelectedText().shouldBe(exactText("No Labels selected"));
    inviteUserLabelModel().getSelectedAllButton().shouldBe(visible);
    inviteUserLabelModel().getSelectedAllButton().shouldBe(exactText("Select All"));
    inviteUserLabelModel().getCancelButton().shouldBe(visible);
    inviteUserLabelModel().getApplyButton().shouldBe(visible);

    inviteUsersPage().getAddRowButton().shouldBe(visible);
    inviteUsersPage().getAddRowButton().shouldBe(disabled);
    inviteUsersPage().getAddUsersText().shouldBe(visible);
    inviteUsersPage().getAddUsersText()
        .shouldBe(exactText("Add users to the table in order to edit or invite them"));
    inviteUsersPage().getNotSavedText().shouldBe(visible);
    inviteUsersPage().getNotSavedText().shouldBe(exactText("Not Saved"));

    inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(disabled);
    inviteUsersPage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
    inviteUsersPage().getInviteSelectedUsersButton()
        .shouldBe(exactText("Invite Selected Users"));
  }

  /**
   * Assert added User row with input data.
   */
  public static void assertUserGridRow(final NewUserInput inputData,
      final String role, final String label, final String team) {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).sibling(0).shouldBe(visible);

    final var addedUserRow = inviteUsersPage().getAddedUserRow().get(0);
    inviteUsersPage().getAddedUserCell(addedUserRow, 1)
        .shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 2)
        .shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 3)
        .shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 4).shouldBe(matchText(role));
    inviteUsersPage().getAddedUserCell(addedUserRow, 5).shouldBe(matchText("1 Team"));
    inviteUsersPage().getAddedUserCell(addedUserRow, 7).shouldBe(matchText("1 Label"));
  }

  /**
   * Assert added User row with required input data.
   */
  public static void assertRequiredUserGridRow(final NewUserInput inputData,
      final String role) {
    inviteUsersPage().getCheckboxAddedUserRow().get(0).sibling(0).shouldBe(visible);

    final var addedUserRow = inviteUsersPage().getAddedUserRow().get(0);
    inviteUsersPage().getAddedUserCell(addedUserRow, 1)
        .shouldBe(matchText(inputData.getFirstName()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 2)
        .shouldBe(matchText(inputData.getLastName()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 3)
        .shouldBe(matchText(inputData.getEmail()));
    inviteUsersPage().getAddedUserCell(addedUserRow, 4).shouldBe(matchText(role));
  }

  /**
   * Assert there is no prompt "Add users to the table in order to edit or invite them".
   */
  public static void assertNoPrompt() {
    inviteUsersPage().getAddUsersText().shouldNotBe(visible);
  }

  /**
   * Assert no teams in the Teams dropdown.
   */
  public static void assertEmptyTeamsfield() {
    inviteUsersPage().getTeamsField().click();
    inviteUserTeamModal().getAllMembersTeam().shouldBe(visible);
    inviteUserTeamModal().getTeamRows().shouldBe(CollectionCondition.size(1));
  }

  /**
   * Assert the top empty row.
   */
  public static void assertEmptyTopRow() {
    inviteUsersPage().getFirstNameField().shouldBe(empty);
    inviteUsersPage().getLastNameField().shouldBe(empty);
    inviteUsersPage().getEmailField().shouldBe(empty);
    inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
    inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
    inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
    inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
  }

  /**
   * Get email validation message.
   */
  public static SelenideElement getEmailValidationMessage() {
    return inviteUsersPage().getEmailValidationError();
  }

  /**
   * Assert empty state.
   */
  public static void assertEmptyState() {
    inviteUsersPage().getAddUsersText().shouldBe(visible);
    inviteUsersPage().getAddUsersText()
        .shouldBe(exactText("Add users to the table in order to edit or invite them"));
  }

  /**
   * Assert grid row with input data.
   */
  public static void assertUserGridRowPending(final NewUserInput inputData,
      final GridRow gridRow) {
    gridRow.get("Users")
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
    //TODO assert role, Invited By, Invited on, Labels
  }

  /**
   * Assert data on 'User Profile' page with input.
   */
  public static void asserUserData(final NewUserInput inputData,
      final UserProfilePage userProfilePage) {
    userProfilePage.getUserName()
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    userProfilePage.getUserEmail().shouldBe(matchText(inputData.getEmail()));
    userProfilePage.getPendingRegistrationLabel().shouldBe(visible);
    userProfilePage.getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));
  }
}
