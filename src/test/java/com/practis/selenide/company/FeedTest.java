package com.practis.selenide.company;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.FeedValidator.assertElementsOnFeedChallengesPage;
import static com.practis.web.selenide.validator.FeedValidator.assertElementsOnFeedPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class FeedTest {

  @Test
  @TestRailTest(caseId = 8905)
  @DisplayName("Check WEB Elements 'Feed' page")
  void assertElementsFeedScreen() {
    navigationCompanies().getFeedNavigationItem().click();
    assertElementsOnFeedPage();
    feedPage().getAccuracyChallengesTab().click();
    assertElementsOnFeedChallengesPage();

  }

}
