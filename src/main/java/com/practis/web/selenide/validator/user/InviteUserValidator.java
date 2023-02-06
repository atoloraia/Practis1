package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.downloadsFolder;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.teamModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.userRoleModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersDraftTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.service.company.UserService.searchPendingUser;
import static com.practis.web.selenide.validator.common.FileValidator.assertFileNameEqual;
import static com.practis.web.selenide.validator.company.navigation.UsersValidator.assertUserGridRowPending;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelsYet;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedPractisSet;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertAssignEmptyTeam;
import static com.practis.web.selenide.validator.selection.TeamSelectionValidator.assertSelectedTeam;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.RestTeamResponse;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.validator.selection.LabelSelectionValidator;
import com.practis.web.util.PractisUtils;
import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class InviteUserValidator {

    /** Assert elements on New Admin page. */
    public static void assertElementsOnInviteUsersPage() {
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage()
                .getInviteUsersToTheAppTitle()
                .shouldBe(exactText("Invite Users to the App"));

        inviteUsersPage().getSearchField().shouldBe(visible);
        inviteUsersPage().getFiltersButton().shouldBe(visible);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
        inviteUsersPage().getUploadTemplateButton().shouldBe(visible);

        // Table header
        inviteUsersPage().getFirstNameColumn().shouldBe(visible);
        inviteUsersPage().getFirstNameColumn().shouldBe(exactText("First Name"));
        inviteUsersPage().getLastNameColumn().shouldBe(visible);
        inviteUsersPage().getLastNameColumn().shouldBe(exactText("Last Name"));
        inviteUsersPage().getUserEmailColumn().shouldBe(visible);
        inviteUsersPage().getUserEmailColumn().shouldBe(exactText("User Email"));
        inviteUsersPage().getRoleColumn().shouldBe(visible);
        inviteUsersPage().getRoleColumn().shouldBe(exactText("Role"));
        inviteUsersPage().getTeamsColumn().shouldBe(visible);
        inviteUsersPage().getTeamsColumn().shouldBe(exactText("Teams"));
        inviteUsersPage().getPractisSetColumn().shouldBe(visible);
        inviteUsersPage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
        inviteUsersPage().getLabelsColumn().shouldBe(visible);
        inviteUsersPage().getLabelsColumn().shouldBe(exactText("Labels"));

        // User row
        inviteUsersPage().getFirstNameField().sibling(0).shouldBe(visible);
        inviteUsersPage().getFirstNameField().sibling(0).shouldBe(exactText("First Name*"));
        inviteUsersPage().getLastNameField().sibling(0).shouldBe(visible);
        inviteUsersPage().getLastNameField().sibling(0).shouldBe(exactText("Last Name*"));
        inviteUsersPage().getEmailField().sibling(0).shouldBe(visible);
        inviteUsersPage().getEmailField().sibling(0).shouldBe(exactText("Email*"));
        // Role modal
        inviteUsersPage().getRoleField().shouldBe(visible);
        inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
        inviteUsersPage().getRoleField().click();
        userRoleModule().getUserRoleRadioButtonInviteUser().shouldBe(visible);
        userRoleModule().getUserRoleRadioButtonInviteUser().shouldBe(exactText("User"));
        userRoleModule().getAdminRoleRadioButtonInviteUser().shouldBe(visible);
        userRoleModule().getAdminRoleRadioButtonInviteUser().shouldBe(exactText("Admin"));
        // Teams Modal
        inviteUsersPage().getTeamsField().shouldBe(visible);
        inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
        inviteUsersPage().getTeamsField().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertAssignEmptyTeam();
        teamModule().getCancelButton().click();

        // Practis Set modal
        inviteUsersPage().getPractisSetsField().shouldBe(visible);
        inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
        inviteUsersPage().getPractisSetsField().click();
        // TODO SHOULD BE FIXED AFTER DEV-10764
        inviteUserPsModule().getNoPractisSetYetTooltip().shouldBe(visible);
        inviteUserPsModule()
                .getNoPractisSetYetTooltip()
                .shouldBe(exactText("No practis sets added yet"));

        // Label Modal
        inviteUsersPage().getLabelsField().shouldBe(visible);
        inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
        inviteUsersPage().getLabelsField().click();
        assertNoLabelsYet();

        inviteUsersPage().getAddRowButton().shouldBe(visible);
        inviteUsersPage().getAddRowButton().shouldBe(disabled);
        inviteUsersPage().getAddUsersText().shouldBe(visible);
        inviteUsersPage()
                .getAddUsersText()
                .shouldBe(exactText("Add users to the table in order to edit or invite them"));
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(exactText("Not Saved"));

        inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
        inviteUsersPage().getSaveAsDraftButton().shouldBe(disabled);
        inviteUsersPage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
        inviteUsersPage()
                .getInviteSelectedUsersButton()
                .shouldBe(exactText("Invite Selected Users"));
    }

    /** Assert Team in User row. */
    public static void assertOneTeamSelected(int row) {
        inviteUsersPage().getTeam().get(row).shouldBe(matchText("1 Team"));
    }

    /** Assert Practis Set in User row. */
    public static void assertOnePractisSetSelected(int row) {
        inviteUsersPage().getPractisSet().get(row).shouldBe(matchText("1 Practis Set"));
    }

    /** Assert Label in User row. */
    public static void assertOneLabelSelected(int row) {
        inviteUsersPage().getLabel().get(row).shouldBe(matchText("1 Label"));
    }

    /** Assert 'Invite User" screen after adding row. */
    public static void assertScreenAfterAddingRow() {
        assertEnabledSaveAsDraft();
        assertDisabledInviteButton();
        assertEmptyTopRow();
        assertNoPrompt();
    }

    /** Assert 'Invite User" screen after adding row. */
    public static void assertUserCounter(String counter) {
        inviteUsersPage().getUserCounter().shouldBe(exactText(counter));
    }

    /** Assert added User row with required input data. */
    public static void assertRequiredUserGridRow(
            final NewUserInput inputData, final String role, int row) {
        inviteUsersPage().getFirstName().get(row).shouldBe(matchText(inputData.getFirstName()));
        inviteUsersPage().getLastName().get(row).shouldBe(matchText(inputData.getLastName()));
        inviteUsersPage().getEmail().get(row).shouldBe(matchText(inputData.getEmail()));
        inviteUsersPage().getRole().get(row).shouldBe(matchText(role));
    }

    /** Assert added User row without First Name. */
    public static void assertGridRowWithoutFirstName(
            final NewUserInput inputData, final String role) {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        inviteUsersPage().getFirstName().get(0).shouldBe(hidden);
        inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
        inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
        inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
    }

    /** Assert added User row without Last Name. */
    public static void assertGridRowWithoutLastName(
            final NewUserInput inputData, final String role) {
        // TODO resolve issue related to checking empty Last name
        inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
        inviteUsersPage().getLastName().get(0).shouldBe(hidden);
        inviteUsersPage().getEmail().get(0).shouldBe(matchText(inputData.getEmail()));
        inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
    }

    /** Assert added User row without Email. */
    public static void assertGridRowWithoutEmail(final NewUserInput inputData, final String role) {
        inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
        inviteUsersPage().getCheckboxWarningRow().get(0).click();
        inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
        inviteUsersPage()
                .getCheckboxWarningText()
                .shouldBe(exactText("Please edit before selecting"));

        inviteUsersPage().getFirstName().get(0).shouldBe(matchText(inputData.getFirstName()));
        inviteUsersPage().getFirstName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getLastName().get(0).shouldBe(matchText(inputData.getLastName()));
        inviteUsersPage().getLastName().get(0).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getEmail().get(0).shouldNotBe(visible);
        inviteUsersPage().getRole().get(0).shouldBe(matchText(role));
    }

    /** Assert added User row without Role. */
    public static void assertGridRowWithoutRole(final NewUserInput inputData, final int row) {
        inviteUsersPage().getCheckboxWarningRow().get(row).shouldBe(visible);
        inviteUsersPage().getCheckboxWarningRow().get(row).click();
        inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
        inviteUsersPage()
                .getCheckboxWarningText()
                .shouldBe(exactText("Please edit before selecting"));

        inviteUsersPage().getFirstName().get(row).shouldBe(matchText(inputData.getFirstName()));
        inviteUsersPage()
                .getFirstName()
                .get(row)
                .shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getLastName().get(row).shouldBe(matchText(inputData.getLastName()));
        inviteUsersPage()
                .getLastName()
                .get(row)
                .shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getEmail().get(row).shouldBe(matchText(inputData.getEmail()));
        inviteUsersPage().getEmail().get(row).shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getRole().get(row).shouldNotBe(visible);
    }

    /** Assert there is no prompt "Add users to the table in order to edit or invite them". */
    public static void assertNoPrompt() {
        inviteUsersPage().getAddUsersText().shouldNotBe(visible);
    }

    /** Assert screen when required field is empty. */
    public static void assertEmptyRequiredFiled() {
        inviteUsersPage().getAddUsersText().shouldNotBe(visible);
    }

    /** Assert enabled state of "Save as Draft" button. */
    public static void assertEnabledSaveAsDraft() {
        inviteUsersPage().getSaveAsDraftButton().shouldBe(enabled);
    }

    /** Assert disabled state of "Invite Selected Users" button. */
    public static void assertDisabledInviteButton() {
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
    }

    /** Assert the top empty row. */
    public static void assertEmptyTopRow() {
        inviteUsersPage().getFirstNameField().shouldBe(empty);
        inviteUsersPage().getLastNameField().shouldBe(empty);
        inviteUsersPage().getEmailField().shouldBe(empty);
        inviteUsersPage().getRoleField().shouldBe(exactText("Role*"));
        inviteUsersPage().getTeamsField().shouldBe(exactText("Teams"));
        inviteUsersPage().getPractisSetsField().shouldBe(exactText("Practis Sets"));
        inviteUsersPage().getLabelsField().shouldBe(exactText("Labels"));
    }

    /** Get email validation message. */
    public static SelenideElement getEmailValidationMessage() {
        return inviteUsersPage().getEmailValidationError();
    }

    /** Assert empty state. */
    public static void assertEmptyState() {
        inviteUsersPage().getAddUsersText().shouldBe(visible);
        inviteUsersPage()
                .getAddUsersText()
                .shouldBe(exactText("Add users to the table in order to edit or invite them"));
    }

    /** Assert data on User Profile. */
    public static void assertUser(
            final NewUserInput inputs, final String team, final String label) {
        assertUserData(inputs);
        userProfilePage().getAssignButton().click();
        assertSelectedTeam(team);
        assertSelectedLabel(label);
    }

    /** Assert data on User Profile. */
    public static void assertUser(
            final NewUserInput inputs,
            final NewTeamInput team,
            final RestCreateLabelResponse label,
            final NewPractisSetInput practisSet) {
        assertUserData(inputs);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(5);
        assertSelectedTeam(team.getName());
        assertSelectedLabel(label.getName());
        assertSelectedPractisSet(practisSet.getName());
    }

    /** Assert data on User Profile. */
    public static void assertUser(final NewUserInput inputs, final String team) {
        assertUserData(inputs);
        userProfilePage().getAssignButton().click();
        assertSelectedTeam(team);
    }

    /** Assert data on User Profile. */
    public static void assertPendingUser(final NewUserInput inputs) {
        assertUserData(inputs);
        userProfilePage().getPendingRegistrationLabel().shouldBe(visible);
        userProfilePage().getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUsers(
            final List<NewUserInput> inputs,
            final RestCreateLabelResponse label,
            final NewTeamInput team,
            final NewPractisSetInput practisSet) {
        IntStream.range(0, 1)
                .forEach(
                        idx -> {
                            var userRow = searchPendingUser(inputs.get(idx));
                            assertUserGridRowPending(inputs.get(idx), userRow);
                            // view User Profile
                            userRow.click();
                            assertUser(inputs.get(idx), team, label, practisSet);

                            PractisUtils.clickOutOfTheForm();
                            userService().openPendingUsersList();
                        });
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUsers(final List<NewUserInput> inputs) {
        IntStream.range(0, 1)
                .forEach(
                        idx -> {
                            var userRow = searchPendingUser(inputs.get(idx));
                            assertUserGridRowPending(inputs.get(idx), userRow);
                            // view User Profile
                            userRow.click();
                            assertPendingUser(inputs.get(idx));

                            PractisUtils.clickOutOfTheForm();
                            userService().openPendingUsersList();
                        });
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUser(
            final NewUserInput input,
            final RestCreateLabelResponse label,
            final NewTeamInput team) {
        var userRow = searchPendingUser(input);
        assertUserGridRowPending(input, userRow);
        // view User Profile
        userRow.click();
        assertUser(input, team.getName(), label.getName());

        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUser(
            final NewUserInput input,
            final RestCreateLabelResponse label,
            final NewTeamInput team,
            NewPractisSetInput practisSet) {
        var userRow = searchPendingUser(input);
        assertUserGridRowPending(input, userRow);
        // view User Profile
        userRow.click();
        assertUser(input, team, label, practisSet);

        PractisUtils.clickOutOfTheForm();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        userService().openPendingUsersList();
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUser(final NewUserInput input, final RestTeamResponse team) {
        var userRow = searchPendingUser(input);
        assertUserGridRowPending(input, userRow);
        // view User Profile
        userRow.click();
        assertUser(input, team.getName());

        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
    }

    /** Assert data on User Profile. */
    public static void assertInvitedUser(final NewUserInput input) {
        var userRow = searchPendingUser(input);
        assertUserGridRowPending(input, userRow);
        // view User Profile
        userRow.click();
        assertPendingUser(input);
        PractisUtils.clickOutOfTheForm();
        userService().openPendingUsersList();
    }

    /** Assert data on User Profile. */
    public static void assertNotInvitedUser(final NewUserInput input) {
        userService().searchUser(input.getEmail());
        assertNoSearchResultsOnPendingTab();
    }

    /** Assert User: search, assert data on Draft list. */
    public static void asserDraftUser(
            String draftName, NewUserInput inputData, String role, int row) {
        final var userGridRow = userService().searchUser(draftName);
        assertUserGridRowDraft(draftName, userGridRow);
        userGridRow.click();
        assertRequiredUserGridRow(inputData, role, row);
    }

    /** Assert grid row with input data. */
    public static void assertUserGridRowDraft(String draftName, final GridRow gridRow) {
        gridRow.get("Drafts").shouldBe(matchText(draftName));
        gridRow.get("Users").shouldBe(exactText("1"));
        gridRow.get("Created by").shouldBe(matchText("A\n" + "Automation User"));
    }

    /** Assert grid row with existing input data. */
    public static void assertExistingGridRowDraft(GridRow gridRow) {
        gridRow.get("Drafts").shouldBe(matchText("existingName"));
    }

    /** Assert No grid row with input data. */
    public static void assertNoSearchResultsOnDraftTab() {
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(matchText("No Drafts Found"));
    }

    /** Assert No grid row with input data. */
    public static void assertNoDraftYetOnDraftTab() {
        usersDraftTab().getNoDraftYetText().shouldBe(visible);
        usersDraftTab().getNoDraftYetText().shouldBe(matchText("No Drafts Yet"));
    }

    /** Assert No grid row with input data. */
    public static void assertNoSearchResultsOnPendingTab() {
        usersPage().getNoUsersFoundIcon().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(visible);
        usersPage().getNoUsersFoundText().shouldBe(matchText("No Users Found"));
    }

    /** Assert required fields. */
    public static void assertRequiredInputs(NewUserInput inputData) {
        inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
        inviteUsersPage().getAddRowButton().shouldBe(disabled);
        inviteUsersPage().getLastNameField().append(inputData.getLastName());
        inviteUsersPage().getAddRowButton().shouldBe(disabled);
        inviteUsersPage().getEmailField().append(inputData.getEmail());
        inviteUsersPage().getAddRowButton().shouldBe(disabled);
        inviteUsersPage().getRoleField().click();
        userRoleModule().getUserRoleRadioButtonInviteUser().click();
        inviteUsersPage().getAddRowButton().shouldBe(enabled);
    }

    /** Assert no teams in the Teams dropdown. */
    public static void assertEmptyTeamList() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getTeamsField().click();
        assertAssignEmptyTeam();
    }

    /** Assert label in the Label dropdown. */
    public static void assertAddedLabel(final String label) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getLabelsField().click();
        LabelSelectionValidator.assertOneLabel(label);
        LabelSelectionValidator.assertDisabledApplyLabelButton();
    }

    /** Assert no labels in the Label dropdown. */
    public static void assertEmptyLabelList() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getLabelsField().click();
        assertEmptyLabelModel();
    }

    /** Assert Download template button. */
    public static void assertDownloadButton() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
        final var hoveredElement = inviteUsersPage().getDownloadTemplateButton();
        Selenide.actions().moveToElement(hoveredElement).perform();
        inviteUsersPage()
                .getDownloadUploadTemplateTooltip()
                .shouldBe(exactText("Download template"));
    }

    /** Assert Upload template button. */
    public static void assertUploadButton() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getUploadTemplateButton().shouldBe(visible);
        final var hoveredElement = inviteUsersPage().getUploadTemplateButton();
        Selenide.actions().moveToElement(hoveredElement).perform();
        inviteUsersPage().getDownloadUploadTemplateTooltip().shouldBe(exactText("Upload template"));
    }

    public static void assertDownloadedFile(final File downloaded, final String filename) {
        awaitSoft(5, () -> new File(downloadsFolder).exists());
        assertFileNameEqual(downloaded, filename);
    }

    /** Assert template has been downloaded. */
    @Deprecated
    public static void assertDownloadedFile(final String filename) {
        awaitSoft(5, () -> new File(downloadsFolder).exists());
        System.out.println(1);
    }

    /** Assert screen after draft saving. */
    public static void assertScreenAfterSaving() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
        inviteUsersPage().getSaveAsDraftButton().shouldNotBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(matchText("Saved"));
    }

    /** Assert screen after draft saving. */
    public static void assertScreenAfterSavingWithIssues() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
        inviteUsersPage().getCheckboxWarningRow().get(0).click();
        inviteUsersPage().getCheckboxWarningText().shouldBe(visible);
        inviteUsersPage()
                .getCheckboxWarningText()
                .shouldBe(exactText("Please edit before selecting"));
    }

    /** Assert screen after draft saving. */
    public static void assertScreenOneFromManyInvitation() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
        inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
        // TODO Should be passed after fixing DEV-10493
        inviteUsersPage().getSaveAsDraftButton().shouldBe(enabled);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(disabled);
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(matchText("Saved"));
    }

    /** Assert screen after draft canceling. */
    public static void assertInviteScreenCancelDraft() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
        // inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(checked);
        inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(enabled);
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(matchText("Not Saved"));
    }

    /** Assert screen after draft canceling. */
    public static void assertUploadedData() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getInviteUsersToTheAppTitle().shouldBe(visible);
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
        inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(checked);
        inviteUsersPage().getSaveAsDraftButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(visible);
        inviteUsersPage().getInviteSelectedUsersButton().shouldBe(enabled);
        inviteUsersPage().getSavedText().shouldBe(visible);
        inviteUsersPage().getSavedText().shouldBe(matchText("Not Saved"));
    }

    /** Assert problematic User row. */
    public static void asserProblemGridRow(int row, String message) {
        inviteUsersPage().getCheckboxWarningRow().get(0).shouldBe(visible);
        inviteUsersPage().getCheckboxWarningRow().get(0).click();
        inviteUsersPage().getCheckboxWarningText().shouldBe(exactText(message));

        inviteUsersPage().getFirstName().get(row).shouldBe(visible);
        inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("font-size", "13px"));
        inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("width", "auto"));
        inviteUsersPage()
                .getFirstName()
                .get(row)
                .shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getLastName().get(row).shouldBe(visible);
        inviteUsersPage().getLastName().get(row).shouldBe(cssValue("font-size", "13px"));
        inviteUsersPage().getLastName().get(row).shouldBe(cssValue("width", "auto"));
        inviteUsersPage()
                .getLastName()
                .get(row)
                .shouldBe(cssValue("color", "rgba(236, 81, 61, 1)"));
        inviteUsersPage().getEmail().get(row).shouldBe(visible);
        inviteUsersPage().getRole().get(row).shouldBe(visible);
    }

    /** Assert problematic User row. */
    public static void asserNormalGridRow(int row) {
        inviteUsersPage().getFirstName().get(row).shouldBe(visible);
        inviteUsersPage().getFirstName().get(row).shouldBe(cssValue("font-size", "13px"));
        inviteUsersPage()
                .getFirstName()
                .get(row)
                .shouldBe(cssValue("color", "rgba(33, 33, 33, 1)"));
        inviteUsersPage().getLastName().get(row).shouldBe(visible);
        inviteUsersPage().getLastName().get(row).shouldBe(cssValue("color", "rgba(33, 33, 33, 1)"));
        inviteUsersPage().getEmail().get(row).shouldNotBe(visible);
        inviteUsersPage().getRole().get(row).shouldBe(visible);
    }

    /** Get warning checkbox. */
    public static SelenideElement getWarningCheckbox() {
        return inviteUsersPage().getCheckboxWarningRow().get(0);
    }

    /** Assert selection panel. */
    public static void asserSelectionPanel() {
        inviteUsersPage().getAssignButton().shouldBe(visible);
        inviteUsersPage().getAssignButton().shouldBe(exactText("Assign..."));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("height", "40px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("width", "152px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("border-radius", "4px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("color", "rgba(109, 127, 140, 1)"));

        inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("height", "40px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("width", "40px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("border-radius", "4px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("margin-left", "8px"));

        inviteUsersPage().getSelectedItemCounterText().shouldBe(visible);
        inviteUsersPage().getSelectedText().shouldBe(matchText(" Item"));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-weight", "800"));
        inviteUsersPage()
                .getSelectedItemCounterText()
                .shouldBe(cssValue("box-sizing", "border-box"));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-size", "13px"));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("line-height", "19px"));
        inviteUsersPage()
                .getSelectedItemCounterText()
                .shouldBe(cssValue("color", "rgba(0, 0, 0, 1)"));

        inviteUsersPage().getClearSelectionButton().shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("border-left", "1px solid rgb(202, 207, 207)"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("padding", "1px 0px 0px 8px"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("padding", "1px 0px 0px 8px"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("margin-left", "8px"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("line-height", "18px"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("font-size", "13px"));
    }

    /** Assert selection panel - User Already Exists. */
    public static void asserSelectionPanelExistingUser() {
        inviteUsersPage().getAssignButton().shouldBe(visible);
        inviteUsersPage().getAssignButton().shouldBe(exactText("Assign..."));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("height", "40px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("width", "152px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("border-radius", "4px"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getAssignButton().shouldBe(cssValue("color", "rgba(109, 127, 140, 1)"));

        inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("height", "40px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("width", "40px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("align-items", "center"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("border-radius", "4px"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getDeleteUsersButton().shouldBe(cssValue("margin-left", "8px"));

        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(visible);
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("height", "40px"));
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("width", "40px"));
        inviteUsersPage()
                .getDeleteExistingUsersButton()
                .shouldBe(cssValue("align-items", "center"));
        inviteUsersPage()
                .getDeleteExistingUsersButton()
                .shouldBe(cssValue("align-items", "center"));
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("border-radius", "4px"));
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(cssValue("margin-left", "8px"));

        inviteUsersPage().getSelectedItemCounterText().shouldBe(visible);
        inviteUsersPage().getSelectedText().shouldBe(matchText(" selected."));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-weight", "800"));
        inviteUsersPage()
                .getSelectedItemCounterText()
                .shouldBe(cssValue("box-sizing", "border-box"));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("font-size", "13px"));
        inviteUsersPage().getSelectedItemCounterText().shouldBe(cssValue("line-height", "19px"));
        inviteUsersPage()
                .getSelectedItemCounterText()
                .shouldBe(cssValue("color", "rgba(0, 0, 0, 1)"));

        inviteUsersPage().getClearSelectionButton().shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("border-left", "1px solid rgb(202, 207, 207)"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("padding", "1px 0px 0px 8px"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("padding", "1px 0px 0px 8px"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("cursor", "pointer"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("margin-left", "8px"));
        inviteUsersPage()
                .getClearSelectionButton()
                .shouldBe(cssValue("color", "rgba(74, 169, 226, 1)"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("line-height", "18px"));
        inviteUsersPage().getClearSelectionButton().shouldBe(cssValue("font-size", "13px"));
    }

    public static void assertHiddenUserCounter() {
        inviteUsersPage().getUserCounter().shouldBe(hidden);
    }

    /** Assert disabled search. */
    public static void assertDisabledSearch() {
        inviteUsersPage().getSearchField().shouldBe(disabled);
        inviteUsersPage().getSearchField().shouldBe(visible);
        inviteUsersPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        inviteUsersPage().getSearchField().shouldBe(attribute("type", "text"));
        inviteUsersPage().getSearchFieldIcon().shouldBe(visible);
        inviteUsersPage().getSearchFieldClearButton().shouldNotBe(visible);
    }

    /** Assert 'Invite Users' screen - Search field after adding row. */
    public static void assertSearchField() {
        inviteUsersPage().getSearchField().shouldBe(enabled);
        inviteUsersPage().getSearchField().shouldBe(visible);
        inviteUsersPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        inviteUsersPage().getSearchField().shouldBe(enabled);
        inviteUsersPage().getSearchField().shouldBe(attribute("type", "text"));
        inviteUsersPage().getSearchFieldIcon().shouldBe(visible);
        inviteUsersPage().getSearchFieldClearButton().shouldNotBe(visible);
    }

    /** Assert 'Invite Users' screen - No search results. */
    public static void assertNoSearchFResults() {
        inviteUsersPage().getSearchField().shouldBe(enabled);
        inviteUsersPage().getSearchFieldIcon().shouldBe(visible);
        inviteUsersPage().getNoSearchResultsIcon().shouldBe(visible);
        inviteUsersPage().getNoSearchResultsTest().shouldBe(visible);
        inviteUsersPage().getSearchFieldClearButton().shouldBe(visible);
        inviteUsersPage().getNoSearchResultsTest().shouldBe(exactText("No Users Found"));
        inviteUsersPage().getSearchFieldClearButton().click();
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertInviteUsersSearchAfter1Char(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        inviteUsersPage().getSearchField().append(String.valueOf(input));
        inviteUsersPage().getSearchFieldIcon().shouldBe(Condition.visible);
        inviteUsersPage().getAddedUserRow().get(0).shouldBe(visible);
    }

    /** Assert Search - first/last name/email. */
    public static void assertInviteUsersSearch(final String searchCriteria) {
        inviteUsersPage().getSearchField().append(searchCriteria);
        inviteUsersPage().getSearchFieldIcon().shouldBe(Condition.visible);
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(1));
        inviteUsersPage().getSearchFieldClearButton().click();
    }

    /** Assert search results. */
    public static void assertUsersSearchResult(final String user) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getCheckboxAddedUserRow().get(0).shouldBe(visible);
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(2));
        inviteUsersPage().getSearchFieldClearButton().click();
    }

    /** Assert clean search on Invite Users screen. */
    public static void assertCleanSearchUsers(int usersRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(usersRow));
        inviteUsersPage().getSearchField().append("check clean icon");
        inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(0));
        inviteUsersPage().getSearchFieldClearButton().click();
        inviteUsersPage().getAddedUserRow().shouldHave(CollectionCondition.size(usersRow));
    }

    /** Assert hidden Delete button on Assign Modal. */
    public static void assertHiddenDeleteButton() {
        inviteUsersPage().getDeleteUsersButton().shouldBe(hidden);
    }

    /** Assert Delete button on Assign Modal. */
    public static void assertDeleteUsersButton() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        inviteUsersPage().getCheckboxAddedUserRow().get(1).click();
        inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
        inviteUsersPage().getDeleteUsersButton().shouldBe(enabled);
    }

    /** Assert several Users Delete. */
    public static void assertSeveralUsersDelete() {
        inviteUsersPage().getDeleteUsersButton().click();
        snackbar().getMessage().shouldBe(exactText("2 Users have been removed"));
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(1));
    }

    /** Assert 1 User Delete. */
    public static void assertOneUserDelete() {
        inviteUsersPage().getSelectAllCheckbox().click();
        inviteUsersPage().getDeleteUsersButton().click();
        snackbar().getMessage().shouldBe(exactText("1 User has been removed"));
        inviteUsersPage().getAddedUserRow().shouldBe(CollectionCondition.size(0));
        inviteUsersPage().getAddUsersText().shouldBe(visible);
    }

    /** Assert hidden Clear Selection button on Assign Modal. */
    public static void assertHiddenClearSelectionButton() {
        inviteUsersPage().getClearSelectionButton().shouldBe(hidden);
    }

    /** Assert hidden Clear Selection button on Assign Modal. */
    public static void assertClearSelectionButton() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        inviteUsersPage().getCheckboxAddedUserRow().get(1).click();
        inviteUsersPage().getCheckboxAddedUserRowClickedState().get(0).shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(visible);
        inviteUsersPage().getAssignButton().shouldBe(visible);
        inviteUsersPage().getCheckboxAddedUserRowClickedState().get(1).shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
    }

    /** Assert click on Clear Selection button on Assign Modal. */
    public static void assertClickClearSelectionButton() {
        inviteUsersPage().getClearSelectionButton().click();
        inviteUsersPage().getClearSelectionButton().shouldBe(hidden);
        inviteUsersPage().getAssignButton().shouldBe(hidden);
        inviteUsersPage().getCheckboxAddedUserRowNotClickedState().get(0).shouldBe(visible);
        inviteUsersPage().getCheckboxAddedUserRowNotClickedState().get(1).shouldBe(visible);
    }

    /** Assert hidden Delete Existing Users button. */
    public static void assertHiddenDeleteExistingUsersButton() {
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(hidden);
    }

    /** Assert Delete Existing Users button. */
    public static void assertDeleteExistingUsersButton() {
        inviteUsersPage().getDeleteExistingUsersButton().shouldBe(visible);
        inviteUsersPage().getDeleteExistingUsersButton().hover();
        inviteUsersPage().getDeleteExistingUsersTooltip().shouldBe(visible);
        inviteUsersPage()
                .getDeleteExistingUsersTooltip()
                .shouldBe(exactText("Remove all existing users"));
    }

    /** Assert Hidden Selection Panel. */
    public static void assertHiddenSelectionPanel() {
        inviteUsersPage().getAssignButton().shouldBe(hidden);
        inviteUsersPage().getDeleteUsersButton().shouldBe(hidden);
        inviteUsersPage().getSelectedText().shouldBe(hidden);
        inviteUsersPage().getClearSelectionButton().shouldBe(hidden);
        inviteUsersPage().getSearchFieldIcon().shouldBe(visible);
        inviteUsersPage().getSearchField().shouldBe(visible);
        inviteUsersPage().getSearchField().shouldBe(disabled);
        inviteUsersPage().getFiltersButton().shouldBe(visible);
        inviteUsersPage().getFiltersButton().shouldBe(disabled);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(enabled);
        inviteUsersPage().getUploadTemplateButton().shouldBe(visible);
        inviteUsersPage().getUploadTemplateButton().shouldBe(enabled);
    }

    /** Assert Selection Panel. */
    public static void assertSelectionPanel() {
        inviteUsersPage().getSelectAllCheckbox().click();
        inviteUsersPage().getAssignButton().shouldBe(visible);
        inviteUsersPage().getAssignButton().shouldBe(exactText("Assign..."));
        inviteUsersPage().getDeleteUsersButton().shouldBe(visible);
        inviteUsersPage().getSelectedText().shouldBe(visible);
        inviteUsersPage().getSelectedText().shouldBe(matchText("2 Items"));
        inviteUsersPage().getClearSelectionButton().shouldBe(visible);
        inviteUsersPage().getClearSelectionButton().shouldBe(exactText("Clear Selection"));
        inviteUsersPage().getClearSelectionButton().click();
        inviteUsersPage().getAssignButton().shouldBe(hidden);
        inviteUsersPage().getDeleteUsersButton().shouldBe(hidden);
        inviteUsersPage().getSelectedText().shouldBe(hidden);
        inviteUsersPage().getClearSelectionButton().shouldBe(hidden);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(visible);
        inviteUsersPage().getDownloadTemplateButton().shouldBe(enabled);
        inviteUsersPage().getUploadTemplateButton().shouldBe(visible);
        inviteUsersPage().getUploadTemplateButton().shouldBe(enabled);
    }
}
