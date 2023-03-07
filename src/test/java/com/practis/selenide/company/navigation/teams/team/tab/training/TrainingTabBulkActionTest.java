package com.practis.selenide.company.navigation.teams.team.tab.training;

import static com.practis.web.selenide.configuration.ServiceObjectFactory.trainingTabService;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;

import com.codeborne.selenide.Selenide;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TrainingTabBulkActionTest {

    @BeforeEach
    void init() {
        ComponentObjectFactory.navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 21655)
    @DisplayName("Team: Training Tab: Bulk Action: Export Report")
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    void exportReportSingleAction(final TeamWithChildren teamWithChildren) {
        Selenide.refresh();

        // Open 'Training Tab'
        trainingTabService().openTeamTrainingTab(teamWithChildren.getTeam().getName());
        trainingTabService().clickSelectAllButton();
        trainingTabService().clickBulkAction();
        trainingTabService().exportReportBulkAction();

        // Assert exported report
        assertDownloadedFile("team-practis-sets");
    }
}
