package com.practis.web.selenide.validator.company.navigation;

import static com.codeborne.selenide.Condition.matchText;

import com.practis.dto.NewUserInput;
import com.practis.web.selenide.component.GridRow;

public class UserValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertUserGridRowPending(final NewUserInput inputData, final GridRow gridRow) {
    gridRow.get("Users")
        .shouldBe(matchText(inputData.getFirstName() + " " + inputData.getLastName()));
    gridRow.get("Email Address").shouldBe(matchText(inputData.getEmail()));
  }

}
