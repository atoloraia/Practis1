package com.practis.selenide.company.navigation.feed;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static com.practis.web.selenide.validator.company.FeedValidator.assertFeedAccuracyTestFilter;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class FeedAccuracyTestsTest {

    @TestRailTest(caseId = 25653)
    @DisplayName("Feed: Accuracy Tests tab: Filters: Check Elements")
    void checkElementsAccuracyTestsFilters() {
        navigationCompany().getFeedNavigationItem().click();
        feedPage().getAccuracyFiltersButton().click();
        assertFeedAccuracyTestFilter();
    }
}
