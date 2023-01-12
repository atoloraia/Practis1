package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class AssignPsAndDueDatesValidator {

    /** Assert no search results. */
    public static void assertNoSearchResultOnAssignPractisSetModule() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getNoSearchResultText().shouldBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getNoSearchResultText()
                .shouldBe(matchText("No Practis Sets found"));
        assignPractisSetsAndDueDatesModule().getNoSearchResultIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemRow().shouldBe(CollectionCondition.size(0));
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnAssignPractisSetsModule() {
        assignPractisSetsAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemRow().shouldBe(CollectionCondition.size(1));
        assignPractisSetsAndDueDatesModule().getDueDatesTitle().shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignPsModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignPractisSetsAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
    }

    /** Assert clean search on Team - Member - Assign Practis Set and Due Date Module. */
    public static void assertCleanSearchAssignPsModule(int pracrisSetRow) {
        assignPractisSetsAndDueDatesModule().getSearchField().append(("check clean icon"));
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPractisSetsAndDueDatesModule().getItemRow().shouldHave(CollectionCondition.size(0));
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().click();
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().shouldNotBe(visible);
        assignPractisSetsAndDueDatesModule()
                .getItemRow()
                .shouldHave(CollectionCondition.size(pracrisSetRow));
    }

    /** Assert clean search on Team - Member - Assign Practis Set and Due Date Module. */
    public static void assertCleanSearchAssignUsersModule(int usersRow) {
        assignUsersAndDueDatesModule().getSearchFields().get(1).append(("check clean icon"));
        assignUsersAndDueDatesModule().getSearchFieldXButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(3));
    }
}
