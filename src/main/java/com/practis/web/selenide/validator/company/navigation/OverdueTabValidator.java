package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertFilterEmptyTeam;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.practis.dto.NewTeamInput;

public class OverdueTabValidator {

    /** Assert basic elements on Overdue tab. */
    public static void assertBasicElementsOnOverdueTab() {
        overdueLearnersTab().getOverdueTitle().shouldBe(visible);
        overdueLearnersTab().getOverdueTitle().shouldBe(exactText("Overdue Learners"));
        overdueLearnersTab().getOverdueTimestamp().shouldBe(visible);
        overdueLearnersTab().getOverdueTimestampRefresh().shouldBe(visible);
        overdueLearnersTab().getOverdueTimestamp().shouldBe(matchText("Updated"));

        overdueLearnersTab().getOverdueSearchField().shouldBe(visible);
        overdueLearnersTab().getOverdueSearchFieldIcon().shouldBe(visible);
        overdueLearnersTab().getOverdueFilterButton().shouldBe(visible);
        overdueLearnersTab().getOverduePrevButton().shouldBe(visible);
        overdueLearnersTab().getOverdueNextButton().shouldBe(visible);
        overdueLearnersTab().getOverduePrevButton().shouldBe(disabled);
        // overdueLearnersTab().getOverdueNextButton().shouldBe(disabled);

        overdueLearnersTab().getOverdueNameColumn().shouldBe(visible);
        overdueLearnersTab().getOverdueNameColumn().shouldBe(exactText("Name"));
        overdueLearnersTab().getOverdueTeamsColumn().shouldBe(visible);
        overdueLearnersTab().getOverdueTeamsColumn().shouldBe(exactText("Teams"));
    }

    /** Assert elements on empty Overdue tab. */
    public static void assertElementsEmptyOverdueTab() {
        assertBasicElementsOnOverdueTab();
        overdueLearnersTab().getOverdueFilterButton().shouldBe(disabled);
        overdueLearnersTab().getNoOverdueFoundIcon().shouldBe(visible);
        overdueLearnersTab().getNoOverdueFoundText().shouldBe(visible);
        overdueLearnersTab().getNoOverdueFoundText().shouldBe(exactText("No one is behind"));
    }

    /** Assert elements on NOT empty Overdue tab. */
    public static void assertElementsOnOverdueTab() {
        assertBasicElementsOnOverdueTab();
        overdueLearnersTab()
                .getOverdueRow()
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
        overdueLearnersTab().getOverdueItemsCounter().should(matchText("Items"));
        overdueLearnersTab().getOverdueFilterButton().shouldBe(enabled);
    }

    /** Assert Search field on Overdue tab. */
    public static void assertSearchFieldOnOverdueTab() {
        overdueLearnersTab().getOverdueSearchField().shouldBe(visible);
        overdueLearnersTab().getOverdueSearchField().shouldBe(disabled);
        overdueLearnersTab().getOverdueSearchFieldIcon().shouldBe(visible);
        overdueLearnersTab().getOverdueSearchFieldCrossButton().shouldBe(hidden);
    }

