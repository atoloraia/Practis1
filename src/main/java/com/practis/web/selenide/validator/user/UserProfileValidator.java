package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.page.company.user.UserProfilePage;

public class UserProfileValidator {


  /**
   * Assert data on empty 'User Profile'.
   */
  public static void assertEmptyUserProfile() {
    userProfilePage().getUserProfileTitle().shouldBe(visible);
    userProfilePage().getUserProfileTitle().shouldBe(exactText("User Profile"));
    userProfilePage().getUserAvatar().shouldBe(visible);
    userProfilePage().getUserRole().shouldBe(visible);
    userProfilePage().getUserName().shouldBe(visible);
    userProfilePage().getUserEmail().shouldBe(visible);
    userProfilePage().getPendingRegistrationLabel().shouldBe(visible);
    userProfilePage().getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));

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

    userProfilePage().getNoPractisSetImage().shouldBe(visible);
    userProfilePage().getNoPractisSetText().shouldBe(visible);
    userProfilePage().getNoPractisSetText().shouldBe(exactText("No Practis Sets Yet"));
  }

  /**
   * Assert data on 'User Profile' page with input.
   */
  public static void assertUserData(final NewUserInput inputData,
      final UserProfilePage userProfilePage) {
    userProfilePage.getUserName()
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    userProfilePage.getUserEmail().shouldBe(matchText(inputData.getEmail()));
    userProfilePage.getPendingRegistrationLabel().shouldBe(visible);
    userProfilePage.getPendingRegistrationLabel().shouldBe(exactText("Pending Registration"));
  }

}
