package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;

import com.practis.dto.NewPractisSetInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.company.PractisSetEditPage;

public class PractisSetValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertPractisSetGridRow(final NewPractisSetInput inputData,
      final GridRow gridRow) {
    gridRow.get("Practis Sets").shouldBe(matchText(".*" + inputData.getTitle()));
  }

  /**
   * Assert input on edit page.
   */
  public static void assertPracrisSetInput(final NewPractisSetInput inputData,
      final PractisSetEditPage practisSetEditPage) {
    practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));
    practisSetEditPage.getDescriptionField()
        .shouldBe(attribute("value", inputData.getDescription()));

  }

  /**
   * Assert Title on edit page.
   */
  public static void assertPracrisSetTitle(final NewPractisSetInput inputData,
      final PractisSetEditPage practisSetEditPage) {
    practisSetEditPage.getTitleField().shouldBe(attribute("value", inputData.getTitle()));

  }
}
