package com.practis.selenide.company.navigation.feed;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompanies;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertElementsOnChallengesFiltersPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertElementsOnFeedChallengesPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertElementsOnFeedFiltersPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertElementsOnFeedPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class FeedTest {

  @TestRailTest(caseId = 8905)
  @DisplayName("Check WEB Elements 'Feed' page")
  void assertElementsFeedScreen() {
    navigationCompanies().getFeedNavigationItem().click();
    assertElementsOnFeedPage();
    assertElementsOnFeedFiltersPage();

    feedPage().getAccuracyChallengesTab().click();
    assertElementsOnFeedChallengesPage();
    assertElementsOnChallengesFiltersPage();
  }

}
