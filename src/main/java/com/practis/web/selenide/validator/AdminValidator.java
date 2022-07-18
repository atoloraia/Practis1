package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
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
    editPage.getFirstNameField().shouldBe(attribute("value", inputData.getFirstName()));
    editPage.getLastNameField().shouldBe(attribute("value", inputData.getLastName()));
    editPage.getEmailField()
        .shouldBe(attribute("value", inputData.getEmail().toLowerCase(ROOT)));

    editPage.getEmailInfo().shouldBe(exactText(inputData.getEmail()));

    editPage.getNameInfo().shouldBe(exactText(
        inputData.getFirstName() + " " + inputData.getLastName()
    ));
  }

  /**
   * Assert elements on New Admin page.
   */
  public static void assertElementsOnCreateCompanyPage() {
    adminCreatePage().getNewPractisAdminTitle().shouldBe(exactText("New Practis Admin"));
    adminCreatePage().getCreateButton().shouldBe(visible);

    adminCreatePage().getEmailFieldElements().shouldBe(size(1));
    adminCreatePage().getEmailFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Email Address"));
    adminCreatePage().getEmailFieldElements().get(0).shouldBe(visible);

    adminCreatePage().getFirstNameField().shouldBe(size(1));
    adminCreatePage().getFirstNameField().get(0).sibling(0)
        .shouldBe(exactText("First Name"));
    adminCreatePage().getFirstNameField().get(0).shouldBe(visible);

    adminCreatePage().getLastNameField().shouldBe(size(1));
    adminCreatePage().getLastNameField().get(0).sibling(0)
        .shouldBe(exactText("Last Name"));
    adminCreatePage().getLastNameField().get(0).shouldBe(visible);

    adminCreatePage().getPasswordField().shouldBe(size(1));
    adminCreatePage().getPasswordField().get(0)
        .sibling(0).shouldBe(exactText("Password"));
    adminCreatePage().getPasswordField().get(0).shouldBe(visible);

    adminCreatePage().getShowPassword().shouldBe(size(1));
    adminCreatePage().getShowPassword().get(0).shouldBe(exactText("Show"));
    adminCreatePage().getShowPassword().get(0).shouldBe(visible);

    adminCreatePage().getShowPassword().get(0).click();
    adminCreatePage().getHidePassword().shouldBe(size(1));
    adminCreatePage().getHidePassword().get(0).shouldBe(exactText("Hide"));
    adminCreatePage().getHidePassword().get(0).shouldBe(visible);

    adminCreatePage().getDeleteRowButton().shouldBe(size(1));
    adminCreatePage().getDeleteRowButton().get(0).shouldBe(visible);

    adminCreatePage().getAddRowLink().shouldBe(visible);
  }

  /**
   * Assert elements on User Settings page.
   */
  public static void assertElementsOnUserSettingsPage() {

    adminEditPage().getUserSettingsTitle().shouldBe(visible);
    adminEditPage().getUserSettingsTitle().shouldBe(exactText("User Settings"));
    adminEditPage().getUserName().shouldBe(visible);
    adminEditPage().getBackButton().shouldBe(visible);

    companySelector().getCompanySelector().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    newItemSelector().getNewItemSelector().shouldBe(visible);

    adminEditPage().getRoleTitle().shouldBe(visible);
    adminEditPage().getRoleTitle().shouldBe(exactText("Practis Admin"));
    adminEditPage().getSmallUserPic().shouldBe(visible);
    adminEditPage().getNameInfo().shouldBe(visible);
    String userName = adminEditPage().getUserName().text();
    adminEditPage().getNameInfo().shouldBe(matchText(userName));
    adminEditPage().getEmailInfo().shouldBe(visible);

    adminEditPage().getChangePasswordButton().shouldBe(visible);
    adminEditPage().getChangePasswordButton().shouldBe(exactText("Change Password"));

    adminEditPage().getLargeUserPic().shouldBe(visible);
    adminEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
    adminEditPage().getUploadPictureButton().shouldBe(visible);
    adminEditPage().getPictureText()
        .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));

    adminEditPage().getFirstNameField().shouldBe(visible);
    adminEditPage().getLastNameField().shouldBe(visible);
    adminEditPage().getEmailField().shouldBe(visible);
    adminEditPage().getMobileField().shouldBe(visible);

    adminEditPage().getDeleteButton().shouldBe(visible);
    adminEditPage().getDeleteButton().shouldBe(exactText("Delete"));
    adminEditPage().getUpdateButton().shouldBe(visible);
    adminEditPage().getUpdateButton().shouldBe(exactText("Update"));

    adminEditPage().getChangePasswordButton().shouldBe(visible);
    adminEditPage().getChangePasswordButton().click();

    adminEditPage().getNewPasswordField().shouldBe(visible);
    adminEditPage().getNewPasswordField().sibling(0).shouldBe(exactText("New Password"));
    adminEditPage().getShowNewPasswordButton().shouldBe(visible);
    adminEditPage().getConfirmPasswordField().shouldBe(visible);
    adminEditPage().getConfirmPasswordField().sibling(0).shouldBe(exactText("Confirm Password"));
    adminEditPage().getShowConfirmPasswordButton().shouldBe(visible);

    adminEditPage().getUpdatePasswordButton().shouldBe(exactText("Update"));

  }

}
