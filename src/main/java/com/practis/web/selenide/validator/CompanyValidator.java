package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;

import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.CompanyEditPage;

public class CompanyValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertCompanyGridRow(final NewCompanyInput inputData, final GridRow gridRow) {
    gridRow.get("Company").shouldBe(matchText(".*" + inputData.getName()));
  }

  /**
   * Assert data on edit page with input.
   */
  public static void assertCompanyData(final NewCompanyInput inputData,
      final CompanyEditPage editPage) {
    editPage.getHeaderNameElement().shouldBe(text(inputData.getName()));
    editPage.getCompanyNameFieldElement().shouldBe(attribute("value", inputData.getName()));
  }
}
