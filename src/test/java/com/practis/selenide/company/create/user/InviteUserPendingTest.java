package com.practis.selenide.company.create.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.invitingUsersPopUpPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.company.navigation.UserValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.popup.InvitingUsersPopUpValidator.asserInvitingUsersPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserProblemGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.asserSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertCleanSearchUsers;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertClickClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDeleteExistingUsersButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDeleteUsersButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDisabledSearch;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenDeleteButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteUsersSearch;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteUsersSearchAfter1Char;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUser;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUsers;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchFResults;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchResultsOnPendingTab;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneLabelSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOnePractisSetSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneTeamSelected;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneUserDelete;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertRequiredUserGridRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenOneFromManyInvitation;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSearchField;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSeveralUsersDelete;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUsersSearchResult;
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
public class InviteUserPendingTest {

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
    @DisplayName("InviteUserPendingTest: User Role")
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
        asserInvitingUsersPopUp();

        // Check snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // search and view user in 'Pending User' list
        assertInvitedUser(inputData, label.get(0), team.get(0), practisSet.get(0));
    }

    /** Invite User to the App: Invite Selected Users with Admin Role. */
    @TestRailTest(caseId = 8844)
    @DisplayName("InviteUserPendingTest: Admin Role")
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
        asserInvitingUsersPopUp();

        // assert snackbar message "All Users have been invited"
        snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        // search and view user in 'Pending User' list
        assertInvitedUser(inputData, label.get(0), team.get(0), practisSet.get(0));
    }

    /** Invite User to the App: Invite not all users. */
    @TestRailTest(caseId = 1127)
    @DisplayName("InviteUserPendingTest: Invite not all users")
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
        // TODO should be passed after fixing DEV-10495
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
    @DisplayName("InviteUserPendingTest: Invite all users")
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
                            var userRow = searchPendingUser(inputs.get(idx));
                            assertUserGridRowPending(inputs.get(idx), userRow);
                            // view User Profile
                            userRow.click();
                            InviteUserValidator.assertUser(
                                    inputs.get(idx), team.get(0), label.get(0), practisSet.get(0));

                            PractisUtils.clickOutOfTheForm();
                            userService().openPendingUsersList();
                        });
    }

    /** Invite User to the App: One user with already existing email: Invite . */
    @TestRailTest(caseId = 11674)
    @DisplayName("InviteUserPendingTest: One user with existing email: Invite ")
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

        // Check snackbar messages
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage().shouldBe(exactText("1 User already exist in our system"));

        asserSelectionPanel();
        asserProblemGridRow(0, "User’s email exists in our system");

        // Delete existing users
        inviteUsersPage().getDeleteExistingUsersButton().click();
        Assertions.assertEquals("1 Existing user has been removed", snackbar().getMessage().text());
        snackbar().getMessage().shouldBe(exactText("1 Existing user has been removed"));
    }

    @TestRailTest(caseId = 1129)
    @DisplayName("InviteUserPendingTest: Some Users with existing emails: All selection")
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
    @DisplayName("InviteUserPendingTest: All Users with existing emails: All selection")
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

        // Check snackbar messages
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage().shouldBe(exactText("2 Users already exist in our system"));

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
    @DisplayName("InviteUserPendingTest: Invite users with existing emails: Partially selection")
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
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage().shouldBe(exactText("All Users have been invited"));

        asserInvitingUsersPopUp();
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));
        userService().openPendingUsersListWithoutSaving();

        // Asserts invited users
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));
    }

    @TestRailTest(caseId = 1140)
    @DisplayName("InviteUserPendingTest: Invite with existing emails: Select All-Unselect some.")
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
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage()
        // .shouldBe(exactText("1 User has been invited but 1  user already exist in our system"));

        // Check the list contains 2 User rows
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // open Pending page
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(1), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(0).getEmail());
        assertNoSearchResultsOnPendingTab();
    }

    @TestRailTest(caseId = 1141)
    @DisplayName(
            "InviteUserPendingTest with already existing Users: "
                    + "Select All-Unselect all-invite some.")
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
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage()
        // .shouldBe(exactText("1 User has been invited but 1 user already exist in our system"));

        // Check the list contains 3 User rows and check one 'problem' User row
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // assert invited User
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(1).getEmail());
        assertNoSearchResultsOnPendingTab();
    }

    @TestRailTest(caseId = 1142)
    @DisplayName(
            "InviteUserPendingTest with already existing Users: Select All, "
                    + "Clear selection, invite some.")
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
        // TODO Should be passed after DEV-10499
        // snackbar().getMessage()
        // .shouldBe(exactText("1 User has been invited but 1 user already exist in our system"));

        // Check the list contains 3 User rows and check one 'problem' User row
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));

        // assert invited User
        userService().openPendingUsersListWithoutSaving();
        assertInvitedUser(inputs.get(0), label.get(0), team.get(0), practisSet.get(0));

        // Check that the others Users were not invited
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
        userService().searchUser(inputs.get(1).getEmail());
        assertNoSearchResultsOnPendingTab();
    }

    /** Invite User to the App: User counter. */
    @TestRailTest(caseId = 9525)
    @DisplayName("InviteUserTest: User counter")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserCounter(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(4);
        final var role = "Admin";

        assertHiddenUserCounter();

        // Add some Users
        userService().addRow(inputs.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        assertUserCounter("1 item");
        userService().addRow(inputs.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        assertUserCounter("2 items");
        userService().addRow(inputs.get(2), role, label.get(0), team.get(0), practisSet.get(0));
        assertUserCounter("3 items");

        // Delete row
        userService().deleteRow(0);

        assertScreenAfterAddingRow();
        assertUserCounter("2 items");
    }

    /** Invite User to the App: Search field. */
    @TestRailTest(caseId = 1115)
    @DisplayName("InviteUserTest: Search")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserSearch(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(4);
        final var role = "Admin";

        assertDisabledSearch();

        // Add some Users
        userService().addRow(inputs.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        assertSearchField();
        userService().addRow(inputs.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        assertSearchField();

        // assert search Users - First name
        assertInviteUsersSearchAfter1Char(inputs.get(0).getFirstName());
        assertUsersSearchResult(inputs.get(0).getFirstName());
        assertInviteUsersSearch(inputs.get(0).getFirstName());

        // assert search Users - Last name
        assertInviteUsersSearchAfter1Char(inputs.get(0).getLastName());
        assertUsersSearchResult(inputs.get(0).getLastName());
        assertInviteUsersSearch(inputs.get(0).getLastName());

        // assert search Users - email
        assertInviteUsersSearchAfter1Char(inputs.get(0).getLastName());
        assertUsersSearchResult(inputs.get(0).getEmail());
        assertInviteUsersSearch(inputs.get(0).getEmail());

        // assert clean search

        // assert empty state
        userService().searchUsersToInvite("no search result");
        assertNoSearchFResults();

        // assert clean search
        assertCleanSearchUsers(2);

        // Delete row
        userService().deleteRow(0);
        userService().deleteRow(0);
        assertDisabledSearch();
    }

    /** Invite User to the App: Delete Users. */
    @TestRailTest(caseId = 14129)
    @DisplayName("InviteUserTest: Delete Users")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserDelete(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSet) {
        // TODO Add Practis Set and assert
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(3);
        final var role = "Admin";

        assertHiddenDeleteButton();

        // Add some Users
        userService().addRow(inputs.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(2), role, label.get(0), team.get(0), practisSet.get(0));
        assertDeleteUsersButton();
        assertSeveralUsersDelete();
        assertOneUserDelete();
    }

    /** Invite User to the App: Clear selection. */
    @TestRailTest(caseId = 14127)
    @DisplayName("InviteUserTest: Clear selection")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserClearSelection(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(3);
        final var role = "User";

        assertHiddenClearSelectionButton();

        // Add some Users
        userService().addRow(inputs.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(inputs.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        assertClearSelectionButton();
        assertClickClearSelectionButton();
    }

    /** Assert Remove Existing Users button . */
    @TestRailTest(caseId = 14128)
    @DisplayName("Selection panel: Remove all existing users")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 4)
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void removeExistingUsersButton(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewUserInput> users,
            final List<NewPractisSetInput> practisSet) {
        Selenide.refresh();

        // Invite User with already existing email
        userService().addRow(users.get(0), "Admin", label.get(0), team.get(0), practisSet.get(0));
        userService().inviteAllUser();

        // assert Inviting model appears
        asserInvitingUsersPopUp();
        awaitElementNotExists(10, () -> invitingUsersPopUpPopUp().getInvitingUsersTitle());

        assertDeleteExistingUsersButton();
        inviteUsersPage().getDeleteExistingUsersButton().click();
        Assertions.assertEquals("1 Existing user has been removed", snackbar().getMessage().text());
        snackbar().getMessage().shouldBe(exactText("1 Existing user has been removed"));
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
    }

    /** Assert Selection panel. */
    @TestRailTest(caseId = 14126)
    @DisplayName("Selection panel: Check Elements")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserSelectionPanel(
            final List<RestCreateLabelResponse> label, final List<NewTeamInput> team) {
        // TODO Add Practis Set and assert
        Selenide.refresh();

        // generate input data for Users
        final var inputs = userService().generateUserInputs(2);
        final var role = "User";

        assertHiddenSelectionPanel();

        // Add some Users
        userService().addRow(inputs.get(0), role, label.get(0), team.get(0));
        userService().addRow(inputs.get(1), role, label.get(0), team.get(0));

        assertSelectionPanel();
    }

    @AfterEach
    void cleanup() {
        //      usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
