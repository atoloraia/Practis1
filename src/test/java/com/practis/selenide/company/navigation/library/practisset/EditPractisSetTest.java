package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsEditPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertNumbers;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewPractisSetInput;
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
public class EditPractisSetTest {

  private NewPractisSetInput inputData;
  private NewScenarioInput scenarioInput;
  private NewChallengeInput challengeInput;

  private List<String> practisSetsToRemove;
  private List<String> scenariosToRemove;
  private List<String> challengesToRemove;

  @BeforeEach
  void init() {
    newItemSelector().create("Practis Set");

    inputData = getNewPractisSetInput();
    inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

    scenarioInput = getNewScenarioInput();
    scenarioInput.setTitle(String.format(scenarioInput.getTitle(), timestamp()));

    challengeInput = getNewChallengeInput();
    challengeInput.setTitle(String.format(challengeInput.getTitle(), timestamp()));

    practisSetsToRemove = new ArrayList<>();
    practisSetsToRemove.add(inputData.getTitle());

    scenariosToRemove = new ArrayList<>();
    scenariosToRemove.add(scenarioInput.getTitle());

    challengesToRemove = new ArrayList<>();
    challengesToRemove.add(challengeInput.getTitle());
  }

  /**
   * Create Practis Set.
   */
  @TestRailTest(caseId = 8789)
  @DisplayName("Check Web Elements on 'View Practis Set' Page")
  @LabelExtension(count = 1)
  void checkElementsViewPractisSet(final List<RestCreateLabelResponse> label) {
    //Create Scenario and Challenge
    final var scenario = practisApi().createScenario(scenarioInput);
    final var challenge = practisApi().createChallenge(challengeInput);

    Selenide.refresh();

    //Create PS
    assertNumbers("0m 0s", "0", "65%");
    practisSetService().createPractisSet(inputData, label.get(0).getName(), scenario.getTitle(),
        challenge.getTitle());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetService().publishPractisSet();
    practisSetService().confirmPublish();

    //CLick Cancel on "Assign Users and Due Dates" modal
    assignUserModuleService().cancel();

    //assert grid row data
    final var practisSetGridRow = practisSetService().searchPS(inputData.getTitle());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetGridRow.click();

    assertElementsViewPractisSet();
    practisSetEditPage().getScenarioTab().click();
    practisSetEditPage().getEditButton().click();
    areYouSurePopUp().getConfirmButton().click();
    assertElementsEditPractisSet();

  }


  @AfterEach
  void cleanup() {
    scenariosToRemove.forEach(scenario -> practisApi().deleteScenario(scenario));
    challengesToRemove.forEach(challenge -> practisApi().deleteChallenge(challenge));
    practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
  }

}
