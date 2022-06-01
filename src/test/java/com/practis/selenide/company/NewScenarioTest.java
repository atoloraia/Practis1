package com.practis.selenide.company;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.AwaitUtils.awaitElementNotExists;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenario;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.ScenarioValidator.assertScenarioData;
import static com.practis.web.selenide.validator.ScenarioValidator.assertScenarioGridRow;
import static com.practis.web.selenide.validator.ScenarioValidator.assertScenarioTitle;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewScenarioInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class NewScenarioTest {

  private List<String> scenariosToRemove;
  private List<String> labelsToRemove;
  private NewScenarioInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("Scenario");

    inputData = getNewScenarioInput();
    inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

    labelsToRemove = new ArrayList<>();
    scenariosToRemove = new ArrayList<>();
    scenariosToRemove.add(inputData.getTitle());
  }

  /**
   * Create Scenario.
   */
  @Test
  @TestRailTest(caseId = 49)
  @DisplayName("Create Scenario")
  void publishScenario() {
    final var labelInput =
        NewLabelInput.builder().name(String.format("test-%s", timestamp())).build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());

    Selenide.refresh();

    scenario().createScenario(inputData, label);

    //Check snackbar message "Scenario published"
    snackbar().getMessage().shouldBe(exactText("Scenario published"));

    //assert grid row data
    final var scenarioGridRow = scenario().searchScenario(inputData.getTitle());
    assertScenarioGridRow(inputData, scenarioGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    scenarioGridRow.click();
    assertScenarioData(inputData, scenarioEditPage());
  }

  /**
   * Challenge: Save As Draft.
   */
  @Test
  @TestRailTest(caseId = 50)
  @DisplayName("Scenario: Save As Draft")
  void saveAsDraftScenario() {
    final var labelInput = NewLabelInput.builder()
        .name(String.format("test-%s", timestamp()))
        .build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());

    Selenide.refresh();

    scenario().saveAsDraftScenario(inputData, label);

    //Check snackbar message "Scenario saved as draft"
    snackbar().getMessage().shouldBe(exactText("Scenario saved as draft"));

    //assert grid row data
    final var scenarioGridRow = scenario().searchScenario(inputData.getTitle());
    assertScenarioGridRow(inputData, scenarioGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    scenarioGridRow.click();
    assertScenarioData(inputData, scenarioEditPage());
  }

  /**
   * Create Scenario: Discard Changes pop-up.
   */
  @Test
  @TestRailTest(caseId = 51)
  @DisplayName("Create Scenario: Discard Changes pop-up")
  void discardChangesScenario() {
    //discard changes
    scenarioCreatePage().fillTitle(inputData);
    scenario().exitScenarioWithDiscard();

    grid().getTableRows().shouldBe(sizeGreaterThan(0));

    //save changes
    newItemSelector().create("Scenario");

    scenarioCreatePage().fillTitle(inputData);
    scenario().exitScenarioeWithSave();

    //assert grid row data
    final var scenarioGridRow = scenario().searchScenario(inputData.getTitle());
    assertScenarioGridRow(inputData, scenarioGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    scenarioGridRow.click();
    assertScenarioTitle(inputData, scenarioEditPage());
  }

  /**
   * Create Scenario: Validation: Required fields.
   */
  @Test
  @TestRailTest(caseId = 52)
  @DisplayName("Create Scenario: Validation: Required fields")
  void validationMessagesScenario() {
    scenarioCreatePage().getPublishButton().click();

    //Check snackbar message "Title required"
    snackbar().getMessage().shouldBe(exactText("Title required"));
    awaitElementNotExists(10, () -> snackbar().getMessage());

    //Add title
    scenarioCreatePage().fillTitle(inputData);
    scenarioCreatePage().getPublishButton().click();

    //Check snackbar message "Scenario should have at least one line"
    snackbar().getMessage().shouldBe(exactText("Scenario should have at least one line"));

    //Add empty customer line
    scenarioCreatePage().fillCustomerLine(inputData);
    scenarioCreatePage().getPublishButton().click();

    //Check snackbar message "Audio records required"
    snackbar().getMessage().shouldBe(exactText("Audio records required"));

    //Fill customer line
    scenarioCreatePage().generateForAll();
    scenarioCreatePage().getPublishButton().click();

    //Check snackbar message "REP line required!"
    snackbar().getMessage().shouldBe(exactText("REP line required!"));

    //Add a rep line
    scenarioCreatePage().fillRepLine(inputData);
    scenarioCreatePage().generateForAll();

    awaitElementNotExists(10, () -> snackbar().getMessage());
    scenarioCreatePage().getPublishButton().click();

    //Check snackbar message "Scenario published!"
    snackbar().getMessage().shouldBe(exactText("Scenario published"));

    //assert grid row data
    final var scenarioGridRow = scenario().searchScenario(inputData.getTitle());
    assertScenarioGridRow(inputData, scenarioGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    scenarioGridRow.click();
    assertScenarioTitle(inputData, scenarioEditPage());
  }

  /**
   * Create Scenario: CRUD for customer and rep lines.
   */
  @Test
  @TestRailTest(caseId = 53)
  @DisplayName("Create Scenario: CRUD for customer and rep lines")
  void crudCustomerRepLines() {
    scenarioCreatePage().fillTitle(inputData);
    scenarioCreatePage().fillCustomerLine(inputData);
    scenarioCreatePage().getDeleteLine().click();

    discardChangeForm().discardChanges();

    scenarioCreatePage().getDeleteLine().click();
    discardChangeForm().saveChanges();

  }

  @AfterEach
  void cleanup() {
    labelsToRemove.forEach(label -> practisApi().deleteLabel(label));
    scenariosToRemove.forEach(title -> practisApi().deleteScenario(title));
  }
}
