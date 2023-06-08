package com.practis.selenide.company.navigation.teams;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.teamsPageService;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertElementsEmptyTeamsPage;
import static com.practis.web.selenide.validator.company.navigation.TeamsPageValidator.assertTeamsRows;
import static com.practis.web.selenide.validator.popup.ConfirmBulkActionPopUpValidator.assertConfirmBulkActionPopUp;
import static com.practis.web.util.AwaitUtils.awaitElementExists;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewTeamInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.TeamExtension;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class TeamsPageBulkActionTest {

    @BeforeEach
    void init() {
        navigationCompany().getTeamsNavigationItem().click();
    }

    @TestRailTest(caseId = 31766)
    @DisplayName("Teams: Bulk Action: Delete")
    @TeamExtension(count = 3)
    void teamsBulkActionDelete(final List<NewTeamInput> team) {
        Selenide.refresh();
        teamsPageService().selectAllTeams();
        teamsPage().getActionButton().parent().click();
        teamsPage().getDeleteTeamsActionButton().parent().click();

        // assert warning message
        assertConfirmBulkActionPopUp();
        confirmationAndWarningPopUp().getConfirmButton().click();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Teams have been deleted."));

        // assert team has been deleted
        assertTeamsRows(0);
        assertElementsEmptyTeamsPage();
    }
}
