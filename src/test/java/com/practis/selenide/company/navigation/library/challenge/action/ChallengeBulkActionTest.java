package com.practis.selenide.company.navigation.library.challenge.action;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioTab;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.scenarioTabService;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertDisabledAssignLabelsButton;
import static com.practis.web.selenide.validator.company.library.scenario.ScenarioTabValidator.assertLabelCountOnScenarioPage;
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
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.ScenarioExtension;
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
        libraryPage().getScenariosTab().click();
    }

    @TestRailTest(caseId = 1916)
    @DisplayName("Scenario: Bulk Action: Assign Labels: Check Elements")
    @ScenarioExtension(count = 2)
    void checkElementsOnAssignLabelsToScenario(final List<RestScenarioResponse> scenario) {
        // check disabled "Assign Labels" button
        Selenide.refresh();

        awaitFullPageLoad(10);
        scenarioTabService().selectAllScenarios();
        scenarioTab().getActionButton().parent().click();
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
        scenarioTabService().selectAllScenarios();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        scenarioTab().getActionButton().parent().click();
        scenarioTab().getAssignLabelsBulkAction().click();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsBulkAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.getName());
        assertSelectedLabel(label.getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 1917)
    @DisplayName("Scenarios: Bulk Action: Assign Labels: Apply")
    @ScenarioExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToPs(
            final List<RestCreateLabelResponse> label, final List<RestScenarioResponse> scenario) {

        Selenide.refresh();
        awaitFullPageLoad(10);

        scenarioTabService().selectAllScenarios();
        scenarioTabService().assignLabelToScenario(label.get(0).getName());

        assertLabelCountOnScenarioPage(scenario.get(0).getTitle(), "1");
    }

    @AfterEach
    void cleanup() {
        labelsToRemove.forEach(label -> practisApi().deleteLabel(label.getName()));
    }
}
