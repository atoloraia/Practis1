package com.practis.selenide.company.create.user.selection;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.processingPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.popup.ProcessingPopUpValidator.asserProcessingPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertClickClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDeleteExistingUsersButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDeleteUsersButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenClearSelectionButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenDeleteButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertOneUserDelete;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSelectionPanel;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertSeveralUsersDelete;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
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
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.web.util.PractisUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUsersSelectionPanelTest {

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

    /** Assert Selection panel. */
    @TestRailTest(caseId = 31917)
    @DisplayName("Invite User to the App: Selection panel: Check Elements")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserSelectionPanel(
            final List<RestCreateLabelResponse> label, final List<NewTeamInput> team) {
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

    /** Invite User to the App: Delete Users. */
    @TestRailTest(caseId = 31918)
    @DisplayName("Invite Users to the App: Selection panel: Delete selected User")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    @TeamExtension(count = 1)
    void inviteUserDelete(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSet) {
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
    @TestRailTest(caseId = 31919)
    @DisplayName("Invite Users to the App: Selection panel: Clear selection")
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
    @Disabled
    // @TestRailTest(caseId = 14128)
    @DisplayName("Invite Users to the App: Selection panel: Remove all existing users")
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
        asserProcessingPopUp("Inviting Users");
        awaitElementNotExists(10, () -> processingPopUp().getProcessTitle());

        assertDeleteExistingUsersButton();
        inviteUsersPage().getDeleteExistingUsersButton().click();
        Assertions.assertEquals("1 Existing user has been removed", snackbar().getMessage().text());
        snackbar().getMessage().shouldBe(exactText("1 Existing user has been removed"));
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
