package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageTeamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;

import com.codeborne.selenide.Condition;

public class TrainingValidator {

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
    teamPage().getUsersCounterCounter().shouldBe(visible);
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

    teamPage().getPopupTitleText().shouldBe(visible);
    teamPage().getPopupTitleText().shouldBe(matchText("Keep track of members’ training progress"));
    teamPage().getPopupText().shouldBe(visible);
    teamPage().getGotItButton().shouldBe(visible);
    teamPage().getGotItButton().shouldBe(exactText("Got it"));
    teamPage().getGotItButton().shouldBe(attribute("color", "default"));
    teamPage().getGotItButton().shouldBe(attribute("type", "submit"));

    teamPage().getSearchField().shouldBe(visible);
    teamPage().getSearchFieldIcon().shouldBe(visible);
    teamPage().getFiltersButton().shouldBe(visible);
    teamPage().getPaginationBackButton().shouldBe(visible);
    teamPage().getPaginationNextButton().shouldBe(visible);
    teamPage().getPaginationCounterText().shouldBe(hidden);

    teamPage().getTrainingPractisSetColumn().shouldBe(visible);
    teamPage().getTrainingPractisSetColumn().shouldBe(attribute("width", "16.5"));
    teamPage().getTrainingPractisSetColumn().shouldBe(exactText("Practis Sets"));
    teamPage().getTrainingOverdueColumn().shouldBe(visible);
    teamPage().getTrainingOverdueColumn().shouldBe(attribute("width", "8"));
    teamPage().getTrainingOverdueColumn().shouldBe(exactText("Overdue"));
    teamPage().getTrainingTeamMemberStatusColumns().shouldBe(visible);
    teamPage().getTrainingTeamMemberStatusColumns().shouldBe(attribute("width", "25"));
    teamPage().getTrainingTeamMemberStatusColumns().shouldBe(exactText("Team Member Status\n"
        + "Not Started\n"
        + "In Progress\n"
        + "Completed"));
    teamPage().getTrainingNotStartedColumn().shouldBe(visible);
    teamPage().getTrainingInProgressColumn().shouldBe(visible);
    teamPage().getTrainingCompletedColumn().shouldBe(visible);
    teamPage().getTrainingLastTrainingColumn().shouldBe(visible);
    teamPage().getTrainingLastTrainingColumn().shouldBe(attribute("width", "10.3"));
    teamPage().getTrainingLastTrainingColumn().shouldBe(exactText("Last Training"));

  }
}
