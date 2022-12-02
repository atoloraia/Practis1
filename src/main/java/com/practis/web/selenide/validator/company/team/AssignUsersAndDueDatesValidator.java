package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;

import com.codepine.api.testrail.TestRail.Users;

public class AssignUsersAndDueDatesValidator {

  /**
   * Assert elements on 'Assign Users and Due Dates' module.
   */
  public static void assertAssignUsersAndDueDatesModule(final String practisSetName) {
    assignUsersAndDueDatesModule().getAssignUsersAndDueDatesTitle().shouldBe(visible);
    assignUsersAndDueDatesModule().getAssignUsersAndDueDatesTitle()
        .shouldBe(exactText("Assign Users and Due Dates"));
    assignUsersAndDueDatesModule().getPractisSetSubtitle().shouldBe(visible);
    assignUsersAndDueDatesModule().getPractisSetSubtitle().shouldBe(exactText(practisSetName));

    assignUsersAndDueDatesModule().getUpdatedTimestampText().shouldBe(visible);
    assignUsersAndDueDatesModule().getUpdatedTimestampText().shouldBe(matchText("Updated"));
    assignUsersAndDueDatesModule().getUpdatedTimestampButton().shouldBe(visible);

    assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
    assignUsersAndDueDatesModule().getFiltersButton().shouldBe(visible);

    assignUsersAndDueDatesModule().getNoUserIcon().shouldBe(visible);
    assignUsersAndDueDatesModule().getNoUserText().shouldBe(visible);
    assignUsersAndDueDatesModule().getNoUserText().shouldBe(matchText("No Users Yet"));

    assignUsersAndDueDatesModule().getAssignSelectedUsersButton().shouldBe(visible);
    assignUsersAndDueDatesModule().getAssignSelectedUsersButton()
        .shouldBe(exactText("Assign Selected Users"));
  }

}
