package com.practis.selenide.company.users.draft.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.draftUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertBulkActionUsersDrafts;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertWarningDeleteDraftPopUp;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;

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
public class UsersDraftsPageBulkActionTest {

    @BeforeEach
    void init(String draftName) {
        navigationCompany().getUsersNavigationItem().click();
        draftUsersService().openDraftUsersList();
    }

    @TestRailTest(caseId = 1734)
    @DisplayName("Users: Drafts: Bulk Action: Check Elements")
    @DraftExtension
    void checkElementsBulkActionUsersDrafts() {

        // asser bulk action Users - Drafts
        usersService().clickBulkAction();
        assertBulkActionUsersDrafts();
    }

    @TestRailTest(caseId = 26935)
    @DisplayName("Users: Drafts: Bulk Action: Delete Drafts")
    @DraftExtension
    void draftUsersBulkActionDeleteDraft() {

        // asser bulk action Users - Drafts: Delete Drafts
        draftUsersService().clickBulkActionDeleteDrafts();

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

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
