package com.practis.web.selenide.validator.company;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.nudgePopup;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetReportPage;

public class PractisSetReportValidator {

    /** Assert elements on Pending Practis Set Report page */
    public static void assertPendingPractisSetReportPage() {
        practisSetReportPage().getPractisSetReportTitle().shouldBe(visible);
        practisSetReportPage().getPractisSetReportTitle().shouldBe(exactText("Practis Set Report"));
        practisSetReportPage().getPractisSetTitle().shouldBe(visible);
        practisSetReportPage().getDueDateLabel().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(exactText("Not Started"));

        practisSetReportPage().getUserPic().shouldBe(visible);
        practisSetReportPage().getUserEmail().shouldBe(visible);
        practisSetReportPage().getUserName().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(enabled);
        practisSetReportPage().getNudgeButton().shouldBe(exactText("Nudge"));

        practisSetReportPage().getAccuracyTestsTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsTitle().shouldBe(exactText("Accuracy Tests"));
        practisSetReportPage().getAccuracyTestsValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsValue().shouldBe(exactText("—"));
        practisSetReportPage().getAverageText().shouldBe(visible);
        practisSetReportPage().getAverageText().shouldBe(exactText("Average"));
        practisSetReportPage().getProgressTitle().shouldBe(visible);
        practisSetReportPage().getProgressTitle().shouldBe(exactText("Progress"));
        practisSetReportPage().getPassedScenariosCount().shouldBe(visible);
        practisSetReportPage().getPassedScenariosCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalScenariosCount().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(exactText("Scenarios"));
        practisSetReportPage().getPassedChallengesCount().shouldBe(visible);
        practisSetReportPage().getPassedChallengesCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalChallengesCount().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(exactText("Challenges"));

        practisSetReportPage().getTimeSpentTitle().shouldBe(visible);
        practisSetReportPage().getTimeSpentTitle().shouldBe(exactText("Training Time"));
        practisSetReportPage().getTimeSpentTotal().shouldBe(hidden);
        practisSetReportPage().getTileSpentChart().shouldBe(hidden);
        practisSetReportPage().getEmptyTimeSpentIcon().shouldBe(visible);
        practisSetReportPage().getEmptyTimeSpentText().shouldBe(visible);
        practisSetReportPage()
                .getEmptyTimeSpentText()
                .shouldBe(exactText("Shows the time spent by a learner on this Practis Set"));

        practisSetReportPage().getScenarioCard().get(0).shouldBe(visible);
        practisSetReportPage().getScenarioCard().get(0).shouldBe(attribute("disabled"));
        practisSetReportPage().getScenarioCardTitle().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(exactText("Scenario"));
        practisSetReportPage().getScenarioCardIcon().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(exactText("Not Started"));
        practisSetReportPage().getRepsTitle().shouldBe(visible);
        practisSetReportPage().getRepsTitle().shouldBe(exactText("Reps"));
        practisSetReportPage().getPassedRepsCount().shouldBe(visible);
        practisSetReportPage().getPassedRepsCount().shouldBe(exactText("0"));
        practisSetReportPage().getRequiredRepsCount().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(exactText("Accuracy Test"));
        practisSetReportPage().getAccuracyTestValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestValue().shouldBe(exactText("—"));
        practisSetReportPage().getViewArrow().get(0).shouldBe(visible);

        practisSetReportPage().getChallengeText().shouldBe(visible);
        practisSetReportPage().getChallengeText().shouldBe(exactText("Challenge"));
        practisSetReportPage().getChallengeCardIcon().shouldBe(visible);
        practisSetReportPage().getChallengeCard().get(0).shouldBe(visible);
        practisSetReportPage().getChallengeCardTitle().shouldBe(visible);
        practisSetReportPage().getChallengeStatus().shouldBe(visible);
        practisSetReportPage().getChallengeStatus().shouldBe(exactText("Not Submitted"));
        practisSetReportPage().getScoreTitle().shouldBe(visible);
        practisSetReportPage().getScoreTitle().shouldBe(exactText("Score"));
        practisSetReportPage().getScoreValue().shouldBe(visible);
        practisSetReportPage().getScoreValue().shouldBe(exactText("—"));

        practisSetReportPage().getPlayButton().shouldBe(hidden);
        practisSetReportPage().getReviewButton().shouldBe(hidden);
    }

