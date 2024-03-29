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
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeSettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createChallengeService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeData;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeGridRow;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeTitle;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertElementsOnNewChallengePage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertChangedNumberOfTries;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertElementsOnChallengeSettingsPage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertHiddenChallengeSettings;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTries;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTriesPreselected;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTriesSelected;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

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
        inputData = getNewChallengeInput();
        inputData.setTitle(String.format(inputData.getTitle(), timestamp()));

        challengesToRemove = new ArrayList<>();
        challengesToRemove.add(inputData.getTitle());
    }

    @TestRailTest(caseId = 31826)
    @DisplayName("Challenge: Create: Check Elements")
    void checkElementsNewChallenge() {
        newItemSelector().create("Challenge");

        assertElementsOnNewChallengePage();
    }

    /** Create Challenge. */
    @TestRailTest(caseId = 31827)
    @DisplayName("Challenge: Create")
    @LabelExtension(count = 1)
    void publishChallenge(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        newItemSelector().create("Challenge");

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // Check snackbar message "Challenge published"
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge published"));

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        assertChallengeData(inputData, challengeEditPage());
    }

    /** Challenge: Save As Draft. */
    @TestRailTest(caseId = 31828)
    @DisplayName("Challenge: Save As Draft")
    @LabelExtension(count = 1)
    void saveAsDraftChallenge(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        newItemSelector().create("Challenge");

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getSaveAsDraftButton().click();

        // Check snackbar message "Challenge saved as draft"
        awaitElementExists(5, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge saved as draft"));

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        assertChallengeData(inputData, challengeEditPage());
    }

    /** Create Challenge: Discard Changes pop-up. */
    @TestRailTest(caseId = 31829)
    @DisplayName("Challenge: Save As Draft: Discard Changes ")
    void discardChangesChallenge() {
        newItemSelector().create("Challenge");

        // discard changes
        createChallengeService().fillTitle(inputData);
        createChallengeService().exitChallengeWithDiscard();

        grid().getTableRows().shouldBe(sizeGreaterThan(0));

        // save changes

        newItemSelector().create("Challenge");

        createChallengeService().fillTitleWithCustomerLine(inputData);
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        createChallengeService().exitChallengeWithSave();

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        assertChallengeTitle(inputData, challengeEditPage());
    }

    /** Create Challenge: Validation: Required fields. */
    @TestRailTest(caseId = 31830)
    @DisplayName("Challenge: Validation: Required fields")
    void validationMessagesChallenge() {
        newItemSelector().create("Challenge");

        challengeCreatePage().getPublishButton().click();

        // Check snackbar message "Title required"
        snackbar().getMessage().shouldBe(exactText("Title required"));
        awaitElementNotExists(10, () -> snackbar().getMessage());

        // Add title
        createChallengeService().fillTitle(inputData);
        challengeCreatePage().getPublishButton().click();

        // Check snackbar message "Audio records required"
        snackbar().getMessage().shouldBe(exactText("Audio records required"));

        // Add title and customer line
        createChallengeService().fillCustomerLine(inputData);
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // Check snackbar message "Challenge published"
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge published"));

        // assert grid row data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        assertChallengeTitle(inputData, challengeEditPage());
    }

    /** Create Challenge: CRUD for customer lines. */
    @TestRailTest(caseId = 31831)
    @DisplayName("Challenge: CRUD for customer lines")
    void crudCustomerRepLines() throws InterruptedException {
        newItemSelector().create("Challenge");

        createChallengeService().fillTitleWithCustomerLine(inputData);
        challengeCreatePage().getDeleteCustomerLine().get(0).click();

        areYouSurePopUp().discardChanges();

        challengeCreatePage().getDeleteCustomerLine().get(0).click();
        areYouSurePopUp().saveChanges();
    }

    @TestRailTest(caseId = 32313)
    @DisplayName("Create Challenge: Challenge Settings: Check Elements")
    void checkElementsOnChallengeSettings() {
        newItemSelector().create("Challenge");
        challengeSettingsService().openChallengeSettings();

        assertElementsOnChallengeSettingsPage();

        challengeSettingsService().closeChallengeSettings();
        assertHiddenChallengeSettings();
    }

    @TestRailTest(caseId = 32318)
    @LabelExtension(count = 1)
    @DisplayName("Create Challenge: Challenge Settings: Edit")
    void challengeCreateEditChallengeSettings(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        newItemSelector().create("Challenge");
        challengeSettingsService().openChallengeSettings();

        challengeSettingsService().changeNumberOfTries("5");
        challengeSettingsService().clickOnApply();
        challengeSettingsService().closeChallengeSettings();
        assertChangedNumberOfTries();

        challengeSettingsService().openChallengeSettings();
        challengeSettingsService().changeToUnlimited();
        assertUnlimitedTriesPreselected();
        challengeSettingsService().clickOnApply();
        assertUnlimitedTriesSelected();

        challengeSettingsService().closeChallengeSettings();
        assertUnlimitedTries();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // Check snackbar message "Challenge published"
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge published"));
    }

    @AfterEach
    void cleanup() {
        challengesToRemove.forEach(title -> practisApi().archiveAndDeleteChallenge(title));
    }
}
