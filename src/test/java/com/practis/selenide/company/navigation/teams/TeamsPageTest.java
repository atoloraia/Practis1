package com.practis.selenide.company.navigation.teams;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertElementsEmptyOverdueTab;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertNoTeamSearchResultTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSearchResultsAllMembers;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertSearchResultsOnTeamsPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31749)
    @DisplayName("Company: Navigation: Teams page: Check Elements")
    void checkElementsTeamsPage() {
        // Assert Training Page
        assertElementsEmptyTeamsPage();
        overdueLearnersTab().getOverdueTitle().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertElementsEmptyOverdueTab();
    }

    @TestRailTest(caseId = 31750)
    @DisplayName("Company: Navigation: Teams page: Search")
    @TeamExtension(count = 1)
    void searchFieldTeamsScreen(final List<NewTeamInput> team) {
        Selenide.refresh();

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoTeamSearchResultTeamsPage();

        // Assert Search Results
        searchService().clearSearch();
        searchService().searchPerform("All Members");
        assertSearchResultsAllMembers();

        // Search should be performed after entering 1 character
        searchAfter1Char(team.get(0).getName());
        assertSearchResultsOnTeamsPage(team.get(0).getName());

        // Assert Clear Search
        assertCleanSearch();
    }
}
