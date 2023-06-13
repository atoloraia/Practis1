package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.inviteUsersPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.pendingUsersService;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import java.util.List;

public class UserProfileValidator {

    /** Assert data on empty Pending User Profile'. */
    public static void assertEmptyPendingUserProfile() {
        assertUserProfile();

        userProfilePage().getPendingRegistrationLabel().shouldBe(visible);
        userProfilePage().getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));

        userProfilePage().getNoPractisSetImage().shouldBe(visible);
        userProfilePage().getNoPractisSetText().shouldBe(visible);
        userProfilePage().getNoPractisSetText().shouldBe(exactText("No Practis Sets Assigned Yet"));
    }

    /** Assert data on 'User Profile'. */
    public static void assertUserProfile() {
        userProfilePage().getUserProfileTitle().shouldBe(visible);
        userProfilePage().getUserProfileTitle().shouldBe(exactText("User Profile"));
        userProfilePage().getUserAvatar().shouldBe(visible);
        userProfilePage().getUserRole().shouldBe(visible);
        userProfilePage().getUserName().shouldBe(visible);
        userProfilePage().getUserEmail().shouldBe(visible);

        userProfilePage().getUpdatedTimestamp().shouldBe(visible);
        userProfilePage().getUpdatedTimestamp().shouldBe(matchText("Updated"));
        userProfilePage().getUpdateButton().shouldBe(visible);

        userProfilePage().getAssignButton().shouldBe(visible);
        userProfilePage().getAssignButton().shouldBe(exactText("Assign..."));
        userProfilePage().getUserSettingsButton().shouldBe(visible);
        userProfilePage().getUserSettingsButton().shouldBe(exactText("User Settings"));
        userProfilePage().getNudgeButton().shouldBe(visible);
        userProfilePage().getNudgeButton().shouldBe(exactText("Nudge User"));

        userProfilePage().getSearchField().shouldBe(visible);
        userProfilePage().getFiltersButton().shouldBe(visible);
        userProfilePage().getPaginationBackButton().shouldBe(visible);
        userProfilePage().getPaginationNextButton().shouldBe(visible);

        userProfilePage().getSelectAllCheckbox().shouldBe(visible);
        userProfilePage().getPractisSetColumn().shouldBe(visible);
        userProfilePage().getPractisSetColumn().shouldBe(exactText("Practis Sets"));
        userProfilePage().getDueDateColumn().shouldBe(visible);
        userProfilePage().getDueDateColumn().shouldBe(exactText("Due Date"));
        userProfilePage().getProgressColumn().shouldBe(visible);
        userProfilePage().getProgressColumn().shouldBe(exactText("Progress"));
        userProfilePage().getAccuracyColumn().shouldBe(visible);
        userProfilePage().getAccuracyColumn().shouldBe(exactText("Accuracy"));
        userProfilePage().getTrainingTimeColumn().shouldBe(visible);
        userProfilePage().getTrainingTimeColumn().shouldBe(exactText("Training Time"));
        userProfilePage().getAssignedColumn().shouldBe(visible);
        userProfilePage().getAssignedColumn().shouldBe(exactText("Assigned"));
        userProfilePage().getStartedColumn().shouldBe(visible);
        userProfilePage().getStartedColumn().shouldBe(exactText("Started"));
        userProfilePage().getLastTrainingColumn().shouldBe(visible);
        userProfilePage().getLastTrainingColumn().shouldBe(exactText("Last Training"));
    }

    /** Assert data on 'User Profile' page with input. */
    public static void assertUserData(final NewUserInput inputData) {
        userProfilePage()
                .getUserName()
                .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
        userProfilePage().getUserEmail().shouldBe(matchText(inputData.getEmail()));
    }

    /** Assert Label in User row. */
    public static void assertOneLabelSelected(int row) {
        inviteUsersPage().getLabel().get(row).shouldBe(matchText("1 Label"));
    }

    public static void assertPractisSetData(final NewPractisSetInput practisSets) {
        userProfilePage().getPractisSetTitle().get(0).shouldBe(matchText(practisSets.getName()));
    }

    /** Assert data on 'Pending User Profile'. */
    public static void assertPendingUserProfile() {
        assertUserProfile();
        userProfilePage().getPendingRegistrationLabel().shouldBe(visible);
        userProfilePage().getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));
        userProfilePage().getPendingRegistrationHourglass().shouldBe(visible);
    }

    // Check assigned Label on User Profile page
    public static void assertUserProfileWithAssignedLabel(
            final List<RestCreateLabelResponse> label) {
        pendingUsersService().clickSingleActionViewProfile();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        userProfilePage().getAssignButton().parent().click();
        assertSelectedLabel(label.get(0).getName());
    }
}
