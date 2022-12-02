package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
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

  /**
   * Assert Search field on 'Assign Users and Due Dates' module.
   */
  public static void assertSearchField(final String practisSetName) {
    assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(hidden);
  }

  /**
   * Assert no search results.
   */
  public static void assertNoSearchResultOnAssignPractisSetModule() {
    await().pollDelay(FIVE_SECONDS).until(() -> true);
    assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(visible);
    assignUsersAndDueDatesModule().getNoUserFoundText().shouldBe(visible);
    assignUsersAndDueDatesModule().getNoUserText().shouldBe(matchText("No Practis Sets found"));
    assignUsersAndDueDatesModule().getItemsCounterText().shouldBe(visible);
    assignUsersAndDueDatesModule().getItemsCounterText().shouldBe(exactText("0 Items"));
    assignUsersAndDueDatesModule().getUserRow().shouldBe(CollectionCondition.size(0));
  }

  /**
   * Assert Search Results.
   */
  public static void assertSearchResultsOnAssignPractisSetsModule() {
    assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
    assignUsersAndDueDatesModule().getUserRow().get(0).shouldBe(visible);
    assignUsersAndDueDatesModule().getItemsCounterText().shouldBe(exactText("1-1 of 1 Items"));
    assignUsersAndDueDatesModule().getDueDateColumn().get(0).shouldBe(visible);
    assignUsersAndDueDatesModule().getColumns().get(0).shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().click();
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(hidden);
  }

  /**
   * Assert Search should be performed after entering 1 characters.
   */
  public static void assertSearchAfter1CharAssignPSModule(final String searchString) {
    final var input = searchString.charAt(searchString.length() - 1);
    assignUsersAndDueDatesModule().getSearchField().append(String.valueOf(input));
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(visible);
    assignUsersAndDueDatesModule().getUserRow().get(0).shouldBe(visible);
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().click();
  }

  /**
   * Assert clean search on Team - Member - Assign Practis Set and Due Date Module.
   */

  public static void assertCleanSearchAssignPSModule(int pracrisSetRow) {
    await().pollDelay(TWO_SECONDS).until(() -> true);
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldNotBe(visible);
    assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(pracrisSetRow));
    assignUsersAndDueDatesModule().getSearchField().append(("check clean icon"));
    assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
    assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(1));
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().click();
    assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldNotBe(visible);
    assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(pracrisSetRow));
  }
}