package com.practis.selenide.company.users.registered.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmBulkActionPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.nudgePopup;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersRegisteredTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignPsAndDueDateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertBulkActionUsersRegistered;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.selenide.validator.popup.InvitingUsersPopUpValidator.assertProcessingDeleteUsersPopUp;
import static com.practis.web.selenide.validator.popup.InvitingUsersPopUpValidator.assertProcessingLabelsPopUp;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPractisSetsAndDueDatesModulewithPs;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertEmptyAssignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertPractisSetData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
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
    void bulkActionUsersRegistered() {

        // asser bulk action Users - Registered
        usersService().clickBulkActionUsersRegistered();
        assertBulkActionUsersRegistered();
    }

    @TestRailTest(caseId = 1607)
    @DisplayName("Users: Registered: Bulk Action: Assign Practis Sets: Empty State")
    void bulkActionAssignPsEmptyState() {

        // Click on Assign - Assign PSs
        usersService().clickBulkActionAssignPsUsersRegistered();

        // Assert empty Assign Practis Sets modal
        assertEmptyAssignPractisSetsAndDueDatesModule();
    }

    @TestRailTest(caseId = 1608)
    @PractisSetExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Assign Practis Sets")
    void bulkActionAssignPs(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        // Click on Assign - Assign PSs
        Selenide.refresh();
        userService().searchUser(users.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickBulkActionAssignPsUsersRegistered();

        // Assert Assign Practis Sets modal
        assertAssignPractisSetsAndDueDatesModulewithPs();

        // Assign Practis Set to User
        assignPsAndDueDateService().clickSelectPractisSet(practisSets);
        usersPage().getUserRowValue().get(3).shouldBe(Condition.exactText("1"));

        // Assert Snackbar
        usersRegisteredTab().getUserSnackbar().shouldBe(visible);
        usersRegisteredTab().getUserSnackbar().shouldBe(exactText("Changes have been saved"));

        // Open User Profile Page
        usersPage().getUserRowValue().get(1).click();
        assertUserData(users.get(0));
        assertPractisSetData(practisSets.get(0));
    }

    @TestRailTest(caseId = 1610)
    @LabelExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Assign Labels: Apply")
    void bulkActionAssignLabels(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> label) {

        // Click on Assign - Assign Labels
        Selenide.refresh();
        userService().searchUser(users.get(0).getFirstName());
        usersService().clickBulkActionAssignLabelsUsersRegistered();

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Registered User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabelBulkAction();

        // Assert Processing pop-up
        assertProcessingLabelsPopUp();

        // Assert assigned label
        // assignedLabelView();

        // Assert Snackbar
        // usersRegisteredTab().getUserSnackbar().shouldBe(visible);
        // usersRegisteredTab().getUserSnackbar()
        //     .shouldBe(exactText("Changes has been saved for 1 item"));
    }

    @TestRailTest(caseId = 1611)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Nudge Users")
    void bulkActionNudgeUser(final List<NewUserInput> users) {

        // Click on Assign - Nudge User
        userService().searchUser(users.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickUsersRegisteredBulkActionNudgeUser();

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmBulkActionPopUp().getProceedNudgeButton().click();

        // Assert Snackbar
        nudgePopup().getSnackbarMessage().shouldBe(visible);
        nudgePopup()
                .getSnackbarMessage()
                .shouldBe(Condition.exactText("Messages were sent successfully"));
    }

    @TestRailTest(caseId = 1613)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Export Report")
    void bulkActionExportReport() {

        // Click on Export Report
        usersService().clickUsersRegisteredBulkActionExportReport();

        // Assert downloaded file
        assertDownloadedFile("Report.csv");
    }

    @TestRailTest(caseId = 1614)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Bulk Action: Delete Users")
    void bulkActionDeleteUser(final List<NewUserInput> user) {

        // Click on Assign - Delete User
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickUsersRegisteredBulkActionDeleteUsers();

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmBulkActionPopUp().getProceedButton().click();

        // Assert Processing pop-up
        assertProcessingDeleteUsersPopUp();

        // Assert Snackbar
        usersRegisteredTab().getUserSnackbar().shouldBe(visible);
        usersRegisteredTab().getUserSnackbar().shouldBe(exactText("Users have been deleted."));

        // Assert No Search Result page
        assertNoSearchResults();
    }
}
