package com.practis.selenide.company.user;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertDraftsFiltersEmptyState;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersDraftsPage;
import static com.practis.web.util.PractisUtils.clickOutOfTheForm;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
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
        newItemSelector().create("User");

        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));
    }

    /** Users, Drafts: Check WEB Elements. */
    @TestRailTest(caseId = 23880)
    @DisplayName("Company: Navigation: Users: Drafts: Check Elements")
    @GeneratedDraftNameExtension
    void checkElementsDraftsUsers() {

        userService().fillText(inputData).selectRole("User");
        userService().addRow();

        // Save as Draft: Save
        userService().clickSaveAsDraftButton();
        clickOutOfTheForm();

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(2).click();
        assertUsersDraftsPage();
    }

    /** Users: Drafts tab: Filters: Check Elements. */
    @TestRailTest(caseId = 23821)
    @DisplayName("Users: Drafts tab: Filters: Check Elements")
    @GeneratedDraftNameExtension
    void checkElementsDraftsUsersFilters() {

        userService().fillText(inputData).selectRole("User");
        userService().addRow();

        // Save as Draft: Save
        userService().clickSaveAsDraftButton();
        clickOutOfTheForm();

        navigationCompany().getUsersNavigationItem().click();
        usersPage().getTabs().get(2).click();
        assertUsersDraftsPage();
        usersPage().getFiltersButton().click();
        assertDraftsFiltersEmptyState();
    }
}
