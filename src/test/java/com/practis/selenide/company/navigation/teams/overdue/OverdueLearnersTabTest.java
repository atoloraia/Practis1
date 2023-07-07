package com.practis.selenide.company.navigation.teams.overdue;

import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.overdueTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertCleanSearchOverdueTab;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertElementsOnOverdueTab;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertElementsOverdueFilters;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertNoSearchResultOverdueTab;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertSearchAfter1CharOverdueTad;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertSearchFieldOnOverdueTab;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertSearchResultsOnOverdueTab;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestEnrollUnEnrollRequest;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.OverdueUserExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class OverdueLearnersTabTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
        overdueLearnersTab().getOverdueTitle().click();
    }

    @TestRailTest(caseId = 31814)
    @DisplayName("Teams: Overdue: Check Elements")
    @OverdueUserExtension
    void checkElementsOverdueTab() {
        // AwaitUtils.awaitSoft(60, () -> false);
        Selenide.refresh();
        assertElementsOnOverdueTab();
    }

    @TestRailTest(caseId = 31815)
    @DisplayName("Teams: Overdue: Search")
    @OverdueUserExtension()
    void searchFieldOverdueTab(final List<RestEnrollUnEnrollRequest> enrollments) {
        Selenide.refresh();
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());

        // Assert Search Field
        assertSearchFieldOnOverdueTab();

        // Assert no Search results
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        overdueTabService().searchOverdueLearners("no search results");
        assertNoSearchResultOverdueTab();

        // Assert Search by Learner First Name
        overdueTabService().searchOverdueLearners(user.getFirstName());
        assertSearchResultsOnOverdueTab(user.getFirstName());

        // Assert Search by Learner First Name
        overdueTabService().clearSearch();
        overdueTabService().searchOverdueLearners(user.getLastName());
        assertSearchResultsOnOverdueTab(user.getFirstName());

        // Search should be performed after entering 1 character
        assertSearchAfter1CharOverdueTad(user.getFirstName());

        // Assert Clear Search
        assertCleanSearchOverdueTab(0);
    }

    @TestRailTest(caseId = 31816)
    @DisplayName("Teams: Overdue: Filters: Check Elements")
    @OverdueUserExtension
    void checkElementsOverdueTabFilters() {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        jsClick(overdueLearnersTab().getOverdueFilter());
        assertElementsOverdueFilters();
    }

    @TestRailTest(caseId = 31817)
    @DisplayName("Teams: Overdue: Filters: Apply")
    @OverdueUserExtension
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void checkElementsOverdueTabFiltersApply(
            final List<RestEnrollUnEnrollRequest> enrollments,
            final List<RestCreateLabelResponse> labels,
            final List<NewTeamInput> team) {
        Selenide.refresh();
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());
        practisApi().assignLabelToUser(user.getId(), List.of(labels.get(0).getId()));
        practisApi().addMembersToTeam(team.get(0).getId(), List.of(user.getId()));

        // Filter by Label
        await().pollDelay(TWO_SECONDS).until(() -> true);
        jsClick(overdueLearnersTab().getOverdueFilterButton());
        labelModuleService().selectLabel(labels.get(0).getName());
        filter().getApplyFilterButton().click();
        // Check results
        overdueTabService().findOverdueLabelCounter("1");

        // Filter by Team
        overdueLearnersTab().getOverdueFilter().click();
        teamModuleService().selectTeam(team.get(0).getName());
        filter().getApplyFilterButton().click();
        // Check results
        overdueTabService().findOverdueTeams(team.get(0).getName());
    }
}
