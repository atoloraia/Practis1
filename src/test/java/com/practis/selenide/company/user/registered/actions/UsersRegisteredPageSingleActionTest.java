package com.practis.selenide.company.user.registered.actions;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignPsAndDueDateService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.usersService;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegistered;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertSingleActionUsersRegisteredNoLabels;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assignedAssignedLabelView;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertAssignPractisSetsAndDueDatesModulewithPs;
import static com.practis.web.selenide.validator.selection.AssignPractisSetsAndDueDatesValidator.assertEmptyAssignPractisSetsAndDueDatesModule;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelsModal;
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
    void checkElementsSingleActionUsersRegisteredNoLabels() {

        // asser single action Users - Registered - without labels
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegisteredNoLabels();
    }

    @TestRailTest(caseId = 1618)
    @LabelExtension(count = 1)
    @DisplayName("Users: Registered: Single Action: Check Elements")
    void checkElementsSingleActionUsersRegistered() {

        // asser single action Users - Registered - with labels
        Selenide.refresh();
        usersService().clickSingleActionUsersRegistered();
        assertSingleActionUsersRegistered();
    }

    @TestRailTest(caseId = 1625)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: View Profile")
    void checkElementsSingleActionRegisteredViewProfile(final List<NewUserInput> user) {

        // Click on View Profile
        usersService().clickUsersRegisteredSingleActionViewProfile(user.get(0).getEmail());

        // Assert 'User Profile' page for the Registered User
        assertUserProfile();
    }

    @TestRailTest(caseId = 1626)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: User Settings")
    void checkElementsSingleActionRegisteredUserSettings(final List<NewUserInput> user) {

        // Click on User Settings
        usersService().clickUsersRegisteredSingleActionUserSettings(user.get(0).getEmail());

        // Assert 'User Settings' page for the Registered User
        assertUserSettingsPage();
    }

    @TestRailTest(caseId = 23904)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Empty State")
    void checkElementsSingleActionRegisterAssignPsEmptyState() {

        // Click on Assign PSs
        usersService().clickUsersRegisteredSingleActionAssignPs();

        // Assert empty Assign Practis Sets modal
        assertEmptyAssignPractisSetsAndDueDatesModule();
    }

    @TestRailTest(caseId = 23905)
    @PractisSetExtension(count = 1)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("Users: Registered: Single Action: Assign Practis Sets: Apply")
    void checkElementsSingleActionRegisterAssignPs(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        // Click on Assign PSs
        Selenide.refresh();
        userService().searchUser(users.get(0).getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersService().clickUsersRegisteredSingleActionAssignPs();

        // Assert Assign Practis Sets modal
        assertAssignPractisSetsAndDueDatesModulewithPs();
        assignPractisSetsAndDueDatesModule().getCancelButton().click();

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
    void checkElementsSingleActionRegisteredAssignLabels(
            final List<NewUserInput> user, final List<RestCreateLabelResponse> label) {

        // Click on Assign Labels
        Selenide.refresh();
        usersService().clickUsersRegisteredSingleActionAssignLabels(user.get(0).getEmail());

        // Assert Labels modal
        assertLabelsModal();

        // Assert 'Assign Labels - apply' for the Registered User
        labelModuleService().selectLabel(label.get(0).getName());
        labelModuleService().assignLabel();

        // Assert assigned label
        assignedAssignedLabelView();
    }
}
