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
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertElementsOnEditDraftPage;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertSingleActionUsersDrafts;
import static com.practis.web.selenide.validator.company.users.DraftsTabValidator.assertWarningDeleteDraftPopUp;
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
public class UsersDraftsPageSingleActionTest {
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

    @TestRailTest(caseId = 1736)
    @DisplayName("Users: Drafts: Single Action: Check Elements")
    void checkElementsBulkActionUsersDrafts() {

        // asser single action Users - Drafts
        usersService().clickSingleAction();
        assertSingleActionUsersDrafts();
    }

    @TestRailTest(caseId = 26936)
    @DisplayName("Users: Drafts: Single Action: Edit")
    void draftUsersSingleActionEdit() {

        // asser single action Users - Drafts: Edit
        draftUsersService().clickSingleActionEdit();
        assertElementsOnEditDraftPage();
    }

    @TestRailTest(caseId = 1735)
    @DisplayName("Users: Drafts: Single Action: Delete Draft")
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

    @AfterEach
    void cleanup(String draftName) {
        draftsToRemove.forEach(name -> practisApi().deleteDraftUser(draftName));
    }
}
