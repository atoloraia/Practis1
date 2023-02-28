package com.practis.selenide.company.users.registered.assign;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
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
import com.practis.support.extension.practis.RegisteredUserExtension;
import java.util.List;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class UserProfileRegisteredAssignLabelsTest {

    /** User Profile: Registered: Assign: Check WEB elements on Label section. */
    @TestRailTest(caseId = 15018)
    @DisplayName("User Profile: Registered: Assign: Label section: Check elements")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    void checkElementsOnLabelSectiond(final List<NewUserInput> users) {
        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        assertElementsOnLabelSection();
    }

    /** User Profile: Registered: Assign: Labels section: Search. */
    @TestRailTest(caseId = 15019)
    @DisplayName("User Profile: Registered: Assign: Label: Search")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 2)
    void assignLabelsSearch(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> labels) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

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

    /** User Profile: Registered: Assign: Label section: Select All. */
    @TestRailTest(caseId = 15020)
    @DisplayName("User Profile: Registered: Assign: Label: Select All")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
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
        // assert modal if one Label is selected
        assertSelectedLabel(labels.get(0).getName());
        assertLabelCounter("1 Label selected");
        assertSelectAllLabelButton();
        // select all
        labelModule().getSelectedAllButton().click();
        assertSelectedAllStateLabels();
    }

    /** User Profile: Registered: Assign: Labels section: Cancel. */
    @TestRailTest(caseId = 15022)
    @DisplayName("User Profile: Registered: Assign: Label: Cancel")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
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
        awaitAjaxComplete(10);
        // assert User row
        userProfilePage().getAssignButton().click();
        assertUnSelectAllStateLabels();
    }

    /** User Profile: Registered: Assign: Labels section: Apply. */
    @TestRailTest(caseId = 15021)
    @DisplayName("User Profile: Registered: Assign: Label: Apply")
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @LabelExtension(count = 1)
    void assignLabelApply(
            final List<NewUserInput> users, final List<RestCreateLabelResponse> label) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        // select one Label and click 'Assign' button
        labelModuleService().selectLabel(label.get(0).getName());
        assignUserModuleService().apply();
        snackbar().getMessage().shouldBe(exactText("Changes have been saved"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // assert User data
        assertUserData(users.get(0));
        userProfilePage().getAssignButton().click();
        assertSelectedLabel(label.get(0).getName());
    }

    /** User Profile: Registered: Assign: Labels section: Empty state. */
    @TestRailTest(caseId = 15025)
    @RegisteredUserExtension(limit = 1, company = "CompanyAuto", role = 7)
    @DisplayName("User Profile: Registered: Assign: Label: Empty state")
    void assignLabelEmptyState(final List<NewUserInput> users) {

        openPage(webApplicationConfig().getUrl() + "/user/performance/" + users.get(0).getId());
        awaitAjaxComplete(10);
        userProfilePage().getAssignButton().click();
        awaitAjaxComplete(10);

        assertEmptyLabelModel();
    }
}
