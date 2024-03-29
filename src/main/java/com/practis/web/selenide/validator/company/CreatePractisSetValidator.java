package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.pacingDropdown;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createPractisSetService;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabelOnPractisSet;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;

import com.codeborne.selenide.Condition;
import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.practisset.PractisSetEditPage;
import java.util.List;

public class CreatePractisSetValidator {

    /** Assert grid row with input data. */
    public static void assertPractisSetGridRow(
            final NewPractisSetInput inputData, final GridRow gridRow) {
        gridRow.get("Practis Sets").shouldBe(matchText(".*" + inputData.getName()));
    }

    /** Assert input on edit page. */
    public static void assertPractisSetInput(
            final NewPractisSetInput inputData, final PractisSetEditPage practisSetEditPage) {
        practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getName()));
    }

    /** Assert Title on edit page. */
    public static void assertPracrisSetTitle(
            final NewPractisSetInput inputData, final PractisSetEditPage practisSetEditPage) {
        practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getName()));
    }

    /** Assert elements on New Practis Set page. */
    public static void assertElementsNewPractisSet() {

        practisSetCreatePage().getAddNewPractisSetTitle().shouldBe(visible);
        practisSetCreatePage().getSaveAsDraftButton().shouldBe(visible);
        practisSetCreatePage().getSaveAsDraftButton().shouldBe(exactText("Save as Draft"));
        practisSetCreatePage().getSaveAsDraftButton().shouldBe(attribute("type", "submit"));
        practisSetCreatePage().getSaveAsDraftButton().shouldBe(attribute("width", "144px"));
        practisSetCreatePage().getSaveAsDraftButton().shouldBe(attribute("color", "default"));

        practisSetCreatePage().getSaveChangesButton().shouldBe(visible);
        practisSetCreatePage().getSaveChangesButton().shouldBe(exactText("Publish"));
        practisSetCreatePage().getSaveChangesButton().shouldBe(attribute("width", "128px"));
        practisSetCreatePage().getSaveChangesButton().shouldBe(attribute("color", "default"));

        practisSetCreatePage().getNotPublishedYetText().shouldBe(visible);
        practisSetCreatePage().getNotPublishedYetText().shouldBe(exactText("Not Published Yet"));

        practisSetCreatePage().getTitleField().shouldBe(visible);
        practisSetCreatePage()
                .getTitleField()
                .shouldBe(attribute("placeholder", "Practis Set Title"));
        practisSetCreatePage().getTitleField().shouldBe(attribute("maxlength", "90"));

        practisSetCreatePage().getAddLabelsButton().shouldBe(visible);
        practisSetCreatePage().getLabelsButtonName().shouldBe(exactText("Labels"));

        practisSetCreatePage().getPacingDropdown().shouldBe(visible);
        practisSetCreatePage().getPacingDropdown().shouldBe(exactText("Free-Form"));

        practisSetCreatePage().getDescriptionField().shouldBe(visible);
        practisSetCreatePage()
                .getDescriptionField()
                .shouldBe(attribute("placeholder", "Description"));
        practisSetCreatePage().getDescriptionField().shouldBe(attribute("maxlength", "500"));
        practisSetCreatePage().getDescriptionField().shouldBe(attribute("margin", "normal"));

        practisSetCreatePage().getTotalDurationText().shouldBe(visible);
        practisSetCreatePage().getTotalDurationText().shouldBe(exactText("Total Duration"));
        practisSetCreatePage().getTotalDuration().shouldBe(visible);
        practisSetCreatePage().getTotalDuration().shouldBe(exactText("0m 0s"));

        practisSetCreatePage().getTotalRepsReqDText().shouldBe(visible);
        practisSetCreatePage().getTotalRepsReqDText().shouldBe(exactText("Total Reps Req'd"));
        practisSetCreatePage().getTotalReps().shouldBe(visible);
        practisSetCreatePage().getTotalReps().shouldBe(exactText("0"));

        practisSetCreatePage().getMinAccuracyText().shouldBe(visible);
        practisSetCreatePage().getMinAccuracyText().shouldBe(exactText("Minimum Accuracy"));
        practisSetCreatePage().getMinAccuracy().shouldBe(visible);
        practisSetCreatePage().getMinAccuracy().shouldBe(exactText("65%"));

        practisSetCreatePage().getScenarioTab().shouldBe(visible);
        practisSetCreatePage().getScenarioTab().shouldBe(matchText("Scenarios"));
        practisSetCreatePage().getSearchScenarioField().shouldBe(visible);
        practisSetCreatePage().getSearchScenarioField().shouldBe(attribute("font-size", "13px"));
        practisSetCreatePage().getFilterScenarioButton().shouldBe(visible);
        practisSetCreatePage().getFirstColumnScenario().shouldBe(visible);
        practisSetCreatePage().getFirstColumnScenario().shouldBe(exactText("Scenarios"));
        practisSetCreatePage().getFirstColumnScenario().shouldBe(attribute("width", "60"));
        practisSetCreatePage().getSecondColumnScenario().shouldBe(visible);
        practisSetCreatePage().getSecondColumnScenario().shouldBe(exactText("Duration"));
        practisSetCreatePage().getSecondColumnScenario().shouldBe(attribute("width", "40"));

        practisSetCreatePage().getChallengeTab().shouldBe(visible);
        practisSetCreatePage().getChallengeTab().shouldBe(matchText("Challenges"));
        practisSetCreatePage().getChallengeTab().click();
        practisSetCreatePage().getSearchChallengeField().shouldBe(visible);
        practisSetCreatePage().getFilterChallengeButton().shouldBe(visible);
        practisSetCreatePage().getFirstColumnChallenge().shouldBe(visible);
        practisSetCreatePage().getFirstColumnChallenge().shouldBe(exactText("Challenges"));
        practisSetCreatePage().getFirstColumnChallenge().shouldBe(attribute("width", "60"));
        practisSetCreatePage().getSecondColumnChallenge().shouldBe(visible);
        practisSetCreatePage().getSecondColumnChallenge().shouldBe(exactText("Duration"));
        practisSetCreatePage().getSecondColumnChallenge().shouldBe(attribute("width", "40"));

        practisSetCreatePage().getNoContentImage().shouldBe(visible);
        practisSetCreatePage().getNoContentText().shouldBe(visible);
        practisSetCreatePage().getNoContentText().shouldBe(exactText("No Content"));
        practisSetCreatePage().getNoContentText().shouldBe(visible);
        practisSetCreatePage()
                .getNoContentDescriptionText()
                .shouldBe(
                        exactText(
                                "Add Scenarios & Challenges from the left in order to build this"
                                        + " practis set."));
    }

    /** Assert elements on View Practis Set page. */
    public static void assertElementsViewPractisSet() {
        practisSetEditPage().getEditPractisSetTitle().shouldBe(visible);
        practisSetEditPage().getEditPractisSetTitle().shouldBe(exactText("View Practis Set"));

        practisSetEditPage().getAssignUsersButton().shouldBe(visible);
        practisSetEditPage().getAssignUsersButton().shouldBe(exactText("Assign Users"));
        practisSetEditPage().getAssignUsersButton().shouldBe(attribute("type", "submit"));
        practisSetEditPage().getAssignUsersButton().shouldBe(attribute("width", "144px"));
        practisSetEditPage().getAssignUsersButton().shouldBe(attribute("color", "default"));

        practisSetEditPage().getEditButton().shouldBe(visible);
        practisSetEditPage().getEditButton().shouldBe(exactText("Edit"));
        practisSetEditPage().getEditButton().shouldBe(attribute("type", "submit"));
        practisSetEditPage().getEditButton().shouldBe(attribute("width", "128px"));
        practisSetEditPage().getEditButton().shouldBe(attribute("color", "default"));

        practisSetEditPage().getArchiveButton().shouldBe(visible);
        practisSetEditPage().getArchiveButton().shouldBe(exactText("Archive"));
        practisSetEditPage().getPublishedText().shouldBe(visible);
        practisSetEditPage().getPublishedText().shouldBe(matchText("Published"));

        practisSetEditPage().getTitleField().shouldBe(visible);
        practisSetEditPage().getTitleField().shouldBe(attribute("maxlength", "90"));

        practisSetEditPage().getCreatedByText().shouldBe(visible);
        practisSetEditPage().getCreatedByText().shouldBe(matchText("Created by"));

        practisSetEditPage().getAddLabelsButton().shouldBe(visible);
        practisSetEditPage().getLabelsButtonName().shouldBe(visible);
        practisSetEditPage().getLabelsButtonName().shouldBe(exactText("Labels"));
        practisSetEditPage().getLabelsButtonName().shouldBe(attribute("color", "#b1c0cb"));

        practisSetEditPage().getPacingDropdown().shouldBe(visible);
        practisSetEditPage().getDescriptionField().shouldBe(visible);

        practisSetEditPage().getTotalDurationText().shouldBe(visible);
        practisSetEditPage().getTotalDurationText().shouldBe(exactText("Total Duration"));
        practisSetEditPage().getTotalDuration().shouldBe(visible);

        practisSetEditPage().getTotalRepsReqDText().shouldBe(visible);
        practisSetEditPage().getTotalRepsReqDText().shouldBe(exactText("Total Reps Req'd"));
        practisSetEditPage().getTotalReps().shouldBe(visible);

        practisSetEditPage().getMinAccuracyText().shouldBe(visible);
        practisSetEditPage().getMinAccuracyText().shouldBe(exactText("Minimum Accuracy"));
        practisSetEditPage().getMinAccuracy().shouldBe(visible);
        practisSetEditPage().getMinAccuracy().shouldBe(exactText("65%"));

        // Scenario tab
        practisSetEditPage().getContentScenarioRow().shouldBe(size(1));
        practisSetEditPage().getContentScenarioRow().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioTab().shouldBe(visible);
        practisSetEditPage().getSearchScenarioField().shouldBe(visible);
        practisSetEditPage().getFilterScenarioButton().shouldBe(visible);
        practisSetEditPage().getFirstColumnScenario().shouldBe(visible);
        practisSetEditPage().getFirstColumnScenario().shouldBe(exactText("Scenarios"));
        practisSetEditPage().getFirstColumnScenario().shouldBe(attribute("width", "60"));
        practisSetEditPage().getSecondColumnScenario().shouldBe(visible);
        practisSetEditPage().getSecondColumnScenario().shouldBe(exactText("Duration"));
        practisSetEditPage().getSecondColumnScenario().shouldBe(attribute("width", "40"));

        // Challenge tab
        practisSetEditPage().getChallengeTab().shouldBe(visible);
        practisSetEditPage().getChallengeTab().click();
        practisSetEditPage().getContentChallengeRow().shouldBe(size(1));
        practisSetEditPage().getContentChallengeRow().get(0).shouldBe(visible);
        practisSetEditPage().getSearchChallengeField().shouldBe(visible);
        practisSetEditPage().getFilterChallengeButton().shouldBe(visible);
        practisSetEditPage().getFirstColumnChallenge().shouldBe(visible);
        practisSetEditPage().getFirstColumnChallenge().shouldBe(exactText("Challenges"));
        practisSetEditPage().getFirstColumnChallenge().shouldBe(attribute("width", "60"));
        practisSetEditPage().getSecondColumnChallenge().shouldBe(visible);
        practisSetEditPage().getSecondColumnChallenge().shouldBe(exactText("Duration"));
        practisSetEditPage().getSecondColumnChallenge().shouldBe(attribute("width", "40"));

        // Check added Scenario
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(exactText("Scenario"));
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(attribute("Color", "#4aa9e2"));
        practisSetEditPage().getScenarioName().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeName().get(0).shouldBe(visible);
        practisSetEditPage().getDurationText().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getMinimumRepsText().get(0).shouldBe(visible);
        practisSetEditPage().getMinimumRepsText().get(0).shouldBe(exactText("Minimum Reps"));
        practisSetEditPage().getMinimumRepsValue().get(0).shouldBe(visible);
        practisSetEditPage().getMinimumRepsValue().get(0).shouldBe(exactText("1"));
        practisSetEditPage().getPlusRep().get(0).shouldBe(visible);
        practisSetEditPage().getMinusRep().get(0).shouldBe(visible);
        practisSetEditPage().getDeleteContentButton().get(0).shouldBe(visible);

        // Check added Challenge
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(exactText("Challenge"));
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(attribute("Color", "#fa6653"));
        practisSetEditPage().getScenarioName().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeName().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getDeleteContentButton().get(0).shouldBe(visible);
    }

    /** Assert elements on Edit Practis Set page. */
    public static void assertElementsEditPractisSet() {

        practisSetEditPage().getEditPractisSetTitle().shouldBe(visible);
        practisSetEditPage().getEditPractisSetTitle().shouldBe(exactText("Edit Practis Set"));

        practisSetEditPage().getSaveButton().shouldBe(visible);
        practisSetEditPage().getSaveButton().shouldBe(exactText("Save"));
        practisSetEditPage().getSaveButton().shouldBe(attribute("type", "submit"));
        practisSetEditPage().getSaveButton().shouldBe(attribute("width", "128px"));
        practisSetEditPage().getSaveButton().shouldBe(attribute("color", "default"));

        practisSetEditPage().getCancelButton().shouldBe(visible);
        practisSetEditPage().getCancelButton().shouldBe(exactText("Cancel"));
        practisSetEditPage().getCancelButton().shouldBe(attribute("type", "submit"));
        practisSetEditPage().getCancelButton().shouldBe(attribute("width", "128px"));
        practisSetEditPage().getCancelButton().shouldBe(attribute("color", "default"));

        practisSetEditPage().getArchiveButton().shouldBe(visible);
        practisSetEditPage().getArchiveButton().shouldBe(exactText("Archive"));
        practisSetEditPage().getPublishedText().shouldBe(hidden);

        practisSetEditPage().getTitleField().shouldBe(visible);
        practisSetEditPage().getTitleField().shouldBe(attribute("maxlength", "90"));

        practisSetEditPage().getCreatedByText().shouldBe(visible);
        practisSetEditPage().getCreatedByText().shouldBe(matchText("Created by"));

        practisSetEditPage().getAddLabelsButton().shouldBe(visible);
        practisSetEditPage().getLabelsButtonName().shouldBe(visible);
        practisSetEditPage().getLabelsButtonName().shouldBe(exactText("Labels"));

        practisSetEditPage().getPacingDropdown().shouldBe(visible);
        practisSetEditPage().getDescriptionField().shouldBe(visible);
        practisSetCreatePage().getDescriptionField().shouldBe(attribute("maxlength", "500"));
        practisSetCreatePage().getDescriptionField().shouldBe(attribute("margin", "normal"));

        practisSetEditPage().getTotalDurationText().shouldBe(visible);
        practisSetEditPage().getTotalDurationText().shouldBe(exactText("Total Duration"));
        practisSetEditPage().getTotalDuration().shouldBe(visible);

        practisSetEditPage().getTotalRepsReqDText().shouldBe(visible);
        practisSetEditPage().getTotalRepsReqDText().shouldBe(exactText("Total Reps Req'd"));
        practisSetEditPage().getTotalReps().shouldBe(visible);

        practisSetEditPage().getMinAccuracyText().shouldBe(visible);
        practisSetEditPage().getMinAccuracyText().shouldBe(exactText("Minimum Accuracy"));
        practisSetEditPage().getMinAccuracy().shouldBe(visible);
        practisSetEditPage().getMinAccuracy().shouldBe(exactText("65%"));

        // Scenario tab
        practisSetEditPage().getScenarioTab().shouldBe(visible);
        practisSetEditPage().getSearchScenarioField().shouldBe(visible);
        practisSetEditPage().getFilterScenarioButton().shouldBe(visible);
        practisSetEditPage().getFirstColumnScenario().shouldBe(visible);
        practisSetEditPage().getFirstColumnScenario().shouldBe(exactText("Scenarios"));
        practisSetEditPage().getFirstColumnScenario().shouldBe(attribute("width", "60"));
        practisSetEditPage().getSecondColumnScenario().shouldBe(visible);
        practisSetEditPage().getSecondColumnScenario().shouldBe(exactText("Duration"));
        practisSetEditPage().getSecondColumnScenario().shouldBe(attribute("width", "40"));

        // Challenge tab
        practisSetEditPage().getChallengeTab().shouldBe(visible);
        practisSetEditPage().getChallengeTab().click();
        practisSetEditPage().getSearchChallengeField().shouldBe(visible);
        practisSetEditPage().getFilterChallengeButton().shouldBe(visible);
        practisSetEditPage().getFirstColumnChallenge().shouldBe(visible);
        practisSetEditPage().getFirstColumnChallenge().shouldBe(exactText("Challenges"));
        practisSetEditPage().getFirstColumnChallenge().shouldBe(attribute("width", "60"));
        practisSetEditPage().getSecondColumnChallenge().shouldBe(visible);
        practisSetEditPage().getSecondColumnChallenge().shouldBe(exactText("Duration"));
        practisSetEditPage().getSecondColumnChallenge().shouldBe(attribute("width", "40"));

        // Check added Scenario
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(exactText("Scenario"));
        practisSetEditPage().getScenarioTitle().get(0).shouldBe(attribute("Color", "#4aa9e2"));
        practisSetEditPage().getScenarioName().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeName().get(0).shouldBe(visible);
        practisSetEditPage().getDurationText().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getMinimumRepsText().get(0).shouldBe(visible);
        practisSetEditPage().getMinimumRepsText().get(0).shouldBe(exactText("Minimum Reps"));
        practisSetEditPage().getMinimumRepsValue().get(0).shouldBe(visible);
        practisSetEditPage().getMinimumRepsValue().get(0).shouldBe(exactText("1"));
        practisSetEditPage().getPlusRep().get(0).shouldBe(visible);
        practisSetEditPage().getMinusRep().get(0).shouldBe(visible);
        practisSetEditPage().getDeleteContentButton().get(0).shouldBe(visible);

        // Check added Challenge
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(exactText("Challenge"));
        practisSetEditPage().getChallengeTitle().get(0).shouldBe(attribute("Color", "#fa6653"));
        practisSetEditPage().getScenarioName().get(0).shouldBe(visible);
        practisSetEditPage().getChallengeName().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getScenarioPreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(visible);
        practisSetEditPage().getChallengePreviewButton().get(0).shouldBe(exactText("Preview"));
        practisSetEditPage().getDeleteContentButton().get(0).shouldBe(visible);
    }

    /** Assert elements on New Practis - Pacing Dropdown. */
    public static void assertElementsPacingDropdown() {
        pacingDropdown().getPacingButton().click();
        pacingDropdown().getPacingItem().get(0).shouldBe(visible);
        pacingDropdown().getPacingItem().get(0).shouldBe(exactText("Sequential"));
        pacingDropdown().getPacingItem().get(1).shouldBe(visible);
        pacingDropdown().getPacingItem().get(1).shouldBe(exactText("Free-Form"));
        pacingDropdown().getPacingButton().click();
    }

    /** Assert Total Duration, Total Reps and Min Accuracy. */
    public static void assertNumbers(
            final String totalDuration, final String totalReps, final String minAccuracy) {
        practisSetCreatePage().getTotalDuration().shouldBe(Condition.exactText(totalDuration));
        practisSetCreatePage().getTotalReps().shouldBe(Condition.exactText(totalReps));
        practisSetCreatePage().getMinAccuracy().shouldBe(Condition.exactText(minAccuracy));
    }

    /** Assert elements on New Practis - Labels Active State. */
    public static void assertElementsLabelsDropdown() {
        practisSetCreatePage().getAddLabelsButton().click();
        practisSetCreatePage().getSaveChangesLabelButton().shouldBe(visible);
        practisSetCreatePage().getSaveChangesLabelButton().shouldBe(exactText("Save Changes"));
        practisSetCreatePage().getLabelItemCheckbox().get(0).click();
        practisSetCreatePage().getSaveChangesLabelButton().click();
    }

    /** Assert created PS. */
    public static void assertCreatedPractisSet(NewPractisSetInput inputData) {
        final var practisSetGridRow = createPractisSetService().searchPS(inputData.getName());
        assertPractisSetGridRow(inputData, practisSetGridRow);

        // assert edit page data
        awaitElementNotExists(10, () -> snackbar().getMessage());
        practisSetGridRow.click();
        assertPractisSetInput(inputData, practisSetEditPage());
    }

    /** Assert data on edit page with input. */
    public static void assertEditedPractisSetData(
            List<RestCreateLabelResponse> label,
            List<RestScenarioResponse> scenarios,
            List<RestChallengeResponse> challenges) {
        practisSetEditPage().getTitleField().getValue().contains("_edit");
        practisSetEditPage().getDescriptionField().shouldBe(matchText("_edit"));
        practisSetEditPage().getAddLabelsButton().click();
        assertSelectedLabelOnPractisSet(label.get(0).getName());

        assertScenarioOnPractisSetPage(scenarios.get(0).getTitle());
        assertScenarioOnPractisSetPage(scenarios.get(1).getTitle());

        assertChallengeOnPractisSetPage(challenges.get(0).getTitle());
        assertChallengeOnPractisSetPage(challenges.get(1).getTitle());
    }

    /** Assert scenario on Edit Practis Set Page. */
    public static void assertScenarioOnPractisSetPage(String scenario) {
        final var scenarioRow =
                practisSetEditPage().getScenarioName().find(Condition.matchText(scenario));
        scenarioRow.shouldBe(visible);
    }

    /** Assert challenge on Edit Practis Set Page. */
    public static void assertChallengeOnPractisSetPage(String challenge) {
        final var challengeRow =
                practisSetEditPage().getChallengeName().find(Condition.matchText(challenge));
        challengeRow.shouldBe(visible);
    }
}
