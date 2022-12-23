package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;

import com.practis.dto.NewScenarioInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.scenario.ScenarioEditPage;

public class ScenarioValidator {

    /** Assert grid row with input data. */
    public static void assertScenarioGridRow(
            final NewScenarioInput inputData, final GridRow gridRow) {
        gridRow.get("Scenarios").shouldBe(matchText(inputData.getTitle()));
    }

    /** Assert data on edit page with input. */
    public static void assertScenarioData(
            final NewScenarioInput inputData, final ScenarioEditPage scenarioEditPage) {
        scenarioEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
        scenarioEditPage.getDescriptionField().shouldBe(text(inputData.getDescription()));
    }

    /** Assert Title on edit page with input. */
    public static void assertScenarioTitle(
            final NewScenarioInput inputData, final ScenarioEditPage scenarioEditPage) {
        scenarioEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    }

    /** Assert elements on Add New Scenario page. */
    public static void assertElementsNewScenario() {
        scenarioCreatePage().getAddNewScenarioTitle().shouldBe(exactText("Add New Scenario"));
        scenarioCreatePage().getAddNewScenarioTitle().shouldBe(visible);
        scenarioCreatePage().getNotPublishTitle().shouldBe(exactText("Not Published Yet"));
        scenarioCreatePage().getNotPublishTitle().shouldBe(visible);

        scenarioCreatePage().getTitleField().shouldBe(visible);
        scenarioCreatePage().getTitleField().shouldBe(attribute("placeholder", "Scenario Title"));
        scenarioCreatePage().getTitleField().shouldBe(attribute("maxlength", "100"));

        scenarioCreatePage().getDescriptionField().shouldBe(visible);
        scenarioCreatePage()
                .getDescriptionField()
                .shouldBe(attribute("placeholder", "Description"));
        scenarioCreatePage().getDescriptionField().shouldBe(attribute("maxlength", "500"));
        scenarioCreatePage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        scenarioCreatePage().getSymbolCount().shouldBe(visible);
        scenarioCreatePage().getSymbolCount().shouldBe(matchText("0/500"));

        scenarioCreatePage().getLabelsButton().shouldBe(visible);
        scenarioCreatePage().getLabelsButton().shouldBe(enabled);
        scenarioCreatePage().getLabelsButtonName().shouldBe(exactText("Labels"));
        scenarioCreatePage().getLabelsButtonName().shouldBe(visible);

        scenarioCreatePage().getDownloadAsPdfButton().shouldBe(visible);
        scenarioCreatePage().getDownloadAsPdfButton().shouldHave(attribute("disabled"));
        scenarioCreatePage().getCustomerLinesTitle().shouldBe(visible);
        scenarioCreatePage().getCustomerLinesTitle().shouldBe(exactText("Customer Lines"));
        scenarioCreatePage().getCustomerLinesValue().shouldBe(visible);
        scenarioCreatePage().getCustomerLinesValue().shouldBe(exactText("0"));

        scenarioCreatePage().getRepLinesTitle().shouldBe(visible);
        scenarioCreatePage().getRepLinesTitle().shouldBe(exactText("Rep Lines"));
        scenarioCreatePage().getRepLinesValue().shouldBe(visible);
        scenarioCreatePage().getRepLinesValue().shouldBe(exactText("0"));

        scenarioCreatePage().getTotalDurationTitle().shouldBe(visible);
        scenarioCreatePage().getTotalDurationTitle().shouldBe(exactText("Total Duration"));
        scenarioCreatePage().getTotalDurationValue().shouldBe(visible);
        scenarioCreatePage().getTotalDurationValue().shouldBe(exactText("0m 0s"));

        scenarioCreatePage().getRepTitle().shouldBe(visible);
        scenarioCreatePage().getRepTitle().shouldBe(exactText("Rep"));
        scenarioCreatePage().getRepImage().shouldBe(visible);
        scenarioCreatePage().getCustomerTitle().shouldBe(visible);
        scenarioCreatePage().getCustomerTitle().shouldBe(exactText("Customer"));
        scenarioCreatePage().getCustomerImage().shouldBe(visible);

        scenarioEditPage().getGenerateForAll().shouldBe(visible);
        scenarioEditPage().getGenerateForAll().shouldBe(exactText("Generate for All"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("width", "136px"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getGenerateForAll().shouldBe(disabled);

        scenarioEditPage().getPlayAll().shouldBe(visible);
        scenarioEditPage().getPlayAll().shouldBe(exactText("Play All"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getPlayAll().shouldBe(disabled);

        scenarioEditPage().getScenarioPreview().shouldBe(visible);
        scenarioEditPage().getScenarioPreview().shouldBe(exactText("Preview"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("width", "96px"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getScenarioPreview().shouldBe(disabled);

        scenarioCreatePage().getAddCustomerLine().shouldBe(visible);
        scenarioCreatePage().getAddCustomerLine().shouldBe(exactText("+ Add a customer line"));

        scenarioCreatePage().getAddARepLine().shouldBe(visible);
        scenarioCreatePage().getAddARepLine().shouldBe(exactText("Add a rep line +"));

        scenarioCreatePage().getPublishButton().shouldBe(visible);
        scenarioCreatePage().getPublishButton().shouldBe(exactText("Publish"));
        scenarioCreatePage().getPublishButton().shouldBe(attribute("width", "128px"));
        scenarioCreatePage().getPublishButton().shouldBe(attribute("color", "default"));
        scenarioCreatePage().getPublishButton().shouldBe(attribute("type", "submit"));

        scenarioCreatePage().getSaveAsDraftButton().shouldBe(visible);
        scenarioCreatePage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
        scenarioCreatePage().getSaveAsDraftButton().shouldBe(attribute("width", "144px"));
        scenarioCreatePage().getSaveAsDraftButton().shouldBe(attribute("color", "default"));
        scenarioCreatePage().getSaveAsDraftButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert elements on View Scenario page. */
    public static void assertElementsViewScenario() {
        scenarioEditPage().getEditScenarioTitle().shouldBe(visible);
        scenarioEditPage().getEditScenarioTitle().shouldBe(exactText("Edit Scenario"));

        scenarioEditPage().getPublishedText().shouldBe(visible);
        scenarioEditPage().getPublishedText().shouldBe(matchText("Published"));

        scenarioEditPage().getArchiveButton().shouldBe(visible);
        scenarioEditPage().getArchiveButton().shouldBe(exactText("Archive"));
        scenarioEditPage().getArchiveButton().shouldBe(attribute("width", "144px"));
        scenarioEditPage().getArchiveButton().shouldBe(attribute("color", "default"));
        scenarioEditPage().getArchiveButton().shouldBe(attribute("type", "submit"));

        scenarioEditPage().getEditButton().shouldBe(visible);
        scenarioEditPage().getEditButton().shouldBe(exactText("Edit"));
        scenarioEditPage().getEditButton().shouldBe(attribute("width", "128px"));
        scenarioEditPage().getEditButton().shouldBe(attribute("color", "default"));
        scenarioEditPage().getEditButton().shouldBe(attribute("type", "submit"));

        scenarioEditPage().getTitleField().shouldBe(visible);
        scenarioEditPage().getCreatedText().shouldBe(visible);
        scenarioEditPage().getCreatedText().shouldBe(matchText("Created by"));

        scenarioEditPage().getDescriptionField().shouldBe(visible);
        scenarioEditPage().getDescriptionField().shouldBe(attribute("maxlength", "500"));
        scenarioEditPage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        scenarioEditPage().getSymbolCount().shouldBe(visible);
        scenarioEditPage().getSymbolCount().shouldBe(matchText("500"));

        scenarioEditPage().getLabels().shouldBe(visible);
        scenarioEditPage().getLabelsButton().shouldBe(visible);
        scenarioEditPage().getLabels().shouldBe(exactText("Labels"));
        scenarioEditPage().getLabels().shouldBe(attribute("color", "#b1c0cb"));

        scenarioEditPage().getGetPdf().shouldBe(visible);
        scenarioEditPage().getGetPdf().shouldBe(enabled);

        scenarioEditPage().getRepTitle().shouldBe(visible);
        scenarioEditPage().getRepTitle().shouldBe(exactText("Rep"));
        scenarioEditPage().getRepImage().shouldBe(visible);
        scenarioEditPage().getCustomerTitle().shouldBe(visible);
        scenarioEditPage().getCustomerTitle().shouldBe(exactText("Customer"));
        scenarioEditPage().getCustomerImage().shouldBe(visible);

        scenarioEditPage().getGenerateForAll().shouldBe(visible);
        scenarioEditPage().getGenerateForAll().shouldBe(exactText("Generate for All"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("width", "136px"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getGenerateForAll().shouldBe(disabled);

        scenarioEditPage().getPlayAll().shouldBe(visible);
        scenarioEditPage().getPlayAll().shouldBe(exactText("Play All"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getPlayAll().shouldBe(enabled);

        scenarioEditPage().getScenarioPreview().shouldBe(visible);
        scenarioEditPage().getScenarioPreview().shouldBe(exactText("Preview"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("width", "96px"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getScenarioPreview().shouldBe(enabled);

        scenarioEditPage().getCustomerLinesTitle().shouldBe(visible);
        scenarioEditPage().getCustomerLinesTitle().shouldBe(exactText("Customer Lines"));
        scenarioEditPage().getCustomerLinesValue().shouldBe(visible);

        scenarioEditPage().getRepLinesTitle().shouldBe(visible);
        scenarioEditPage().getRepLinesTitle().shouldBe(exactText("Rep Lines"));
        scenarioEditPage().getRepLinesValue().shouldBe(visible);

        scenarioEditPage().getTotalDurationTitle().shouldBe(visible);
        scenarioEditPage().getTotalDurationTitle().shouldBe(exactText("Total Duration"));
        scenarioEditPage().getTotalDurationValue().shouldBe(visible);

        scenarioEditPage().getScenarioPlayCustomer().shouldBe(visible);
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(exactText("Play"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("title", "Play"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("width", "40px"));

        scenarioEditPage().getScenarioPlayRep().shouldBe(visible);
        scenarioEditPage().getScenarioPlayRep().shouldBe(exactText("Play"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("title", "Play"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("width", "40px"));

        scenarioEditPage().getScenarioPlayRep().shouldBe(enabled);
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(enabled);
        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(hidden);
        scenarioCreatePage().getGenerateForRepButton().shouldBe(hidden);
        scenarioCreatePage().getAddARepLine().shouldBe(hidden);
        scenarioCreatePage().getAddCustomerLine().shouldBe(hidden);
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(hidden);
        scenarioEditPage().getRecordAudioRepButton().shouldBe(hidden);
    }

    /** Assert elements on Edit Scenario page. */
    public static void assertElementsEditScenario() {
        scenarioEditPage().getEditScenarioTitle().shouldBe(visible);
        scenarioEditPage().getEditScenarioTitle().shouldBe(exactText("Edit Scenario"));

        scenarioEditPage().getSaveChangesButton().shouldBe(visible);
        scenarioEditPage().getSaveChangesButton().shouldBe(exactText("Save Changes"));
        scenarioEditPage().getSaveChangesButton().shouldBe(attribute("width", "128px"));
        scenarioEditPage().getSaveChangesButton().shouldBe(attribute("color", "default"));
        scenarioEditPage().getSaveChangesButton().shouldBe(attribute("type", "submit"));

        scenarioEditPage().getCancelEditButton().shouldBe(visible);
        scenarioEditPage().getCancelEditButton().shouldBe(exactText("Cancel Edit"));
        scenarioEditPage().getCancelEditButton().shouldBe(attribute("width", "144px"));
        scenarioEditPage().getCancelEditButton().shouldBe(attribute("color", "default"));
        scenarioEditPage().getCancelEditButton().shouldBe(attribute("type", "submit"));

        scenarioEditPage().getTitleField().shouldBe(visible);
        scenarioEditPage().getCreatedText().shouldBe(visible);
        scenarioEditPage().getCreatedText().shouldBe(matchText("Created by"));

        scenarioEditPage().getDescriptionField().shouldBe(visible);
        scenarioEditPage().getDescriptionField().shouldBe(attribute("maxlength", "500"));
        scenarioEditPage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        scenarioEditPage().getSymbolCount().shouldBe(visible);

        scenarioEditPage().getLabels().shouldBe(visible);
        scenarioEditPage().getLabelsButton().shouldBe(visible);
        scenarioEditPage().getLabels().shouldBe(exactText("Labels"));
        scenarioEditPage().getLabels().shouldBe(attribute("color", "#6d7f8c"));

        scenarioEditPage().getGetPdf().shouldBe(visible);
        scenarioEditPage().getGetPdf().shouldBe(enabled);

        scenarioEditPage().getRepTitle().shouldBe(visible);
        scenarioEditPage().getRepTitle().shouldBe(exactText("Rep"));
        scenarioEditPage().getRepImage().shouldBe(visible);
        scenarioEditPage().getCustomerTitle().shouldBe(visible);
        scenarioEditPage().getCustomerTitle().shouldBe(exactText("Customer"));
        scenarioEditPage().getCustomerImage().shouldBe(visible);

        scenarioEditPage().getGenerateForAll().shouldBe(visible);
        scenarioEditPage().getGenerateForAll().shouldBe(exactText("Generate for All"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("width", "136px"));
        scenarioEditPage().getGenerateForAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getGenerateForAll().shouldBe(enabled);

        scenarioEditPage().getPlayAll().shouldBe(visible);
        scenarioEditPage().getPlayAll().shouldBe(exactText("Play All"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("color", "default"));
        scenarioEditPage().getPlayAll().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getPlayAll().shouldBe(enabled);

        scenarioEditPage().getScenarioPreview().shouldBe(visible);
        scenarioEditPage().getScenarioPreview().shouldBe(exactText("Preview"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("width", "96px"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPreview().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getScenarioPreview().shouldBe(enabled);

        scenarioEditPage().getCustomerLinesTitle().shouldBe(visible);
        scenarioEditPage().getCustomerLinesTitle().shouldBe(exactText("Customer Lines"));
        scenarioEditPage().getCustomerLinesValue().shouldBe(visible);

        scenarioEditPage().getRepLinesTitle().shouldBe(visible);
        scenarioEditPage().getRepLinesTitle().shouldBe(exactText("Rep Lines"));
        scenarioEditPage().getRepLinesValue().shouldBe(visible);

        scenarioEditPage().getTotalDurationTitle().shouldBe(visible);
        scenarioEditPage().getTotalDurationTitle().shouldBe(exactText("Total Duration"));
        scenarioEditPage().getTotalDurationValue().shouldBe(visible);

        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(visible);
        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(enabled);
        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(exactText("Generate Audio"));
        scenarioCreatePage()
                .getGenerateForCustomerButton()
                .shouldBe(attribute("title", "Generate Audio"));
        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(attribute("color", "default"));
        scenarioCreatePage().getGenerateForCustomerButton().shouldBe(attribute("type", "submit"));

        scenarioCreatePage().getGenerateForRepButton().shouldBe(visible);
        scenarioCreatePage().getGenerateForRepButton().shouldBe(enabled);
        scenarioCreatePage().getGenerateForRepButton().shouldBe(exactText("Generate Audio"));
        scenarioCreatePage()
                .getGenerateForRepButton()
                .shouldBe(attribute("title", "Generate Audio"));
        scenarioCreatePage().getGenerateForRepButton().shouldBe(attribute("color", "default"));
        scenarioCreatePage().getGenerateForRepButton().shouldBe(attribute("type", "submit"));

        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(visible);
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(enabled);
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(exactText("Record Audio"));
        scenarioEditPage()
                .getRecordAudioCustomerButton()
                .shouldBe(attribute("title", "Record Audio"));
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(attribute("color", "warning"));
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getRecordAudioCustomerButton().shouldBe(attribute("width", "90px"));

        scenarioEditPage().getRecordAudioRepButton().shouldBe(visible);
        scenarioEditPage().getRecordAudioRepButton().shouldBe(enabled);
        scenarioEditPage().getRecordAudioRepButton().shouldBe(exactText("Record Audio"));
        scenarioEditPage().getRecordAudioRepButton().shouldBe(attribute("title", "Record Audio"));
        scenarioEditPage().getRecordAudioRepButton().shouldBe(attribute("color", "warning"));
        scenarioEditPage().getRecordAudioRepButton().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getRecordAudioRepButton().shouldBe(attribute("width", "90px"));

        scenarioEditPage().getScenarioPlayCustomer().shouldBe(visible);
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(enabled);
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(exactText("Play"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("title", "Play"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getScenarioPlayCustomer().shouldBe(attribute("width", "40px"));

        scenarioEditPage().getScenarioPlayRep().shouldBe(visible);
        scenarioEditPage().getScenarioPlayRep().shouldBe(enabled);
        scenarioEditPage().getScenarioPlayRep().shouldBe(exactText("Play"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("title", "Play"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("color", "default"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("type", "submit"));
        scenarioEditPage().getScenarioPlayRep().shouldBe(attribute("width", "40px"));

        scenarioCreatePage().getAddCustomerLine().shouldBe(visible);
        scenarioCreatePage().getAddCustomerLine().shouldBe(exactText("+ Add a customer line"));

        scenarioCreatePage().getAddARepLine().shouldBe(visible);
        scenarioCreatePage().getAddARepLine().shouldBe(exactText("Add a rep line +"));

        scenarioCreatePage().getDeleteCustomerLine().shouldBe(visible);
        scenarioCreatePage().getDeleteRepLine().shouldBe(visible);
    }
}
