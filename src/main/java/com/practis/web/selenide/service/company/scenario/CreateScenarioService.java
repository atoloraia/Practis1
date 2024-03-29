package com.practis.web.selenide.service.company.scenario;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.confirmationAndWarningPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.validator.popup.ProcessingPopUpValidator.asserProcessingPopUp;
import static com.practis.web.util.AwaitUtils.awaitElementCollectionSize;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.PractisUtils.clickOutOfTheForm;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewScenarioInput;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateScenarioService {

    private static final int GENERATE_ALL_TIMEOUT = 10;

    /** Fill Title. */
    public void fillTitle(final NewScenarioInput inputData) {
        scenarioCreatePage().getTitleField().append(inputData.getTitle());
    }

    /** Fill Scenario Form. */
    @SneakyThrows
    public void fillForm(final NewScenarioInput inputData, final String label) {
        fillTitle(inputData);
        scenarioCreatePage().getDescriptionField().append(inputData.getDescription());

        scenarioCreatePage().getLabelsButton().click();
        addLabel(label);

        // Check snackbar message "Challenge published"
        snackbar().getMessage().shouldBe(exactText("labels have been assigned to Scenario"));

        scenarioCreatePage().getAddCustomerLine().click();
        setDivText(scenarioCreatePage().getCustomerField().get(0), inputData.getCustomerLine());

        scenarioCreatePage().getAddARepLine().click();
        setDivText(scenarioCreatePage().getRepField().get(0), inputData.getRepLine());
        log.info("Click Generate All button");
        awaitElementEnabled(10, () -> scenarioCreatePage().getGenerateForAllButton()).click();
        asserProcessingPopUp("AI Voice Processing");
        log.info("Await until audio generated");
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> scenarioCreatePage().getPlayButtons(), 2);
    }

    /** Edit Scenario Form. */
    @SneakyThrows
    public void editForm(final String label) {
        scenarioCreatePage().getTitleField().append("_edit");
        scenarioCreatePage().getDescriptionField().append("_edit");
        scenarioCreatePage().getLabelsButton().click();
        addLabel(label);

        // Check snackbar message "Challenge published"
        snackbar().getMessage().shouldBe(exactText("labels have been assigned to Scenario"));

        scenarioCreatePage().getCustomerField().get(0).append("_edit");
        scenarioCreatePage().getGenerateForCustomerButton().click();
        scenarioCreatePage().getRepField().get(0).append("_edit");
        scenarioCreatePage().getGenerateForRepButton().click();
        log.info("Click Generate All button");
        awaitElementEnabled(10, () -> scenarioCreatePage().getGenerateForAllButton()).click();
        asserProcessingPopUp("AI Voice Processing");
        log.info("Await until audio generated");
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> scenarioCreatePage().getPlayButtons(), 2);
    }

    /** Select label and click 'Save Changes'. */
    public void addLabel(final String label) {
        scenarioCreatePage().findLabelCheckbox(label).click();
        scenarioCreatePage().getSaveChangesLabelButton().click();
    }

    /** Fill Customer Line. */
    public void fillCustomerLine(final NewScenarioInput inputData) {
        scenarioCreatePage().getAddCustomerLine().click();
        setDivText(scenarioCreatePage().getCustomerField().get(0), inputData.getCustomerLine());
    }

    /** Fill rep Line. */
    public void fillRepLine(final NewScenarioInput inputData) {
        scenarioCreatePage().getAddARepLine().click();
        setDivText(scenarioCreatePage().getRepField().get(0), inputData.getRepLine());
        awaitElementExists(10, () -> scenarioCreatePage().getGenerateForRepButton());
    }

    /** Generate for all. */
    @SneakyThrows
    public void generateForAll() {
        awaitElementEnabled(10, () -> scenarioCreatePage().getGenerateForAllButton()).click();
        asserProcessingPopUp("AI Voice Processing");
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> scenarioCreatePage().getPlayButtons(), 1);
    }

    /** Drag and drop lines. */
    public void moveLine(final int linePosition, final int moveLines) {
        final var draggableElement = scenarioCreatePage().getDraggableElements().get(linePosition);
        final var lineHeight = draggableElement.parent().getRect().getHeight();
        final var yOffset = lineHeight * moveLines;

        draggableElement.parent().parent().scrollIntoView(false);
        Selenide.actions()
                .clickAndHold(draggableElement)
                .moveByOffset(0, yOffset)
                .moveByOffset(0, yOffset * 2)
                .release(draggableElement)
                .perform();
    }

    /** Click outside the scenario form and click Discard Changes. */
    public void exitScenarioWithDiscard() {
        clickOutOfTheForm();
        confirmationAndWarningPopUp().discardChanges();
    }

    /** Click outside the scenario form and click Save Changes. */
    public void exitScenarioWithSave() {
        jsClick(navigationCompany().getTeamsNavigationItem());
        confirmationAndWarningPopUp().saveChanges();
    }
}