    /** Assert no search results. */
    public static void assertNoSearchResultOverdueTab() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        overdueLearnersTab().getNoLearnersFoundIcon().shouldBe(visible);
        overdueLearnersTab().getNoLearnersFoundText().shouldBe(visible);
        overdueLearnersTab().getNoLearnersFoundText().shouldBe(exactText("No Learners Found"));
        overdueLearnersTab().getOverdueFilterButton().shouldBe(visible);
        overdueLearnersTab().getOverdueNameColumn().shouldBe(visible);
        overdueLearnersTab().getOverdueTeamsColumn().shouldBe(visible);
        overdueLearnersTab().getOverdueSearchFieldCrossButton().click();
    }

    /** Assert Search Results. */
    public static void assertSearchResultsOnOverdueTab(String input) {
        overdueLearnersTab().getOverdueSearchField().shouldBe(visible);
        overdueLearnersTab().getOverdueRow().shouldBe(CollectionCondition.size(2));
        final var overdueRow =
                overdueLearnersTab().getOverdueRow().find(Condition.matchText(input));
        overdueRow.shouldBe(visible);
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharOverdueTad(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        overdueLearnersTab().getOverdueSearchField().append(String.valueOf(input));
        overdueLearnersTab().getOverdueSearchFieldCrossButton().shouldBe(visible);
        overdueLearnersTab().getOverdueRow().get(0).shouldBe(visible);
        overdueLearnersTab().getOverdueSearchFieldCrossButton().click();
    }

    /** Assert clean search on Overdue Learners tab. */
    public static void assertCleanSearchOverdueTab(int overdueRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        overdueLearnersTab().getOverdueSearchFieldCrossButton().shouldNotBe(visible);
        // overdueLearnersTab().getOverdueRow().shouldHave(CollectionCondition.size(overdueRow));
        overdueLearnersTab().getOverdueSearchField().append(("check clean icon"));
        overdueLearnersTab().getOverdueSearchField().shouldBe(visible);
        overdueLearnersTab().getOverdueSearchFieldCrossButton().click();
        overdueLearnersTab().getOverdueSearchFieldCrossButton().shouldNotBe(visible);
    }

    /** Clear search */
    public void clearSearch() {
        overdueLearnersTab().getOverdueSearchFieldCrossButton().click();
    }

    /** Assert filters modal. */
    public static void assertElementsOverdueFilters() {
        assertFilterEmptyTeam();
        assertEmptyLabelModel();
    }

    /** Assert bulk action. */
    public static void assertBulkActionOverdue() {
        overdueLearnersTab().getNudgeActionButton().shouldBe(visible);
    }

    /** Assert Single action. */
    public static void assertSingleActionOverdue() {
        overdueLearnersTab().getViewProfileSingleAction().shouldBe(visible);
        overdueLearnersTab().getNudgeSingleAction().shouldBe(visible);
    }

    //////////

    /** Assert grid row with input data. */
    public static void assertTeamGridRow(
            final NewTeamInput inputData,
            final String members,
            final String ps,
            final String teamsLeader) {
        var teamRow = teamsPageService().searchTeam(inputData.getName());
        teamRow.get("Teams").shouldBe(matchText(inputData.getName()));
        teamRow.get("Members").shouldBe(matchText(members));
        teamRow.get("Practis Sets").shouldBe(matchText(ps));
        teamRow.get("Team Leaders").shouldBe(matchText(teamsLeader));
    }

    /** Assert Label counter. */
    public static void assertLabelCountOnTeamsPage(final String team, final String count) {
        teamsPageService().findTeamLabelCounter(team).shouldBe(visible);
        teamsPageService().findTeamLabelCounter(team).shouldBe(exactText(count));
    }

    /** Assert Members counter. */
    public static void assertMemberCountOnTeamsPage(
            final NewTeamInput inputData, final String count) {
        teamsPageService().findMembersCounter(inputData.getName()).shouldBe(visible);
        teamsPageService().findMembersCounter(inputData.getName()).shouldBe(exactText(count));
    }

    /** Assert number of Teams rows. */
    public static void assertTeamsRows(final Integer rows) {
        teamsPage().getTeamRow().shouldBe(CollectionCondition.size(rows));
    }

    /** Search Team on grid by Team Name. */
    public static void assertDataOnTeamsPage(
            final NewTeamInput inputData, String members, String ps, String leader, String label) {
        teamsPageService().openTeamsPage();
        var teamRow = teamsPageService().searchTeam(inputData.getName());
        assertTeamGridRow(inputData, members, ps, leader);
        assertLabelCountOnTeamsPage(inputData.getName(), label);
        teamRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Assert single action for All Members. */
    public static void assertSingleActionAllMembers() {
        teamsPage().getViewTeamSingleAction().shouldBe(visible);
        teamsPage().getViewTeamSingleAction().shouldBe(exactText("View Team"));
        teamsPage().getManageTeamSingleAction().shouldBe(visible);
        teamsPage().getManageTeamSingleAction().shouldBe(exactText("Manage Team"));
        teamsPage().getAssignLabelsSingleAction().shouldBe(hidden);
        teamsPage().getDuplicateSingleAction().shouldBe(hidden);
        teamsPage().getDeleteSingleAction().shouldBe(hidden);
    }

    /** Assert single action for the Team. */
    public static void assertSingleActionTeam() {
        teamsPage().getViewTeamSingleAction().shouldBe(visible);
        teamsPage().getViewTeamSingleAction().shouldBe(exactText("View Team"));
        teamsPage().getManageTeamSingleAction().shouldBe(visible);
        teamsPage().getManageTeamSingleAction().shouldBe(exactText("Manage Team"));
        teamsPage().getAssignLabelsSingleAction().shouldBe(visible);
        teamsPage().getAssignLabelsSingleAction().shouldBe(exactText("Assign Labels"));
        teamsPage().getDuplicateSingleAction().shouldBe(visible);
        teamsPage().getDuplicateSingleAction().shouldBe(exactText("Duplicate"));
        teamsPage().getDeleteSingleAction().shouldBe(visible);
        teamsPage().getDeleteSingleAction().shouldBe(exactText("Delete"));
    }
}
