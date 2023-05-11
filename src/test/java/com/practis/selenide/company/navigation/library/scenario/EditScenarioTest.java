package com.practis.selenide.company.navigation.library.scenario;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createScenarioService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertElementsEditScenario;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertElementsViewScenario;
import static com.practis.web.selenide.validator.company.ScenarioValidator.assertScenarioGridRow;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class EditScenarioTest {

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

    /** Scenario: Check WEB Elements 'Edit Scenario' page. */
    @TestRailTest(caseId = 8688)
    @DisplayName("Scenario: View/Edit Scenario': Check Elements")
    @LabelExtension(count = 1)
    void checkElementsEditScenario(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createScenarioService().fillForm(inputData, label.get(0).getName());
        jsClick(scenarioCreatePage().getPublishButton());
        awaitSoft(10, () -> !scenarioCreatePage().getTitleField().isDisplayed());

        final var scenarioGridRow = scenarioTabService().searchScenario(inputData.getTitle());
        assertScenarioGridRow(inputData, scenarioGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        scenarioGridRow.click();

        assertElementsViewScenario();

        scenarioEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        assertElementsEditScenario();
    }

    @AfterEach
    void cleanup() {
        scenariosToRemove.forEach(title -> practisApi().archiveAndDeleteScenario(title));
    }
}
