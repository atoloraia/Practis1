package com.practis.web.selenide.validator.company.team;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;

public class AssignUsersAndDueDatesValidator {

    /** Assert elements on 'Assign Users and Due Dates' module. */
    public static void assertAssignUsersAndDueDatesModule(final String practisSetName) {
        assignUsersAndDueDatesModule().getAssignUsersAndDueDatesTitle().shouldBe(visible);
        assignUsersAndDueDatesModule()
                .getAssignUsersAndDueDatesTitle()
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
        assignUsersAndDueDatesModule()
                .getAssignSelectedUsersButton()
                .shouldBe(exactText("Assign Selected Users"));
    }

    /** Assert Search field on 'Assign Users and Due Dates' module. */
    public static void assertSearchField(final String practisSetName) {
        assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(hidden);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharAssignPsModule(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        assignUsersAndDueDatesModule().getSearchField().append(String.valueOf(input));
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().get(0).shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().click();
    }

    /** Assert clean search on Team - Member - Assign Practis Set and Due Date Module. */
    public static void assertCleanSearchAssignPsModule(int pracrisSetRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldNotBe(visible);
        assignUsersAndDueDatesModule()
                .getUserRow()
                .shouldHave(CollectionCondition.size(pracrisSetRow));
        assignUsersAndDueDatesModule().getSearchField().append(("check clean icon"));
        assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
        assignUsersAndDueDatesModule().getUserRow().shouldHave(CollectionCondition.size(1));
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().click();
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldNotBe(visible);
        assignUsersAndDueDatesModule()
                .getUserRow()
                .shouldHave(CollectionCondition.size(pracrisSetRow));
    }

    /** Assert elements on 'Assign Users and Due Dates' module. */
    public static void assertEmptyAssignUsersAndDueDatesModule(final String practisSetName) {
        assignUsersAndDueDatesModule().getAssignUsersAndDueDatesTitle().shouldBe(visible);
        assignUsersAndDueDatesModule()
                .getAssignUsersAndDueDatesTitle()
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
        assignUsersAndDueDatesModule()
                .getAssignSelectedUsersButton()
                .shouldBe(exactText("Assign Selected Users"));
    }

    /** Assert elements on 'Assign Users and Due Dates' module. */
    public static void assertSearchFieldAssignUsersAndDueDatesModule() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        assignUsersAndDueDatesModule().getSearchField().shouldBe(attribute("type", "text"));
        assignUsersAndDueDatesModule().getSearchField().shouldBe(enabled);
        assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(hidden);
    }

    /** Assert elements on 'Assign Users and Due Dates' module. */
    public static void assertNoSearchResultAssignUsersAndDueDatesModule() {
        assignUsersAndDueDatesModule().getSearchField().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        assignUsersAndDueDatesModule().getSearchField().shouldBe(attribute("type", "text"));
        assignUsersAndDueDatesModule().getSearchField().shouldBe(enabled);
        assignUsersAndDueDatesModule().getSearchFieldIcon().shouldBe(visible);
        assignUsersAndDueDatesModule().getSearchFieldCrossButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getFiltersButton().shouldBe(visible);
        assignUsersAndDueDatesModule().getFiltersButton().shouldBe(disabled);

        assignUsersAndDueDatesModule().getNoUsersFoundIcon().shouldBe(visible);
        assignUsersAndDueDatesModule().getNoUsersFoundText().shouldBe(visible);
        assignUsersAndDueDatesModule().getNoUsersFoundText().shouldBe(exactText("No Users Found"));

        assignUsersAndDueDatesModule().getAssignSelectedUsersButton().shouldBe(visible);
        assignUsersAndDueDatesModule()
                .getAssignSelectedUsersButton()
                .shouldBe(exactText("Assign Selected Users"));
    }
}
