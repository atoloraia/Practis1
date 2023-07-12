package com.practis.selenide.company.users.registered.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignPsAndDueDateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.registeredUsersService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.service.company.RegisteredUsersService.assertPsCountOnUsersPage;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedLabelView;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertSingleActionNoLabels;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertSingleActionUsersRegistered;
import static com.practis.web.selenide.validator.popup.ConfirmAndWarningPopUpsValidator.assertConfirmationModal;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDate;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateEmpty;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertPractisSetData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfileWithAssignedLabel;
import static com.practis.web.selenide.validator.user.UserSettingsValidator.assertUserSettingsPage;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
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
public class UsersRegisteredPageSingleActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getUsersNavigationItem().click();
    }

    @TestRailTest(caseId = 32072)
    @DisplayName("Users: Registered: Single Action: No Labels + Check Elements")
    void checkElementsSingleActionUsersRegisteredNoLabels() {

        // asser single action Users - Registered - without labels
        usersService().clickSingleAction();
        assertSingleActionNoLabels();
    }

    @TestRailTest(caseId = 31975)
    @LabelExtension(count = 1)
    @DisplayName("Users: Registered: Single Action: Check Elements")
    void checkElementsSingleActionUsersRegistered() {

        // asser single action Users - Registered - with labels
        Selenide.refresh();
        usersService().clickSingleAction();
        assertSingleActionUsersRegistered();
    }

    @TestRailTest(caseId = 31976)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: View Profile")
    void registeredUsersSingleActionViewProfile(final List<NewUserInput> user) {

        // Click on View Profile
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionViewProfile();

        // Assert 'User Profile' page for the Registered User
        assertUserProfile();
    }

    @TestRailTest(caseId = 31977)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: User Settings")
    void registeredUsersSingleActionUserSettings(final List<NewUserInput> user) {

        // Click on User Settings
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionUserSettings();

        // Assert 'User Settings' page for the Registered User
        assertUserSettingsPage("User");
    }

    @TestRailTest(caseId = 31978)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Empty State")
    void registeredUsersSingleActionAssignPsEmptyState(final List<NewUserInput> user) {

        // Click on Assign PSs
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionAssignPs();

        // Assert empty Assign Practis Sets modal
        assertAssignPsAndDueDateEmpty();
    }

    @TestRailTest(caseId = 31979)
    @PractisSetExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Apply")
    void registeredUsersSingleActionAssignPs(
            final List<NewUserInput> user, final List<NewPractisSetInput> practisSets) {

        // Click on Assign PSs
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionAssignPs();

        // Assert Assign Practis Sets modal
        assertAssignPsAndDueDate("No Practis Sets selected");

        // Assign Practis Set to User
        assignPsAndDueDateService().clickSelectPractisSet(practisSets);
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Changes have been saved"));
        assertPsCountOnUsersPage(user.get(0).getFirstName(), "1");

        // Open User Profile Page
        registeredUsersService().clickUserRow(user.get(0).getFirstName());
        assertUserData(user.get(0));
        assertPractisSetData(practisSets.get(0));
    }

    @TestRailTest(caseId = 31980)
    @LabelExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Labels: Apply")
    void registeredUsersSingleActionAssignLabels(
            final List<NewUserInput> user, final List<RestCreateLabelResponse> label) {

        // Click on Assign Labels
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionAssignLabels();

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Registered User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabel();

        // Assert assigned label
        assignedLabelView(user.get(0).getFirstName(), "1");

        // Check assigned Label on Registered User Profile page
        assertUserProfileWithAssignedLabel(label);
    }

    @TestRailTest(caseId = 31981)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Nudge User")
    void registeredUsersSingleActionNudgeUser() {

        // Click on Nudge User
        registeredUsersService().clickSingleActionNudgeUser();

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(Condition.exactText("Message was sent successfully"));
    }

    @TestRailTest(caseId = 31982)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Export Report")
    void registeredUsersSingleActionExportReport() {

        // Click on Export Report
        registeredUsersService().clickSingleActionExportReport();

        // Assert downloaded file
        assertDownloadedFile("Report.csv");
    }

    @TestRailTest(caseId = 31983)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Deactivate User")
    void registeredUsersSingleActionDeleteUser(final List<NewUserInput> user) {

        // Click on 3 dot - Delete User
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        registeredUsersService().clickSingleActionDeactivateUser();

        // Assert Warning pop-up
        assertConfirmationModal(
                "Deactivate this user?",
                "This action will prevent this user from accessing the platform, but it will not"
                        + " delete their data. To re-activate this user you will need to contact"
                        + " Practis Support.",
                "Deactivate",
                "Cancel");

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(exactText("1 User has been deactivated"));

        // Assert No Search Result page
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        assertNoSearchResults("No Users Found");
    }
}
