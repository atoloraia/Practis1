package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.page.company.user.UserProfilePage;

public class UserProfileValidator {

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

  /**
   * Assert no search results.
   */
  public static void assertTeam() {

  }

}
