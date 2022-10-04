package com.practis.selenide.admin.navigation;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigation;
import static com.practis.web.selenide.configuration.PageObjectFactory.usersPage;
import static com.practis.web.selenide.validator.admin.AiAssessmentValidator.assertElementsOnAiAssessmentPage;
import static com.practis.web.selenide.validator.admin.AiAssessmentValidator.assertElementsOnNoSearchResultAiAssessmentPage;
import static com.practis.web.selenide.validator.admin.LogsValidator.assertElementsOnLogsPage;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertInvitedUser;

import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@SelenideTestClass
@TestRailTestClass
@PractisAdminTestClass
class AiAssessmentTest {

  /**
   * Check Web elements on AI Assessment page.
   */
  @TestRailTest(caseId = 27)
  @DisplayName("Check Elements on 'AI Assessment' Page")
  void checkElementsOnAiAssessmentPage() {
    navigation().assessmentNavigationItem.click();

    assertElementsOnAiAssessmentPage();

  }
}
