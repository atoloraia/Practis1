package com.practis.selenide.company.users.pending.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.pendingUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUsersEmptyState;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedLabelView;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertSingleActionUsersPending;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertSingleActionUsersPendingNoLabels;
import static com.practis.web.selenide.validator.popup.ConfirmAndWarningPopUpsValidator.assertWarningRevokeUserPopUp;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertPendingUserProfile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfileWithAssignedLabel;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UsersPendingPageSingleActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getUsersNavigationItem().click();
        userService().openPendingUsersList();
    }

    @TestRailTest(caseId = 26910)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: No Labels + Check Elements")
    void checkElementsSingleActionPendingUsersNoLabels() {

        // asser single action Users - Pending - without labels
        usersService().clickSingleAction();
        assertSingleActionUsersPendingNoLabels();
    }

    @TestRailTest(caseId = 26908)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    @DisplayName("Users: Pending: Single Action: Check Elements")
    void checkElementsSingleActionUsersPending() {

        // asser single action Users - Pending - with labels
        Selenide.refresh();
        usersService().clickSingleAction();
        assertSingleActionUsersPending();
    }

    @TestRailTest(caseId = 1659)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: View Profile")
    void pendingUsersSingleActionViewProfile() {

        // Click on View Profile
        pendingUsersService().clickSingleActionViewProfile();

        // Assert 'User Profile' page for the Pending User
        assertPendingUserProfile();
    }

    @TestRailTest(caseId = 26911)
    @LabelExtension(count = 1)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: Assign Labels: Apply")
    void pendingUsersSingleActionAssignLabels(
            final List<RestCreateLabelResponse> label, final List<NewUserInput> user) {

        // Click on Assign Labels
        Selenide.refresh();
        pendingUsersService().clickSingleActionAssignLabels();

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Pending User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabel();

        // Assert assigned label
        assignedLabelView(user.get(0).getFirstName(), "1");

        // Check assigned Label on Pending User Profile page
        assertUserProfileWithAssignedLabel(label);
    }

    @TestRailTest(caseId = 26912)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: Resend Invite")
    void pendingUsersSingleActionResendInvite() {

        // asser single action Users - Pending - Resend Invite
        pendingUsersService().clickSingleActionResendInvite();

        // assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Invite has been sent"));
    }

    @TestRailTest(caseId = 1660)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: Copy Invite Text")
    void pendingUsersSingleActionCopyInviteText() {

        // asser single action Users - Pending - Copy Invite Text
        pendingUsersService().clickSingleActionCopyInviteText();

        // assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Invite text has been copied"));
    }

    @TestRailTest(caseId = 26913)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Pending: Single Action: Revoke")
    void pendingUsersSingleActionRevoke() {

        // Click on 3 dot - Revoke
        pendingUsersService().clickSingleActionRevoke();

        // Assert Warning pop-up
        assertWarningRevokeUserPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("Invite has been revoked"));

        // Assert No Search Result page
        Selenide.refresh();
        assertUsersEmptyState("No Pending Users Yet");
    }
}
