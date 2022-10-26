package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;

import com.codeborne.selenide.Condition;

public class TeamsValidator {

  /**
   * Assert elements on Create New Team page.
   */
  public static void assertElementsEmptyCreateNewTeam() {
    teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
    teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

    teamCreatePage().getTitleField().shouldBe(visible);
    teamCreatePage().getTitleField().shouldBe(attribute("maxlength", "50"));
    teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getCreateButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(disabled);
    teamCreatePage().getCancelButton().shouldBe(attribute("width", "126px"));
    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(exactText("Create"));
  }

  /**
   * Assert elements on Manage Team page.
   */
  public static void assertElementsEmptyManageTeam() {
    manageTeamPage().getCreateNewTeamTitle().shouldBe(visible);
    manageTeamPage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Manage Team"));

    manageTeamPage().getCloseButton().shouldBe(visible);
    manageTeamPage().getCloseButton().shouldBe(exactText("Close"));

    manageTeamPage().getTitleField().shouldBe(visible);;
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
   * Assert elements on Teams page.
   */
  public static void assertElementsTeamsPage() {
    teamPage().getTeamsTitle().shouldBe(visible);
    teamPage().getTeamsTitle().shouldBe(exactText("Teams"));
    teamPage().getTeamsTimestamp().shouldBe(visible);
    teamPage().getTeamsTimestampRefresh().shouldBe(visible);
    teamPage().getTeamsTimestamp().shouldBe(matchText("Updated"));
    teamPage().getBackButton().shouldBe(hidden);

    teamPage().getTeamSearchField().shouldBe(visible);
    teamPage().getTeamSearchFieldIcon().shouldBe(visible);
    teamPage().getTeamFilterButton().shouldBe(visible);
    teamPage().getTeamsItemsCounter().shouldBe(visible);
    teamPage().getTeamsItemsCounter().shouldBe(matchText("Items"));
    teamPage().getTeamsPrevButton().shouldBe(visible);
    teamPage().getTeamsNextButton().shouldBe(visible);
    teamPage().getTeamsPrevButton().shouldBe(disabled);
    teamPage().getTeamsNextButton().shouldBe(disabled);

    teamPage().getTeamsColumn().shouldBe(visible);
    teamPage().getTeamsColumn().shouldBe(exactText("Teams"));
    teamPage().getTeamsColumn().shouldBe(attribute("width", "23"));
    teamPage().getTeamMembersColumn().shouldBe(visible);
    teamPage().getTeamMembersColumn().shouldBe(exactText("Members"));
    teamPage().getTeamMembersColumn().shouldBe(attribute("width", "12"));
    teamPage().getTeamPractisSetsColumn().shouldBe(visible);
    teamPage().getTeamPractisSetsColumn().shouldBe(exactText("Practis Sets"));
    teamPage().getTeamPractisSetsColumn().shouldBe(attribute("width", "12"));
    teamPage().getTeamTeamLeadersColumn().shouldBe(visible);
    teamPage().getTeamTeamLeadersColumn().shouldBe(exactText("Team Leaders"));
    teamPage().getTeamTeamLeadersColumn().shouldBe(attribute("width", "22"));
    teamPage().getTeamsAllMembersItem().shouldBe(visible);
    teamPage().getTeamsAllMembersItem().shouldBe(matchText("All Members"));
    teamPage().getTeamsAllMembersStar().shouldBe(visible);
  }

}
