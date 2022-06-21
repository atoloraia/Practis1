package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static java.util.Locale.ROOT;

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
    editPage.getFirstNameFieldElement().shouldBe(attribute("value", inputData.getFirstName()));
    editPage.getLastNameFieldElement().shouldBe(attribute("value", inputData.getLastName()));
    editPage.getEmailFieldElement()
        .shouldBe(attribute("value", inputData.getEmail().toLowerCase(ROOT)));

    editPage.getEmailInfoElement().shouldBe(exactText(inputData.getEmail()));

    editPage.getNameInfoElement().shouldBe(exactText(
        inputData.getFirstName() + " " + inputData.getLastName()
    ));
  }

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnCreateCompanyPage() {
    adminCreatePage().getNewPractisAdminTitle().shouldBe(exactText("New Practis Admin"));
    adminCreatePage().getCreateButtonElement().shouldBe(visible);

    adminCreatePage().getEmailFieldElements().shouldBe(size(1));
    adminCreatePage().getEmailFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Email Address"));
    adminCreatePage().getEmailFieldElements().get(0).shouldBe(visible);

    adminCreatePage().getFirstNameFieldElements().shouldBe(size(1));
    adminCreatePage().getFirstNameFieldElements().get(0).sibling(0)
        .shouldBe(exactText("First Name"));
    adminCreatePage().getFirstNameFieldElements().get(0).shouldBe(visible);

    adminCreatePage().getLastNameFieldElements().shouldBe(size(1));
    adminCreatePage().getLastNameFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Last Name"));
    adminCreatePage().getLastNameFieldElements().get(0).shouldBe(visible);

    adminCreatePage().getPasswordFieldElements().shouldBe(size(1));
    adminCreatePage().getPasswordFieldElements().get(0)
        .sibling(0).shouldBe(exactText("Password"));
    adminCreatePage().getPasswordFieldElements().get(0).shouldBe(visible);

    adminCreatePage().getShowPasswordElements().shouldBe(size(1));
    adminCreatePage().getShowPasswordElements().get(0).shouldBe(exactText("Show"));
    adminCreatePage().getShowPasswordElements().get(0).shouldBe(visible);

    adminCreatePage().getShowPasswordElements().get(0).click();
    adminCreatePage().getHidePasswordElements().shouldBe(size(1));
    adminCreatePage().getHidePasswordElements().get(0).shouldBe(exactText("Hide"));
    adminCreatePage().getHidePasswordElements().get(0).shouldBe(visible);

    adminCreatePage().getDeleteRowButtonElements().shouldBe(size(1));
    adminCreatePage().getDeleteRowButtonElements().get(0).shouldBe(visible);

    adminCreatePage().getAddRowLinkElement().shouldBe(visible);
  }

}
