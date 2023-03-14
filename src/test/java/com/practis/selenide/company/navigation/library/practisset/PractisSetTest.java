package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertElementsOnLibraryPractisSetsTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertEmptyStateLibraryPractisSetsTab;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PractisSetExtension;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetTest {

    /** View Library: Practis Set tab: default view */
    @TestRailTest(caseId = 1847)
    @DisplayName("Library: Practis Sets Tab: Check Elements")
    @PractisSetExtension(count = 1)
    void checkElementsViewPractisSet() {
        // open Library: Practis Set tab
        navigationCompany().getLibraryNavigationItem().click();
        assertElementsOnLibraryPractisSetsTab();
    }

    /** View Library: Practis Set tab: Empty State */
    @TestRailTest(caseId = 1848)
    @DisplayName("Library: Practis Sets Tab: Check Elements: Empty")
    void checkElementsViewPractisSetEmptyState() {
        // open Library: Practis Set tab
        navigationCompany().getLibraryNavigationItem().click();
        assertEmptyStateLibraryPractisSetsTab();
    }
}