    /** Assert elements on In Progress Practis Set Report page */
    public static void assertInProgressPractisSetReportPage() {
        practisSetReportPage().getPractisSetReportTitle().shouldBe(visible);
        practisSetReportPage().getPractisSetReportTitle().shouldBe(exactText("Practis Set Report"));
        practisSetReportPage().getPractisSetTitle().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(exactText("In Progress"));

        practisSetReportPage().getUserPic().shouldBe(visible);
        practisSetReportPage().getUserEmail().shouldBe(visible);
        practisSetReportPage().getUserName().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(enabled);
        practisSetReportPage().getNudgeButton().shouldBe(exactText("Nudge"));

        practisSetReportPage().getAccuracyTestsTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsTitle().shouldBe(exactText("Accuracy Tests"));
        practisSetReportPage().getAccuracyTestsValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsValue().shouldBe(exactText("—"));
        practisSetReportPage().getAverageText().shouldBe(visible);
        practisSetReportPage().getAverageText().shouldBe(exactText("Average"));
        practisSetReportPage().getProgressTitle().shouldBe(visible);
        practisSetReportPage().getProgressTitle().shouldBe(exactText("Progress"));
        practisSetReportPage().getPassedScenariosCount().shouldBe(visible);
        practisSetReportPage().getPassedScenariosCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalScenariosCount().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(exactText("Scenarios"));
        practisSetReportPage().getPassedChallengesCount().shouldBe(visible);
        practisSetReportPage().getPassedChallengesCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalChallengesCount().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(exactText("Challenges"));

        practisSetReportPage().getTimeSpentTitle().shouldBe(visible);
        practisSetReportPage().getTimeSpentTitle().shouldBe(exactText("Time Spent"));
        practisSetReportPage().getTimeSpentTotal().shouldBe(visible);
        practisSetReportPage().getTileSpentChart().shouldBe(visible);

        practisSetReportPage().getScenarioCard().get(0).shouldBe(visible);
        practisSetReportPage().getScenarioCardTitle().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(exactText("Scenario"));
        practisSetReportPage().getScenarioCardIcon().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(exactText("In Progress"));
        practisSetReportPage().getRepsTitle().shouldBe(visible);
        practisSetReportPage().getRepsTitle().shouldBe(exactText("Reps"));
        practisSetReportPage().getPassedRepsCount().shouldBe(visible);
        practisSetReportPage().getPassedRepsCount().shouldBe(exactText("0"));
        practisSetReportPage().getRequiredRepsCount().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(exactText("Accuracy Test"));
        practisSetReportPage().getAccuracyTestValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestValue().shouldBe(exactText("—"));

        practisSetReportPage().getChallengeText().shouldBe(visible);
        practisSetReportPage().getChallengeText().shouldBe(exactText("Challenge"));
        practisSetReportPage().getChallengeCardIcon().shouldBe(visible);
        practisSetReportPage().getChallengeCard().get(0).shouldBe(visible);
        practisSetReportPage().getChallengeCardTitle().shouldBe(visible);
        practisSetReportPage().getChallengeStatus().shouldBe(visible);
        practisSetReportPage().getScoreTitle().shouldBe(visible);
        practisSetReportPage().getScoreTitle().shouldBe(exactText("Score"));
        practisSetReportPage().getScoreValue().shouldBe(visible);
        practisSetReportPage().getScoreValue().shouldBe(exactText("—"));

        practisSetReportPage().getPlayButton().shouldBe(hidden);
        practisSetReportPage().getReviewButton().shouldBe(hidden);
    }

