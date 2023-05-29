package com.practis.web.selenide.service.company.challenge;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.libraryTabs;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeCreatePage;
import static com.practis.web.util.AwaitUtils.awaitElementCollectionSize;
import static com.practis.web.util.AwaitUtils.awaitElementEnabled;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static com.practis.web.util.SelenideSetDivUtilUtil.setDivText;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewChallengeInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.util.AwaitUtils;
import lombok.SneakyThrows;

public class CreateChallengeService {

    private static final int GENERATE_ALL_TIMEOUT = 10;

    /** Fill Title. */
    public void fillTitle(final NewChallengeInput inputData) {
        challengeCreatePage().getTitleField().append(inputData.getTitle());
    }

    /** Fill Title and Customer Line. */
    public void fillTitleWithCustomerLine(final NewChallengeInput inputData) {
        challengeCreatePage().getTitleField().append(inputData.getTitle());
        setDivText(challengeCreatePage().getCustomerLine(), inputData.getCustomerLine());
        awaitElementEnabled(10, () -> challengeCreatePage().getGenerateForAllButton()).click();
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> challengeCreatePage().getPlayButtons(), 1);
    }

    /** Fill Customer Line. */
    public void fillCustomerLine(final NewChallengeInput inputData) {

        setDivText(challengeCreatePage().getCustomerLine(), inputData.getCustomerLine());
        awaitElementEnabled(10, () -> challengeCreatePage().getGenerateForAllButton()).click();
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> challengeCreatePage().getPlayButtons(), 1);
    }

    /** Fill Add New Challenge form. */
    @SneakyThrows
    public void fillForm(final NewChallengeInput inputData, final String label) {
        fillTitle(inputData);
        challengeCreatePage().getDescriptionField().append(inputData.getDescription());

        challengeCreatePage().getLabelsButton().click();
        addLabel(label);

        // Check snackbar message "labels have been assigned to Challenge"
        snackbar().getMessage().shouldBe(exactText("labels have been assigned to Challenge"));

        setDivText(challengeCreatePage().getCustomerLine(), inputData.getCustomerLine());
        awaitElementEnabled(10, () -> challengeCreatePage().getGenerateForAllButton()).click();
        awaitElementCollectionSize(
                GENERATE_ALL_TIMEOUT, () -> challengeCreatePage().getPlayButtons(), 1);
    }

    public void createChallenge(final NewChallengeInput inputData, final String label) {
        fillForm(inputData, label);
        challengeCreatePage().getPublishButton().click();
    }

    public void saveAsDraftChallenge(final NewChallengeInput inputData, final String label) {
        fillForm(inputData, label);
        challengeCreatePage().getSaveAsDraftButton().click();
    }

    public void exitChallengeWithDiscard() {
        jsClick(navigationCompany().getTeamsNavigationItem());
        areYouSurePopUp().discardChanges();
    }

    public void exitChallengeWithSave() {
        jsClick(navigationCompany().getTeamsNavigationItem());
        areYouSurePopUp().saveChanges();
    }

    /** Select label and click 'Save Changes'. */
    public void addLabel(final String label) {
        challengeCreatePage().findLabelCheckbox(label).click();
        challengeCreatePage().getSaveChangesLabelButton().click();
    }

    /** Search challenge on grid by Challenge Title. */
    public GridRow searchChallenge(final String name) {
        jsClick(navigationCompany().getLibraryNavigationItem());
        await().pollDelay(TWO_SECONDS).until(() -> true);
        jsClick(libraryTabs().getChallengesLibraryTab());
        AwaitUtils.awaitSoft(10, () -> search().getSearchField().isDisplayed());
        search().search(name);

        return awaitGridRowExists(5, () -> grid().getRow(name));
    }
}
