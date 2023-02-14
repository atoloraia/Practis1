package com.practis.selenide.company.users.pending.assign;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.userProfilePage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCleanLabelSearch;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertEmptyLabelModel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelSearchAfter1Char;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertNoLabelSearchResult;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSearchElementsOnLabelsModal;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectAllLabelButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.user.UserProfileValidator.assertUserData;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageUtil.openPage;

import com.practis.dto.NewUserInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PendingUserExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UserProfilePendingAssignLabelsTest {

    /** User Profile: Pending: Assign: Check WEB elements on Label section. */
    @TestRailTest(caseId = 14987)
    @DisplayName("User Profile: Pending: Assign: Label: Check Elements")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    void checkElementsOnLabelSection(final List<NewUserInput> users) {
        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        assertElementsOnLabelSection();
    }

    /** User Profile: Pending: Assign: Labels section: Search. */
    @TestRailTest(caseId = 14988)
    @DisplayName("User Profile: Pending: Assign: Label: Search")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 2)
    void assignLabelsSearch(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> labels) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(15);

        // assert search team
        assertSearchElementsOnLabelsModal();
        // assert clean search
        assertCleanLabelSearch(2);
        // Search should be performed after entering 1 character
        assertLabelSearchAfter1Char(labels.get(0).getName());
        // assert empty state
        labelModuleService().searchLabel("no results");
        assertNoLabelSearchResult();
    }

    /** User Profile: Pending: Assign: Label section: Select All. */
    @TestRailTest(caseId = 14989)
    @DisplayName("User Profile: Pending: Assign: Label: Select All")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 2)
    void assignLabelsSelectAll(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> labels) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // assert unselected state
        assertUnSelectAllStateLabels();

        // select one Label
        labelModuleService().selectLabel(labels.get(0).getName());
        assertSelectedLabel(labels.get(0).getName());
        assertLabelCounter("1 Label selected");
        assertSelectAllLabelButton();

        // select all
        // TODO Update clicking on "Select All" when DEV-10367 will be done
        $(".sc-eHtcfq.kFgRZY").click();
        assertSelectedAllStateLabels();
    }

    /** User Profile: Pending: Assign: Labels section: Cancel. */
    @TestRailTest(caseId = 14991)
    @DisplayName("User Profile: Pending: Assign: Label: Cancel")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    void assignLabelCancel(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> label) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // select one Label and click "Cancel"
        labelModuleService().selectLabel(label.get(0).getName());
        assignUserModuleService().cancel();
        // assert User row
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);
        assertUnSelectAllStateLabels();
    }

    /** User Profile: Pending: Assign: Labels section: Apply. */
    @TestRailTest(caseId = 14990)
    @DisplayName("User Profile: Pending: Assign: Label: Apply")
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    void assignLabelApply(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> label) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(15);

        // select one Label and click 'Assign' button
        labelModuleService().selectLabel(label.get(0).getName());
        assignUserModuleService().apply();
        snackbar().getMessage().shouldBe(exactText("Changes have been saved"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // assert User row
        assertUserData(users.get(0));
        userProfilePage().getAssignButton().click();
        assertSelectedLabel(label.get(0).getName());
    }

    /** User Profile: Pending: Assign: Labels section: Empty state. */
    @TestRailTest(caseId = 14994)
    @PendingUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("User Profile: Pending: Assign: Label: Empty state")
    void assignLabelEmptyState(final List<NewUserInput> users) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(15);

        assertEmptyLabelModel();
    }
}
