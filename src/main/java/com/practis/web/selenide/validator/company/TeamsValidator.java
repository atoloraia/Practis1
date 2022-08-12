package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;

import com.codeborne.selenide.Condition;

public class TeamsValidator {

  /**
   * Assert elements on Create New Team page.
   */
  public static void assertElementsCreateNewTeam() {
    teamCreatePage().getCreateNewTeamTitle().shouldBe(visible);
    teamCreatePage().getCreateNewTeamTitle().shouldBe(Condition.exactText("Create New Team"));

    teamCreatePage().getNotCreatedYet().shouldBe(visible);
    teamCreatePage().getNotCreatedYet().shouldBe(Condition.exactText("Not Created Yet"));

    teamCreatePage().getCreateButton().shouldBe(visible);
    teamCreatePage().getCreateButton().shouldBe(exactText("Create"));

    teamCreatePage().getCancelButton().shouldBe(visible);
    teamCreatePage().getCancelButton().shouldBe(exactText("Cancel"));

    teamCreatePage().getTitleField().shouldBe(visible);
    teamCreatePage().getTitleField().shouldBe(attribute("placeholder", "Team Name"));

    teamCreatePage().getAssignLabelsButton().shouldBe(visible);
    teamCreatePage().getAssignLabelsButton().shouldBe(exactText("Assign Labels"));

    //All Users section
    teamCreatePage().getAllUserTitle().shouldBe(visible);
    teamCreatePage().getAllUserTitle().shouldBe(exactText("All Users"));

    teamCreatePage().getAllUsersUpdatedLabel().shouldBe(visible);
    teamCreatePage().getAllUsersUpdatedLabel().shouldBe(matchText("Updated"));
    teamCreatePage().getAllUsersUpdatedButton().shouldBe(visible);

    teamCreatePage().getAllUsersFilter().shouldBe(visible);
    teamCreatePage().getSearchField().get(0).shouldBe(visible);

    teamCreatePage().getUserCounter().shouldBe(visible);
    teamCreatePage().getUserCounter().shouldBe(matchText("Items"));

    //Columns of "All Users" table
    teamCreatePage().getSelectAllCheckboxUsersTable().shouldBe(visible);
    teamCreatePage().getUsersColumnUsersTable().shouldBe(visible);
    teamCreatePage().getUsersColumnUsersTable().shouldBe(exactText("Users"));
    teamCreatePage().getLastTrainingColumnUsersTable().shouldBe(visible);
    teamCreatePage().getLastTrainingColumnUsersTable().shouldBe(exactText("Last Training"));

    //teamCreatePage().getCheckboxUserRow().get(0).shouldBe(visible);
    teamCreatePage().getLabelsUserRow().get(0).shouldBe(visible);
    teamCreatePage().getAvatarUserRow().get(0).shouldBe(visible);
    teamCreatePage().getNameUserRow().get(0).shouldBe(visible);
    teamCreatePage().getAddSelectedUsersButton().shouldBe(visible);

    //Team Members section
    teamCreatePage().getTeamMemberTitle().shouldBe(visible);
    teamCreatePage().getTeamMemberTitle().shouldBe(exactText("Team Members"));
    teamCreatePage().getNoTeamLeaderTitle().shouldBe(visible);
    teamCreatePage().getNoTeamLeaderTitle().shouldBe(exactText("No Team Leaders"));

    teamCreatePage().getTeamMembersUpdatedLabel().shouldBe(visible);
    teamCreatePage().getTeamMembersUpdatedLabel().shouldBe(matchText("Updated"));
    teamCreatePage().getTeamMembersUpdatedButton().shouldBe(visible);

    teamCreatePage().getSearchField().get(1).shouldBe(visible);
    teamCreatePage().getTeamMembersFilter().shouldBe(visible);
    teamCreatePage().getTeamLeaderCounter().shouldBe(visible);
    teamCreatePage().getTeamLeaderCounter().shouldBe(matchText("Items"));

    teamCreatePage().getUserColumnMembersTable().shouldBe(visible);
    teamCreatePage().getUserColumnMembersTable().shouldBe(exactText("Users"));
    teamCreatePage().getTeamLeaderColumnMembersTable().shouldBe(visible);
    teamCreatePage().getTeamLeaderColumnMembersTable().shouldBe(exactText("Team Leader"));

    teamCreatePage().getNoTeamMembersIcon().shouldBe(visible);
    teamCreatePage().getAddMemberLabel().shouldBe(visible);
    teamCreatePage().getAddMemberLabel().shouldBe(exactText("Add Members"));

    teamCreatePage().getRemoveSelectedUsersButton().shouldBe(visible);
    teamCreatePage().getRemoveSelectedUsersButton().shouldBe(exactText("Remove Selected Users"));
  }
}
