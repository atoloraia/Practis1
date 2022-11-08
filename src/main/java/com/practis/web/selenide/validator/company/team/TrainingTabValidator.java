package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.overdueModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.registrationStatus;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamMemberStatus;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;

public class TrainingTabValidator {

  /**
   * Assert elements on Empty Training page.
   */
  public static void assertElementsTrainingPage() {
    teamPage().getTeamTitle().shouldBe(visible);
    teamPage().getTeamTitle().shouldBe(exactText("Team"));
    teamPage().getTeamNameTitle().shouldBe(visible);
    teamPage().getPractisSetCounterIcon().shouldBe(visible);
    teamPage().getPractisSetCounter().shouldBe(visible);
    teamPage().getUsersCounterIcon().shouldBe(visible);
    teamPage().getUsersCounter().shouldBe(visible);
    teamPage().getTeamLeaderCounterIcon().shouldBe(visible);
    teamPage().getTeamLeaderCounter().shouldBe(visible);
    teamPage().getBackButton().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(visible);
    teamPage().getTrainingTab().shouldBe(exactText("Training"));
    teamPage().getMembersTab().shouldBe(visible);
    teamPage().getMembersTab().shouldBe(exactText("Members"));
    teamPage().getUpdateButton().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(visible);
    teamPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

    teamPage().getSearchField().shouldBe(visible);
    teamPage().getSearchFieldIcon().shouldBe(visible);
    teamPage().getFiltersButton().shouldBe(visible);
    teamPage().getPaginationBackButton().shouldBe(visible);
    teamPage().getPaginationNextButton().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(hidden);

    trainingTab().getTrainingPractisSetColumn().shouldBe(visible);
    trainingTab().getTrainingPractisSetColumn().shouldBe(attribute("width", "16.5"));
    trainingTab().getTrainingPractisSetColumn().shouldBe(exactText("Practis Sets"));
    trainingTab().getTrainingOverdueColumn().shouldBe(visible);
    trainingTab().getTrainingOverdueColumn().shouldBe(attribute("width", "8"));
    trainingTab().getTrainingOverdueColumn().shouldBe(exactText("Overdue"));
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(visible);
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(attribute("width", "25"));
    trainingTab().getTrainingTeamMemberStatusColumns().shouldBe(exactText("Team Member Status\n"
        + "Not Started\n"
        + "In Progress\n"
        + "Completed"));
    trainingTab().getTrainingNotStartedColumn().shouldBe(visible);
    trainingTab().getTrainingInProgressColumn().shouldBe(visible);
    trainingTab().getTrainingCompletedColumn().shouldBe(visible);
    trainingTab().getTrainingLastTrainingColumn().shouldBe(visible);
    trainingTab().getTrainingLastTrainingColumn().shouldBe(attribute("width", "10.3"));
    trainingTab().getTrainingLastTrainingColumn().shouldBe(exactText("Last Training"));

  }

  /**
   * Assert Filters in Members List.
   */
  public static void assertTrainingFiltersModal() {
    teamMemberStatus().getTeamMemberStatusTitle().shouldBe(visible);
    teamMemberStatus().getTeamMemberStatusTitle().shouldBe(exactText("Team Member Status"));
    teamMemberStatus().getNotStartedCheckbox().shouldBe(visible);
    teamMemberStatus().getNotStartedCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getNotStartedStatus().shouldBe(visible);
    teamMemberStatus().getNotStartedStatus().shouldBe(exactText("Not Started"));
    teamMemberStatus().getNotStartedStatus().shouldBe(attribute("color", "#212121"));
    teamMemberStatus().getInProgressCheckbox().shouldBe(visible);
    teamMemberStatus().getInProgressCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getInProgressStatus().shouldBe(visible);
    teamMemberStatus().getInProgressStatus().shouldBe(exactText("In Progress"));
    teamMemberStatus().getInProgressStatus().shouldBe(attribute("color", "#212121"));
    teamMemberStatus().getCompletedCheckbox().shouldBe(visible);
    teamMemberStatus().getCompletedCheckbox().shouldBe(attribute("size", "12"));
    teamMemberStatus().getCompletedStatus().shouldBe(visible);
    teamMemberStatus().getCompletedStatus().shouldBe(exactText("Completed"));
    teamMemberStatus().getCompletedStatus().shouldBe(attribute("color", "#212121"));

    overdueModule().getOverdueTitle().shouldBe(visible);
    overdueModule().getOverdueTitle().shouldBe(exactText("Overdue"));
    overdueModule().getOverdueStatus().shouldBe(visible);
    overdueModule().getOverdueStatus().shouldBe(attribute("color", "#212121"));
    overdueModule().getOverdueStatus().shouldBe(exactText("Overdue"));
    overdueModule().getOverdueCheckbox().shouldBe(visible);
    overdueModule().getOverdueCheckbox().shouldBe(attribute("size", "12"));
    overdueModule().getOverdueIcon().shouldBe(visible);
    overdueModule().getOverdueIcon().shouldBe(attribute("width", "16"));
    overdueModule().getOverdueIcon().shouldBe(attribute("height", "16"));

    membersTab().getClearFiltersButton().shouldBe(visible);
    membersTab().getClearFiltersButton().shouldBe(disabled);
    membersTab().getClearFiltersButton().shouldBe(exactText("Clear"));
    membersTab().getClearFiltersButton().shouldBe(attribute("type", "submit"));
    membersTab().getClearFiltersButton().shouldBe(attribute("width", "122px"));
    membersTab().getClearFiltersButton().shouldBe(attribute("color", "default"));

    membersTab().getApplyFiltersButton().shouldBe(visible);
    membersTab().getApplyFiltersButton().shouldBe(enabled);
    membersTab().getApplyFiltersButton().shouldBe(exactText("Apply Filter"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("type", "submit"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("width", "122px"));
    membersTab().getApplyFiltersButton().shouldBe(attribute("color", "default"));

    membersTab().getSelectionCounter().shouldBe(visible);
    membersTab().getSelectionCounter().shouldBe(exactText("0 Selected"));
  }
}
