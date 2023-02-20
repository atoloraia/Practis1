package com.practis.selenide.company.navigation.library.practisset.action;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.teamsPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertEmptyPractisSetsTab;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertLabelCountOnPsPage;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertPractisSetsRows;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertPsStatusRow;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertSingleActionArchivedPs;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertSingleActionPractisSet;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertSingleActionPractisSetNoLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelApplyButtonsSingleAction;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnselectedLabel;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ArchivedPractisSetExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetSingleActionTest {

    private List<String> practisSetsToRemove;

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        practisSetsToRemove = new ArrayList<>();
    }

    @TestRailTest(caseId = 1864)
    @DisplayName("Practis Sets: Single Action:Check Elements")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    void checkElementsSingleActionPs(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());

        // asser single action practis set
        assertSingleActionPractisSet();
    }

    @TestRailTest(caseId = 23850)
    @DisplayName("Practis Sets: Single Action: No Labels: Check Elements")
    @PractisSetExtension(count = 1)
    void checkElementsSingleActionPsNoLabels(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());

        // asser single action practis set
        assertSingleActionPractisSetNoLabels();
    }

    @TestRailTest(caseId = 1866)
    @DisplayName("Practis Sets: Single Action: Edit")
    @PractisSetExtension(count = 1)
    void editPractisSetSingleAction(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickEditSingleAction();

        // asser View Practis Set page
        assertElementsViewPractisSet();
    }

    @TestRailTest(caseId = 23847)
    @DisplayName("Practis Set: Single Action: Assign Labels: Check Elements")
    @PractisSetExtension(count = 2)
    @LabelExtension(count = 1)
    void checkElementsOnAssignLabelsToPs(
            final List<NewPractisSetInput> practisSets, final List<RestCreateLabelResponse> label) {
        // check elements on "Assign Labels" modal
        Selenide.refresh();
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickAssignLabelsSingleAction();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsSingleAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.get(0).getName());
        assertSelectedLabel(label.get(0).getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 23848)
    @DisplayName("Practis Set: Single Action: Assign Labels: Apply")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToPs(
            final List<RestCreateLabelResponse> label, final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        awaitFullPageLoad(10);
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickAssignLabelsSingleAction();
        labelModuleService().selectLabel(label.get(0).getName());
        labelModule().getApplyButton().click();

        Selenide.refresh();
        awaitFullPageLoad(10);
        assertLabelCountOnPsPage(practisSets.get(0).getName(), "1");
    }

    @TestRailTest(caseId = 23849)
    @DisplayName("Practis Sets: Single Action: Assign Labels: Check already assigned label")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 2)
    void alreadyAssignLabelsPractisSetSingleAction(
            final List<RestCreateLabelResponse> label, final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisApi()
                .assignLabelToPractisSet(practisSets.get(0).getId(), List.of(label.get(0).getId()));

        Selenide.refresh();
        awaitFullPageLoad(10);
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickAssignLabelsSingleAction();
        assertSelectedLabel(label.get(0).getName());
        assertUnselectedLabel(label.get(1).getName());
    }

    @TestRailTest(caseId = 1860)
    @DisplayName("Practis Sets: Single Action: Duplicate")
    @PractisSetExtension(count = 1)
    void duplicatePractisSetSingleAction(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        awaitFullPageLoad(10);

        // Check number of teams in the list
        assertPractisSetsRows(1);

        // Duplicate the team
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickDuplicateSingleAction();

        awaitSoft(10, () -> teamsPage().getTeamRow().size() == 2);
        assertPractisSetsRows(2);
        final var originalPs = practisSetTabService().getOriginalPs(practisSets.get(0).getName());
        final var duplicatedPs =
                practisSetTabService().getDuplicatedPs(practisSets.get(0).getName());
        practisSetsToRemove.add(duplicatedPs.get("Practis Sets").text());

        assertTrue(
                duplicatedPs
                        .get("Practis Sets")
                        .text()
                        .endsWith(originalPs.get("Practis Sets").text()));
    }

    @TestRailTest(caseId = 1861)
    @DisplayName("Practis Sets: Single Action: Archive")
    @PractisSetExtension(count = 1)
    void archivePractisSetSingleAction(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        // click 'Delete' single action
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickArchiveSingleAction();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Practis Set has been archived"));

        assertEmptyPractisSetsTab();
    }

    @TestRailTest(caseId = 26930)
    @DisplayName("Practis Sets: Archived: Single Action: Check Elements")
    @ArchivedPractisSetExtension()
    void checkElementsSingleActionArchivedPs(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        awaitFullPageLoad(10);

        practisSetTabService().filterByArchivedPs();
        practisSetTabService().clickSingleAction(practisSets.get(0).getName());

        assertSingleActionArchivedPs();
    }

    @TestRailTest(caseId = 26929)
    @DisplayName("Practis Sets: Archived: Single Action: Restore")
    @ArchivedPractisSetExtension()
    void restorePractisSetSingleAction(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        awaitFullPageLoad(10);

        practisSetTabService().filterByArchivedPs();

        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickRestoreSingleAction();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Practis Set has been restored"));

        assertEmptyPractisSetsTab();

        practisSetTabService().filterByDraftPs();
        assertPractisSetsRows(1);
        assertPsStatusRow(practisSets.get(0).getName(), "Draft");
        practisSetsToRemove.add(practisSets.get(0).getName());
    }

    @TestRailTest(caseId = 26931)
    @DisplayName("Practis Sets: Archived: Single Action: Delete")
    @ArchivedPractisSetExtension()
    void deletePractisSetSingleAction(final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();

        awaitFullPageLoad(10);
        practisSetTabService().filterByArchivedPs();

        practisSetTabService().clickSingleAction(practisSets.get(0).getName());
        practisSetTabService().clickDeleteSingleAction();

        areYouSurePopUp().saveChanges();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("1 Practis Set has been deleted"));

        assertEmptyPractisSetsTab();
    }

    @AfterEach
    void cleanup() {
        practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
    }
}
