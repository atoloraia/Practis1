package com.practis.selenide.company.users.registered.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.processingPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignPsAndDueDateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.registeredUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedLabelView;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertBulkActionUsersRegistered;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.selenide.validator.popup.ProcessingPopUpValidator.asserProcessingPopUp;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDate;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateEmpty;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertPractisSetData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfileWithAssignedLabel;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersRegisteredPageBulkActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 1606)
    @DisplayName("Users: Registered: Bulk Action: Check Elements")
    void checkElementsBulkActionUsersRegistered() {

        // asser bulk action Users - Registered
        usersService().clickBulkAction();
        assertBulkActionUsersRegistered();
    }

    @TestRailTest(caseId = 1607)
    @DisplayName("Users: Registered: Bulk Action: Assign Practis Sets: Empty State")
    void registeredUsersBulkActionAssignPsEmptyState() {
        // Click on Assign - Assign PSs
        registeredUsersService().clickBulkActionAssignPs();

        // Assert empty Assign Practis Sets modal
        assertAssignPsAndDueDateEmpty();
    }

    @TestRailTest(caseId = 1608)
    @PractisSetExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Assign Practis Sets: Apply")
    void registeredUsersBulkActionAssignPs(
            final List<NewUserInput> user, final List<NewPractisSetInput> practisSets) {

        // Click on Assign - Assign PSs
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickBulkActionAssignPs();

        // Assert Assign Practis Sets modal
        assertAssignPsAndDueDate("No Practis Sets selected");

        // Assign Practis Set to User
        assignPsAndDueDateService().clickSelectPractisSet(practisSets);
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersPage().getUserRowValue().get(3).shouldBe(Condition.exactText("1"));

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Changes have been saved"));

        // Open User Profile Page
        registeredUsersService().clickUserRow(user.get(0).getFirstName());
        assertUserData(user.get(0));
        assertPractisSetData(practisSets.get(0));
    }

    @TestRailTest(caseId = 1610)
    @LabelExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Assign Labels: Apply")
    void registeredUsersBulkActionAssignLabels(
            final List<NewUserInput> user, final List<RestCreateLabelResponse> label) {

        // Click on Assign - Assign Labels
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickBulkActionAssignLabels();

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Registered User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabelBulkAction();

        // Assert Processing pop-up
        awaitElementExists(5, () -> processingPopUp().getProcessingTitle());
        asserProcessingPopUp("Processing Labels");
        awaitElementNotExists(5, () -> processingPopUp().getProcessingTitle());

        // Assert assigned label
        assignedLabelView(user.get(0).getFirstName(), "1");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Changes has been saved for 1 item"));

        // Check assigned Label on User Profile page
        assertUserProfileWithAssignedLabel(label);
    }

    @TestRailTest(caseId = 1611)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Nudge Users")
    void registeredUsersBulkActionNudgeUser(final List<NewUserInput> users) {

        // Click on Assign - Nudge User
        userService().searchUser(users.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickBulkActionNudge();

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(Condition.exactText("Messages were sent successfully"));
    }

    @TestRailTest(caseId = 1613)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Export Report")
    void registeredUsersBulkActionExportReport() {

        // Click on Export Report
        registeredUsersService().clickBulkActionExportReport();

        // Assert downloaded file
        assertDownloadedFile("Report.csv");
    }

    @TestRailTest(caseId = 1614)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Delete Users")
    void registeredUsersBulkActionDeleteUser(final List<NewUserInput> user) {

        // Click on Assign - Delete User
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickBulkActionDeleteUsers();

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Processing pop-up
        awaitElementExists(10, () -> processingPopUp().getProcessingTitle());
        asserProcessingPopUp("Delete Users");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Users have been deleted."));

        // Assert No Search Result page
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        assertNoSearchResults("No Users Found");
    }
}
