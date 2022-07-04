package com.practis.selenide.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenario;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.ScenarioValidator.assertElementsNewScenario;
import static com.practis.web.selenide.validator.ScenarioValidator.assertScenarioData;
import static com.practis.web.selenide.validator.ScenarioValidator.assertScenarioGridRow;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewScenarioInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class EditScenarioTest {

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
   * Scenario: Check WEB Elements 'Edit Scenario' page.
   */
  @Test
  @TestRailTest(caseId = 8476)
  @DisplayName("Check WEB Elements 'Add New Scenario' page")
  void checkElementsEditScenario() {

    final var labelInput =
        NewLabelInput.builder().name(String.format("test-%s", timestamp())).build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());

    Selenide.refresh();

    scenario().fillForm(inputData, label);
    scenarioCreatePage().getPublishButton().click();

    final var scenarioGridRow = scenario().searchScenario(inputData.getTitle());

    scenarioGridRow.click();


  }

}
