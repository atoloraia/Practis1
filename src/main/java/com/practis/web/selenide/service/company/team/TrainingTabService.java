package com.practis.web.selenide.service.company.team;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.keepTrackPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.trainingTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.web.selenide.component.GridRow;

public class TrainingTabService {

    /** Search Team on grid by Team Name. */
    public GridRow searchTraining(final String name) {
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Open Team: Training Tab: find by name. */
    public void openTeamTrainingTab(String teamName) {
        teamsPageService().searchTeam(teamName);
        teamsPage().getTeamRow().get(0).click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        jsClick(keepTrackPopUp().getGotItButton());
    }

    /** Click 3-dot menu for the Practis Set. */
    public void clickTrainingTabSingleAction() {
        trainingTab().getTrainingThreeDotMenu().get(0).click();
    }

    /** Click 'View Progress' on 3-dot menu for the Practis set. */
    public void clickViewProgressSingleAction() {
        trainingTab().getTrainingViewProgressOption().click();
    }

    /** Click 'Assign Users' on 3-dot menu for the Practis set. */
    public void clickAssignUsersSingleAction() {
        trainingTab().getTrainingAssignUsersOption().click();
    }

    /** Click 'Export Report' on 3-dot menu for the Practis set. */
    public void clickExportReportSingleAction() {
        trainingTab().getTrainingExportActionOption().click();
    }

    /** Click 'Edit Practis Set' on 3-dot menu for the Practis set. */
    public void clickEditPsSingleAction() {
        trainingTab().getTrainingEditPractisSetOption().click();
    }

    /** Click 'Action' on Training tab. */
    public void clickBulkAction() {
        trainingTab().getBulkActionButton().parent().click();
    }

    /** Click Select All check box. */
    public void clickSelectAllButton() {
        trainingTab().getTrainingTabSelectAllCheckbox().click();
    }

    /** Click Action: Export Report check box. */
    public void exportReportBulkAction() {
        trainingTab().getBulkActionExportReport().click();
    }
}
