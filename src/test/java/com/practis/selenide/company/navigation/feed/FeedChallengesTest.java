package com.practis.selenide.company.navigation.feed;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedChallengeFilter;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class FeedChallengesTest {

    @TestRailTest(caseId = 25654)
    @DisplayName("Feed: Challenges tab: Filters: Check Elements")
    void checkElementsChallengesFilters() {
        navigationCompany().getFeedNavigationItem().click();
        feedPage().getAccuracyChallengesTab().click();
        feedPage().getChallengesFiltersButton().click();
        assertFeedChallengeFilter();
    }
}
