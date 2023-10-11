package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabelOnChallenge;
import static com.practis.web.util.AwaitUtils.awaitSoft;

import com.practis.dto.NewChallengeInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.challenge.ChallengeEditPage;
import com.practis.web.selenide.page.company.scenario.ScenarioEditPage;
import java.util.List;

public class ChallengeValidator {

    /** Assert grid row with input data. */
    public static void assertChallengeGridRow(
            final NewChallengeInput inputData, final GridRow gridRow) {
        gridRow.get("Challenges").shouldBe(matchText(".*" + inputData.getTitle()));
    }

    /** Assert data on edit page with input. */
    public static void assertChallengeData(
            final NewChallengeInput inputData, final ChallengeEditPage challengeEditPage) {
        challengeEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
        challengeEditPage.getDescriptionField().shouldBe(text(inputData.getDescription()));
    }

    /** Assert Title on edit page with input. */
    public static void assertChallengeTitle(
            final NewChallengeInput inputData, final ChallengeEditPage challengeEditPage) {
        challengeEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    }

    /** Assert elements on New Challenge page. */
    public static void assertElementsOnNewChallengePage() {
        challengeCreatePage().getAddNewChallengeTitle().shouldBe(exactText("Add New Challenge"));
        challengeCreatePage().getAddNewChallengeTitle().shouldBe(visible);
        challengeCreatePage().getNotPublishTitle().shouldBe(exactText("Not Published Yet"));
        challengeCreatePage().getNotPublishTitle().shouldBe(visible);

        challengeCreatePage().getSaveAsDraftButton().shouldBe(visible);
        challengeCreatePage().getSaveAsDraftButton().shouldBe(attribute("color", "default"));
        challengeCreatePage().getSaveAsDraftButton().shouldBe(attribute("width", "144px"));
        challengeCreatePage().getPublishButton().shouldBe(visible);
        challengeCreatePage().getSaveAsDraftButton().shouldBe(attribute("color", "default"));
        challengeCreatePage().getSaveAsDraftButton().shouldBe(attribute("width", "144px"));

        challengeCreatePage().getTitleField().shouldBe(visible);
        challengeCreatePage().getTitleField().shouldBe(attribute("placeholder", "Challenge Title"));
        challengeEditPage().getTitleField().shouldBe(attribute("maxlength", "100"));

        challengeCreatePage().getLabelsButton().shouldBe(visible);
        challengeCreatePage().getLabelsButtonName().shouldBe(visible);
        challengeCreatePage().getLabelsButtonName().shouldBe(exactText("Labels"));

        challengeCreatePage().getDescriptionField().shouldBe(visible);
        challengeCreatePage()
                .getDescriptionField()
                .shouldBe(attribute("placeholder", "Description"));
        challengeCreatePage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        challengeCreatePage().getDescriptionField().shouldBe(attribute("maxlength", "500"));

        challengeCreatePage().getGenerateForAllButton().shouldBe(visible);
        challengeCreatePage()
                .getGenerateForAllButton()
                .shouldBe(attribute("title", "Generate for All"));
        challengeCreatePage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
        challengeCreatePage().getGenerateForAllButton().shouldBe(attribute("width", "127px"));
        challengeCreatePage().getGenerateForAllButton().shouldBe(attribute("color", "default"));

        challengeCreatePage().getPlayForAllButton().shouldBe(visible);
        challengeCreatePage().getPlayForAllButton().shouldBe(exactText("Play"));
        challengeCreatePage().getPlayForAllButton().shouldBe(attribute("color", "default"));

        challengeCreatePage().getCustomerPic().shouldBe(visible);
        challengeCreatePage().getCustomerTitle().shouldBe(visible);
        challengeCreatePage().getCustomerTitle().shouldBe(exactText("Customer"));

        challengeCreatePage().getRepPic().shouldBe(visible);
        challengeCreatePage().getRepTitle().shouldBe(visible);
        challengeCreatePage().getRepTitle().shouldBe(exactText("Rep"));

        challengeCreatePage().getCustomerLine().get(0).shouldBe(visible);
        challengeCreatePage()
                .getCustomerLine()
                .get(0)
                .shouldBe(attribute("placeholder", "Write customer’s line here"));
        challengeCreatePage().getCustomerLine().get(0).shouldBe(attribute("font-size", "13px"));
        challengeCreatePage()
                .getCustomerLine()
                .get(0)
                .shouldBe(attribute("contenteditable", "true"));
        challengeCreatePage().getDeleteCustomerLine().get(0).shouldBe(visible);
        challengeCreatePage().getRecordAudioButton().get(0).shouldBe(visible);
        challengeCreatePage().getRecordAudioButton().get(0).shouldBe(attribute("color", "warning"));

        challengeCreatePage()
                .getUserWillRespondHereLine()
                .get(0)
                .shouldBe(exactText("User will respond here"));
        challengeCreatePage().getAddCustomerLineButton().shouldBe(visible);

        // Attempts
        challengeCreatePage().getChallengeSettingsButton().shouldBe(visible);
        challengeCreatePage().getChallengeSettingsButton().shouldBe(enabled);
        challengeCreatePage().getChallengeSettingsButton().shouldBe(attribute("title", "Settings"));
        challengeCreatePage().getChallengeSettingsButton().shouldBe(attribute("type", "submit"));
        challengeCreatePage().getChallengeSettingsButton().shouldBe(attribute("color", "default"));
        challengeCreatePage().getChallengeSettingsButton().shouldBe(attribute("width", "84px"));

        challengeCreatePage().getMaxAttemptText().shouldBe(visible);
        challengeCreatePage().getMaxAttemptText().shouldBe(exactText("Max. Attempts"));

        challengeCreatePage().getAttemptLimit().shouldBe(visible);
        challengeCreatePage().getAttemptLimit().shouldBe(exactText("3"));
    }

