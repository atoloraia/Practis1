package com.practis.selenide.company.navigation.teams.overdue;

import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.overdueTabService;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertSingleActionOverdue;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserProfile;

import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.company.RestEnrollUnEnrollRequest;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.OverdueUserExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class OverdueTabSingleActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
        overdueLearnersTab().getOverdueTitle().click();
    }

    @TestRailTest(caseId = 31687)
    @DisplayName("Teams: Overdue: Single Actions: Check Elements")
    @OverdueUserExtension
    void overdueSingleActionCheckElements(final List<RestEnrollUnEnrollRequest> enrollments) {
        Selenide.refresh();
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());
        overdueTabService().clickSingleActionOverdue(user.getFirstName());
        assertSingleActionOverdue();
    }

    @TestRailTest(caseId = 31689)
    @DisplayName("Teams: Overdue: Single Action: View Profile")
    @OverdueUserExtension
    void overdueSingleActionViewProfile(final List<RestEnrollUnEnrollRequest> enrollments) {
        Selenide.refresh();
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());
        overdueTabService().clickSingleActionOverdue(user.getFirstName());
        overdueLearnersTab().getViewProfileSingleAction().click();
        assertUserProfile();
    }

    @TestRailTest(caseId = 31688)
    @DisplayName("Teams: Overdue: Single Action: Nudge")
    @OverdueUserExtension
    void overdueSingleActionNudge(final List<RestEnrollUnEnrollRequest> enrollments) {
        Selenide.refresh();
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());
        overdueTabService().clickSingleActionOverdue(user.getFirstName());
        overdueLearnersTab().getNudgeSingleAction().click();

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");
    }
}
