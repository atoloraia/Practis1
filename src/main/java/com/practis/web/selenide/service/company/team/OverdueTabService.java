package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

public class OverdueTabService {

    /** Search Team on grid by Team Name. */
    public GridRow searchOverdueLearners(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Open Overdue Learners page. */
    public void selectAllOverdueLearners() {
        overdueLearnersTab().getSelectAllCheckbox().sibling(0).click();
    }

    /** Click bulk action for the Overdue Learners. */
    public void clickBulkActionNudge() {
        jsClick(overdueLearnersTab().getSelectAllCheckbox());
        overdueLearnersTab().getActionButton().parent().click();
        overdueLearnersTab().getNudgeActionButton().click();
    }

    /** Click 3-dot menu for the team. */
    public void clickSingleActionOverdue(final String overdue) {
        final var overdueRow =
                overdueLearnersTab().getOverdueRow().find(Condition.matchText(overdue));
        overdueRow.$("div[data-test='list-item-menu-button']").click();
    }

    /** Clear search */
    public void clearSearch() {
        overdueLearnersTab().getOverdueSearchFieldCrossButton().click();
    }

    /** Find overdue labels. */
    public SelenideElement findOverdueLabelCounter(final String overdue) {
        final var overdueRow =
                overdueLearnersTab().getOverdueRow().find(Condition.matchText(overdue));
        return overdueRow.$("[data-test='overdue-learners-labels']");
    }

    /** Find overdue team. */
    public SelenideElement findOverdueTeams(final String overdue) {
        final var overdueRow =
                overdueLearnersTab().getOverdueRow().find(Condition.matchText(overdue));
        return overdueRow.$("[data-test='overdue-users-teams-item']");
    }
}
