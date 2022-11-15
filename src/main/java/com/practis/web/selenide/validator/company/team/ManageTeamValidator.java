package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.util.AwaitUtils.awaitElementVisible;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.practis.dto.NewUserInput;
import com.practis.web.util.AwaitUtils;

public class ManageTeamValidator {

  /**
   * Assert elements on Manage Team page.
   */
  public static void assertElementsEmptyManageTeam() {
    manageTeamPage().getCreateNewTeamTitle().shouldBe(visible);
    manageTeamPage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Manage Team"));

    manageTeamPage().getLastUpdatedTimestamp().shouldBe(visible);
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(matchText("Last Updated"));

    manageTeamPage().getCloseButton().shouldBe(visible);
    manageTeamPage().getCloseButton().shouldBe(exactText("Close"));

    manageTeamPage().getTitleField().shouldBe(visible);
    ;
    manageTeamPage().getTitleField().shouldBe(attribute("maxlength", "50"));

    manageTeamPage().getAssignLabelsButton().shouldBe(visible);
    manageTeamPage().getAssignLabelsButton().shouldBe(exactText("Assign Labels"));

    //"All Users" section
    manageTeamPage().getAllUserTitle().shouldBe(visible);
    manageTeamPage().getAllUserTitle().shouldBe(exactText("All Users"));

    manageTeamPage().getAllUsersUpdatedLabel().shouldBe(visible);
    manageTeamPage().getAllUsersUpdatedLabel().shouldBe(matchText("Updated"));
    manageTeamPage().getAllUsersUpdatedButton().shouldBe(visible);

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));

    manageTeamPage().getAllUsersFilter().shouldBe(visible);
    manageTeamPage().getUserCounter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(matchText("0 Items"));

    //Check columns of "All Users" table
    manageTeamPage().getSelectAllCheckboxUsersTable().shouldBe(visible);
    manageTeamPage().getUsersColumnUsersTable().shouldBe(visible);
    manageTeamPage().getUsersColumnUsersTable().shouldBe(exactText("Users"));
    manageTeamPage().getLastTrainingColumnUsersTable().shouldBe(visible);
    manageTeamPage().getLastTrainingColumnUsersTable().shouldBe(exactText("Last Training"));

    //manageTeamPage().getCheckboxUserRow().get(0).shouldBe(hidden);
    //manageTeamPage().getLabelsUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getAvatarUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getNameUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getLastTrainingUserRow().get(0).shouldBe(visible);

    //"Team Members" section
    manageTeamPage().getTeamMemberTitle().shouldBe(visible);
    manageTeamPage().getTeamMemberTitle().shouldBe(exactText("Team Members"));

    manageTeamPage().getTeamMembersUpdatedLabel().shouldBe(visible);
    manageTeamPage().getTeamMembersUpdatedLabel().shouldBe(matchText("Updated"));
    manageTeamPage().getTeamMembersUpdatedButton().shouldBe(visible);

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));

    manageTeamPage().getTeamMembersFilter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(matchText("0 Items"));

    //Check columns of "Team Members" table
    manageTeamPage().getSelectAllCheckboxMembersTable().shouldBe(visible);
    manageTeamPage().getUserColumnMembersTable().shouldBe(visible);
    manageTeamPage().getUserColumnMembersTable().shouldBe(exactText("Users"));
    manageTeamPage().getTeamLeaderColumnMembersTable().shouldBe(visible);
    manageTeamPage().getTeamLeaderColumnMembersTable().shouldBe(exactText("Team Leader"));

    manageTeamPage().getNoTeamMembersIcon().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(exactText("Add Members"));

    manageTeamPage().getAddSelectedUsersButton().shouldBe(visible);
    manageTeamPage().getAddSelectedUsersButton().shouldBe(exactText("Add Selected Users"));
    manageTeamPage().getRemoveSelectedUsersButton().shouldBe(visible);
    manageTeamPage().getRemoveSelectedUsersButton().shouldBe(exactText("Remove Selected Users"));
  }


  /**
   * Assert elements on Manage Team page.
   */
  public static void assertElementsManageTeamPage(final String name) {
    manageTeamPage().getCreateNewTeamTitle().shouldBe(visible);
    manageTeamPage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Manage Team"));

    manageTeamPage().getLastUpdatedTimestamp().shouldBe(visible);
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(matchText("Last Updated"));

    manageTeamPage().getCloseButton().shouldBe(visible);
    manageTeamPage().getCloseButton().shouldBe(exactText("Close"));

    manageTeamPage().getTitleField().shouldBe(visible);
    manageTeamPage().getTitleField().shouldBe(value(name));
    manageTeamPage().getTitleField().shouldBe(attribute("maxlength", "50"));

    manageTeamPage().getAssignLabelsButton().shouldBe(visible);
    manageTeamPage().getAssignLabelsButton().shouldBe(exactText("Assign Labels"));

    //"All Users" section
    manageTeamPage().getAllUserTitle().shouldBe(visible);
    manageTeamPage().getAllUserTitle().shouldBe(exactText("All Users"));

    manageTeamPage().getAllUsersUpdatedLabel().shouldBe(visible);
    manageTeamPage().getAllUsersUpdatedLabel().shouldBe(matchText("Updated"));
    manageTeamPage().getAllUsersUpdatedButton().shouldBe(visible);

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));

    manageTeamPage().getAllUsersFilter().shouldBe(visible);
    manageTeamPage().getUserCounter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(matchText("0 Items"));

    //Check columns of "All Users" table
    manageTeamPage().getSelectAllCheckboxUsersTable().shouldBe(visible);
    manageTeamPage().getUsersColumnUsersTable().shouldBe(visible);
    manageTeamPage().getUsersColumnUsersTable().shouldBe(exactText("Users"));
    manageTeamPage().getLastTrainingColumnUsersTable().shouldBe(visible);
    manageTeamPage().getLastTrainingColumnUsersTable().shouldBe(exactText("Last Training"));

    //manageTeamPage().getCheckboxUserRow().get(0).shouldBe(hidden);
    //manageTeamPage().getLabelsUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getAvatarUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getNameUserRow().get(0).shouldBe(visible);
    //manageTeamPage().getLastTrainingUserRow().get(0).shouldBe(visible);

    //"Team Members" section
    manageTeamPage().getTeamMemberTitle().shouldBe(visible);
    manageTeamPage().getTeamMemberTitle().shouldBe(exactText("Team Members"));

    manageTeamPage().getTeamMembersUpdatedLabel().shouldBe(visible);
    manageTeamPage().getTeamMembersUpdatedLabel().shouldBe(matchText("Updated"));
    manageTeamPage().getTeamMembersUpdatedButton().shouldBe(visible);

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));

    manageTeamPage().getTeamMembersFilter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(visible);
    manageTeamPage().getTeamLeaderCounter().shouldBe(matchText("0 Items"));

    //Check columns of "Team Members" table
    manageTeamPage().getSelectAllCheckboxMembersTable().shouldBe(visible);
    manageTeamPage().getUserColumnMembersTable().shouldBe(visible);
    manageTeamPage().getUserColumnMembersTable().shouldBe(exactText("Users"));
    manageTeamPage().getTeamLeaderColumnMembersTable().shouldBe(visible);
    manageTeamPage().getTeamLeaderColumnMembersTable().shouldBe(exactText("Team Leader"));

    manageTeamPage().getNoTeamMembersIcon().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(visible);
    manageTeamPage().getAddMemberLabel().shouldBe(exactText("Add Members"));

    manageTeamPage().getAddSelectedUsersButton().shouldBe(visible);
    manageTeamPage().getAddSelectedUsersButton().shouldBe(exactText("Add Selected Users"));
    manageTeamPage().getRemoveSelectedUsersButton().shouldBe(visible);
    manageTeamPage().getRemoveSelectedUsersButton().shouldBe(exactText("Remove Selected Users"));
  }

  /**
   * Assert "Cancel" and "Apply" buttons for Edit Team Name.
   */
  public static void assertEditTeamName() {
    manageTeamPage().getTitleCancelButton().shouldBe(visible);
    manageTeamPage().getTitleCancelButton().shouldBe(visible);
  }


  /**
   * Assert 'Saving Changes...' text.
   */
  public static void assertSavingChangesText() {
    manageTeamPage().getSavingChangesIcon().shouldBe(visible);
    manageTeamPage().getSavingChangesText().shouldBe(visible);
    manageTeamPage().getSavingChangesText().shouldBe(matchText("Saving Changes..."));
  }

  /**
   * Assert 'Changes Saved' text.
   */
  public static void assertChangesSavedText() {
    manageTeamPage().getSavedChangesIcon().shouldBe(visible);
    manageTeamPage().getSavedChangesText().shouldBe(visible);
    manageTeamPage().getSavedChangesText().shouldBe(matchText("Changes Saved"));
  }

  /**
   * Assert added user on "Team Members" section.
   */
  public static void assertQuantityOfAddedTeamMembers(int count) {
    manageTeamPage().getTeamMemberRow().shouldBe(CollectionCondition.size(count));
  }

  /**
   * Assert pending icon for pending user on "Team Members" section.
   */
  public static void assertPendingUserOnTeamMembers(final NewUserInput user) {
    final var userRow = manageTeamPage().getTeamMemberRow()
        .find(Condition.matchText(user.getFirstName()));
    final var pendingUser = userRow.$(".sc-edESPO.heEMwv").shouldBe(visible);
    pendingUser.hover();
    manageTeamPage().getPendingToolTip().shouldBe(visible);

  }

  /**
   * Assert pending icon for pending user on "All Users" section.
   */
  public static void assertPendingUserOnTeamUsers(final NewUserInput user) {
    final var userRow = manageTeamPage().getUserRow()
        .find(Condition.matchText(user.getFirstName()));
    final var pendingUser = userRow.$(".sc-edESPO.heEMwv").shouldBe(visible);
    pendingUser.hover();
    manageTeamPage().getPendingToolTip().shouldBe(visible);
  }


  /**
   * Assert All Members Manage Team screen.
   */
  public static void assertAllMembersEmptyManageTeamScreen() {
    manageTeamPage().getCreateNewTeamTitle().shouldBe(visible);
    manageTeamPage().getCreateNewTeamTitle().shouldBe(exactText("Manage Team"));
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(visible);
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(matchText("Last Updated"));
    manageTeamPage().getCloseButton().shouldBe(visible);
    manageTeamPage().getCloseButton().shouldBe(exactText("Close"));
    manageTeamPage().getCloseButton().shouldBe(attribute("type", "submit"));
    manageTeamPage().getCloseButton().shouldBe(attribute("color", "default"));
    manageTeamPage().getCloseButton().shouldBe(attribute("width", "128px"));

    manageTeamPage().getAllMembersText().shouldBe(visible);
    manageTeamPage().getAllMembersText().shouldBe(exactText("All Members"));
    manageTeamPage().getTeamLeadersCount().shouldBe(visible);
    manageTeamPage().getTeamLeadersCount().shouldBe(exactText("No Team Leaders"));

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(disabled);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));
    manageTeamPage().getManageTeamFilter().get(0).shouldBe(visible);
    manageTeamPage().getManageTeamItemsCounter().shouldBe(hidden);

    manageTeamPage().getUserColumnAllMembers().shouldBe(visible);
    manageTeamPage().getUserColumnAllMembers().shouldHave(attribute("disabled"));
    manageTeamPage().getUserColumnAllMembers().shouldBe(exactText("Users"));
    manageTeamPage().getTeamLeaderColumnAllMembers().shouldBe(visible);
    manageTeamPage().getTeamLeaderColumnAllMembers().shouldHave(attribute("disabled"));
    manageTeamPage().getTeamLeaderColumnAllMembers().shouldBe(exactText("Team Leader"));

    manageTeamPage().getCloseButton().click();
    membersTab().getMembersManageTeamButton().shouldBe(visible);
  }

  /**
   * Assert Manage Team screen (not All Members).
   */
  public static void assertAllMembersManageTeamScreen() {
    manageTeamPage().getCreateNewTeamTitle().shouldBe(visible);
    manageTeamPage().getCreateNewTeamTitle().shouldBe(exactText("Manage Team"));
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(visible);
    manageTeamPage().getLastUpdatedTimestamp().shouldBe(matchText("Last Updated"));
    manageTeamPage().getCloseButton().shouldBe(visible);
    manageTeamPage().getCloseButton().shouldBe(exactText("Close"));
    manageTeamPage().getCloseButton().shouldBe(attribute("type", "submit"));
    manageTeamPage().getCloseButton().shouldBe(attribute("color", "default"));
    manageTeamPage().getCloseButton().shouldBe(attribute("width", "128px"));

    manageTeamPage().getAllMembersText().shouldBe(visible);
    manageTeamPage().getAllMembersText().shouldBe(exactText("All Members"));
    manageTeamPage().getTeamLeadersCount().shouldBe(visible);
    manageTeamPage().getTeamLeadersCount().shouldBe(exactText("No Team Leaders"));

    manageTeamPage().getSearchField().get(0).shouldBe(visible);
    manageTeamPage().getSearchField().get(0).shouldBe(enabled);
    manageTeamPage().getSearchField().get(0).shouldBe(attribute("font-size", "13px"));
    manageTeamPage().getManageTeamFilter().get(0).shouldBe(visible);
    manageTeamPage().getManageTeamItemsCounter().shouldBe(visible);
    manageTeamPage().getManageTeamItemsCounter().shouldBe(exactText("2 items"));

    manageTeamPage().getNoAllMembersIcon().get(1).shouldBe(hidden);
    manageTeamPage().getAddAllMembersLabel().get(1).shouldBe(hidden);

    manageTeamPage().getTableColumns().get(8).shouldBe(visible);
    manageTeamPage().getTableColumns().get(8).shouldBe(exactText("Users"));
    manageTeamPage().getTableColumns().get(9).shouldBe(visible);
    manageTeamPage().getTableColumns().get(9).shouldBe(exactText("Team Leader"));
    manageTeamPage().getTableRow().get(0).shouldBe(visible);
    manageTeamPage().getTableRow().get(1).shouldBe(visible);

    manageTeamPage().getCloseButton().click();
    membersTab().getMembersManageTeamButton().shouldBe(visible);
  }


}
