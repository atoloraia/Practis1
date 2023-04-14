package com.practis.selenide.company.navigation.reports;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetSummaryReportService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.reportsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertCleanSearchTeamPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertElementsOnPractisSetSummaryReportsPage;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertHiddenSearchFiledTeam;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertNoTeamsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertTeamsSearchAfter1CharPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertTeamsSearchResultPractisSetSummaryReport;
import static com.practis.web.selenide.validator.company.reports.PractisSetSummaryReportValidator.assertVisibleSearchFieldTeam;
import static com.practis.web.selenide.validator.company.reports.ReportsValidator.assertElementsOnReportsPage;

import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.dto.TeamWithChildren;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class ReportsTest {
    @BeforeEach
    void init() {
        navigationCompany().getReportsItem().click();
    }

    @TestRailTest(caseId = 30076)
    @DisplayName("Reports: Reporting Screen: Check Elements for Admins")
    void checkElementsOnReportsPage() {

        assertElementsOnReportsPage();
    }

    @TestRailTest(caseId = 30062)
    @DisplayName("Reports: Practis Set Summary Report: Check Elements")
    void checkElementsOnPractisSetSummaryReportPage() {

        reportsService().clickOnPractisSetSummaryCard();
        assertElementsOnPractisSetSummaryReportsPage();
        assertHiddenSearchFiledTeam();
    }

    @TestRailTest(caseId = 31696)
    @TeamExtension(count = 1)
    @DisplayName("Reports: Practis Set Summary Report: Team: Search")
    void teamSearchOnPractisSetSummaryReportPage(final List<NewTeamInput> team) {

        reportsService().clickOnPractisSetSummaryCard();
        practisSetSummaryReportService().clickOnSearchIconTeamModal();
        assertVisibleSearchFieldTeam();

        // Assert Clear Search
        assertCleanSearchTeamPractisSetSummaryReport();

        // Assert no Search results
        teamsPageService().searchTeam("no results");
        assertNoTeamsSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Assert Search Results
        teamsPageService().searchTeam(team.get(0).getName());
        assertTeamsSearchResultPractisSetSummaryReport();
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Search should be performed after entering 1 character
        assertTeamsSearchAfter1CharPractisSetSummaryReport(team.get(0).getName());
        practisSetSummaryReportService().clickOnClearSearchTeamModal();

        // Assert Clear Search
        assertCleanSearchTeamPractisSetSummaryReport();
    }
}
