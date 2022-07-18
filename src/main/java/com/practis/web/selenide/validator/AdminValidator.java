package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
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
    gridRow.get("Administrators")
        .shouldBe(text(inputData.getFirstName() + " " + inputData.getLastName()));
    gridRow.get("Email Address").shouldBe(exactText(inputData.getEmail().toLowerCase(ROOT)));
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

  /**
   * Assert elements on User Settings page.
   */
  public static void assertElementsOnUserSettingsPage() {
    adminEditPage().getHeaderNameText().shouldBe(exactText("User Settings"));
    adminEditPage().getHeaderNameElement().shouldBe(visible);
    companyEditPage().getBackButton().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    newItemSelector().getNewItemSelector().shouldBe(visible);

    adminEditPage().getRoleTitle().shouldBe(exactText("Practis Admin"));
    adminEditPage().getNameInfoElement().shouldBe(visible);
    adminEditPage().getEmailInfoElement().shouldBe(visible);
    adminEditPage().getHeaderNameElement()
        .equals((adminEditPage().getNameInfoElement()));

    adminEditPage().getEditUserDetailsButton().get(0).shouldBe(visible);
    adminEditPage().getEditUserDetailsButton().get(0).shouldBe(exactText("Edit User Details"));
    adminEditPage().getEditUserDetailsButton().get(1).shouldBe(visible);
    adminEditPage().getEditUserDetailsButton().get(1).shouldBe(exactText("Change Password"));

    companyEditPage().getLargeUserpic().shouldBe(visible);
    companyEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
    companyEditPage().getPictureText()
        .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));

    adminEditPage().getFirstNameFieldElement().shouldBe(visible);
    adminEditPage().getLastNameFieldElement().shouldBe(visible);
    adminEditPage().getEmailFieldElement().shouldBe(visible);
    adminEditPage().getMobileFieldElement().shouldBe(visible);
    adminEditPage().getEmailFieldElement()
        .equals((adminEditPage().getEmailInfoElement()));

    adminEditPage().getDeleteButton().shouldBe(visible);
    adminEditPage().getDeleteButton().shouldBe(exactText("Delete"));
    adminEditPage().getUpdateButton().shouldBe(visible);
    adminEditPage().getUpdateButton().shouldBe(exactText("Update"));

  }

}
