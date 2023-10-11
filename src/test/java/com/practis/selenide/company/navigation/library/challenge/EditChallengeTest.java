package com.practis.selenide.company.navigation.library.challenge;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeSettingsService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createChallengeService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertChallengeGridRow;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertEditedChallengeData;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertElementsOnEditChallengePage;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertElementsOnViewChallengePage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertChangedNumberOfTries;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertElementsOnChallengeSettingsPage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertHiddenChallengeSettings;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTries;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTriesPreselected;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeSettingsValidator.assertUnlimitedTriesSelected;
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

    /** Challenge: Check WEB Elements 'View Challenge' page. */
    @TestRailTest(caseId = 31832)
    @DisplayName("Edit Challenge: Preview mode: Check Elements")
    @LabelExtension(count = 1)
    void viewChallenge(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();

        assertElementsOnViewChallengePage();
    }

    /** Challenge: Check WEB Elements 'Edit Challenge' page. */
    @TestRailTest(caseId = 31833)
    @DisplayName("Edit Challenge: Edit mode: Check Elements")
    @LabelExtension(count = 1)
    void editChallengeCheckElements(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();

        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        assertElementsOnEditChallengePage();
    }

    /** Challenge: Edit Challenge. */
    @TestRailTest(caseId = 31834)
    @DisplayName("Library: Challenge: Edit Challenge")
    @LabelExtension(count = 2)
    void editChallenge(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // Open Edit Scenario page
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        challengeGridRow.click();
        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        // Edit Scenario
        createChallengeService().editForm(label.get(1).getName());
        challengeEditPage().getSaveChangesButton().click();

        // Assert that changes have been applied
        final var editedChallengeGridRow = createChallengeService().searchChallenge("_edit");
        awaitElementNotExists(10, () -> snackbar().getMessage());
        editedChallengeGridRow.click();

        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();
        assertEditedChallengeData(scenarioEditPage(), label);
    }

    @TestRailTest(caseId = 32312)
    @LabelExtension(count = 1)
    @DisplayName("Edit Challenge: Challenge Settings: Check Elements")
    void checkElementsOnChallengeSettings(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());
        assertChallengeGridRow(inputData, challengeGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        challengeSettingsService().openChallengeSettings();

        assertElementsOnChallengeSettingsPage();

        challengeSettingsService().closeChallengeSettings();
        assertHiddenChallengeSettings();
    }

    @TestRailTest(caseId = 32317)
    @LabelExtension(count = 1)
    @DisplayName("Edit Challenge: Challenge Settings: Edit")
    void editChallengeEditChallengeSettings(final List<RestCreateLabelResponse> label) {
        Selenide.refresh();

        createChallengeService().fillForm(inputData, label.get(0).getName());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeCreatePage().getPublishButton().click();

        // assert grid row data
        final var challengeGridRow = createChallengeService().searchChallenge(inputData.getTitle());

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        challengeSettingsService().openChallengeSettings();

        challengeSettingsService().changeNumberOfTries("5");
        challengeSettingsService().clickOnApply();
        challengeSettingsService().closeChallengeSettings();
        assertChangedNumberOfTries();
        challengeEditPage().getSaveChangesButton().click();

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        challengeGridRow.click();
        challengeEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        challengeSettingsService().openChallengeSettings();

        challengeSettingsService().changeToUnlimited();
        assertUnlimitedTriesPreselected();
        challengeSettingsService().clickOnApply();
        assertUnlimitedTriesSelected();

        challengeSettingsService().closeChallengeSettings();
        assertUnlimitedTries();

        challengeEditPage().getSaveChangesButton().click();

        // Check snackbar message "Challenge published"
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge has been updated"));
    }

    @AfterEach
    void cleanup() {
        challengesToRemove.forEach(title -> practisApi().archiveAndDeleteChallenge(title));
    }
}
