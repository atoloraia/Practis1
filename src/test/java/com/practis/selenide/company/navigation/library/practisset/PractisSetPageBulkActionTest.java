package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationCompany;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetTabService;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertDisabledAssignLabelsButton;
import static com.practis.web.selenide.validator.company.library.practisset.PractisSetTabValidator.assertLabelCountOnPsPage;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertCancelApplyButtonsBulkAction;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertElementsOnLabelSection;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertLabelCounter;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedAllStateLabels;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertSelectedLabel;
import static com.practis.web.selenide.validator.selection.LabelSelectionValidator.assertUnSelectAllStateLabels;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewLabelInput;
import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class PractisSetPageBulkActionTest {

    private final AtomicInteger integer = new AtomicInteger();
    private final List<RestCreateLabelResponse> labelsToRemove = new ArrayList<>();

    @BeforeEach
    void init() {
        navigationCompany().getLibraryNavigationItem().click();
    }

    @TestRailTest(caseId = 1858)
    @DisplayName("Practis Sets: Bulk Action: Assign Labels: Check Elements")
    @PractisSetExtension(count = 2)
    void checkElementsOnAssignLabelsToPs(final List<NewPractisSetInput> practisSets) {
        // check disabled "Assign Labels" button
        Selenide.refresh();
        practisSetTabService().selectAllPractisSets();
        practisSetTab().getActionButton().click();
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
        practisSetTabService().selectAllPractisSets();
        practisSetTab().getActionButton().click();
        practisSetTab().getAssignLabelsBulkAction().click();
        assertElementsOnLabelSection();
        assertCancelApplyButtonsBulkAction();

        // assert Unselect, Select All state
        assertUnSelectAllStateLabels();

        labelModuleService().selectLabel(label.getName());
        assertSelectedLabel(label.getName());
        assertLabelCounter("1 Label selected");

        assertSelectedAllStateLabels();
    }

    @TestRailTest(caseId = 1859)
    @DisplayName("Practis Sets: Bulk Action: Assign Labels: Apply")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 1)
    void assignLabelsToPs(
            final List<RestCreateLabelResponse> label, final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisSetTabService().selectAllPractisSets();
        practisSetTabService().assignLabelToPractisSets(label.get(0).getName());

        assertLabelCountOnPsPage(practisSets.get(0).getName(), "1");
    }

    @TestRailTest(caseId = 23838)
    @DisplayName("Practis Sets: Bulk Action: Assign Labels: Check already assigned label")
    @PractisSetExtension(count = 1)
    @LabelExtension(count = 2)
    void alreadyAssignLabelsPractisSetSingleAction(
            final List<RestCreateLabelResponse> label, final List<NewPractisSetInput> practisSets) {
        Selenide.refresh();
        practisApi()
                .assignLabelToPractisSet(practisSets.get(0).getId(), List.of(label.get(0).getId()));

        practisSetTabService().selectAllPractisSets();
        practisSetTab().getActionButton().click();
        practisSetTab().getAssignLabelsBulkAction().click();
        assertSelectedLabel(label.get(0).getName());
        assertSelectedLabel(label.get(1).getName());
    }

    @AfterEach
    void cleanup() {
        labelsToRemove.forEach(label -> practisApi().deleteLabel(label.getName()));
    }
}
