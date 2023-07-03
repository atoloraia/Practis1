package com.practis.selenide.company.users.draft;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.draftUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.searchService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.service.SearchService.searchAfter1Char;
import static com.practis.web.selenide.validator.common.SearchValidator.assertCleanSearch;
import static com.practis.web.selenide.validator.common.SearchValidator.assertSearchField;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertDraftsPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertElementsDraftsFilters;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertNoSearchResultOnDraftUserTab;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertSearchResultsOnDraftUserTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserDraftUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedLabels;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedPSs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertAddedTeams;
import static com.practis.web.util.PractisUtils.clickOutOfTheFormForPopup;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.DraftExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsTest {

    private List<String> usersToRemove;
    private NewUserInput inputData;

    @BeforeEach
    void init() {
        // newItemSelector().create("User");

        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

        usersToRemove = new ArrayList<>();
        usersToRemove.add(inputData.getEmail());
    }

    /** Users, Drafts: Check WEB Elements. */
    @TestRailTest(caseId = 32005)
    @DisplayName("Company: Navigation: Users: Drafts: Check Elements")
    @DraftExtension
    void checkElementsDraftsUsers() {
        navigationCompany().getUsersNavigationItem().click();
        awaitFullPageLoad(10);
        usersPage().getDraftTab().click();
        assertDraftsPage();
    }

    /** Users: Drafts tab: Filters: Check Elements. */
    @TestRailTest(caseId = 32006)
    @DisplayName("Users: Drafts tab: Filters: Check Elements")
    @DraftExtension
    void checkElementsDraftsUsersFilters() {
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getDraftTab().click();
        awaitFullPageLoad(15);
        jsClick(usersPage().getFiltersButton());
        assertElementsDraftsFilters();
    }

    /** Users: Draft tab: Search */
    @DisplayName("Users: Draft tab: Search")
    @TestRailTest(caseId = 32007)
    @DraftExtension
    void searchOnDraftTab(final List<RestStagingResponse> user) {
        navigationCompany().getUsersNavigationItem().click();
        usersPage().getDraftTab().click();

        // Assert Search Field
        assertSearchField();

        // Assert no Search results
        searchService().searchPerform("no results");
        assertNoSearchResultOnDraftUserTab();

        // Assert Search by First Name
        searchService().clearSearch();
        searchService().searchPerform(user.get(0).getName());
        assertSearchResultsOnDraftUserTab(user.get(0).getName());

        // Search should be performed after entering 1 character
        searchService().clearSearch();
        searchAfter1Char(user.get(0).getName());
        assertSearchResultsOnDraftUserTab(user.get(0).getName());

        // Assert Clear Search
        assertCleanSearch();
    }

    /** Users: Draft Tab: Edit User */
    @DisplayName("Users: Draft Tab: Edit User")
    @TestRailTest(caseId = 32081)
    @DraftExtension
    @LabelExtension(count = 2)
    @TeamExtension(count = 2)
    @PractisSetExtension(count = 2)
    @GeneratedDraftNameExtension
    void editDraftUser(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSets,
            String draftName) {
        Selenide.refresh();
        final var inputs = userService().generateUserInputs(2);

        newItemSelector().create("User");
        // Add User row
        Selenide.refresh();
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().saveAsDraft(draftName);

        // Edit User row and apply changes
        userService().clickEdit(0);
        userService().editRow(inputs.get(1), "User", label.get(1), team.get(1), practisSets.get(1));
        clickOutOfTheFormForPopup();

        // assert grid row data
        draftUsersService().openDraftUsersList();
        asserDraftUser(draftName, inputs.get(1), "User", 0);
        userService().clickEdit(0);

        assertAddedTeams(team);
        assertAddedLabels(label);
        assertAddedPSs(practisSets);
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().deleteUser(email));
    }
}
