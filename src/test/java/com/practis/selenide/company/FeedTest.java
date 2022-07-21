package com.practis.selenide.company;

import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;

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
    feedPage().getFeedButton().click();
    assertElementsFeedScreen();
  }

}
