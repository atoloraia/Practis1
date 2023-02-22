package com.practis.selenide.company.create.scenario;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.scenarioConfirmationPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioService;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertElementsNewScenario;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertScenarioData;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertScenarioGridRow;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertScenarioTitle;
import static com.practis.web.util.AwaitUtils.awaitElementCollectionSize;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.web.util.SelenidePageLoadAwait;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class NewScenarioTest {

    private List<String> scenariosToRemove;
    private NewScenarioInput inputData;

    @BeforeEach
    void init() {
        newItemSelector().create("Scenario");

        inputData = getNewScenarioInput();
        inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

        scenariosToRemove = new ArrayList<>();
        scenariosToRemove.add(inputData.getTitle());
    }

    /** Scenario: Check WEB Elements 'Add New Scenario' page. */
    @TestRailTest(caseId = 8476)
    @DisplayName("Scenario: Create: Check Elements")
    void checkElementsNewScenario() {
        assertElementsNewScenario();
    }

    /** Create Scenario. */
    @TestRailTest(caseId = 49)
    @DisplayName("Create Scenario")
    @LabelExtension(count = 1)
    void publishScenario(final List<RestCreateLabelResponse> label) {

        Selenide.refresh();
        SelenidePageLoadAwait.awaitFullPageLoad(10);

        scenarioService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Scenario published"
        awaitElementNotExists(10, () -> snackbar().getMessage());
        snackbar().getMessage().shouldBe(exactText("Scenario published"));

        // assert grid row data
        final var scenarioGridRow = scenarioService().searchScenario(inputData.getTitle());
        assertScenarioGridRow(inputData, scenarioGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioGridRow.click();
        assertScenarioData(inputData, scenarioEditPage());
    }

    /** Challenge: Save As Draft. */
    @TestRailTest(caseId = 50)
    @DisplayName("Scenario: Save As Draft")
    @LabelExtension(count = 1)
    void saveAsDraftScenario(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        scenarioService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioCreatePage().getSaveAsDraftButton().click();

        // Check snackbar message "Scenario saved as draft"
        snackbar().getMessage().shouldBe(exactText("Scenario saved as draft"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // assert grid row data
        final var scenarioGridRow = scenarioService().searchScenario(inputData.getTitle());
        assertScenarioGridRow(inputData, scenarioGridRow);

        // assert edit page data
        awaitSoft(10, () -> grid().getTableRows().size() == 1);
        scenarioGridRow.click();
        assertScenarioData(inputData, scenarioEditPage());
    }

    /** Create Scenario: Discard Changes pop-up. */
    @TestRailTest(caseId = 51)
    @DisplayName("Create Scenario: Discard Changes pop-up")
    void discardChangesScenario() {
        // discard changes
        scenarioService().fillTitle(inputData);
        scenarioService().exitScenarioWithDiscard();

        // grid().getTableRows().shouldBe(sizeGreaterThan(0));

        // save changes
        newItemSelector().create("Scenario");

        scenarioService().fillTitle(inputData);
        scenarioService().exitScenarioWithSave();

        // assert grid row data
        final var scenarioGridRow = scenarioService().searchScenario(inputData.getTitle());
        assertScenarioGridRow(inputData, scenarioGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioGridRow.click();
        assertScenarioTitle(inputData, scenarioEditPage());
    }

    /** Create Scenario: Validation: Required fields. */
    @TestRailTest(caseId = 52)
    @DisplayName("Create Scenario: Validation: Required fields")
    void validationMessagesScenario() throws InterruptedException {
        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Title required"
        snackbar().getMessage().shouldBe(exactText("Title required"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // Add title
        scenarioService().fillTitle(inputData);
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Scenario should have at least one line"
        snackbar().getMessage().shouldBe(exactText("Scenario should have at least one line"));

        // Add empty customer line
        scenarioService().fillCustomerLine(inputData);
        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Audio records required"
        snackbar().getMessage().shouldBe(exactText("Audio records required"));

        // Fill customer line
        scenarioService().generateForAll();
        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Audio records required"
        snackbar().getMessage().shouldBe(exactText("REP line required!"));

        // Add a rep line
        scenarioService().fillRepLine(inputData);
        scenarioService().generateForAll();
        awaitElementCollectionSize(10, () -> scenarioCreatePage().getPlayButtons(), 2);

        scenarioCreatePage().getPublishButton().click();

        // Check snackbar message "Scenario published!"
        awaitElementExists(10, () -> snackbar().getMessage());
        snackbar().getMessage().shouldBe(exactText("Scenario published"));

        // assert grid row data
        final var scenarioGridRow = scenarioService().searchScenario(inputData.getTitle());
        assertScenarioGridRow(inputData, scenarioGridRow);

        // assert edit page data
        scenarioGridRow.click();
        assertScenarioTitle(inputData, scenarioEditPage());
    }

    /** Create Scenario: CRUD for customer and rep lines. */
    @TestRailTest(caseId = 53)
    @DisplayName("Create Scenario: CRUD for customer and rep lines")
    void crudCustomerRepLines() {
        scenarioService().fillTitle(inputData);

        scenarioService().fillCustomerLine(inputData);
        scenarioService().fillRepLine(inputData);

        scenarioService().moveLine(1, -1);

        scenarioService().fillCustomerLine(inputData);
        scenarioCreatePage().getDeleteCustomerLine().click();

        scenarioConfirmationPopUp().discardChanges();

        scenarioCreatePage().getDeleteRepLine().click();
        scenarioConfirmationPopUp().saveChanges();
    }

    @AfterEach
    void cleanup() {
        scenariosToRemove.forEach(title -> practisApi().archiveAndDeleteScenario(title));
    }
}
