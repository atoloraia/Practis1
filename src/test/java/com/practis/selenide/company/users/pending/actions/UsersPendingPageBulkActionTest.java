package com.practis.selenide.company.users.pending.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.processingPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.pendingUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedLabelView;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertBulkActionUsersPending;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.selenide.validator.popup.ProcessingPopUpValidator.asserProcessingPopUp;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfileWithAssignedLabel;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersPendingPageBulkActionTest {

    @TestRailTest(caseId = 32001)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Bulk Action: Check Elements")
    void checkElementsBulkActionUsersPending() {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        navigationCompany().getUsersNavigationItem().click();
        userService().openPendingUsersList();

        // asser bulk action Users - Pending
        usersService().clickBulkAction();
        assertBulkActionUsersPending();
    }

    @TestRailTest(caseId = 32002)
    @LabelExtension(count = 1)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Bulk Action: Assign Labels: Apply")
    void pendingUsersBulkActionAssignLabels(
            final List<RestCreateLabelResponse> label, final List<NewUserInput> user) {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        navigationCompany().getUsersNavigationItem().click();
        userService().openPendingUsersList();

        // Click on Assign - Assign Labels
        pendingUsersService().clickBulkActionAssignLabels();

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Pending User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabelBulkAction();

        // Assert Processing pop-up
        awaitSoft(5, () -> processingPopUp().getProcessTitle().isDisplayed());

        asserProcessingPopUp("Processing Labels");

        // Assert assigned label
        assignedLabelView(user.get(0).getFirstName(), "1");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Changes has been saved for 1 item"));

        // Check assigned Label on Pending User Profile page
        assertUserProfileWithAssignedLabel(label);
    }

    @TestRailTest(caseId = 32003)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    @DisplayName("Users: Pending: Bulk Action: Resend Invites")
    void pendingUsersBulkActionResendInvites() {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        navigationCompany().getUsersNavigationItem().click();
        userService().openPendingUsersList();

        // asser bulk action Users - Pending - Resend Invites
        pendingUsersService().clickBulkActionResendInvites();

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Processing pop-up
        asserProcessingPopUp("Resend Invitations");

        // assert Snackbar
        snackbar().getMessage().shouldBe(exactText("All Invites have been sent"));
    }

    @TestRailTest(caseId = 32004)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Bulk Action: Revoke")
    void pendingUsersBulkActionDeleteUser() {
        Selenide.refresh();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        navigationCompany().getUsersNavigationItem().click();
        userService().openPendingUsersList();

        // Click on Assign - Revoke
        pendingUsersService().clickBulkActionRevoke();

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Processing pop-up
        awaitElementExists(10, () -> processingPopUp().getProcessTitle());
        asserProcessingPopUp("Revoke Invitations");

        // Assert Snackbar
        await().pollDelay(TWO_SECONDS).until(() -> true);
        snackbar().getMessage().shouldBe(exactText("All Invites have been revoked"));

        // Assert No Search Result page
        Selenide.refresh();
        assertUsersEmptyState("No Pending Users Yet");
    }
}
