package com.practis.web.selenide.service.company.practisset;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.publishPractisSetPopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;

import com.codeborne.selenide.Condition;
import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.util.AwaitUtils;

public class CreatePractisSetService {

    /** Fill Practis Set Title. */
    public void fillTitle(final NewPractisSetInput inputData) {
        practisSetCreatePage().getTitleField().append(inputData.getName());
    }

    /** Fill Practis Set Title and Description. */
    public void fillForm(final NewPractisSetInput inputData) {
        fillTitle(inputData);
        practisSetCreatePage().getDescriptionField().append(inputData.getDescription());
    }

    /** Fill Title, Description. Add Challenge and Scenario. */
    public void createPractisSet(
            final NewPractisSetInput inputData,
            final String scenarioTitle,
            final String challengeTitle) {
        practisSetCreatePage().getTitleField().append(inputData.getName());
        practisSetCreatePage().getDescriptionField().append(inputData.getDescription());
        addScenario(scenarioTitle);
        addChallenge(challengeTitle);
    }

    /** Edit Title, Description. Add Challenge and Scenario. */
    public void editPractisSet(
            final String label, final String scenarioTitle, final String challengeTitle) {
        practisSetCreatePage().getTitleField().append("_edit");
        practisSetCreatePage().getDescriptionField().append("_edit");
        practisSetCreatePage().getAddLabelsButton().click();
        addLabel(label);
        addScenario(scenarioTitle);
        addChallenge(challengeTitle);
    }

    /** Adds Scenario to PractisSet. */
    public void addScenario(final String scenarioTitle) {
        practisSetCreatePage()
                .getScenarioItems()
                .find(Condition.matchText(scenarioTitle))
                .doubleClick();
    }

    /** Adds Challenge to PractisSet. */
    public void addChallenge(final String challengeTitle) {
        practisSetCreatePage().getChallengeTab().click();
        practisSetCreatePage()
                .getChallengeItems()
                .find(Condition.matchText(challengeTitle))
                .doubleClick();
    }

    /** Click Publish button. */
    public void publishPractisSet() {
        practisSetCreatePage().getSaveChangesButton().click();
    }

    /** Click Publish on 'Publish Practis Set' pop-up . */
    public void confirmPublish() {
        publishPractisSetPopUp().publish();
    }

    /** Save Practis Set as Draft. */
    public void saveAsDraftPractisSet() {
        practisSetCreatePage().getSaveAsDraftButton().click();
    }

    /** Click go back on 'Publish Practis Set' pop-up . */
    public void goBack() {
        publishPractisSetPopUp().goBack();
    }

    /** Search PS on grid by PS Title. */
    public GridRow searchPS(final String name) {
        navigationCompany().libraryNavigationItem.click();
        libraryTabs().practisSetLibraryTab.click();
        AwaitUtils.awaitSoft(10, () -> search().getSearchField().isDisplayed());
        search().search(name);

        return awaitGridRowExists(5, () -> grid().getRow(name));
    }

    /** Click outside the Practis Set form and discard changes. */
    public void exitPractisSetWithDiscard() {
        jsClick(navigationCompany().getTeamsNavigationItem());
        areYouSurePopUp().discardChanges();
    }

    /** Click outside the Practis Set form and save changes. */
    public void exitPractisSetWithSave() {
        jsClick(navigationCompany().getTeamsNavigationItem());
        areYouSurePopUp().saveChanges();
    }

    /** Select label and click 'Save Changes'. */
    public void addLabel(final String label) {
        practisSetCreatePage().findLabelCheckbox(label).click();
        practisSetCreatePage().getSaveChangesLabelButton().click();
    }
}
