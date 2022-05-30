package com.practis.selenide.company;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.label;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewLabelInputData.getNewLabelInput;
import static java.lang.String.format;

import com.practis.dto.company.NewLabelInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
class NewLabelTest {

  private NewLabelInput input;
  private List<String> labelsToRemove;

  @BeforeEach
  void init() {
    input = getNewLabelInput();
    input.setName(format(input.getName(), timestamp()));

    labelsToRemove = new ArrayList<>();
    labelsToRemove.add(input.getName());
  }

  @Test
  @TestRailTest(caseId = 48)
  @DisplayName("Create Label")
  void createLabel() {
    label().openPanel().clickAddLabel().fillLabelInput(input.getName()).save();

    snackbar().getMessage().shouldBe(exactText("Label Created"));
    label().getLabel(input.getName()).should(exist);
  }

  @AfterEach
  void cleanup() {
    labelsToRemove.forEach(label -> practisApi().deleteLabel(label));
  }
}
