package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;

import com.practis.dto.NewAdminInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.AdminEditPage;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertAdminGridRow(
      final NewAdminInput inputData, final GridRow gridRow) {
    gridRow.get("Administrators").shouldBe(
        text(inputData.getFirstName() + " " + inputData.getLastName()));
    gridRow.get("Email Address").shouldBe(exactText(inputData.getEmail()));
  }

  /**
   * Assert data on edit page with input.
   */
  public static void assertAdminData(final NewAdminInput inputData, final AdminEditPage editPage) {
    editPage.getFirstNameFieldElement().shouldBe(exactText(inputData.getFirstName()));
    editPage.getLastNameFieldElement().shouldBe(exactText(inputData.getLastName()));
    editPage.getEmailFieldElement().shouldBe(exactText(inputData.getEmail()));

    editPage.getEmailInfoElement().shouldBe(exactText(inputData.getEmail()));

    editPage.getNameInfoElement().shouldBe(exactText(
        inputData.getFirstName() + " " + inputData.getLastName()
    ));
  }
}
