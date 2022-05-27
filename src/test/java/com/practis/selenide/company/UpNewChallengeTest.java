package com.practis.selenide.company;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.AwaitUtils.awaitElementNotExists;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.discardChangeForm;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challenge;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeData;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeGridRow;
import static com.practis.web.selenide.validator.ChallengeValidator.assertChallengeTitle;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewLabelInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
public class UpNewChallengeTest {

  private List<String> challengesToRemove;
  private List<String> labelsToRemove;
  private NewChallengeInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("Challenge");

    inputData = getNewChallengeInput();
    inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

    labelsToRemove = new ArrayList<>();
    challengesToRemove = new ArrayList<>();
    challengesToRemove.add(inputData.getTitle());
  }

  /**
   * Create Challenge.
   */
  @Test
  @TestRailTest(caseId = 54)
  @DisplayName("Create Challenge")
  void publishChallenge() {
    final var labelInput = NewLabelInput.builder()
        .name(String.format("test-%s", timestamp()))
        .build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());

    Selenide.refresh();

    challenge().createChallenge(inputData, label);

    //Check snackbar message "Challenge published"
    //   snackbar().getMessage().shouldBe(exactText("Challenge published"));

    //assert grid row data
    final var challengeGridRow = challenge().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeData(inputData, challengeEditPage());
  }


  /**
   * Challenge: Save As Draft.
   */
  @Test
  @TestRailTest(caseId = 55)
  @DisplayName("Save As Draft Challenge")
  void saveAsDraftChallenge() {
    final var labelInput = NewLabelInput.builder()
        .name(String.format("test-%s", timestamp()))
        .build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());

    Selenide.refresh();

    challenge().saveAsDraftChallenge(inputData, label);

    //Check snackbar message "Challenge saved as draft"
    snackbar().getMessage().shouldBe(exactText("Challenge saved as draft"));

    //assert grid row data
    final var challengeGridRow = challenge().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeData(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: Discard Changes pop-up.
   */
  @Test
  @TestRailTest(caseId = 56)
  @DisplayName("Discard Changes pop-up")
  void discardChangesScenario() {
    //discard changes
    challengeCreatePage().fillTitle(inputData);
    challenge().exitChallengeWithDiscard();

    grid().getTableRows().shouldBe(sizeGreaterThan(0));

    //save changes
    newItemSelector().create("Challenge");

    challengeCreatePage().fillTitle(inputData);
    challenge().exitChallengeWithSave();

    //assert grid row data
    final var challengeGridRow = challenge().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeTitle(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: Validation: Required fields.
   */
  @Test
  @TestRailTest(caseId = 57)
  @DisplayName("Validation: Required fields")
  void validationMessagesChallenge() {
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Title required"
    snackbar().getMessage().shouldBe(exactText("Title required"));
    awaitElementNotExists(10, () -> snackbar().getMessage());

    //Add title
    challengeCreatePage().fillTitle(inputData);
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Audio records required"
    snackbar().getMessage().shouldBe(exactText("Audio records required"));

    //Add title and customer line
    challengeCreatePage().fillCustomerLine(inputData);
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("Challenge published"));

    //assert grid row data
    final var challengeGridRow = challenge().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeTitle(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: CRUD for customer lines.
   */
  @Test
  @TestRailTest(caseId = 58)
  @DisplayName("CRUD for customer lines")
  void crudCustomerRepLines() throws InterruptedException {
    challengeCreatePage().fillTitleWithCustomerLine(inputData);
    challengeCreatePage().getDeleteCustomerLine().click();

    discardChangeForm().discardChanges();

    challengeCreatePage().getDeleteCustomerLine().click();
    discardChangeForm().saveChanges();

  }

  @AfterEach
  void cleanup() {
    labelsToRemove.forEach(label -> practisApi().deleteLabel(label));
    challengesToRemove.forEach(title -> practisApi().deleteChallenge(title));
  }
}
