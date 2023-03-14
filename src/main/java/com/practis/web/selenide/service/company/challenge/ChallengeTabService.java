package com.practis.web.selenide.service.company.challenge;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static java.lang.String.format;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

public class ChallengeTabService {

    private static final String DUPLICATED_TEMPLATE =
            "\\[[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\\s[0-9]{2}:[0-9]{2}:[0-9]{2}\\s(AM|PM)\\]-%s";
    private static final String ORIGINAL_TEMPLATE = "^(%s).*";

    /** Select All on Scenario Tab. */
    public void selectAllScenarios() {
        jsClick(scenarioTab().getSelectAllCheckbox());
    }

    /** Assign Label to Scenario Tab. */
    public void assignLabelToScenario(String label) {
        scenarioTab().getActionButton().parent().click();
        scenarioTab().getAssignLabelsBulkAction().click();
        labelModuleService().selectLabel(label);
        labelModule().getApplyButton().click();
    }

    /** Find scenario labels. */
    public SelenideElement findScenarioLabelCounter(final String scenario) {
        final var scenarioRow = scenarioTab().getScenarioRow().find(Condition.matchText(scenario));
        return scenarioRow.$("[data-test='library-scenarios-item-labels']");
    }

    /** Click 3-dot menu for the Challenge. */
    public void clickSingleAction(final String challenge) {
        final var challengeRow =
                challengeTab().getChallengeRow().find(Condition.matchText(challenge));
        challengeRow.$("div[data-test='library-challenges-item-menu-button']").click();
    }

    /** Click on 3-dot menu for the Challenge. */
    public void clickViewSingleAction() {
        challengeTab().getViewSingleAction().click();
    }

    /** Click 'Assign Labels' on 3-dot menu for the team. */
    public void clickAssignLabelsSingleAction() {
        challengeTab().getAssignLabelsSingleAction().click();
    }

    /** Click 'Duplicate' on 3-dot menu for the team. */
    public void clickDuplicateSingleAction() {
        scenarioTab().getDuplicateSingleAction().click();
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickArchiveSingleAction() {
        scenarioTab().getArchiveSingleAction().click();
    }

    /** Click 'Generate Challenge' on 3-dot menu for the team. */
    public void clickGenerateChallengeSingleAction() {
        scenarioTab().getGenerateChallengeSingleAction().click();
    }

    /** Click 'Restore' on 3-dot menu for the team. */
    public void clickRestoreSingleAction(String scenario) {
        scenarioTabService().clickSingleAction(scenario);
        scenarioTab().getRestoreSingleAction().click();
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickDeleteSingleAction(String name) {
        scenarioTabService().clickSingleAction(name);
        scenarioTab().getDeleteSingleAction().click();
    }

    public GridRow getOriginalScenario(final String name) {
        return grid().getRow(format(ORIGINAL_TEMPLATE, name));
    }

    /** Find Status in the row. */
    public SelenideElement findStatus(final String scenarioName) {
        final var scenario = scenarioTab().getScenarioRow().find(Condition.matchText(scenarioName));
        return scenario.$("[data-test='library-scenarios-item-status']");
    }
}
