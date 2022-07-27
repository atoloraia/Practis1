package com.practis.selenide.company.challenge;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.scenarioConfirmationPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challenge;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeData;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeGridRow;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeTitle;
import static com.practis.web.selenide.validator.ChallengeValidator.assertElementsOnEditChallengePage;
import static com.practis.web.selenide.validator.ChallengeValidator.assertElementsOnNewChallengePage;
import static com.practis.web.selenide.validator.ChallengeValidator.assertElementsOnViewChallengePage;
import static com.practis.web.selenide.validator.ScenarioValidator.assertElementsEditScenario;
import static com.practis.web.selenide.validator.ScenarioValidator.assertElementsViewScenario;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewChallengeInput;
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
public class EditChallengeTest {

  private List<String> challengesToRemove;
  private NewChallengeInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("Challenge");

    inputData = getNewChallengeInput();
    inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

    challengesToRemove = new ArrayList<>();
    challengesToRemove.add(inputData.getTitle());
  }

  /**
   * Challenge: Check WEB Elements 'Vew Challenge' page.
   */
  @Test
  @TestRailTest(caseId = 9138)
  @DisplayName("View Challenge")
  @LabelExtension
  void publishChallenge(final RestCreateLabelResponse label) {
    Selenide.refresh();

    challenge().fillForm(inputData, label.getName());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getPublishButton().click();


    //assert grid row data
    final var challengeGridRow = challenge().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();

    assertElementsOnViewChallengePage();

    challengeEditPage().getEditButtonChallenge().click();
    areYouSurePopUp().getConfirmButton().click();

    assertElementsOnEditChallengePage();
  }

  @AfterEach
  void cleanup() {
    challengesToRemove.forEach(title -> practisApi().deleteChallenge(title));
  }
}
