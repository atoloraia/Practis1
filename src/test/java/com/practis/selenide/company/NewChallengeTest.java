package com.practis.selenide.company;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
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
import static com.practis.web.selenide.validator.ChallengeValidator.assertElementsOnNewChallengePage;
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
public class NewChallengeTest {

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

  @Test
  @TestRailTest(caseId = 5306)
  @DisplayName("Check WEB Elements on 'Add New Challenge' page")
  void checkElementsNewChallenge() {
    assertElementsOnNewChallengePage();
  }

  /**
   * Create Challenge.
   */
  @Test
  @TestRailTest(caseId = 54)
  @DisplayName("Create Challenge")
  @LabelExtension
  void publishChallenge(final RestCreateLabelResponse label) {
    Selenide.refresh();

    challenge().fillForm(inputData, label.getName());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Challenge published"
    snackbar().getMessage().shouldBe(exactText("Challenge published"));

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
  @LabelExtension
  void saveAsDraftChallenge(final RestCreateLabelResponse label) {

    Selenide.refresh();

    challenge().fillForm(inputData, label.getName());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getSaveAsDraftButton().click();

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
  void discardChangesChallenge() {
    //discard changes
    challenge().fillTitle(inputData);
    challenge().exitChallengeWithDiscard();

    grid().getTableRows().shouldBe(sizeGreaterThan(0));

    //save changes

    newItemSelector().create("Challenge");

    challenge().fillTitle(inputData);
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
    challenge().fillTitle(inputData);
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Audio records required"
    snackbar().getMessage().shouldBe(exactText("Audio records required"));

    //Add title and customer line
    challenge().fillCustomerLine(inputData);
    awaitElementNotExists(10, () -> snackbar().getMessage());
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
    challenge().fillTitleWithCustomerLine(inputData);
    challengeCreatePage().getDeleteCustomerLine().get(0).click();

    discardChangeForm().discardChanges();

    challengeCreatePage().getDeleteCustomerLine().get(0).click();
    discardChangeForm().saveChanges();
  }

  @AfterEach
  void cleanup() {
    challengesToRemove.forEach(title -> practisApi().deleteChallenge(title));
  }
}
