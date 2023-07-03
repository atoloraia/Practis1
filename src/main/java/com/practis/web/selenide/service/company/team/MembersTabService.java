package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.selenide.component.GridRow;
import com.practis.web.util.SelenideJsUtils;

public class MembersTabService {

    /** Search Team on grid by Team Name. */
    public static GridRow searchMember(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Open Team: Members Tab: find by name. */
    public void openTeamMembersTab(String teamName) {
        teamsPageService().searchTeamTabs(teamName);
        teamsPage().getTeamRow().get(0).click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        SelenideJsUtils.jsClick(keepTrackPopUp().getGotItButton());
        teamPage().getMembersTab().click();
    }

    /** Click Select All check box. */
    public void clickSelectAllButton() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        membersTab().getMembersSelectAllCheckbox().click();
    }

    /** Click 3-dot menu for the team. */
    public void clickMembersTabSingleAction() {
        membersTab().getMembersThreeDotMenu().click();
    }

    /** Click 'Action' button for the member. */
    public void clickMembersTabBulkAction() {
        membersTab().getMembersActionButton().parent().click();
    }

    /** Click "View Profile' on 3-dot menu for the team. */
    public void clickViewProfileSingleAction() {
        membersTab().getMembersViewProfileOption().click();
    }

    /** Click "Nudge User" on 3-dot menu for the team. */
    public void clickNudgeUserSingleAction() {
        membersTab().getMembersNudgeUserOption().click();
    }

    /** Click "Export Report" on 3-dot menu for the team. */
    public void clickExportReportSingleAction() {
        membersTab().getMembersExportReportOption().click();
    }

    /** Click "Remove From Team" on 3-dot menu for the team. */
    public void clickRemoveFromTeamSingleAction() {
        membersTab().getRemoveFromTeamOption().click();
    }
}
