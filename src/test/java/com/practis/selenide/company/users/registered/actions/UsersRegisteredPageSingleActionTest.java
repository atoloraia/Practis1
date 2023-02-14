package com.practis.selenide.company.users.registered.actions;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignPsAndDueDateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegistered;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegisteredNoLabels;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedLabelView;
import static com.practis.web.selenide.validator.popup.WarningDeletePopUpValidator.assertWarningDeleteUsersPopUp;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateModule;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPsAndDueDateModuleEmpty;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertPractisSetData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;
import static com.practis.web.selenide.validator.user.UserSettingsValidator.assertUserSettingsPage;
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

    @TestRailTest(caseId = 23903)
    @DisplayName("Users: Registered: Single Action: No Labels + Check Elements")
    void singleActionUsersRegisteredNoLabels() {

        // asser single action Users - Registered - without labels
        usersService().clickSingleAction();
        assertSingleActionUsersRegisteredNoLabels();
    }

    @TestRailTest(caseId = 1618)
    @LabelExtension(count = 1)
    @DisplayName("Users: Registered: Single Action: Check Elements")
    void singleActionUsersRegistered() {

        // asser single action Users - Registered - with labels
        Selenide.refresh();
        usersService().clickSingleAction();
        assertSingleActionUsersRegistered();
    }

    @TestRailTest(caseId = 1625)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: View Profile")
    void singleActionViewProfile(final List<NewUserInput> user) {

        // Click on View Profile
        usersService().clickSingleActionViewProfile(user.get(0).getEmail());

        // Assert 'User Profile' page for the Registered User
        assertUserProfile();
    }

    @TestRailTest(caseId = 1626)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: User Settings")
    void singleActionUserSettings(final List<NewUserInput> user) {

        // Click on User Settings
        usersService().clickSingleActionUserSettings(user.get(0).getEmail());

        // Assert 'User Settings' page for the Registered User
        assertUserSettingsPage();
    }

    @TestRailTest(caseId = 23904)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Empty State")
    void singleActionAssignPsEmptyState() {

        // Click on Assign PSs
        usersService().clickSingleActionAssignPs();

        // Assert empty Assign Practis Sets modal
        assertAssignPsAndDueDateModuleEmpty();
    }

    @TestRailTest(caseId = 23905)
    @PractisSetExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Apply")
    void singleActionAssignPs(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        // Click on Assign PSs
        Selenide.refresh();
        userService().searchUser(users.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickSingleActionAssignPs();

        // Assert Assign Practis Sets modal
        assertAssignPsAndDueDateModule();

        // Assign Practis Set to User
        assignPsAndDueDateService().clickSelectPractisSet(practisSets);
        usersPage().getUserRowValue().get(3).shouldBe(Condition.exactText("1"));

        // Open User Profile Page
        usersPage().getUserRowValue().get(1).click();
        assertUserData(users.get(0));
        assertPractisSetData(practisSets.get(0));
    }

    @TestRailTest(caseId = 25652)
    @LabelExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Labels: Apply")
    void singleActionAssignLabels(
            final List<NewUserInput> user, final List<RestCreateLabelResponse> label) {

        // Click on Assign Labels
        Selenide.refresh();
        usersService().clickSingleActionAssignLabels(user.get(0).getEmail());

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Registered User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabel();

        // Assert assigned label
        assignedLabelView();
    }

    @TestRailTest(caseId = 25959)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Nudge User")
    void singleActionNudgeUser(final List<NewUserInput> user) {

        // Click on Nudge User
        usersService().clickSingleActionNudgeUser(user.get(0).getEmail());

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().SendNudge("Test Text");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(visible);
        snackbar().getMessage().shouldBe(Condition.exactText("Message was sent successfully"));
    }

    @TestRailTest(caseId = 25960)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Export Report")
    void singleActionExportReport(final List<NewUserInput> user) {

        // Click on Export Report
        usersService().clickSingleActionExportReport(user.get(0).getEmail());

        // Assert downloaded file
        assertDownloadedFile("Report.csv");
    }

    @TestRailTest(caseId = 25961)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Delete User")
    void singleActionDeleteUser(final List<NewUserInput> user) {

        // Click on 3 dot - Delete User
        userService().searchUser(user.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickSingleActionDeleteUser(user.get(0).getEmail());

        // Assert Warning pop-up
        assertWarningDeleteUsersPopUp();

        // Click on Proceed
        usersService().clickSingleActionDeleteUserProceed();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(visible);
        snackbar().getMessage().shouldBe(exactText("1 User has been deleted"));

        // Assert No Search Result page
        Selenide.refresh();
        userService().searchUser(user.get(0).getFirstName());
        assertNoSearchResults();
    }
}
