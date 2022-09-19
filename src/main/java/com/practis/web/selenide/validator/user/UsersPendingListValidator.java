package com.practis.web.selenide.validator.user;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPendingPage;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.page.company.user.UserProfilePage;

public class UsersPendingListValidator {


  /**
   * Assert data on empty 'User Profile'.
   */
  public static void assertEmptyPendingUsersList() {
    usersPendingPage().getEmptyStateIcon().shouldBe(visible);
    usersPendingPage().getEmptyStateText().shouldBe(visible);
    usersPendingPage().getEmptyStateText().shouldBe(exactText("No Users Found"));
  }
}
