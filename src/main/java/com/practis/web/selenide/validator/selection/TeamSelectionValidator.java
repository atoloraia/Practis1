package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import java.util.stream.IntStream;

public class TeamSelectionValidator {

    /** Assert search on Teams model. */
    public static void assertSearchElementsOnTeamsModal() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        teamModule().getSearchField().shouldBe(visible);
        teamModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        teamModule().getSearchField().shouldBe(enabled);
        teamModule().getSearchField().shouldBe(attribute("type", "text"));
        teamModule().getSearchFieldIcon().shouldBe(visible);
        teamModule().getCleanSearchIcon().shouldNotBe(visible);
    }

    /** Assert no search results. */
    public static void assertNoTeamSearchResult() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        teamModule().getNoSearchResultText().shouldBe(visible);
        teamModule().getNoSearchResultImage().shouldBe(visible);
        teamModule().getSelectedText().shouldBe(visible);
        // TODO clarify Selected/Unselected state
        // teamModule().getSelectedAllButton().shouldBe(visible);
        teamModule().getTeamRows().shouldBe(CollectionCondition.size(0));
    }

    /** Assert search results. */
    public static void assertTeamSearchResult(final String team) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModuleService().findTeamCheckbox(team).shouldBe(visible);
        teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertTeamSearchAfter1Char(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        teamModule().getSearchField().append(String.valueOf(input));
        teamModule().getCleanSearchIcon().shouldBe(Condition.visible);
        teamModule().getTeamRows().get(0).shouldBe(visible);
    }

    /** Assert Search should be performed after entering 3 characters. */
    public static void assertStartSearchingAfter3Char(final String searchString) {
        final var initSize = teamModule().getTeamRows().size();
        IntStream.range(0, 2)
                .forEach(
                        idx -> {
                            final var input = searchString.charAt(searchString.length() - 1 - idx);
                            teamModule().getSearchField().append(String.valueOf(input));
                            teamModule().getCleanSearchIcon().shouldBe(Condition.visible);
                            teamModule()
                                    .getTeamRows()
                                    .shouldHave(CollectionCondition.size(initSize));
                            assertNoTeamSearchResult();
                        });
        final var input = searchString.charAt(searchString.length() - 4);
        teamModule().getSearchField().append(String.valueOf(input));
    }

    /** Assert clean search on Teams model. */
    public static void assertCleanTeamSearch(int teamRows) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModule().getCleanSearchIcon().shouldNotBe(visible);
        teamModule().getTeamRows().shouldHave(CollectionCondition.size(teamRows));
        teamModule().getSearchField().append("check clean icon");
        teamModule().getCleanSearchIcon().shouldBe(visible);
        teamModule().getTeamRows().shouldHave(CollectionCondition.size(0));
        teamModule().getCleanSearchIcon().click();
        teamModule().getCleanSearchIcon().shouldNotBe(visible);
        teamModule().getTeamRows().shouldHave(CollectionCondition.size(teamRows));
    }

    /** Assert empty Team state. */
    public static void assertEmptyTeam() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertSearchElementsOnTeamsModal();

        teamModule().getSelectedText().shouldBe(visible);
        teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
        teamModule().getSelectedAllButton().shouldBe(visible);
        teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Teams model: "Feed" page: Filter. */
    public static void assertFilterEmptyTeam() {
        assertEmptyTeam();
        teamModule().getTeamName().get(0).shouldBe(visible);
        teamModule().getTeamName().get(0).shouldBe(exactText("All Members"));
    }

    /** Assert Teams model: Invite User to the App: Assign . */
    public static void assertAssignEmptyTeam() {
        assertEmptyTeam();
        teamModule().getAllTeamName().shouldBe(visible);
        teamModule().getAllTeamName().shouldBe(exactText("All Members"));
    }

    /** Assert Select All button. */
    public static void assertSelectAllTeamButton() {
        teamModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        teamModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Unselect All button. */
    public static void assertUnSelectAllTeamButton() {
        teamModule().getUnSelectedAllButton().shouldBe(exactText("Unselect All"));
        teamModule().getUnSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Select All. */
    public static void assertSelectedAllStateTeam() {
        teamModule()
                .getTeamCheckbox()
                .shouldBe(
                        CollectionCondition.allMatch(
                                "checked",
                                element -> Selenide.$(element).has(attribute("checked"))));
        teamModule().getSelectedText().shouldBe(matchText(" selected"));
        assertUnSelectAllTeamButton();
    }

    /** Assert Unselect All. */
    public static void assertUnSelectedAllStateTeam() {
        teamModule()
                .getTeamCheckbox()
                .should(
                        CollectionCondition.allMatch(
                                "checked",
                                element -> !Selenide.$(element).has(attribute("checked"))));

        teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
        assertSelectAllTeamButton();
    }

    /** Assert Unselect All. */
    public static void assertUnSelectedAllStateTeamWithAllMembers() {
        teamModule()
                .getTeamCheckbox()
                .should(
                        CollectionCondition.allMatch(
                                "checked",
                                element -> !Selenide.$(element).has(attribute("checked"))));

        teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
        assertSelectAllTeamButton();
    }

    /** Assert the Team is selected. */
    public static void assertSelectedTeam(final String team) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModuleService().findTeamCheckbox(team).shouldBe(visible);
        teamModuleService().findSelectedTeamCheckbox(team).shouldBe(enabled);
    }

    /** Assert the Team is unselected. */
    public static void assertUnselectedTeam(final String team) {
        teamModuleService().findTeamCheckbox(team).shouldNotHave(attribute("checked"));
    }

    /** Assert created team. */
    public static void assertOneTeam(final String team) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModule().getTeamName().get(0).shouldBe(visible);
        teamModuleService().findTeamCheckbox(team).shouldBe(visible);
        teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert the Team is selected. */
    public static void assertTeamCounter(String counter) {
        teamModule().getSelectedText().shouldBe(visible);
        teamModule().getSelectedText().shouldBe(matchText(counter));
    }

    /** Assert Apply button. */
    public static void assertDisabledApplyTeamButton() {
        teamModule().getApplyButton().shouldBe(disabled);
        teamModule().getApplyButton().shouldBe(visible);
    }

    /** Assert Cancel button. */
    public static void assertCancelTeamButton() {
        teamModule().getCancelButton().shouldBe(enabled);
        teamModule().getCancelButton().shouldBe(visible);
    }

    /** Assert team in the Teams dropdown. */
    public static void assertAddedTeam(final String team) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getTeamsField().click();
        assertOneTeam(team);
        assertDisabledApplyTeamButton();
    }

    /** Assert WEB elements on Team section. */
    public static void assertElementsOnTeamSection() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertSearchElementsOnTeamsModal();
        teamModule().getSelectedText().shouldBe(visible);
        teamModule().getSelectedText().shouldBe(exactText("No Teams selected"));
        assertSelectAllTeamButton();
        teamModule().getTeamName().shouldBe(CollectionCondition.size(1));
        teamModule().getTeamCheckbox().shouldBe(CollectionCondition.size(1));
        teamModule().getTeamRows().shouldBe(CollectionCondition.size(1));
    }
}
