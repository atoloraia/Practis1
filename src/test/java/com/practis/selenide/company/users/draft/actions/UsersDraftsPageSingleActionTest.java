package com.practis.selenide.company.users.draft.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.draftUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertElementsOnEditDraftPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertSingleActionUsersDrafts;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertWarningDeleteDraftPopUp;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.DraftExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@GeneratedDraftNameExtension
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsPageSingleActionTest {

    @BeforeEach
    void init(String draftName) {
        navigationCompany().getUsersNavigationItem().click();
        draftUsersService().openDraftUsersList();
    }

    @TestRailTest(caseId = 32008)
    @DisplayName("Users: Drafts: Single Action: Check Elements")
    @DraftExtension
    void checkElementsBulkActionUsersDrafts() {
        // asser single action Users - Drafts
        usersService().clickSingleAction();
        assertSingleActionUsersDrafts();
    }

    @TestRailTest(caseId = 32009)
    @DisplayName("Users: Drafts: Single Action: Edit")
    @DraftExtension
    void draftUsersSingleActionEdit() {
        // asser single action Users - Drafts: Edit
        draftUsersService().clickSingleActionEdit();
        assertElementsOnEditDraftPage();
    }

    @TestRailTest(caseId = 32010)
    @DisplayName("Users: Drafts: Single Action: Delete Draft")
    @DraftExtension
    void draftUsersSingleActionDeleteDraft() {
        // asser single action Users - Drafts: Delete Draft
        draftUsersService().clickSingleActionDeleteDraft();

        // Assert confirmation pop-up
        assertWarningDeleteDraftPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Invitation draft has been deleted"));

        // Assert Empty state
        assertUsersEmptyState("No Drafts Yet");
    }
}
