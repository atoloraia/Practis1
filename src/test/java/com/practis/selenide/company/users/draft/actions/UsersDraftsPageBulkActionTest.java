package com.practis.selenide.company.users.draft.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.draftUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertBulkActionUsersDrafts;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertWarningDeleteDraftPopUp;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.util.PractisUtils.clickOutOfTheForm;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@GeneratedDraftNameExtension
@SelenideTestClass
@TestRailTestClass
public class UsersDraftsPageBulkActionTest {

    private NewUserInput inputData;
    private List<String> draftsToRemove;

    @BeforeEach
    void init(String draftName) {
        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));
        draftsToRemove = new ArrayList<>();

        newItemSelector().create("User");
        userService().addRow(inputData, "User");
        userService().saveAsDraft(draftName);
        await().pollDelay(TWO_SECONDS).until(() -> true);
        clickOutOfTheForm();

        navigationCompany().getUsersNavigationItem().click();
        draftUsersService().openDraftUsersList();
    }

    @TestRailTest(caseId = 1734)
    @DisplayName("Users: Drafts: Bulk Action: Check Elements")
    void checkElementsBulkActionUsersDrafts() {

        // asser bulk action Users - Drafts
        usersService().clickBulkAction();
        assertBulkActionUsersDrafts();
    }

    @TestRailTest(caseId = 26935)
    @DisplayName("Users: Drafts: Bulk Action: Delete Drafts")
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

    @AfterEach
    void cleanup(String draftName) {
        draftsToRemove.forEach(name -> practisApi().deleteDraftUser(draftName));
    }
}
