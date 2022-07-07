package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersToTheAppPage;

import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;

public class UserValidator {

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnInviteUsersPage() {
    inviteUsersToTheAppPage().getInviteUsersToTheAppTitle().shouldBe(visible);
    inviteUsersToTheAppPage().getInviteUsersToTheAppTitle()
        .shouldBe(exactText("Invite Users to the App"));

    inviteUsersToTheAppPage().getSearchField().shouldBe(visible);
    inviteUsersToTheAppPage().getFiltersButton().shouldBe(visible);
    inviteUsersToTheAppPage().getDownloadTemplateButton().shouldBe(visible);
    inviteUsersToTheAppPage().getUploadTemplateButton().shouldBe(visible);

    //Table header
    inviteUsersToTheAppPage().getFirstNameColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getFirstNameColumn().shouldBe(exactText("First Name"));
    inviteUsersToTheAppPage().getLastNameColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getLastNameColumn().shouldBe(exactText("Last Name"));
    inviteUsersToTheAppPage().getUserEmailColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getUserEmailColumn().shouldBe(exactText("User Email"));
    inviteUsersToTheAppPage().getRoleColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getRoleColumn().shouldBe(exactText("Role"));
    inviteUsersToTheAppPage().getTeamsColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getTeamsColumn().shouldBe(exactText("Teams"));
    inviteUsersToTheAppPage().getPractisSetColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
    inviteUsersToTheAppPage().getLabelsColumn().shouldBe(visible);
    inviteUsersToTheAppPage().getLabelsColumn().shouldBe(exactText("Labels"));

    //User row
    inviteUsersToTheAppPage().getFirstNameField().sibling(0).shouldBe(visible);
    inviteUsersToTheAppPage().getFirstNameField().sibling(0).shouldBe(exactText("First Name*"));
    inviteUsersToTheAppPage().getLastNameField().sibling(0).shouldBe(visible);
    inviteUsersToTheAppPage().getLastNameField().sibling(0).shouldBe(exactText("Last Name*"));
    inviteUsersToTheAppPage().getEmailField().sibling(0).shouldBe(visible);
    inviteUsersToTheAppPage().getEmailField().sibling(0).shouldBe(exactText("Email*"));
    inviteUsersToTheAppPage().getRoleField().shouldBe(visible);
    inviteUsersToTheAppPage().getRoleField().shouldBe(exactText("Role*"));
    inviteUsersToTheAppPage().getTeamsField().shouldBe(visible);
    inviteUsersToTheAppPage().getTeamsField().shouldBe(exactText("Teams"));
    inviteUsersToTheAppPage().getPractisSetsField().shouldBe(visible);
    inviteUsersToTheAppPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
    inviteUsersToTheAppPage().getLabelsField().shouldBe(visible);
    inviteUsersToTheAppPage().getLabelsField().shouldBe(exactText("Labels"));

    inviteUsersToTheAppPage().getAddUsersText().shouldBe(visible);
    inviteUsersToTheAppPage().getAddUsersText()
        .shouldBe(exactText("Add users to the table in order to edit or invite them"));
    inviteUsersToTheAppPage().getNotSavedText().shouldBe(visible);
    inviteUsersToTheAppPage().getNotSavedText().shouldBe(exactText("Not Saved"));

    inviteUsersToTheAppPage().getSaveAsDraftButton().shouldBe(visible);
    inviteUsersToTheAppPage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
    inviteUsersToTheAppPage().getInviteSelectedUsersButton().shouldBe(visible);
    inviteUsersToTheAppPage().getInviteSelectedUsersButton()
        .shouldBe(exactText("Invite Selected Users"));
  }

  /**
   * Assert added User row with input data.
   */
  public static void assertUserGridRow(final NewUserInput inputData,
      final String role, final String label, final String team) {
    inviteUsersToTheAppPage().getCheckboxAddedUserRow().get(0).sibling(0).shouldBe(visible);

    final var addedUserRow = inviteUsersToTheAppPage().getAddedUserRow().get(0);
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 1)
        .shouldBe(matchText(inputData.getFirstName()));
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 2)
        .shouldBe(matchText(inputData.getLastName()));
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 3)
        .shouldBe(matchText(inputData.getEmail()));
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 4).shouldBe(matchText(role));
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 5).shouldBe(matchText("1 Team"));
    inviteUsersToTheAppPage().getAddedUserCell(addedUserRow, 7).shouldBe(matchText("1 Label"));
  }

  /**
   * Assert there is no prompt "Add users to the table in order to edit or invite them".
   */
  public static void assertNoPrompt() {
    inviteUsersToTheAppPage().getAddUsersText().shouldNotBe(visible);
  }
}
