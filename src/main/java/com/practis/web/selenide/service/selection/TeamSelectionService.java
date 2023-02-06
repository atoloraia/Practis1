package com.practis.web.selenide.service.selection;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.service.company.InviteUserService;

public class TeamSelectionService {

    /** Find team checkbox. */
    public SelenideElement findTeamCheckbox(final String team) {
        final var teamRow = teamModule().getTeamRows().find(Condition.matchText(team));
        final var checkbox = teamRow.$("[data-test*='team-item-checkbox']").sibling(0);
        return checkbox.parent();
    }

    /** Find selected team checkbox. */
    public SelenideElement findSelectedTeamCheckbox(final String team) {
        final var teamRow = teamModule().getTeamRows().find(Condition.matchText(team));
        return teamRow.$("[data-test='team-item-checkbox-checked']");
    }

    /** Search Team. */
    public void searchTeam(final String input) {
        teamModule().getSearchField().setValue(input.substring(0, input.length() - 1));
        teamModule().getSearchField().append(input.substring(input.length() - 1));
    }

    /** Select All Team. */
    public void selectAllTeam() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModule().getSelectedAllButton().click();
    }

    /** Unselect All Team. */
    public void unSelectAllTeam() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamModule().getUnSelectedAllButton().click();
    }

    /** Select team. */
    public InviteUserService selectTeam(final String team) {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        teamModuleService().findTeamCheckbox(team).click();
        return null;
    }
}
