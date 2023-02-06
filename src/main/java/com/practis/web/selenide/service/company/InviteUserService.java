package com.practis.web.selenide.service.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.inviteUserPsModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.userRoleModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.saveAsDraftService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.unsavedProgressPopUpService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertHiddenDeleteExistingUsersButton;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserGridRowDraft;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.PractisUtils.clickOutOfTheFormForPopup;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.stream.Collectors.toList;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.ONE_SECOND;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.utils.XmlService;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.configuration.ComponentObjectFactory;
import java.io.File;
import java.util.List;
import lombok.ToString;

@ToString
public class InviteUserService {

    /** Generate User inputs. */
    public List<NewUserInput> generateUserInputs(int limit) {
        final var inputs =
                getNewUserInputs().stream()
                        .limit(limit)
                        .peek(
                                input -> {
                                    input.setEmail(format(input.getEmail(), timestamp()));
                                    input.setFirstName(format(input.getFirstName(), timestamp()));
                                })
                        .collect(toList());
        return inputs;
    }

    /** Generate User inputs. */
    public List<NewUserInput> generateUserData(int limit, List<String> usersToRemove) {
        final var inputs = userService().generateUserInputs(limit);
        inputs.forEach(input -> usersToRemove.add(input.getEmail()));
        return inputs;
    }

    /** Generate User inputs. */
    public void uploadTemplate(File file) {
        inviteUsersPage().getUploadTemplateButton().parent().$("input").uploadFile(file);
    }

    /** Generate XML file with User data. */
    public File getXml(final List<NewUserInput> inputs, final String role) {
        final var file = new File("test.xls");
        final var service =
                new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users");
        inputs.forEach(
                input ->
                        service.setUserRow(
                                input.getFirstName(), input.getLastName(), input.getEmail(), role));
        service.write(file);
        return file;
    }

    /** User Row: Fill First Name, Last Name and Email row. */
    public InviteUserService fillText(final NewUserInput inputData) {
        inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
        inviteUsersPage().getLastNameField().append(inputData.getLastName());
        inviteUsersPage().getEmailField().append(inputData.getEmail());
        return this;
    }

    /** User Row: select role. */
    public InviteUserService selectRole(final String role) {
        inviteUsersPage().getRoleField().click();
        switch (role) {
            case "Admin":
                userRoleModule().getAdminRoleRadioButtonInviteUser().click();
                break;
            case "User":
                userRoleModule().getUserRoleRadioButtonInviteUser().click();
                break;
            default:
                throw new RuntimeException(format("Unknown role: %s", role));
        }
        return this;
    }

