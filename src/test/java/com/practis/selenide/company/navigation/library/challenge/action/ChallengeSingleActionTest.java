package com.practis.selenide.company.navigation.library.challenge.action;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.libraryService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.selenide.validator.company.ChallengeValidator.assertElementsOnViewChallengePage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertChallengeStatusRow;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertChallengesRows;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertEmptyChallengesTab;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertLabelCountOnChallengePage;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertSingleActionArchivedChallenge;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertSingleActionChallenge;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertSingleActionChallengeNoLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelApplyButtonsSingleAction;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewChallengeInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ArchivedChallengeExtension;
import com.practis.support.extension.practis.ChallengeExtension;
import com.practis.support.extension.practis.LabelExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class ChallengeSingleActionTest {

    private final AtomicInteger integer = new AtomicInteger();
    private final List<RestCreateLabelResponse> labelsToRemove = new ArrayList<>();
    private List<String> challengesToRemove;

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        libraryPage().getChallengesTab().click();
        challengesToRemove = new ArrayList<>();
    }

    @TestRailTest(caseId = 30052)
    @DisplayName("Challenge: Single Action: Check Elements")
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void checkElementsSingleActionChallenge(List<RestChallengeResponse> challenges) {
        Selenide.refresh();
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());

        // asser single action Challenge
        assertSingleActionChallenge();
    }

    @TestRailTest(caseId = 1959)
    @DisplayName("Challenge: Single Action: No Labels: Check Elements")
    @ChallengeExtension(count = 1)
    void checkElementsSingleActionScenarioNoLabels(List<RestScenarioResponse> scenario) {
        Selenide.refresh();
        scenarioTabService().clickSingleAction(scenario.get(0).getTitle());

        // asser single action challenge
        assertSingleActionChallengeNoLabels();
    }

    @TestRailTest(caseId = 1964)
    @DisplayName("Challenges: Single Action: View")
    @ChallengeExtension(count = 1)
    void viewChallengeSingleAction(List<RestChallengeResponse> challenges) {
        Selenide.refresh();
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());
        challengeTabService().clickViewSingleAction();

        // assert 'View Challenge' page
        assertElementsOnViewChallengePage();
    }

    @TestRailTest(caseId = 30053)
    @DisplayName("Challenges: Single Action: Assign Labels: Check Elements")
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void checkElementsOnAssignLabelsToChallenge(
            List<RestChallengeResponse> challenges, final List<RestCreateLabelResponse> label) {

        // check elements on "Assign Labels" modal
        Selenide.refresh();
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());
        challengeTabService().clickAssignLabelsSingleAction();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsSingleAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.get(0).getName());
        assertSelectedLabel(label.get(0).getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 1963)
    @DisplayName("Challenge: Single Action: Assign Labels: Apply")
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToScenario(
            List<RestChallengeResponse> challenges, final List<RestCreateLabelResponse> label) {
        Selenide.refresh();
        awaitFullPageLoad(10);
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());
        challengeTabService().clickAssignLabelsSingleAction();
        labelModuleService().selectLabel(label.get(0).getName());
        labelModule().getApplyButton().click();

        Selenide.refresh();
        awaitFullPageLoad(10);
        assertLabelCountOnChallengePage(challenges.get(0).getTitle(), "1");
    }

    @TestRailTest(caseId = 1952)
    @DisplayName("Challenges: Single Action: Duplicate")
    @ChallengeExtension(count = 1)
    void duplicateChallengeSingleAction(List<RestChallengeResponse> challenges) {
        Selenide.refresh();
        awaitFullPageLoad(10);

        // Check number of teams in the list
        assertChallengesRows(1);

        // Duplicate the team
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());
        challengeTabService().clickDuplicateSingleAction();

        awaitSoft(10, () -> challengeTab().getChallengeRow().size() == 2);
        assertChallengesRows(2);
        final var originalChallenge =
                challengeTabService().getOriginalChallenge(challenges.get(0).getTitle());
        final var duplicatedScenario =
                challengeTabService().getDuplicatedPs(challenges.get(0).getTitle());
        challengesToRemove.add(duplicatedScenario.get("Challenges").text());

        assertTrue(
                duplicatedScenario
                        .get("Challenges")
                        .text()
                        .endsWith(originalChallenge.get("Challenge").text()));
    }

    @TestRailTest(caseId = 1953)
    @DisplayName("Challenges: Single Action: Archive")
    @ChallengeExtension(count = 1)
    void archiveChallengesSingleAction(List<RestChallengeResponse> challenges) {
        // click 'Archive' single action
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());
        challengeTabService().clickArchiveSingleAction();

        // check snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge has been archived"));

        // assert Active Challenge list
        assertEmptyChallengesTab();

        // assert Archived Challenges list
        libraryService().filterByArchivedItems();
        assertChallengesRows(1);
        assertChallengeStatusRow(challenges.get(0).getTitle(), "Archived");
    }

    @TestRailTest(caseId = 1960)
    @DisplayName("Challenge: Archived: Single Action: Check Elements")
    @ArchivedChallengeExtension
    void checkElementsSingleActionArchivedChallenge(final List<NewChallengeInput> challenges) {
        // assert elements on single action
        libraryService().filterByArchivedItems();
        challengeTabService().clickSingleAction(challenges.get(0).getTitle());

        assertSingleActionArchivedChallenge();
    }

    @TestRailTest(caseId = 1954)
    @DisplayName("Challenges: Archived: Single Action: Restore")
    @ArchivedChallengeExtension
    void restoreChallengeSingleAction(final List<NewChallengeInput> challenges) {
        awaitFullPageLoad(10);

        libraryService().filterByArchivedItems();

        challengeTabService().clickRestoreSingleAction(challenges.get(0).getTitle());

        // assert snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Challenge has been restored"));

        // assert Draft challenge list
        libraryService().filterByDraftItems();
        assertChallengesRows(1);
        assertChallengeStatusRow(challenges.get(0).getTitle(), "Draft");
    }

    @TestRailTest(caseId = 1955)
    @DisplayName("Challenges: Archived: Single Action: Delete")
    @ArchivedChallengeExtension
    void deleteChallengeSingleAction(final List<NewChallengeInput> challenges) {
        awaitFullPageLoad(10);

        libraryService().filterByArchivedItems();
        challengeTabService().clickDeleteSingleAction(challenges.get(0).getTitle());

        // assert "Are you sure" pop-up
        areYouSurePopUp().saveChanges();

        // assert snackbar
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("1 Challenge has been deleted"));

        // assert empty tab
        awaitFullPageLoad(10);
        assertChallengesRows(0);
    }

    @AfterEach
    void cleanup() {
        challengesToRemove.forEach(challenge -> practisApi().archiveAndDeleteChallenge(challenge));
    }
}