    /** Assert elements on View Challenge page. */
    public static void assertElementsOnViewChallengePage() {
        challengeEditPage().getHeaderText().shouldBe(exactText("View Challenge"));
        challengeEditPage().getPublishedText().shouldBe(matchText("Published"));
        challengeEditPage().getArchiveButton().shouldBe(exactText("Archive"));
        challengeEditPage().getArchiveButton().shouldBe(attribute("color", "default"));
        challengeEditPage().getArchiveButton().shouldBe(attribute("width", "144px"));
        challengeEditPage().getEditButton().shouldBe(exactText("Edit"));
        challengeEditPage().getEditButton().shouldBe(attribute("color", "default"));
        challengeEditPage().getEditButton().shouldBe(attribute("width", "128px"));

        challengeEditPage().getTitleField().shouldBe(visible);
        challengeEditPage().getTitleField().shouldBe(attribute("maxlength", "100"));
        challengeEditPage().getTitleField().shouldBe(attribute("placeholder", "Challenge Title"));
        challengeEditPage().getCreatedByText().shouldBe(matchText("Created by"));
        challengeEditPage().getLabelsButton().shouldBe(visible);
        challengeEditPage().getLabelsText().shouldBe(exactText("Labels"));
        challengeEditPage().getLabelsText().shouldBe(attribute("color", "#b1c0cb"));

        challengeEditPage().getDescriptionField().shouldBe(visible);
        challengeEditPage().getDescriptionCounterText().shouldBe(visible);
        challengeEditPage().getDescriptionCounterText().shouldBe(matchText("/500"));
        challengeEditPage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        challengeEditPage().getDescriptionField().shouldBe(attribute("maxlength", "500"));

        challengeEditPage().getGenerateForAllButton().shouldBe(visible);
        challengeEditPage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
        challengeEditPage().getGenerateForAllButton().shouldBe(attribute("width", "127px"));
        challengeEditPage().getGenerateForAllButton().shouldBe(attribute("color", "default"));

        challengeEditPage().getPlayAllButton().shouldBe(visible);
        challengeEditPage().getPlayAllButton().shouldBe(exactText("Play"));
        challengeEditPage().getPlayAllButton().shouldBe(attribute("color", "default"));

        challengeEditPage().getCustomerAvatar().shouldBe(visible);
        challengeEditPage().getCustomerText().shouldBe(exactText("Customer"));
        challengeEditPage().getRepAvatar().shouldBe(visible);
        challengeEditPage().getRepText().shouldBe(exactText("Rep"));
        challengeEditPage().getCustomerLineField().shouldBe(visible);
        challengeEditPage().getPlayCustomerLineButton().shouldBe(visible);
        challengeEditPage().getPlayCustomerLineButton().shouldBe(exactText("Play"));
        challengeEditPage().getPlayCustomerLineButton().shouldBe(attribute("color", "default"));
        challengeEditPage().getCustomerLineText().shouldBe(exactText("Customer"));
        challengeEditPage().getRepLineText().shouldBe(exactText("User will respond here"));

        // Attempts
        challengeEditPage().getChallengeSettingsButton().shouldBe(visible);
        challengeEditPage().getChallengeSettingsButton().shouldBe(disabled);
        challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("title", "Settings"));
        challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("type", "submit"));
        challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("color", "default"));
        challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("width", "84px"));

        challengeEditPage().getMaxAttemptText().shouldBe(visible);
        challengeEditPage().getMaxAttemptText().shouldBe(exactText("Max. Attempts"));

        challengeEditPage().getAttemptLimit().shouldBe(visible);
        challengeEditPage().getAttemptLimit().shouldBe(exactText("3"));
    }

    /** Assert elements on Edit Challenge page. */
    public static void assertElementsOnEditChallengePage() {
        {
            challengeEditPage().getHeaderText().shouldBe(visible);
            challengeEditPage().getHeaderText().shouldBe(exactText("Edit Challenge"));

            challengeEditPage().getCancelEditButton().shouldBe(visible);
            challengeEditPage().getCancelEditButton().shouldBe(exactText("Cancel"));
            challengeEditPage().getCancelEditButton().shouldBe(attribute("width", "144px"));
            challengeEditPage().getCancelEditButton().shouldBe(attribute("color", "default"));

            challengeEditPage().getSaveChangesButton().shouldBe(visible);
            challengeEditPage().getSaveChangesButton().shouldBe(exactText("Save"));
            challengeEditPage().getSaveChangesButton().shouldBe(attribute("width", "128px"));
            challengeEditPage().getSaveChangesButton().shouldBe(attribute("color", "default"));

            challengeEditPage().getTitleField().shouldBe(visible);
            challengeEditPage().getTitleField().shouldBe(attribute("maxlength", "100"));
            challengeEditPage().getCreatedByText().shouldBe(matchText("Created by"));
            challengeEditPage().getLabelsButton().shouldBe(visible);
            challengeEditPage().getLabelsText().shouldBe(exactText("Labels"));
            challengeEditPage().getLabelsText().shouldBe(attribute("color", "#6d7f8c"));

            challengeEditPage().getDescriptionField().shouldBe(visible);
            challengeEditPage().getDescriptionCounterText().shouldBe(visible);
            challengeEditPage().getDescriptionCounterText().shouldBe(matchText("/500"));
            challengeEditPage().getDescriptionField().shouldBe(attribute("margin", "normal"));
            challengeEditPage().getDescriptionField().shouldBe(attribute("maxlength", "500"));

            challengeEditPage().getGenerateForAllButton().shouldBe(visible);
            challengeEditPage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
            challengeEditPage().getGenerateForAllButton().shouldBe(attribute("width", "127px"));
            challengeEditPage().getGenerateForAllButton().shouldBe(attribute("color", "default"));

            challengeEditPage().getPlayAllButton().shouldBe(visible);
            challengeEditPage().getPlayAllButton().shouldBe(exactText("Play"));
            challengeEditPage().getPlayAllButton().shouldBe(attribute("color", "default"));

            challengeEditPage().getCustomerAvatar().shouldBe(visible);
            challengeEditPage().getCustomerText().shouldBe(exactText("Customer"));
            challengeEditPage().getRepAvatar().shouldBe(visible);
            challengeEditPage().getDragButton().shouldBe(visible);
            challengeEditPage().getRepText().shouldBe(exactText("Rep"));
            challengeEditPage().getCustomerLineField().shouldBe(visible);
            challengeEditPage()
                    .getGenerateCustomerLineAudioButton()
                    .shouldBe(exactText("Generate Audio"));
            challengeEditPage().getRecordCustomerLineButton().shouldBe(exactText("Record Audio"));
            challengeEditPage().getPlayCustomerLineButton().shouldBe(exactText("Play"));
            challengeEditPage().getCustomerLineText().shouldBe(exactText("Customer"));
            challengeEditPage().getRepLineText().shouldBe(exactText("User will respond here"));
            challengeEditPage().getAddCustomerLineButton().shouldBe(visible);
            challengeEditPage()
                    .getAddCustomerLineButton()
                    .shouldBe(exactText("+ Add a customer line"));

            // Attempts
            challengeEditPage().getChallengeSettingsButton().shouldBe(visible);
            challengeEditPage().getChallengeSettingsButton().shouldBe(enabled);
            challengeEditPage()
                    .getChallengeSettingsButton()
                    .shouldBe(attribute("title", "Settings"));
            challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("type", "submit"));
            challengeEditPage()
                    .getChallengeSettingsButton()
                    .shouldBe(attribute("color", "default"));
            challengeEditPage().getChallengeSettingsButton().shouldBe(attribute("width", "84px"));

            challengeEditPage().getMaxAttemptText().shouldBe(visible);
            challengeEditPage().getMaxAttemptText().shouldBe(exactText("Max. Attempts"));

            challengeEditPage().getAttemptLimit().shouldBe(visible);
            challengeEditPage().getAttemptLimit().shouldBe(exactText("3"));
        }
    }

    /** Assert elements on "Edit Challenge" page for the Draft */
    public static void assertEditDraftChallengePage() {
        challengeEditPage().getHeaderText().shouldBe(exactText("Edit Challenge"));
        challengeEditPage().getPublishButton().shouldBe(matchText("Publish"));
        challengeEditPage().getSaveAsDraftButton().shouldBe(exactText("Save As Draft"));

        challengeEditPage().getTitleField().shouldBe(visible);
        challengeEditPage().getTitleField().shouldBe(attribute("maxlength", "100"));
        challengeEditPage().getTitleField().shouldBe(attribute("placeholder", "Challenge Title"));
        challengeEditPage().getCreatedByText().shouldBe(matchText("Created by"));
        challengeEditPage().getLabelsButton().shouldBe(visible);
        challengeEditPage().getLabelsText().shouldBe(exactText("Labels"));
        challengeEditPage().getLabelsText().shouldBe(attribute("color", "#b1c0cb"));

        challengeEditPage().getDescriptionField().shouldBe(visible);
        challengeEditPage().getDescriptionCounterText().shouldBe(visible);
        challengeEditPage().getDescriptionCounterText().shouldBe(matchText("/500"));
        challengeEditPage().getDescriptionField().shouldBe(attribute("margin", "normal"));
        challengeEditPage().getDescriptionField().shouldBe(attribute("maxlength", "500"));

        challengeEditPage().getGenerateForAllButton().shouldBe(visible);
        challengeEditPage().getGenerateForAllButton().shouldBe(exactText("Generate for All"));
        challengeEditPage().getGenerateForAllButton().shouldBe(attribute("width", "136px"));
        challengeEditPage().getGenerateForAllButton().shouldBe(attribute("color", "default"));

        challengeEditPage().getPlayAllButton().shouldBe(visible);
        challengeEditPage().getPlayAllButton().shouldBe(exactText("Play"));
        challengeEditPage().getPlayAllButton().shouldBe(attribute("color", "default"));

        challengeEditPage().getCustomerAvatar().shouldBe(visible);
        challengeEditPage().getCustomerText().shouldBe(exactText("Customer"));
        challengeEditPage().getRepAvatar().shouldBe(visible);
        challengeEditPage().getRepText().shouldBe(exactText("Rep"));

        // Customer field
        challengeEditPage().getCustomerLineField().shouldBe(visible);
        challengeEditPage().getPlayCustomerLineButton().shouldBe(visible);
        challengeEditPage().getPlayCustomerLineButton().shouldBe(exactText("Play"));
        challengeEditPage().getPlayCustomerLineButton().shouldBe(attribute("color", "default"));
        challengeEditPage().getCustomerLineText().shouldBe(exactText("Customer"));

        // Rep field
        challengeEditPage().getRepSection().shouldHave(attribute("draggable", "false"));
        challengeEditPage().getRepTitle().shouldBe(visible);
        challengeEditPage().getRepTitle().shouldBe(exactText("Rep"));
        challengeEditPage().getRepline().shouldBe(visible);
        challengeEditPage().getRepline().shouldHave(attribute("disabled"));
    }

    /** Assert data on edit page with input. */
    public static void assertEditedChallengeData(
            final ScenarioEditPage scenarioEditPage, final List<RestCreateLabelResponse> labels) {
        awaitSoft(10, () -> scenarioEditPage.getTitleField().exists());
        challengeEditPage().getTitleField().getValue().contains("_edit");
        challengeEditPage().getDescriptionField().shouldBe(matchText("_edit"));
        challengeEditPage().getCustomerLineField().getText().contains("_edit");
        challengeEditPage().getLabelsButton().click();
        assertSelectedLabelOnChallenge(labels.get(0).getName());
        assertSelectedLabelOnChallenge(labels.get(1).getName());
    }
}