    /** User Row: select label. */
    public InviteUserService selectLabel(final String label) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getLabelsField().click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().findLabelCheckbox(label).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        labelModule().getApplyButton().click();
        return this;
    }

    /** User Row: select team. */
    public InviteUserService selectTeam(final String team) {
        inviteUsersPage().getTeamsField().click();
        teamModuleService().selectTeam(team);
        ComponentObjectFactory.teamModule().getApplyButton().click();
        return null;
    }

    /** User Row: select practis set. */
    public InviteUserService selectPractisSet(final String practisSet) {
        awaitSoft(
                20,
                () -> {
                    inviteUsersPage().getPractisSetsField().click();
                    return inviteUserPsModule().getPractisSetRows().size() > 0;
                });
        psModuleService().selectPractisSet(practisSet);
        ComponentObjectFactory.inviteUserPsModule().getApplyButton().click();
        return this;
    }

    /** Fill form with wrong email format. */
    public void wrongEmailFormatFillRow(final NewUserInput inputData) {
        inviteUsersPage().getFirstNameField().append(inputData.getFirstName());
        inviteUsersPage().getLastNameField().append(inputData.getLastName());
        inviteUsersPage().getEmailField().append("test");
        inviteUsersPage().getRoleField().click();
        userRoleModule().getUserRoleRadioButtonInviteUser().click();
    }

    /** Select first User checkbox and click "Assign". */
    public void assignFirstUser() {
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        inviteUsersPage().getAssignButton().click();
    }

    /** Select first User checkbox and click "Assign". */
    public void assignAllUsers() {
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getSelectAllCheckbox().click();
        inviteUsersPage().getAssignButton().click();
    }

    /** Select first User checkbox and click 'Invite Selected Users' button. */
    public void inviteFirstUser() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getInviteSelectedUsersButton().click();
    }

    /** Select all Users and click 'Invite Selected Users' button. */
    public void inviteAllUser() {
        inviteUsersPage().getSelectAllCheckbox().click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getInviteSelectedUsersButton().click();
    }

    /** Select some Users and click 'Invite Selected Users' button. */
    public void inviteSomeUser(int row1, int row2) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getCheckboxAddedUserRow().get(row1).click();
        inviteUsersPage().getCheckboxAddedUserRow().get(row2).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        assertHiddenDeleteExistingUsersButton();
        inviteUsersPage().getInviteSelectedUsersButton().click();
    }

    /** Fill First Name, Last Name, Email, Role, Team and click + button. */
    public void addRow(
            NewUserInput inputData, String role, RestCreateLabelResponse label, NewTeamInput team) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        selectLabel(label.getName());
        selectTeam(team.getName());
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Fill First Name, Last Name, Email, Role, Label and click + button. */
    public void addRow(NewUserInput inputData, String role, RestCreateLabelResponse label) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        selectLabel(label.getName());
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Fill First Name, Last Name, Email, Role, Team and click + button. */
    public void addRow(NewUserInput inputData, String role, NewTeamInput team) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        selectTeam(team.getName());
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Fill First Name, Last Name, Email, Role, Practis Set and click + button. */
    public void addRow(NewUserInput inputData, String role, NewPractisSetInput practisSet) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        selectPractisSet(practisSet.getName());
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Fill First Name, Last Name, Email, Role, Practis Set and click + button. */
    public void addRow(
            NewUserInput inputData,
            String role,
            RestCreateLabelResponse label,
            NewTeamInput team,
            NewPractisSetInput practisSet) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        selectLabel(label.getName());
        selectTeam(team.getName());
        selectPractisSet(practisSet.getName());
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Click + button. */
    public void addRow() {
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Fill First Name, Last Name, Email, Role and click + button. */
    public void addRow(NewUserInput inputData, String role) {
        await().pollDelay(ONE_SECOND).until(() -> true);
        fillText(inputData);
        selectRole(role);
        inviteUsersPage().getAddRowButton().lastChild().click();
    }

    /** Delete User row. */
    public void deleteRow(int rowNum) {
        final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
        Selenide.actions().moveToElement(hoveredElement).perform();
        inviteUsersPage().getDeleteRowButton().get(rowNum).click();
    }

    /** Click Edit button. */
    public InviteUserService clickEdit(int rowNum) {
        final var hoveredElement = inviteUsersPage().getAddedUserCell().get(9);
        Selenide.actions().moveToElement(hoveredElement).perform();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getEditRowButton().click();
        return this;
    }

    /** Edit text. */
    public InviteUserService editText(final NewUserInput inputData) {
        inviteUsersPage().getEditFirstNameField().clear();
        inviteUsersPage().getEditFirstNameField().append(inputData.getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getEditLastNameField().clear();
        inviteUsersPage().getEditLastNameField().append(inputData.getLastName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        inviteUsersPage().getEditEmailField().clear();
        inviteUsersPage().getEditEmailField().append(inputData.getEmail());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        return this;
    }

    /** Edit role. */
    public InviteUserService editRole(final String role) {
        inviteUsersPage().getEditRoleField().click();
        switch (role) {
            case "Admin":
                userRoleModule().getAdminRoleRadioButtonEditInviteUser().click();
                break;
            case "User":
                userRoleModule().getUserRoleRadioButtonEditInviteUser().click();
                break;
            default:
                throw new RuntimeException(format("Unknown role: %s", role));
        }
        return this;
    }

    /** Apply Edit changes. */
    public InviteUserService applyEditChanges(int rowNum) {
        inviteUsersPage().getApplyEditChangesButton().click();
        return this;
    }

    /** Cancel Edit changes. */
    public InviteUserService cancelEditChanges(int rowNum) {
        inviteUsersPage().getCancelEditChangesButton().click();
        return this;
    }

    /** Search User on grid by User Name. */
    public GridRow searchUser(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().userSearch(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Click Save As Draft button. */
    public void clickSaveAsDraftButton() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getSaveAsDraftButton().click();
        saveAsDraftService().saveAsDraft("Test Draft");
    }

    /** Save as draft. */
    public void saveAsDraft(String draftName) {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getSaveAsDraftButton().click();
        saveAsDraftService().saveAsDraft(draftName);
    }

    /** Cancel 'Save as draft' action. */
    public void cancelSaveAsDraft() {
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        await().pollDelay(ONE_SECOND).until(() -> true);
        inviteUsersPage().getSaveAsDraftButton().click();
        saveAsDraftService().clickCancel();
    }

    /** Open 'Pending' list without saving. */
    public void openPendingUsersListWithoutSaving() {
        clickOutOfTheFormForPopup();
        unsavedProgressPopUpService().clickExitWithoutSavingButton();
        navigationCompany().getUsersNavigationItem().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersPage().getPendingTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Open 'Pending' list without saving. */
    public void openPendingUsersList() {
        navigationCompany().getUsersNavigationItem().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        usersPage().getPendingTab().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    /** Open 'Draft' list. */
    public void openDraftUsersList() {
        await().pollDelay(1, SECONDS).until(() -> true);
        navigationCompany().getUsersNavigationItem().click();
        await().pollDelay(1, SECONDS).until(() -> true);
        usersPage().getDraftTab().click();
    }

    /** Open draft from list. */
    public void openDraftFromList(String draftName) {
        final var userGridRow = userService().searchUser(draftName);
        assertUserGridRowDraft(draftName, userGridRow);
        userGridRow.click();
    }

    /** Exit the page without saving. */
    public void exitWithoutSaving() {
        clickOutOfTheFormForPopup();
        unsavedProgressPopUpService().clickExitWithoutSavingButton();
    }

    /** Generate template. */
    public File generateTemplate(String firstName, String lastName, String email, String role) {
        final var file = new File("test.xls");
        new XmlService("/configuration/web/input/template/upload.xlsx", "List Of Users")
                .set("First Name", firstName)
                .set("Last Name", lastName)
                .set("Email", email)
                .set("Role", role)
                .write(file);
        return file;
    }

    /** Search Users to invite. */
    public void searchUsersToInvite(final String input) {
        inviteUsersPage().getSearchField().setValue(input.substring(0, input.length() - 1));
        inviteUsersPage().getSearchField().append(input.substring(input.length() - 1));
    }
}
