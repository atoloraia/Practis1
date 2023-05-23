package com.practis.selenide.company.navigation;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetReportService;
import static com.practis.web.selenide.validator.company.PractisSetReportValidator.assertEmptyNudgePopUp;
import static com.practis.web.selenide.validator.company.PractisSetReportValidator.assertPendingPractisSetReportPage;

import com.codeborne.selenide.Condition;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtensionWithUsersAndPractisSets;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetReportTest {

    @TestRailTest(caseId = 31723)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Practis Set Report: Pending: Check Elements")
    void assertElementsPendingPractisSetReportPage() {

        // Open 'Pending Practis Set Report' page
        practisSetReportService().openPsReportFromUserProfile();

        // Assert Pending Practis Set Report page
        assertPendingPractisSetReportPage();
    }

    @TestRailTest(caseId = 31724)
    @TeamExtensionWithUsersAndPractisSets(practisSets = 1, users = 1)
    @DisplayName("Practis Set Report: Nudge")
    void assertNudgePractisSetReportPage() {

        // Open 'Pending Practis Set Report' page
        practisSetReportService().openPsReportFromUserProfile();

        // Assert Nudge User modal
        practisSetReportService().psReportClickOnNudge();
        assertEmptyNudgePopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Snackbar
        snackbar().getMessage().shouldBe(Condition.exactText("Nudge has been sent"));
    }
}
