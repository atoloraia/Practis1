package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;

import com.codeborne.selenide.Condition;

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

    manageTeamPage().getCheckboxUserRow().get(0).shouldBe(hidden);
    manageTeamPage().getCheckboxUserRow().get(0).shouldBe(attribute("size", "20"));
    manageTeamPage().getLabelsUserRow().get(0).shouldBe(visible);
    manageTeamPage().getAvatarUserRow().get(0).shouldBe(visible);
    manageTeamPage().getNameUserRow().get(0).shouldBe(visible);
    manageTeamPage().getLastTrainingUserRow().get(0).shouldBe(visible);

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


}
