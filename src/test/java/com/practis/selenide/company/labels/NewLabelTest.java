package com.practis.selenide.company.labels;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelPanelService;
import static com.practis.web.selenide.configuration.data.company.NewLabelInputData.getNewLabelInput;
import static com.practis.web.selenide.validator.LabelPanelValidator.assertElementsEmptyLabelPanel;
import static com.practis.web.selenide.validator.LabelPanelValidator.assertElementsLabelPanel;
import static java.lang.String.format;

import com.practis.dto.NewLabelInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class NewLabelTest {

    private NewLabelInput inputData;
    private List<String> labelsToRemove;

    @BeforeEach
    void init() {
        inputData = getNewLabelInput();
        inputData.setName(format(inputData.getName(), timestamp()));

        labelsToRemove = new ArrayList<>();
        labelsToRemove.add(inputData.getName());
    }

    /** Labels Panel: Check WEB Elements on Empty Labels panel. */
    @TestRailTest(caseId = 5307)
    @DisplayName("Check WEB Elements on Empty Labels panel")
    void checkElementsEmptyLabelPanel() {
        assertElementsEmptyLabelPanel();
    }

    /** Labels Panel: Check WEB Elements on Labels panel. */
    @TestRailTest(caseId = 5308)
    @DisplayName("Check WEB Elements on Labels panel")
    void checkElementsLabelPanel() {
        labelPanelService().createLabel(inputData);
        assertElementsLabelPanel();
    }

    /** Labels Panel: Add Label. */
    @TestRailTest(caseId = 48)
    @DisplayName("Create Label")
    void createLabel() {
        labelPanelService().createLabel(inputData);

        snackbar().getMessage().shouldBe(exactText("Label Created"));
        labelPanelService().checkLabelExists(inputData.getName());
    }

    @AfterEach
    void cleanup() {
        labelsToRemove.forEach(label -> practisApi().deleteLabel(label));
    }
}
