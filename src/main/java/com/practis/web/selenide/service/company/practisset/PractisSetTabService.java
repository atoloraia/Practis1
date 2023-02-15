package com.practis.web.selenide.service.company.practisset;

import static com.codeborne.selenide.Condition.matchText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.membersTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.statusModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;

public class PractisSetTabService {

    private static final String DUPLICATED_TEMPLATE =
            "\\[[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s(AM|PM)\\]-%s";
    private static final String ORIGINAL_TEMPLATE = "^(%s).*";

    /** Select All on Practis Set Tab. */
    public void selectAllPractisSets() {
        practisSetTab().getPractisSetsSelectAllCheckbox().click();
    }

    /** Assign Label to Practis Set Tab. */
    public void assignLabelToPractisSets(String label) {
        practisSetTab().getActionButton().click();
        practisSetTab().getAssignLabelsBulkAction().click();
        labelModuleService().selectLabel(label);
        labelModule().getGetApplyButtonBulkAction().click();
    }

    /** Find team labels. */
    public SelenideElement findPsLabelCounter(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        return practisSetRow.$("[data-test='library-practis-sets-item-labels']");
    }

    /** Filter by archived Practis Sets. */
    public void filterByArchivedPs() {
        libraryPage().getFiltersButton().click();
        filter().getLibraryClearButton().click();
        statusModuleService().selectArchivedStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }

    /** Filter by archived Practis Sets. */
    public void filterByDraftPs() {
        libraryPage().getFiltersButton().click();
        filter().getLibraryClearButton().click();
        statusModuleService().selectDraftStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }

    // Single Actions

    /** Click 3-dot menu for the Practis Set. */
    public void clickSingleActionPractisSet(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        practisSetRow.$("div[data-test='library-practis-sets-item-menu-button']").click();
    }

    /** Click on 3-dot menu for the Practis Set. */
    public void clickEditSingleAction() {
        practisSetTab().getEditSingleAction().click();
    }

    /** Click 'Assign Labels' on 3-dot menu for the team. */
    public void clickAssignLabelsSingleAction() {
        practisSetTab().getAssignLabelsSingleAction().click();
    }

    /** Click 'Duplicate' on 3-dot menu for the team. */
    public void clickDuplicateSingleAction() {
        practisSetTab().getDuplicateSingleAction().click();
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickArchiveSingleAction() {
        practisSetTab().getArchiveSingleAction().click();
    }

    /** Click 'Restore' on 3-dot menu for the team. */
    public void clickRestoreSingleAction() {
        practisSetTab().getRestoreSingleAction().click();
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickDeleteSingleAction() {
        practisSetTab().getDeleteSingleAction().click();
    }

    public GridRow getOriginalPs(final String name) {
        return grid().getRow(format(ORIGINAL_TEMPLATE, name));
    }

    public GridRow getDuplicatedPs(final String name) {
        return grid().getRow(format(DUPLICATED_TEMPLATE, name));
    }

    /** Find Status in the row. */
    public SelenideElement findStatus(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        return practisSetRow.$("[data-test='library-practis-sets-item-status']");
    }

    /** Search Practis Set on grid by Name. */
    public GridRow searchPS(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Search PS on 'Assign Practis Sets and Due Dates' model */
    public void searchPsOnAssignPsModel(final String input) {
        assignPractisSetsAndDueDatesModule().getSearchField().append(input);
    }

    /** Clear search on 'Assign Practis Sets and Due Dates' model */
    public void clearSearchPsOnAssignPsModel() {
        assignPractisSetsAndDueDatesModule().getSearchClearIcon().click();
    }

    /** Search Team on grid by Team Name. */
    public void awaitTheRow(final NewPractisSetInput practisSets) {
        awaitSoft(
                10,
                () -> {
                    final var isTeamDisplayed =
                            teamsPage()
                                    .getTeamRow()
                                    .find(matchText(practisSets.getName()))
                                    .isDisplayed();
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

    /** Open Manage Team page. */
    public void openManageTeamFromTeamsPage(final String team) {
        var teamRow = teamsPageService().searchTeam(team);
        teamRow.click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        keepTrackPopUp().getGotItButton().click();
        teamPage().getMembersTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        membersTab().getMembersManageTeamButton().click();
    }

    /** Click 3-dot menu for All Members team. */
    public void clickSingleActionAllMembers() {
        final var teamRow = teamsPage().getTeamsAllMembersRow();
        teamRow.$("div[data-test='teams-item-menu-button']").click();
    }

    /** Search PS on 'Assign Practis Sets and Due Dates' model */
    public void searchUsersOnAssignPsModel(final String input) {
        assignUsersAndDueDatesModule().getSearchFields().get(1).append(input);
    }

    /** Clear search on 'Assign Practis Sets and Due Dates' model */
    public void clearSearchUsersOnAssignPsModel() {
        assignUsersAndDueDatesModule().getSearchFieldXButton().click();
    }
}
