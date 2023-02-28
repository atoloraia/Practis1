package com.practis.selenide.company.create.user.action;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.service.company.UsersService.searchUser;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertNoSearchResults;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUserGridRow;
import static com.practis.web.selenide.validator.popup.ProcessingPopUpValidator.asserProcessingPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserProblemGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUsers;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneLabelSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOnePractisSetSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.lang.String.format;
import static java.util.stream.IntStream.range;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.web.selenide.validator.user.InviteUserValidator;
import com.practis.web.util.PractisUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserTest {

    private List<String> usersToRemove;
    private NewUserInput inputData;

    @BeforeEach
    void init() {
        newItemSelector().create("User");

        inputData = getNewUserInput();
        inputData.setEmail(format(inputData.getEmail(), timestamp()));
        inputData.setFirstName(format(inputData.getFirstName(), timestamp()));

        usersToRemove = new ArrayList<>();
        usersToRemove.add(inputData.getEmail());
    }

    /** Invite User to the App: Invite Selected Users with User Role. */
    @TestRailTest(caseId = 8735)
    @DisplayName("Invite User to the App: Invite Selected Users with User Role")
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUser(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            List<NewPractisSetInput> practisSet) {

        Selenide.refresh();
        userService().addRow(inputData, "User", label.get(0), team.get(0), practisSet.get(0));

        // assert User row
        assertScreenAfterAddingRow();
        assertRequiredUserGridRow(inputData, "User", 0);
        assertOneTeamSelected(0);
        assertOneLabelSelected(0);
        assertOnePractisSetSelected(0);
        assertUserCounter("1 item");

        // select the user and click "Invite Selected Users" button
        userService().inviteFirstUser();

        // assert Inviting model appears
        asserProcessingPopUp("Inviting Users");

        // Check snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // search and view user in 'Pending User' list
        assertInvitedUser(inputData, label.get(0), team.get(0), practisSet.get(0));
    }

    /** Invite User to the App: Invite Selected Users with Admin Role. */
    @TestRailTest(caseId = 8844)
    @DisplayName("Invite User to the App: Invite Selected Users with Admin Role")
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteAdmin(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            List<NewPractisSetInput> practisSet) {

        Selenide.refresh();
        userService().addRow(inputData, "Admin", label.get(0), team.get(0), practisSet.get(0));

        // assert User row
        assertScreenAfterAddingRow();
        assertRequiredUserGridRow(inputData, "Admin", 0);
        assertOneTeamSelected(0);
        assertOneLabelSelected(0);
        assertUserCounter("1 item");

        // select the user and click "Invite Selected Users" button
        userService().inviteFirstUser();

        // assert Inviting model appears
        asserProcessingPopUp("Inviting Users");

        // assert snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // search and view user in 'Pending User' list
        assertInvitedUser(inputData, label.get(0), team.get(0), practisSet.get(0));
    }

    /** Invite User to the App: Invite not all users. */
    @TestRailTest(caseId = 1127)
    @DisplayName("Invite User to the App: Invite not all users")
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteNotAllUsers(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate data for Users
        final var inputs = userService().generateUserInputs(3);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        final var role = "User";

        // add users
        range(0, inputs.size())
                .forEach(
                        idx ->
                                userService()
                                        .addRow(
                                                inputs.get(idx),
                                                role,
                                                label.get(0),
                                                team.get(0),
                                                practisSet.get(0)));

        // assert counter
        assertUserCounter("3 items");

        // select the first user and click "Invite Selected Users" button
        userService().inviteFirstUser();

        // Check snackbar message "1 User has been invited."
        snackbar().getMessage().shouldBe(exactText("1 User has been invited"));

        // assert screen after invitation
        awaitElementNotExists(10, () -> snackbar().getMessage());
        assertScreenOneFromManyInvitation();
        inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(2));

        // open Pending page
        userService().exitWithoutSaving();
        userService().openPendingUsersList();

        // search and view user in 'Pending User' list
        assertInvitedUser(inputs.get(2), label.get(0), team.get(0), practisSet.get(0));
    }

    /** Invite User to the App: Invite All users. */
    @TestRailTest(caseId = 1162)
    @DisplayName("Invite User to the App: Invite All users")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteAllUsers(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(3);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        final var role = "User";

        // add users
        range(0, inputs.size())
                .forEach(
                        idx ->
                                userService()
                                        .addRow(
                                                inputs.get(idx),
                                                role,
                                                label.get(0),
                                                team.get(0),
                                                practisSet.get(0)));

        // select all user and click "Invite Selected Users" button
        userService().inviteAllUser();

        // Check snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // Asserts invited users
        IntStream.range(0, 2)
                .forEach(
                        idx -> {
                            var userRow = searchUser(inputs.get(idx));
                            assertUserGridRow(inputs.get(idx), userRow);
                            // view User Profile
                            userRow.click();
                            InviteUserValidator.assertUser(
                                    inputs.get(idx), team.get(0), label.get(0), practisSet.get(0));

                            PractisUtils.clickOutOfTheForm();
                            userService().openPendingUsersList();
                        });
    }

    /** Invite User to the App: One user with already existing email: Invite. */
    @TestRailTest(caseId = 11674)
    @DisplayName("Invite User to the App: One user with existing email: Invite")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void oneUserExistsInviteAllSelection(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // Invite User with already existing email
        userService().addRow(users.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().inviteAllUser();

        asserSelectionPanel();
        asserProblemGridRow(0, "User’s email exists in our system");

        // Delete existing users
        inviteUsersPage().getDeleteExistingUsersButton().click();
        Assertions.assertEquals("1 Existing user has been removed", snackbar().getMessage().text());
        snackbar().getMessage().shouldBe(exactText("1 Existing user has been removed"));
    }

    @TestRailTest(caseId = 1129)
    @DisplayName("Invite User to the App: Some Users with already existing emails: Select All")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void severalUsersExistInviteAllSelection(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));

        // Add some Users with already existing emails
        userService().addRow(users.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().inviteAllUser();

        // Check snackbar messages
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // Asserts invited users
        assertInvitedUsers(inputs, label.get(0), team.get(0), practisSet.get(0));
    }

    @TestRailTest(caseId = 15691)
    @DisplayName("Invite User to the App: All Users with already existing emails: Select All")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void allUsersExistInviteAllSelection(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // Add some Users with already existing emails
        userService().addRow(users.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().inviteAllUser();

        asserSelectionPanel();
        asserProblemGridRow(0, "User’s email exists in our system");
        asserProblemGridRow(1, "User’s email exists in our system");

        // Delete existing users
        inviteUsersPage().getDeleteExistingUsersButton().click();
        Assertions.assertEquals(
                "2 Existing users have been removed", snackbar().getMessage().text());
        snackbar().getMessage().shouldBe(exactText("2 Existing users have been removed"));
    }

    @TestRailTest(caseId = 1135)
    @DisplayName(
            "Invite User to the App: Invite users with already existing emails: "
                    + "Partially selection")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void someUsersExistInvitePartiallySelection(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));

        // Add some Users with already existing emails
        userService().addRow(users.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().inviteSomeUser(1, 2);
        awaitFullPageLoad(10);

        // Check snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("2 Users have been invited"));

        asserProcessingPopUp("Inviting Users");
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));
        userService().openPendingUsersListWithoutSaving();

        // Asserts invited users
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));
    }

    @TestRailTest(caseId = 1140)
    @DisplayName(
            "Invite User to the App: Invite users with already existing emails: Selection All "
                    + "-> Unselect some Users")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void someUsersExistInviteAllUnselectSome(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        final var role = "Admin";

        // Add some Users with already existing emails
        userService().addRow(users.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(0), "User", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), "User", label.get(0), team.get(0), practisSet.get(0));
        // Select all users
        inviteUsersPage().getSelectAllCheckbox().click();
        // Unselect some Users and click "Invite Selected Users" button
        userService().inviteSomeUser(1, 2);

        // Check snackbar notifications
        snackbar().getMessage().shouldBe(exactText("2 Users have been invited"));

        // Check the list contains 2 User rows
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // open Pending page
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(1), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(0).getEmail());
        assertNoSearchResults("No Users Found");
    }

    @TestRailTest(caseId = 1141)
    @DisplayName(
            "Invite User to the App: Invite users with already existing emails: Selection All "
                    + "-> Unselect -> Select some Users")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void someUsersExistSelectAllUnselectAllInviteSome(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        final var role = "Admin";

        // Add some Users with already existing emails
        userService().addRow(users.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(0), "User", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), "User", label.get(0), team.get(0), practisSet.get(0));
        // Select all users
        inviteUsersPage().getSelectAllCheckbox().click();
        // Unselect all users
        inviteUsersPage().getSelectAllCheckbox().click();
        // Unselect some Users and click "Invite Selected Users" button
        userService().inviteSomeUser(1, 2);

        // Check snackbar notifications
        snackbar().getMessage().shouldBe(exactText("2 Users have been invited"));

        // Check the list contains 3 User rows and check one 'problem' User row
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // assert invited User
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(1).getEmail());
        assertNoSearchResults("No Users Found");
    }

    @TestRailTest(caseId = 1142)
    @DisplayName(
            "Invite User to the App: Invite users with already existing emails: "
                    + "Selection All -> Clear -> Select some Users")
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void someUsersExistClearSelectionInviteSome(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        final var role = "Admin";

        // Add some Users with already existing emails
        userService().addRow(users.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(users.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(0), "User", label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), "User", label.get(0), team.get(0), practisSet.get(0));
        // Select all users
        inviteUsersPage().getSelectAllCheckbox().click();
        // Click on Clear Selection
        inviteUsersPage().getClearSelectionButton().click();
        // Unselect some Users and click "Invite Selected Users" button
        userService().inviteSomeUser(1, 2);

        // Check snackbar notifications
        snackbar().getMessage().shouldBe(exactText("2 Users have been invited"));

        // Check the list contains 3 User rows and check one 'problem' User row
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // assert invited User
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(1).getEmail());
        assertNoSearchResults("No Users Found");
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
