package com.practis.selenide.company.limit;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.youNeedMoreSeatsPopUpService;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;
import static com.practis.web.selenide.validator.popup.LimitUsersPopUpValidator.assertYouNeedMoreSeatsPopUp;
import static com.practis.web.selenide.validator.popup.LimitUsersPopUpValidator.youCantInviteNewUsersPopUp;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.CompanyUserLimitExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteDraftUserLimit {

    private List<String> usersToRemove;
    private List<NewUserInput> inputData;

    @BeforeEach
    void init() {
        inputData = userService().generateUserInputs(2);

        usersToRemove = new ArrayList<>();
        inputData.forEach(input -> usersToRemove.add(input.getEmail()));
    }

    /** Invite Draft User to the App: Invite Users: Users Limit Validation: Manage Users. */
    @TestRailTest(caseId = 32176)
    @DisplayName("Invite Draft User to the App: Invite Users: Users Limit Validation: Manage Users")
    @CompanyUserLimitExtension(2)
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    @GeneratedDraftNameExtension
    void draftUserLimitManageUsers(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSets,
            String draftName) {

        Selenide.refresh();
        final var inputs = userService().generateUserInputs(3);
        usersToRemove.add(inputs.get(0).getEmail());
        usersToRemove.add(inputs.get(1).getEmail());
        usersToRemove.add(inputs.get(2).getEmail());

        newItemSelector().create("User");
        // Add User rows
        Selenide.refresh();
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().addRow(inputs.get(1), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().addRow(inputs.get(2), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().saveAsDraft(draftName);

        // click "Invite Selected Users" button
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        inviteUsersPage().getInviteSelectedUsersButton().click();

        // assert warning message
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertYouNeedMoreSeatsPopUp();

        // click "Manage Users" button
        youNeedMoreSeatsPopUpService().clickManageUsersButton();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyPendingPage();
    }

    /** Invite Draft to the App: Users Limit Validation While Inviting Users: Set a Limit. */
    @TestRailTest(caseId = 32177)
    @DisplayName(
            "Invite Draft to the App: Users Limit Validation While Inviting Users: Set a Limit")
    @CompanyUserLimitExtension(2)
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    @GeneratedDraftNameExtension
    void draftUserLimitReachTheLimit(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSets,
            String draftName) {

        Selenide.refresh();
        final var inputs = userService().generateUserInputs(3);
        usersToRemove.add(inputs.get(0).getEmail());
        usersToRemove.add(inputs.get(1).getEmail());
        usersToRemove.add(inputs.get(2).getEmail());

        newItemSelector().create("User");
        // Add User rows
        Selenide.refresh();
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().addRow(inputs.get(1), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().addRow(inputs.get(2), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().saveAsDraft(draftName);

        await().pollDelay(FIVE_SECONDS).until(() -> true);

        // Select users, so the limit should be reached, not above and click "Invite Selected Users"
        // button
        inviteUsersPage().getSelectAllCheckbox().click();
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        inviteUsersPage().getCheckboxAddedUserRow().get(1).click();
        inviteUsersPage().getInviteSelectedUsersButton().click();

        await().pollDelay(FIVE_SECONDS).until(() -> true);

        // Select Users, which are left in the list and click "Invite Selected Users" button
        inviteUsersPage().getCheckboxAddedUserRow().get(0).click();
        inviteUsersPage().getInviteSelectedUsersButton().click();
        await().pollDelay(TWO_SECONDS).until(() -> true);

        // assert warning message
        youCantInviteNewUsersPopUp();

        // click "Request Limit Change" button
        youNeedMoreSeatsPopUpService().clickSetALimitButton();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
