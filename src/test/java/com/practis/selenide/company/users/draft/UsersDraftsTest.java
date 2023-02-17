package com.practis.selenide.company.users.draft;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertDraftsPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertElementsDraftsFilters;
import static com.practis.web.util.PractisUtils.clickOutOfTheForm;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.DraftExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsTest {

    private NewUserInput inputData;

    @BeforeEach
    void init() {
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));
    }

    /** Users, Drafts: Check WEB Elements. */
    @TestRailTest(caseId = 23880)
    @DisplayName("Company: Navigation: Users: Drafts: Check Elements")
    @GeneratedDraftNameExtension
    void checkElementsDraftsUsers() {

        newItemSelector().create("User");
        userService().fillText(inputData).selectRole("User");
        userService().addRow();

        // Save as Draft: Save
        userService().clickSaveAsDraftButton();
        clickOutOfTheForm();

        navigationCompany().getUsersNavigationItem().click();
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
        usersPage().getFiltersButton().click();
        assertElementsDraftsFilters();
    }
}
