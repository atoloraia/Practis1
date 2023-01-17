package com.practis.selenide.company.user.pending.assign;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.psModuleService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertCleanPractisSetSearch;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertElementsOnPsSection;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertEmptyPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertNoPsSearchResult;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPractisSetCounter;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertPsSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSearchElementsOnPSsModal;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectAllPractisSetButton;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedAllStatePs;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertSelectedPractisSet;
import static com.practis.web.selenide.validator.selection.PractisSetSelectionValidator.assertUnSelectedAllStatePs;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageUtil.openPage;

import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PendingUserExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UserProfilePendingAssignPsTest {

    /** User Profile: Pending: Assign: Check WEB elements on PS section. */
    @TestRailTest(caseId = 14979)
    @DisplayName("User Profile: Pending: Assign: Practis Set section: Check elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 1)
    void checkElementsOnPsSection(final List<NewUserInput> users) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(15);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(15);

        assertElementsOnPsSection();
    }

    /** User Profile: Pending: Assign: Practis Set section: Search. */
    @TestRailTest(caseId = 14966)
    @DisplayName("User Profile: Pending: Assign: Ps: Search")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 2)
    void assignPractisSetSearch(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // assert search PS
        assertSearchElementsOnPSsModal();
        // assert clean search
        assertCleanPractisSetSearch(2);
        // Search should be performed after entering 1 character
        assertPsSearchAfter1Char(practisSets.get(0).getName());
        // assert empty state
        psModuleService().searchPs("no results");
        assertNoPsSearchResult();
    }

    /** User Profile: Pending: Assign: Practis Set section: Select All. */
    @TestRailTest(caseId = 14967)
    @DisplayName("User Profile: Pending: Assign: Ps: Select All")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 2)
    void assignTeamsSelectAll(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // assert unselected state
        assertUnSelectedAllStatePs();
        // select one Team
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        // assert modal if one Team is selected
        assertSelectedPractisSet(practisSets.get(0).getName());
        assertPractisSetCounter("1 Practis Set selected");
        assertSelectAllPractisSetButton();
        // select all
        psModuleService().selectAllPractisSets();
        assertSelectedAllStatePs();
    }

    /** User Profile: Pending: Assign: Practis Set section: Cancel. */
    @TestRailTest(caseId = 14969)
    @DisplayName("User Profile: Pending: Assign: Ps: Cancel")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 2)
    void assignPractisSetCancel(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // select one Practis Set and click "Cancel"
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        assignUserModuleService().cancel();
        // assert User row
        userProfilePage().getAssignButton().click();
        assertUnSelectedAllStatePs();
    }

    /** User Profile: Pending: Assign: Practis Set section: Apply. */
    @TestRailTest(caseId = 14968)
    @DisplayName("User Profile: Pending: Assign: Ps: Apply")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @PractisSetExtension(count = 1)
    void assignPractisSetApply(
            final List<NewUserInput> users, final List<NewPractisSetInput> practisSets) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // select one Practis Set and click 'Assign' button
        psModuleService().selectPractisSet(practisSets.get(0).getName());
        assignUserModuleService().apply();
        awaitAjaxComplete(10);

        snackbar().getMessage().shouldBe(exactText("Changes have been saved"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // assert User row
        assertUserData(users.get(0));
        userProfilePage().getAssignButton().click();

        assertSelectedPractisSet(practisSets.get(0).getName());
    }

    /** User Profile: Pending: Assign: Practis Set section: Empty State. */
    @TestRailTest(caseId = 14972)
    @DisplayName("User Profile: Pending: Assign: Ps: Empty state")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    void assignPractisSetEmptyState(final List<NewUserInput> users) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        assertEmptyPractisSet();
    }
}
