package com.practis.selenide.company.create.practisset;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertCreatedPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsNewPractisSet;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ChallengeExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.ScenarioExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class NewPractisSetTest {

  private NewPractisSetInput inputData;

  private List<String> practisSetsToRemove;

  @BeforeEach
  void init() {
    newItemSelector().create("Practis Set");

    inputData = getNewPractisSetInput();
    inputData.setName(String.format(inputData.getName(), timestamp()));

    practisSetsToRemove = new ArrayList<>();
    practisSetsToRemove.add(inputData.getName());

  }

  /**
   * Practis Set: Check WEB Elements 'Add New Practis Set' page.
   */
  @TestRailTest(caseId = 5309)
  @DisplayName("Check WEB Elements 'Add New Practis Set' page")
  void checkElementsNewPs() {
    assertElementsNewPractisSet();
  }

  /**
   * Create Practis Set.
   */
  @TestRailTest(caseId = 59)
  @DisplayName("Create Practis Set")
  @ScenarioExtension
  @ChallengeExtension
  @LabelExtension(count = 1)
  void publishPractisSet(final List<RestCreateLabelResponse> label, RestScenarioResponse scenario,
      RestChallengeResponse challenge) {

    Selenide.refresh();

    //Create PS
    //awaitFullPageLoad(10);
    practisSetService().createPractisSet(inputData, label.get(0).getName(),
        scenario.getTitle(), challenge.getTitle());

    practisSetService().publishPractisSet();
    practisSetService().confirmPublish();

    //Check snackbar message "Practis Set Published"
    snackbar().getMessage().shouldBe(exactText("Practis Set Published"));

    //CLick Cancel on "Assign Users and Due Dates" modal
    assignUserModuleService().cancel();

    //assert created PS
    assertCreatedPractisSet(inputData);
  }

  /**
   * Practis Set: Save As Draft.
   */
  @TestRailTest(caseId = 60)
  @DisplayName("Practis Set: Save As Draft")
  @ScenarioExtension
  @ChallengeExtension
  @LabelExtension(count = 1)
  void saveAsDraftPractisSet(final List<RestCreateLabelResponse> label,
      RestScenarioResponse scenario, RestChallengeResponse challenge) {

    Selenide.refresh();

    //Save as Draft Practis Set
    practisSetService().createPractisSet(inputData, label.get(0).getName(), scenario.getTitle(),
        challenge.getTitle());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    practisSetService().saveAsDraftPractisSet();

    //Check snackbar message "Practis Set Saved as Draft"
    awaitElementExists(10, () -> snackbar().getMessage())
        .shouldBe(exactText("Practis Set Saved as Draft"));

    //assert created PS
    assertCreatedPractisSet(inputData);
  }

  /**
   * Create Practis Set: Discard Changes pop-up.
   */
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

    //assert created PS
    assertCreatedPractisSet(inputData);
  }

  @AfterEach
  void cleanup() {
    practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
  }

}
