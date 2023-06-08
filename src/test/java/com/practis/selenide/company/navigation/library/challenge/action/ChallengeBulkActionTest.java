package com.practis.selenide.company.navigation.library.challenge.action;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.challengeTab;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.challengeTabService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.validator.company.library.challenge.ChallengeTabValidator.assertLabelCountOnChallengePage;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertDisabledAssignLabelsButton;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelApplyButtonsBulkAction;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewLabelInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
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
public class ChallengeBulkActionTest {

    private final AtomicInteger integer = new AtomicInteger();
    private final List<RestCreateLabelResponse> labelsToRemove = new ArrayList<>();

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
        libraryPage().getChallengesTab().click();
    }

    @TestRailTest(caseId = 31844)
    @DisplayName("Challenge: Bulk Action: Assign Labels: Check Elements")
    @ChallengeExtension(count = 2)
    void checkElementsOnAssignLabelsToChallenge() {
        // check disabled "Assign Labels" button
        Selenide.refresh();

        awaitFullPageLoad(10);
        challengeTabService().selectAllScenarios();
        challengeTab().getActionButton().parent().click();
        assertDisabledAssignLabelsButton();

        // create Label
        final var label =
                practisApi()
                        .createLabel(
                                NewLabelInput.builder()
                                        .name(
                                                String.format(
                                                        "test-%s-%s",
                                                        integer.addAndGet(1), timestamp()))
                                        .build());
        labelsToRemove.add(label);

        // check elements on 'Assign Label' model
        Selenide.refresh();
        awaitFullPageLoad(10);
        challengeTabService().selectAllScenarios();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        challengeTab().getActionButton().parent().click();
        challengeTab().getAssignLabelsBulkAction().click();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsBulkAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.getName());
        assertSelectedLabel(label.getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 31845)
    @DisplayName("Challenges: Bulk Action: Assign Labels: Apply")
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToPs(
            final List<RestCreateLabelResponse> label,
            final List<RestChallengeResponse> challenge) {

        Selenide.refresh();
        awaitFullPageLoad(10);

        challengeTabService().selectAllScenarios();
        challengeTabService().assignLabelToChallenge(label.get(0).getName());

        assertLabelCountOnChallengePage(challenge.get(0).getTitle(), "1");
    }

    @AfterEach
    void cleanup() {
        labelsToRemove.forEach(label -> practisApi().deleteLabel(label.getName()));
    }
}
