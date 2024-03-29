package com.practis.web.selenide.service.company.team;

import static com.codeborne.selenide.Condition.matchText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPSAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewTeamInput;
import com.practis.web.selenide.component.GridRow;

public class TeamsPageService {
    private static final String DUPLICATED_TEMPLATE =
            "\\[[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\\s[0-9]{1,2}:[0-9]{2}:[0-9]{2}\\s(AM|PM)\\]-%s";
    private static final String ORIGINAL_TEMPLATE = "^(%s).*";

    /** Search Team on grid by Team Name. */
    public GridRow searchTeam(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamsPage().getTeamSearchField().setValue(name.substring(0, name.length() - 1));
        teamsPage().getTeamSearchField().append(name.substring(name.length() - 1));
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search Team on grid by Team Name. */
    public GridRow searchTeamTabs(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        teamsPage().getTeamSearchFieldTabs().setValue(name.substring(0, name.length() - 1));
        teamsPage().getTeamSearchFieldTabs().append(name.substring(name.length() - 1));
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search PS on 'Assign Practis Sets and Due Dates' model */
    public void searchPsOnAssignPsModel(final String input) {
        assignPSAndDueDatesModule().getSearchField().append(input);
    }

    /** Clear search on 'Assign Practis Sets and Due Dates' model */
    public void clearSearchPsOnAssignPsModel() {
        assignPSAndDueDatesModule().getSearchClearIcon().click();
    }

    /** Search Team on grid by Team Name. */
    public void awaitTheRow(final NewTeamInput team) {
        awaitSoft(
                10,
                () -> {
                    final var isTeamDisplayed =
                            teamsPage().getTeamRow().find(matchText(team.getName())).isDisplayed();
                    if (!isTeamDisplayed) {
                        Selenide.refresh();
                    }
                    return isTeamDisplayed;
                });
    }

    /** Open Teams page. */
    public void openTeamsPage() {
        openPage(webApplicationConfig().getUrl() + "/teams");
    }

    /** Open Teams page. */
    public void selectAllTeams() {
        teamsPage().getSelectAllCheckbox().sibling(0).click();
    }

    /** Open Manage Team page. */
    public void openManageTeamFromTeamsPage(final String team) {
        var teamRow = teamsPageService().searchTeamTabs(team);
        teamRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        membersTab().getMembersManageTeamButton().click();
    }

    /** Find team labels. */
    public SelenideElement findTeamLabelCounter(final String team) {
        final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
        return teamRow.$("[data-test='teams-item-labels']");
    }

    /** Find Members counter. */
    public SelenideElement findMembersCounter(final String team) {
        final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
        return teamRow.$("[data-test='teams-item-members']");
    }

    /** Click 3-dot menu for All Members team. */
    public void clickSingleActionAllMembers() {
        final var teamRow = teamsPage().getTeamsAllMembersRow();
        teamRow.$("div[data-test='teams-item-menu-button']").click();
    }

    /** Click 3-dot menu for the team. */
    public void clickSingleActionTeam(final String team) {
        final var teamRow = teamsPage().getTeamRow().find(Condition.matchText(team));
        teamRow.$("div[data-test='teams-item-menu-button']").click();
    }

    /** Click on 3-dot menu for the team. */
    public void clickViewTeamSingleAction() {
        teamsPage().getViewTeamSingleAction().click();
    }

    /** Click "View Team' on 3-dot menu for the team. */
    public void clickManageTeamSingleAction() {
        teamsPage().getManageTeamSingleAction().click();
    }

    /** Click "Assign Labels' on 3-dot menu for the team. */
    public void clickAssignLabelsSingleAction() {
        teamsPage().getAssignLabelsSingleAction().click();
    }

    /** Click "Duplicate' on 3-dot menu for the team. */
    public void clickDuplicateSingleAction() {
        teamsPage().getDuplicateSingleAction().click();
    }

    /** Click "Delete' on 3-dot menu for the team. */
    public void clickDeleteSingleAction() {
        teamsPage().getDeleteSingleAction().click();
    }

    public GridRow getOriginalTeam(final String name) {
        return grid().getRow(format(ORIGINAL_TEMPLATE, name));
    }

    public GridRow getDuplicatedTeam(final String name) {
        return grid().getRow(format(DUPLICATED_TEMPLATE, name));
    }

    /** Search PS on 'Assign Practis Sets and Due Dates' model */
    public void searchUsersOnAssignPsModel(final String input) {
        assignUsersAndDueDatesModule().getSearchField().append(input);
    }

    /** Clear search on 'Assign Practis Sets and Due Dates' model */
    public void clearSearchUsersOnAssignPsModel() {
        assignUsersAndDueDatesModule().getSearchField().click();
    }
}