    /** Assert elements on Completed Practis Set Report page */
    public static void assertCompletedPractisSetReportPage() {
        practisSetReportPage().getPractisSetReportTitle().shouldBe(visible);
        practisSetReportPage().getPractisSetReportTitle().shouldBe(exactText("Practis Set Report"));
        practisSetReportPage().getPractisSetTitle().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(visible);
        practisSetReportPage().getStatusLabel().shouldBe(exactText("Completed"));

        practisSetReportPage().getUserPic().shouldBe(visible);
        practisSetReportPage().getUserEmail().shouldBe(visible);
        practisSetReportPage().getUserName().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(visible);
        practisSetReportPage().getNudgeButton().shouldBe(enabled);
        practisSetReportPage().getNudgeButton().shouldBe(exactText("Nudge"));

        practisSetReportPage().getAccuracyTestsTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsTitle().shouldBe(exactText("Accuracy Tests"));
        practisSetReportPage().getAccuracyTestsValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestsValue().shouldBe(exactText("—"));
        practisSetReportPage().getAverageText().shouldBe(visible);
        practisSetReportPage().getAverageText().shouldBe(exactText("Average"));
        practisSetReportPage().getProgressTitle().shouldBe(visible);
        practisSetReportPage().getProgressTitle().shouldBe(exactText("Progress"));
        practisSetReportPage().getPassedScenariosCount().shouldBe(visible);
        practisSetReportPage().getPassedScenariosCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalScenariosCount().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(visible);
        practisSetReportPage().getScenariosText().shouldBe(exactText("Scenarios"));
        practisSetReportPage().getPassedChallengesCount().shouldBe(visible);
        practisSetReportPage().getPassedChallengesCount().shouldBe(exactText("0"));
        practisSetReportPage().getTotalChallengesCount().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(visible);
        practisSetReportPage().getChallengesText().shouldBe(exactText("Challenges"));

        practisSetReportPage().getTimeSpentTitle().shouldBe(visible);
        practisSetReportPage().getTimeSpentTitle().shouldBe(exactText("Time Spent"));
        practisSetReportPage().getTimeSpentTotal().shouldBe(visible);
        practisSetReportPage().getTileSpentChart().shouldBe(visible);

        practisSetReportPage().getScenarioCard().get(0).shouldBe(visible);
        practisSetReportPage().getScenarioCardTitle().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(visible);
        practisSetReportPage().getScenarioText().shouldBe(exactText("Scenario"));
        practisSetReportPage().getScenarioCardIcon().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(visible);
        practisSetReportPage().getScenarioStatus().shouldBe(exactText("Completed"));
        practisSetReportPage().getRepsTitle().shouldBe(visible);
        practisSetReportPage().getRepsTitle().shouldBe(exactText("Reps"));
        practisSetReportPage().getPassedRepsCount().shouldBe(visible);
        practisSetReportPage().getPassedRepsCount().shouldBe(exactText("0"));
        practisSetReportPage().getRequiredRepsCount().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(visible);
        practisSetReportPage().getAccuracyTestTitle().shouldBe(exactText("Accuracy Test"));
        practisSetReportPage().getAccuracyTestValue().shouldBe(visible);
        practisSetReportPage().getAccuracyTestValue().shouldBe(exactText("—"));

        practisSetReportPage().getChallengeText().shouldBe(visible);
        practisSetReportPage().getChallengeText().shouldBe(exactText("Challenge"));
        practisSetReportPage().getChallengeCardIcon().shouldBe(visible);
        practisSetReportPage().getChallengeCard().get(0).shouldBe(visible);
        practisSetReportPage().getChallengeCardTitle().shouldBe(visible);
        practisSetReportPage().getChallengeStatus().shouldBe(visible);
        practisSetReportPage().getScoreTitle().shouldBe(visible);
        practisSetReportPage().getScoreValue().shouldBe(exactText("Score"));
        practisSetReportPage().getScoreValue().shouldBe(visible);
        practisSetReportPage().getScoreValue().shouldBe(exactText("—"));

        practisSetReportPage().getPlayButton().shouldBe(hidden);
        practisSetReportPage().getReviewButton().shouldBe(hidden);
    }

    /** Assert empty nudge pop-up. */
    public static void assertEmptyNudgePopUp() {
        nudgePopup().getNudgeTitle().shouldBe(visible);
        nudgePopup().getNudgeTitle().shouldBe(exactText("Nudge"));
        nudgePopup().getNudgeDescription().shouldBe(visible);
        nudgePopup().getNudgeDescription().shouldBe(matchText("Send a direct message to"));
        nudgePopup().getFromField().shouldBe(visible);
        nudgePopup().getFromField().shouldHave(value("Automation User"));
        nudgePopup().getMessageField().shouldBe(visible);
        nudgePopup().getMessageField().shouldBe(attribute("font-size", "13px"));
        nudgePopup().getMessageField().shouldBe(attribute("maxlength", "200"));
        nudgePopup().getApplyButton().shouldBe(visible);
        nudgePopup().getApplyButton().shouldBe(exactText("Send"));
        nudgePopup().getCancelButton().shouldBe(visible);
        nudgePopup().getCancelButton().shouldBe(exactText("Cancel"));
    }
}
