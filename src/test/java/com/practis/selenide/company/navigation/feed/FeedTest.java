package com.practis.selenide.company.navigation.feed;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedAccuracyPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedAccuracyTestFilter;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedChallengeFilter;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedChallengesPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class FeedTest {

    @TestRailTest(caseId = 31746)
    @DisplayName("Company: Navigation: Feed page: Accuracy Tests, Challenges tabs: Check Elements")
    void assertElementsFeedScreen() {
        // Open 'Feed' page
        navigationCompany().getFeedNavigationItem().click();

        // Assert Feed Page: Accuracy Test tab
        assertFeedAccuracyPage();

        // Click on Filter and check filter module
        feedPage().getAccuracyFiltersButton().click();
        assertFeedAccuracyTestFilter();
        filter().getApplyFilterButton().click();

        // Open 'Challenge' tab and check elements
        feedPage().getAccuracyChallengesTab().click();
        assertFeedChallengesPage();
        feedPage().getChallengesFiltersButton().click();
        assertFeedChallengeFilter();
    }
}
