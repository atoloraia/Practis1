package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import java.util.stream.IntStream;

public class PractisSetSelectionValidator {

    /** Assert search on Practis Set model. */
    public static void assertSearchElementsOnPSsModal() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        inviteUserPsModule().getSearchField().shouldBe(visible);
        inviteUserPsModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        inviteUserPsModule().getSearchField().shouldBe(enabled);
        inviteUserPsModule().getSearchField().shouldBe(attribute("type", "text"));
        inviteUserPsModule().getSearchFieldIcon().shouldBe(visible);
    }

    /** Assert search on empty Practis Set model. */
    public static void assertSearchElementsOnPsEmptyModal() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUserPsModule().getSearchField().shouldBe(visible);
        inviteUserPsModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        // TODO should be fixed after DEV-10763
        inviteUserPsModule().getSearchField().shouldBe(disabled);
        inviteUserPsModule().getSearchField().shouldBe(attribute("type", "text"));
        inviteUserPsModule().getSearchFieldIcon().shouldBe(visible);
    }

    /** Assert no search results. */
    public static void assertNoPsSearchResult() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        inviteUserPsModule().getNoPractisSetYetText().shouldBe(visible);
        inviteUserPsModule().getNoSearchResultImage().shouldBe(visible);
        inviteUserPsModule().getSelectedText().shouldBe(visible);
        inviteUserPsModule().getPractisSetRows().shouldBe(CollectionCondition.size(0));
    }

    /** Assert search results. */
    public static void assertPsSearchResult(final String practisSet) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        psModuleService().findPractisSetCheckbox(practisSet).shouldBe(visible);
        inviteUserPsModule().getPractisSetRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertPsSearchAfter1Char(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        inviteUserPsModule().getSearchField().append(String.valueOf(input));
        inviteUserPsModule().getCleanSearchIcon().shouldBe(Condition.visible);
        inviteUserPsModule().getPractisSetRows().get(0).shouldBe(visible);
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
                            assertNoPsSearchResult();
                        });
        final var input = searchString.charAt(searchString.length() - 4);
        teamModule().getSearchField().append(String.valueOf(input));
    }

    /** Assert clean search on Practis Set model. */
    public static void assertCleanPractisSetSearch(int practisSetRows) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUserPsModule().getCleanSearchIcon().shouldNotBe(visible);
        inviteUserPsModule()
                .getPractisSetRows()
                .shouldHave(CollectionCondition.size(practisSetRows));
        inviteUserPsModule().getSearchField().append("check clean icon");
        inviteUserPsModule().getCleanSearchIcon().shouldBe(visible);
        inviteUserPsModule().getPractisSetRows().shouldHave(CollectionCondition.size(0));
        inviteUserPsModule().getCleanSearchIcon().click();
        inviteUserPsModule().getCleanSearchIcon().shouldNotBe(visible);
        inviteUserPsModule()
                .getPractisSetRows()
                .shouldHave(CollectionCondition.size(practisSetRows));
    }

    /** Assert empty Practis Set state. */
    public static void assertEmptyPractisSet() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertSearchElementsOnPsEmptyModal();

        inviteUserPsModule().getNoSearchResultImage().shouldBe(visible);
        inviteUserPsModule().getNoPractisSetYetText().shouldBe(visible);
        inviteUserPsModule().getNoPractisSetYetText().shouldBe(exactText("No Practis Sets yet"));
    }

    /** Assert Select All button. */
    public static void assertSelectAllPractisSetButton() {
        inviteUserPsModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        inviteUserPsModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Unselect All button. */
    public static void assertUnSelectAllPractisSetButton() {
        inviteUserPsModule().getUnSelectedAllButton().shouldBe(exactText("Unselect All"));
        inviteUserPsModule().getUnSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Select All. */
    public static void assertSelectedAllStatePs() {
        inviteUserPsModule()
                .getPractisSetCheckbox()
                .shouldBe(
                        CollectionCondition.allMatch(
                                "checked",
                                element -> Selenide.$(element).has(attribute("checked"))));
        inviteUserPsModule().getSelectedText().shouldBe(matchText(" selected"));
        assertUnSelectAllPractisSetButton();
    }

    /** Assert Unselect All. */
    public static void assertUnSelectedAllStatePs() {
        inviteUserPsModule()
                .getPractisSetCheckbox()
                .should(
                        CollectionCondition.allMatch(
                                "checked",
                                element -> !Selenide.$(element).has(attribute("checked"))));
        inviteUserPsModule().getSelectedText().shouldBe(exactText("No Practis Sets selected"));
        assertSelectAllPractisSetButton();
    }

    /** Assert the Practis Set is selected. */
    public static void assertSelectedPractisSet(final String practisSet) {
        psModuleService().findPractisSetCheckbox(practisSet).shouldBe(visible);
        await().pollDelay(TWO_SECONDS).until(() -> true);
        psModuleService().findSelectedPractisSetCheckbox(practisSet);
    }

    /** Assert the Practis Set is unselected. */
    public static void assertUnselectedPractisSet(final String practisSet) {
        psModuleService().findUnselectedPractisSetCheckbox(practisSet).shouldBe(Condition.exist);
    }

    /** Assert created Practis Set. */
    public static void assertOnePractisSet(final String practisSet) {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        inviteUserPsModule().getPractisSetName().get(0).shouldBe(visible);
        psModuleService().findPractisSetCheckbox(practisSet).shouldBe(visible);
        inviteUserPsModule().getPractisSetRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert the Practis Set is selected. */
    public static void assertPractisSetCounter(String counter) {
        inviteUserPsModule().getSelectedText().shouldBe(visible);
        inviteUserPsModule().getSelectedText().shouldBe(matchText(counter));
    }

    /** Assert Apply button. */
    public static void assertDisabledApplyPractisSetButton() {
        inviteUserPsModule().getApplyButton().shouldBe(disabled);
    }

    /** Assert Cancel button. */
    public static void assertEnabledCancelPractisSetButton() {
        inviteUserPsModule().getCancelButton().shouldBe(enabled);
    }

    /** Assert 'No Practis Sets Added Yet' tooltip. */
    public static void assertNoPsAddedYet() {
        inviteUserPsModule().getNoPractisSetYetTooltip().shouldBe(visible);
        inviteUserPsModule()
                .getNoPractisSetYetTooltip()
                .shouldBe(exactText("No practis sets added yet"));
    }

    /** Assert Practis Set in the Practis Set dropdown. */
    public static void assertAddedPs(final String team) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getPractisSetsField().click();
        assertOnePractisSet(team);
        assertDisabledApplyPractisSetButton();
    }

    /** Assert WEB elements on PS section. */
    public static void assertElementsOnPsSection() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertSearchElementsOnPSsModal();
        inviteUserPsModule().getSelectedText().shouldBe(visible);
        inviteUserPsModule().getSelectedText().shouldBe(exactText("No Practis Sets selected"));
        assertSelectAllPractisSetButton();
        inviteUserPsModule().getDueDatesColumnTitle().shouldBe(visible);
        inviteUserPsModule().getDueDatesColumnTitle().shouldBe(exactText("Due Dates"));
        inviteUserPsModule().getPractisSetRows().shouldBe(CollectionCondition.size(1));
        inviteUserPsModule().getPractisSetCheckbox().shouldBe(CollectionCondition.size(1));
        inviteUserPsModule().getPractisSetName().shouldBe(CollectionCondition.size(1));
        inviteUserPsModule().getDueDateValue().shouldBe(CollectionCondition.size(1));
        psModuleService().hoverDueDateField();
        inviteUserPsModule().getEditDueDateButton().get(0).shouldBe(visible);
    }
}
