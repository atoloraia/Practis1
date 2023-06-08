package com.practis.selenide.company.create.user;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertCleanSearchUsers;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDisabledSearch;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertElementsOnInviteUsersPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteUsersSearch;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInviteUsersSearchAfter1Char;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertNoSearchFResults;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertScreenAfterAddingRow;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSearchField;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUsersSearchResult;
import static java.lang.String.format;

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
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserPageTest {

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

    /** Invite User to the App: Check WEB Elements. */
    @TestRailTest(caseId = 31893)
    @DisplayName("Invite User to the App: Check Elements")
    void checkElementsInviteUser() {
        assertElementsOnInviteUsersPage();
    }

    @TestRailTest(caseId = 31894)
    @DisplayName("Invite User to the App: Download Template button")
    void checkDownloadTemplate() throws FileNotFoundException {
        assertDownloadButton();
        final File downloaded = inviteUsersPage().getDownloadTemplateButton().download();
        assertDownloadedFile(downloaded, "List+of+Users+to+Add+v3.xlsx");
    }

    /** Invite User to the App: User counter. */
    @TestRailTest(caseId = 31895)
    @DisplayName("Invite User to the App: User counter")
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
    @TestRailTest(caseId = 31896)
    @DisplayName("Invite Users to the App: Search")
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
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
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

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().deleteUser(email));
    }
}
