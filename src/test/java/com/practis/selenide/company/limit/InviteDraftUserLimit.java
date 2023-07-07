package com.practis.selenide.company.limit;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.youNeedMoreSeatsPopUpService;
import static com.practis.web.selenide.validator.company.users.RegisteredTabValidator.assertUsersRegisteredPage;
import static com.practis.web.selenide.validator.popup.YouNeedMoreSeatsValidator.assertYouNeedMoreSeatsPopUp;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewTeamInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.DraftExtension;
import com.practis.support.extension.practis.GeneratedDraftNameExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    // @TestRailTest(caseId = 32176)
    @DisplayName("Invite Draft User to the App: Invite Users: Users Limit Validation: Manage Users")
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
        final var inputs = userService().generateUserInputs(2);

        newItemSelector().create("User");
        // Add User row
        Selenide.refresh();
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().addRow(inputs.get(1), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().saveAsDraft(draftName);

        // select all user and click "Invite Selected Users" button
        userService().inviteAllUser();

        // assert warning message
        assertYouNeedMoreSeatsPopUp();

        // click "Manage Users" button and assert "Registered Users" tab
        youNeedMoreSeatsPopUpService().clickManageUsersButton();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertUsersRegisteredPage();
    }

    /** Invite Draft to the App: Users Limit Validation While Inviting Users: Set a Limit. */
    @Disabled
    // @TestRailTest(caseId = 32177)
    @DisplayName(
            "Invite Draft to the App: Users Limit Validation While Inviting Users: Set a Limit")
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    @DraftExtension(usersPerDraft = 3)
    void draftUserLimitReachTheLimit(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            final List<NewPractisSetInput> practisSets,
            String draftName) {

        Selenide.refresh();
        final var inputs = userService().generateUserInputs(1);

        newItemSelector().create("User");
        // Add User row
        Selenide.refresh();
        userService().addRow(inputs.get(0), "Admin", label.get(0), team.get(0), practisSets.get(0));
        userService().saveAsDraft(draftName);

        // select all user and click "Invite Selected Users" button
        userService().inviteAllUser();

        // assert warning message
        assertYouNeedMoreSeatsPopUp();

        // click "Manage Users" button and assert "Registered Users" tab
        youNeedMoreSeatsPopUpService().clickManageUsersButton();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertUsersRegisteredPage();
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
