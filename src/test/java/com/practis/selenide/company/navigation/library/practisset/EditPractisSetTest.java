package com.practis.selenide.company.navigation.library.practisset;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsEditPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
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
public class EditPractisSetTest {

  private NewPractisSetInput inputData;
  private NewScenarioInput scenarioInput;
  private NewChallengeInput challengeInput;

  private List<String> practisSetsToRemove;
  private List<String> scenariosToRemove;
  private List<String> challengesToRemove;

  @BeforeEach
  void init() {
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
  @PractisSetExtension
  void checkElementsViewPractisSet(
      final List<RestCreateLabelResponse> label, final RestPractisSetResponse practisSet) {
    open(webApplicationConfig().getUrl() + "/library/practis-sets");
    final var practisSetGridRow = practisSetService().searchPS(practisSet.getName());
    practisSetGridRow.click();

    awaitFullPageLoad(10);

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
