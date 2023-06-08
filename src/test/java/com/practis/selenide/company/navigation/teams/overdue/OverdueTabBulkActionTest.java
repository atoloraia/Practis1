package com.practis.selenide.company.navigation.teams.overdue;

import static com.practis.rest.configuration.PractisApiV2ClientConfiguration.practisApiClientV2;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.overdueLearnersTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.nudgeUserService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.overdueTabService;
import static com.practis.web.selenide.validator.company.navigation.OverdueTabValidator.assertBulkActionOverdue;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.selenide.validator.selection.NudgeUserValidator.assertEmptyNudgeUserPopUp;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
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
public class OverdueTabBulkActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
        overdueLearnersTab().getOverdueTitle().click();
    }

    @TestRailTest(caseId = 31821)
    @DisplayName("Teams: Overdue: Bulk Actions: Check Elements")
    @OverdueUserExtension
    void overdueBulkActionCheckElements(final List<RestEnrollUnEnrollRequest> enrollments) {
        Selenide.refresh();
        overdueTabService().selectAllOverdueLearners();
        overdueLearnersTab().getActionButton().parent().click();
        assertBulkActionOverdue();
    }

    @TestRailTest(caseId = 31822)
    @DisplayName("Teams: Overdue: Bulk Action: Nudge")
    @OverdueUserExtension
    void overdueBulkActionNudge(final List<RestEnrollUnEnrollRequest> enrollments) {
        final var user = practisApiClientV2().searchUserById(enrollments.get(0).getUserId());

        // Click on Assign - Nudge User
        overdueTabService().searchOverdueLearners(user.getFirstName());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        overdueTabService().clickBulkActionNudge();

        // Assert Nudge User modal
        assertEmptyNudgeUserPopUp();

        // Assert 'Nudge - Send' for the Registered User
        nudgeUserService().sendNudge("Test Text");

        // Assert Bulk Action pop-up
        assertConfirmBulkActionPopUp();

        // Click on Proceed
        confirmationAndWarningPopUp().getConfirmButton().click();

        // Assert Snackbar
        snackbar().getMessage().shouldBe(Condition.exactText("Messages were sent successfully"));
    }
}
