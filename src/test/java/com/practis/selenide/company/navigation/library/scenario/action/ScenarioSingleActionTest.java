package com.practis.selenide.company.navigation.library.scenario.action;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.libraryService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertElementsViewScenario;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertEmptyScenarioTab;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertLabelCountOnScenarioPage;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertScenarioStatusRow;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertScenariosRows;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertSingleActionArchivedScenario;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertSingleActionScenario;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertSingleActionScenarioNoLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelApplyButtonsSingleAction;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.selenide.validator.user.InviteUserValidator.assertDownloadedFile;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ArchivedScenariosExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.ScenarioExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ScenarioSingleActionTest {

    private final AtomicInteger integer = new AtomicInteger();
    private final List<RestCreateLabelResponse> labelsToRemove = new ArrayList<>();
    private List<String> scenarioToRemove;
    private List<String> scenarioToDelete;
    private List<String> challengesToRemove;

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        libraryPage().getScenariosTab().click();
        scenarioToRemove = new ArrayList<>();
        scenarioToDelete = new ArrayList<>();
        challengesToRemove = new ArrayList<>();
    }

    @TestRailTest(caseId = 31856)
    @DisplayName("Scenario: Single Action: Check Elements")
    @ScenarioExtension(count = 1)
    @LabelExtension(count = 1)
    void checkElementsSingleActionScenario(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());

        // asser single action practis set
        assertSingleActionScenario();
    }

    @TestRailTest(caseId = 31857)
    @DisplayName("Scenario: Single Action: No Labels: Check Elements")
    @ScenarioExtension(count = 1)
    void checkElementsSingleActionScenarioNoLabels(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());

        // asser single action practis set
        assertSingleActionScenarioNoLabels();
    }

    @TestRailTest(caseId = 31858)
    @DisplayName("Scenarios: Single Action: View")
    @ScenarioExtension(count = 1)
    void editPractisSetSingleAction(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickViewSingleAction();

        // assert 'Edit Scenario' page
        assertElementsViewScenario();
    }

    @TestRailTest(caseId = 31859)
    @DisplayName("Scenarios: Single Action: Assign Labels: Check Elements")
    @ScenarioExtension(count = 1)
    @LabelExtension(count = 1)
    void checkElementsOnAssignLabelsToScenario(
            List<RestScenarioResponse> scenario, final List<RestCreateLabelResponse> label) {

        // check elements on "Assign Labels" modal
        Selenide.refresh();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickAssignLabelsSingleAction();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsSingleAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.get(0).getName());
        assertSelectedLabel(label.get(0).getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 31860)
    @DisplayName("Scenarios: Single Action: Assign Labels: Apply")
    @ScenarioExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToScenario(
            List<RestScenarioResponse> scenario, final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        awaitFullPageLoad(10);
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickAssignLabelsSingleAction();
        labelModuleService().selectLabel(label.get(0).getName());
        labelModule().getApplyButton().click();

        Selenide.refresh();
        awaitFullPageLoad(10);
        assertLabelCountOnScenarioPage(scenario.get(0).getTitle(), "1");
    }

    @TestRailTest(caseId = 31861)
    @DisplayName("Scenarios: Single Action: Duplicate")
    @ScenarioExtension(count = 1)
    void duplicateScenarioSingleAction(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        awaitFullPageLoad(10);

        // Check number of teams in the list
        assertScenariosRows(1);

        // Duplicate the team
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickDuplicateSingleAction();

        awaitSoft(10, () -> scenarioTab().getScenarioRow().size() == 2);
        assertScenariosRows(2);
        final var originalScenario =
                scenarioTabService().getOriginalScenario(scenario.get(0).getTitle());
        final var duplicatedScenario =
                scenarioTabService().getDuplicatedScenario(scenario.get(0).getTitle());
        scenarioToRemove.add(duplicatedScenario.get("Scenarios").text());

        assertTrue(
                duplicatedScenario
                        .get("Scenarios")
                        .text()
                        .endsWith(originalScenario.get("Scenarios").text()));
    }

    @TestRailTest(caseId = 31862)
    @DisplayName("Scenarios: Single Action: Generate Challenge")
    @ScenarioExtension(count = 1)
    void generateChallengeScenarioSingleAction(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        // click 'Generate Challenge' single action
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickGenerateChallengeSingleAction();

        // TODO Processing popup describe

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge generated!"));
        challengesToRemove.add(scenario.get(0).getTitle());
    }

    @TestRailTest(caseId = 31863)
    @DisplayName("Scenarios: Single Action: Download as PDF")
    @ScenarioExtension(count = 1)
    void downloadAsPDFScenarioSingleAction(List<RestScenarioResponse> scenario)
            throws FileNotFoundException {
        Selenide.refresh();
        // click 'Download As PDF' single action
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        final File downloaded = scenarioTab().getDownloadPDFSingleAction().download();

        assertDownloadedFile(downloaded, downloaded.getName());
    }

    @TestRailTest(caseId = 31864)
    @DisplayName("Scenarios: Single Action: Archive")
    @ScenarioExtension(count = 1)
    void archiveScenarioSingleAction(List<RestScenarioResponse> scenario) {
        // click 'Archive' single action
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());
        scenarioTabService().clickArchiveSingleAction();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Scenario has been archived"));

        // assert Active Scenario list
        assertEmptyScenarioTab();

        // assert Archived scenario list
        libraryService().filterByArchivedItems();
        assertScenariosRows(1);
        assertScenarioStatusRow(scenario.get(0).getTitle(), "Archived");
    }

    @TestRailTest(caseId = 31865)
    @DisplayName("Scenarios: Archived: Single Action: Check Elements")
    @ArchivedScenariosExtension
    void checkElementsSingleActionArchivedScenario(final List<NewScenarioInput> scenario) {
        // assert elements on single action
        libraryService().filterByArchivedItems();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());

        assertSingleActionArchivedScenario();
    }

    @TestRailTest(caseId = 31866)
    @DisplayName("Scenarios: Archived: Single Action: Restore")
    @ArchivedScenariosExtension
    void restoreScenarioSingleAction(final List<NewScenarioInput> scenario) {
        awaitFullPageLoad(10);

        libraryService().filterByArchivedItems();

        scenarioTabService().clickRestoreSingleAction(scenario.get(0).getTitle());

        // assert snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Scenario has been restored"));

        // assert Draft scenario list
        libraryService().filterByDraftItems();
        assertScenariosRows(1);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        assertScenarioStatusRow(scenario.get(0).getTitle(), "Draft");
    }

    @TestRailTest(caseId = 31867)
    @DisplayName("Scenarios: Archived: Single Action: Delete")
    @ArchivedScenariosExtension
    void deleteScenarioSingleAction(final List<NewScenarioInput> scenario) {
        awaitFullPageLoad(10);

        libraryService().filterByArchivedItems();
        scenarioTabService().clickDeleteSingleAction(scenario.get(0).getTitle());

        // assert "Are you sure" pop-up
        areYouSurePopUp().saveChanges();

        // assert snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("1 Scenario has been deleted"));

        // assert empty tab
        awaitFullPageLoad(10);
        assertScenariosRows(0);
    }

    @AfterEach
    void cleanup() {
        scenarioToRemove.forEach(scenario -> practisApi().archiveAndDeleteScenario(scenario));
        scenarioToDelete.forEach(scenario -> practisApi().deleteScenario(scenario));
        challengesToRemove.forEach(title -> practisApi().archiveAndDeleteChallenge(title));
    }
}
