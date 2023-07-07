package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPSAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class AssignPsAndDueDatesValidator {

    /** Assert no search results. */
    public static void assertNoSearchResultOnAssignPractisSetModule() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assignPSAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPSAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getNoSearchResultText().shouldBe(visible);
        assignPSAndDueDatesModule()
                .getNoSearchResultText()
                .shouldBe(matchText("No Practis Sets found"));
        assignPSAndDueDatesModule().getNoSearchResultIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getItemRow().shouldBe(CollectionCondition.size(0));
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnAssignPractisSetsModule() {
        assignPSAndDueDatesModule().getSearchField().shouldBe(visible);
        assignPSAndDueDatesModule().getItemRow().shouldBe(CollectionCondition.size(1));
        assignPSAndDueDatesModule().getDueDatesTitle().shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignPsModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignPSAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignPSAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getItemRow().get(0).shouldBe(visible);
    }

    /** Assert clean search on Team - Member - Assign Practis Set and Due Date Module. */
    public static void assertCleanSearchAssignPsModule(int pracrisSetRow) {
        assignPSAndDueDatesModule().getSearchField().append(("check clean icon"));
        assignPSAndDueDatesModule().getSearchClearIcon().shouldBe(visible);
        assignPSAndDueDatesModule().getItemRow().shouldHave(CollectionCondition.size(0));
        assignPSAndDueDatesModule().getSearchClearIcon().click();
        assignPSAndDueDatesModule().getSearchClearIcon().shouldNotBe(visible);
        assignPSAndDueDatesModule()
                .getItemRow()
                .shouldHave(CollectionCondition.size(pracrisSetRow));
    }

    /** Assert clean search on Team - Member - Assign Practis Set and Due Date Module. */
    public static void assertCleanSearchAssignUsersModule(int usersRow) {
        assignUsersAndDueDatesModule().getSearchField().append(("check clean icon"));
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(2));
    }
}
