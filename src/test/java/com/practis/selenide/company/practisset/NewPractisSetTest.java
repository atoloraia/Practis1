package com.practis.selenide.company.practisset;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.validator.PractisSetValidator.assertElementsNewPractisSet;
import static com.practis.web.selenide.validator.PractisSetValidator.assertPracrisSetTitle;
import static com.practis.web.selenide.validator.PractisSetValidator.assertPractisSetGridRow;
import static com.practis.web.selenide.validator.PractisSetValidator.assertPractisSetInput;
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
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class NewPractisSetTest {

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
   * Practis Set: Check WEB Elements 'Add New Practis Set' page.
   */
  @Test
  @TestRailTest(caseId = 5309)
  @DisplayName("Check WEB Elements 'Add New Practis Set' page")
  void checkElementsNewPs() {
    assertElementsNewPractisSet();
  }

  /**
   * Create Practis Set.
   */
  @Test
  @TestRailTest(caseId = 59)
  @DisplayName("Create Practis Set")
  @LabelExtension
  void publishPractisSet(final RestCreateLabelResponse label) {
    //Create Scenario and Challenge
    final var scenario = practisApi().createScenario(scenarioInput);
    final var challenge = practisApi().createChallenge(challengeInput);

    Selenide.refresh();

    //Create PS
    practisSetService().assertNumbers("0m 0s", "0", "65%");
    practisSetService().createPractisSet(inputData, label.getName(), scenario.getTitle(),
        challenge.getTitle());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetService().publishPractisSet();
    practisSetService().confirmPublish();

    //Check snackbar message "Practis Set Published"
    snackbar().getMessage().shouldBe(exactText("Practis Set Published"));

    //CLick Cancel on "Assign Users and Due Dates" modal
    assignUsersModule().cancel();

    //assert grid row data
    final var practisSetGridRow = practisSetService().searchPS(inputData.getTitle());
    assertPractisSetGridRow(inputData, practisSetGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetGridRow.click();
    assertPractisSetInput(inputData, practisSetEditPage());
  }

  /**
   * Practis Set: Save As Draft.
   */
  @Test
  @TestRailTest(caseId = 60)
  @DisplayName("Practis Set: Save As Draft")
  @LabelExtension
  void saveAsDraftPractisSet(final RestCreateLabelResponse label) {
    //Create Scenario and Challenge
    final var scenario = practisApi().createScenario(scenarioInput);
    final var challenge = practisApi().createChallenge(challengeInput);

    Selenide.refresh();

    //Save as Draft Practis Set
    practisSetService().createPractisSet(inputData, label.getName(), scenario.getTitle(),
        challenge.getTitle());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetService().saveAsDraftPractisSet();

    //Check snackbar message "Practis Set Saved as Draft"
    snackbar().getMessage().shouldBe(exactText("Practis Set Saved as Draft"));

    //assert grid row data
    final var practisSetGridRow = practisSetService().searchPS(inputData.getTitle());
    assertPractisSetGridRow(inputData, practisSetGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetGridRow.click();
    assertPractisSetInput(inputData, practisSetEditPage());
  }

  /**
   * Create Practis Set: Discard Changes pop-up.
   */
  @Test
  @TestRailTest(caseId = 62)
  @DisplayName("Create Practis Set: Discard Changes pop-up")
  void discardChangesPractisSet() {
    //discard changes
    practisSetService().fillTitle(inputData);
    practisSetService().exitPractisSetWithDiscard();

    grid().getTableRows().shouldBe(sizeGreaterThan(0));

    //save changes
    newItemSelector().create("Practis Set");

    practisSetService().fillTitle(inputData);
    practisSetService().exitPractisSetWithSave();

    //Check snackbar message "Practis Set Published"
    snackbar().getMessage().shouldBe(exactText("Practis Set Published"));

    //assert grid row data
    final var practisSetGridRow = practisSetService().searchPS(inputData.getTitle());
    assertPractisSetGridRow(inputData, practisSetGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetGridRow.click();
    assertPracrisSetTitle(inputData, practisSetEditPage());
  }

  @AfterEach
  void cleanup() {
    scenariosToRemove.forEach(scenario -> practisApi().deleteScenario(scenario));
    challengesToRemove.forEach(challenge -> practisApi().deleteChallenge(challenge));
    practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
  }

}
