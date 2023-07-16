package com.practis.selenide.company.limit;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.userService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.youNeedMoreSeatsPopUpService;
import static com.practis.web.selenide.validator.company.users.PendingTabValidator.assertEmptyPendingPage;
import static com.practis.web.selenide.validator.popup.LimitUsersPopUpValidator.assertYouNeedMoreSeatsPopUp;
import static com.practis.web.selenide.validator.popup.LimitUsersPopUpValidator.youCantInviteNewUsersPopUp;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertUserCounter;
import static org.awaitility.Awaitility.await;
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
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.TeamExtension;
import com.practis.web.util.AwaitUtils;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserLimit {

    private List<String> usersToRemove;
    private List<NewUserInput> inputData;

    @BeforeEach
    void init() {
        inputData = userService().generateUserInputs(2);

        usersToRemove = new ArrayList<>();
        inputData.forEach(input -> usersToRemove.add(input.getEmail()));
    }

    /** Invite User to the App: Invite Users: Users Limit Validation: Manage Users. */
    @TestRailTest(caseId = 32174)
    @CompanyUserLimitExtension(2)
    @DisplayName("Invite User to the App: Invite Users: Users Limit Validation: Manage Users")
    @LabelExtension(count = 1)
    @PractisSetExtension(count = 1)
    @TeamExtension(count = 1)
    void userLimitManageUsers(
            final List<RestCreateLabelResponse> label,
            final List<NewTeamInput> team,
            List<NewPractisSetInput> practisSet) {

        Selenide.refresh();
        AwaitUtils.awaitSoft(2, () -> false);
        newItemSelector().create("User");

        final var input = userService().generateUserData(3, usersToRemove);
        final var role = "User";

        // generate input data for Users
        userService().addRow(input.get(0), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(input.get(1), role, label.get(0), team.get(0), practisSet.get(0));
        userService().addRow(input.get(2), role, label.get(0), team.get(0), practisSet.get(0));

        // assert counter
        assertUserCounter("3 items");

        // select all user and click "Invite Selected Users" button
        userService().inviteAllUser();

        // assert warning message
        assertYouNeedMoreSeatsPopUp();

        // click "Manage Users" button
        youNeedMoreSeatsPopUpService().clickManageUsersButton();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertEmptyPendingPage();
    }

    /** Invite User to the App: Users Limit Validation While Inviting Users: Set a Limit. */
    @TestRailTest(caseId = 32175)
    @DisplayName(
            "Invite User to the App: Users Limit Validation While Inviting Users: Set a Limit ")
    @CompanyUserLimitExtension(2)
    @PendingUserExtension(limit = 2, company = "CompanyAuto", role = 7)
    void userLimitReachTheLimit() {

        Selenide.refresh();
        AwaitUtils.awaitSoft(2, () -> false);
        newItemSelector().create("User");

        final var input = userService().generateUserData(1, usersToRemove);
        final var role = "User";

        // generate input data for Users
        userService().addRow(input.get(0), role);

        // select all user and click "Invite Selected Users" button
        userService().inviteAllUser();

        // assert warning message
        youCantInviteNewUsersPopUp();

        // click "Request Limit Change" button
        youNeedMoreSeatsPopUpService().clickSetALimitButton();
        await().pollDelay(TWO_SECONDS).until(() -> true);
    }

    @AfterEach
    void cleanup() {
        usersToRemove.forEach(email -> practisApi().revokeUser(email));
    }
}
