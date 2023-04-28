package com.practis.selenide.company.users.draft;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.saveAsDraftPopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
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
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.PractisUtils.clickOutOfTheForm;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestStagingResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.DraftExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsTest {

    private NewUserInput inputData;
    private List<String> draftsToRemove;

    @BeforeEach
    void init() {
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));
        draftsToRemove = new ArrayList<>();
    }

    /** Users, Drafts: Check WEB Elements. */
    @TestRailTest(caseId = 23880)
    @DisplayName("Company: Navigation: Users: Drafts: Check Elements")
    @GeneratedDraftNameExtension
    void checkElementsDraftsUsers(String draftName) {
        draftsToRemove.add(draftName);

        newItemSelector().create("User");
        userService().fillText(inputData).selectRole("User");
        userService().addRow();

        // Save as Draft: Save
        userService().clickSaveAsDraftButton(draftName);
        awaitElementNotExists(10, () -> saveAsDraftPopUp().getSaveButton());
        clickOutOfTheForm();

        navigationCompany().getUsersNavigationItem().click();
        awaitFullPageLoad(10);
        usersPage().getDraftTab().click();
        assertDraftsPage();
    }

    /** Users: Drafts tab: Filters: Check Elements. */
    @TestRailTest(caseId = 23821)
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
    @TestRailTest(caseId = 31717)
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

    @AfterEach
    void cleanup() {
        draftsToRemove.forEach(draftName -> practisApi().deleteDraftUser(draftName));
    }
}
