package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedAccuracyPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedAccuracyTestFilter;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedChallengeFilter;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedChallengesPage;
import static com.practis.web.selenide.validator.company.navigation.WebAiAssessmentValidator.assertWebEmptyAiAssessmentScreen;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class WebAiAssessmentTest {

  @TestRailTest(caseId = 8905)
  @DisplayName("Check WEB Elements 'AI Assessment' screen")
  void assertElementsAiAssessmentScreen() {
    //Open 'AI Assessment' page
    navigationCompanies().getAiAssessmentItem().click();

    //Assert AI Assessment Page
    assertWebEmptyAiAssessmentScreen();

  }

}
