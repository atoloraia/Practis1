package com.practis.selenide.company.create.challenge;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeData;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeGridRow;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeTitle;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertElementsOnNewChallengePage;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
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

  @TestRailTest(caseId = 5306)
  @DisplayName("Check WEB Elements on 'Add New Challenge' page")
  void checkElementsNewChallenge() {
    assertElementsOnNewChallengePage();
  }

  /**
   * Create Challenge.
   */
  @TestRailTest(caseId = 54)
  @DisplayName("Create Challenge")
  @LabelExtension(count = 1)
  void publishChallenge(final List<RestCreateLabelResponse> label) {
    Selenide.refresh();

    challengeService().fillForm(inputData, label.get(0).getName());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Challenge published"
    //TODO should be fixed after DEV-3426
    //awaitElementExists(10, () -> snackbar().getMessage())
    //.shouldBe(exactText("Challenge published"));

    //assert grid row data
    final var challengeGridRow = challengeService().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeData(inputData, challengeEditPage());
  }

  /**
   * Challenge: Save As Draft.
   */
  @TestRailTest(caseId = 55)
  @DisplayName("Save As Draft Challenge")
  @LabelExtension(count = 1)
  void saveAsDraftChallenge(final List<RestCreateLabelResponse> label) {

    Selenide.refresh();

    challengeService().fillForm(inputData, label.get(0).getName());
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getSaveAsDraftButton().click();

    //Check snackbar message "Challenge saved as draft"
    awaitElementExists(10, () -> snackbar().getMessage())
        .shouldBe(exactText("Challenge saved as draft"));

    //assert grid row data
    final var challengeGridRow = challengeService().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeData(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: Discard Changes pop-up.
   */
  @TestRailTest(caseId = 56)
  @DisplayName("Discard Changes pop-up")
  void discardChangesChallenge() {
    //discard changes
    challengeService().fillTitle(inputData);
    challengeService().exitChallengeWithDiscard();

    grid().getTableRows().shouldBe(sizeGreaterThan(0));

    //save changes

    newItemSelector().create("Challenge");

    challengeService().fillTitle(inputData);
    challengeService().exitChallengeWithSave();

    //assert grid row data
    final var challengeGridRow = challengeService().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeTitle(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: Validation: Required fields.
   */
  @TestRailTest(caseId = 57)
  @DisplayName("Validation: Required fields")
  void validationMessagesChallenge() {
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Title required"
    snackbar().getMessage().shouldBe(exactText("Title required"));
    awaitElementNotExists(10, () -> snackbar().getMessage());

    //Add title
    challengeService().fillTitle(inputData);
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Audio records required"
    snackbar().getMessage().shouldBe(exactText("Audio records required"));

    //Add title and customer line
    challengeService().fillCustomerLine(inputData);
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeCreatePage().getPublishButton().click();

    //Check snackbar message "Challenge published"
    //TODO should be fixed after DEV-3426
    //awaitElementExists(10, () -> snackbar().getMessage())
    //.shouldBe(exactText("Challenge published"));

    //assert grid row data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    final var challengeGridRow = challengeService().searchChallenge(inputData.getTitle());
    assertChallengeGridRow(inputData, challengeGridRow);

    //assert edit page data
    awaitElementNotExists(10, () -> snackbar().getMessage());
    challengeGridRow.click();
    assertChallengeTitle(inputData, challengeEditPage());
  }

  /**
   * Create Challenge: CRUD for customer lines.
   */
  @TestRailTest(caseId = 58)
  @DisplayName("CRUD for customer lines")
  void crudCustomerRepLines() throws InterruptedException {
    challengeService().fillTitleWithCustomerLine(inputData);
    challengeCreatePage().getDeleteCustomerLine().get(0).click();

    areYouSurePopUp().discardChanges();

    challengeCreatePage().getDeleteCustomerLine().get(0).click();
    areYouSurePopUp().saveChanges();
  }

  @AfterEach
  void cleanup() {
    challengesToRemove.forEach(title -> practisApi().deleteChallenge(title));
  }
}
