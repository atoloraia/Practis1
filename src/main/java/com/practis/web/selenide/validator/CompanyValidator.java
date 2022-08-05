package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyPage;

import com.practis.dto.NewCompanyInput;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.CompanyEditPage;

public class CompanyValidator {

  /**
   * Assert grid row with input data.
   */
  public static void assertCompanyGridRow(final NewCompanyInput inputData, final GridRow gridRow) {
    gridRow.get("Company").shouldBe(matchText(".*\\n" + inputData.getName()));
  }

  /**
   * Assert data on edit page with input.
   */
  public static void assertCompanyData(final NewCompanyInput inputData,
      final CompanyEditPage editPage) {
    editPage.getCompanyName().shouldBe(text(inputData.getName()));
    editPage.getCompanyNameField().shouldBe(attribute("value", inputData.getName()));
  }

  /**
   * Assert elements on New Company page.
   */
  public static void assertElementsOnCreateCompanyPage() {
    companyCreatePage().getNewPractisCompanyTitle().shouldBe(exactText("New Company Account"));
    companyCreatePage().getInviteButton().shouldBe(visible);
    companyCreatePage().getInviteButton().shouldBe(exactText("Invite"));
    companyCreatePage().getInviteButton().shouldBe(disabled);
    companyCreatePage().getInviteButton().shouldBe(attribute("color", "default"));
    companyCreatePage().getInviteButton().shouldBe(attribute("width", "128px"));

    companyCreatePage().getCompanyNameField().shouldBe(size(1));
    companyCreatePage().getCompanyNameField().get(0).sibling(0)
        .shouldBe(exactText("Company"));
    companyCreatePage().getCompanyNameField().get(0).shouldBe(visible);

    companyCreatePage().getCompanyEmailField().shouldBe(size(1));
    companyCreatePage().getCompanyEmailField().get(0).sibling(0)
        .shouldBe(exactText("Email Address"));
    companyCreatePage().getCompanyEmailField().get(0).shouldBe(visible);

    companyCreatePage().getFirstNameField().shouldBe(size(1));
    companyCreatePage().getFirstNameField().get(0).sibling(0)
        .shouldBe(exactText("First Name"));
    companyCreatePage().getFirstNameField().get(0).shouldBe(visible);

    companyCreatePage().getLastNameField().shouldBe(size(1));
    companyCreatePage().getLastNameField().get(0).sibling(0)
        .shouldBe(exactText("Last Name"));
    companyCreatePage().getLastNameField().get(0).shouldBe(visible);

    companyCreatePage().getAddRowLink().shouldBe(visible);
    companyCreatePage().getAddRowLink().shouldBe(attribute("color", "#4aa9e2"));
    companyCreatePage().getAddRowLink().shouldBe(attribute("font-size", "13"));

    companyCreatePage().getDeleteRowButton().get(0).click();
  }

  /**
   * Assert elements on Company Settings page.
   */
  public static void assertElementsOnCompanySettingsPage() {
    companyEditPage().getCompanySettingsTitle().shouldBe(exactText("Company Settings"));
    companyEditPage().getCompanyName().shouldBe(visible);
    companyEditPage().getBackButton().shouldBe(visible);

    companySelector().getCompanySelector().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    newItemSelector().getNewItemSelector().shouldBe(visible);

    companyEditPage().getSmallUserPic().shouldBe(visible);
    companyEditPage().getCompanyTitle().shouldBe(visible);
    String companyName = companyEditPage().getCompanyName().text();
    companyEditPage().getCompanyTitle().shouldBe(matchText(companyName));
    companyEditPage().getCreatedAtText().shouldBe(visible);
    companyEditPage().getCreatedAtText().shouldBe(matchText("Created"));

    companyEditPage().getDownloadReportButton().shouldBe(visible);
    companyEditPage().getDownloadReportButton().shouldBe(exactText("Download Report"));
    companyEditPage().getDownloadReportButton().shouldBe(attribute("type", "submit"));
    companyEditPage().getDownloadReportButton().shouldBe(attribute("title", "Download Report"));
    companyEditPage().getDownloadReportButton().shouldBe(attribute("width", "136px"));
    companyEditPage().getDownloadReportButton().shouldBe(attribute("color", "default"));

    companyEditPage().getViewLogsButton().shouldBe(visible);
    companyEditPage().getViewLogsButton().shouldBe(exactText("View Logs"));
    companyEditPage().getViewLogsButton().shouldBe(attribute("type", "submit"));
    companyEditPage().getViewLogsButton().shouldBe(attribute("title", "View Logs"));
    companyEditPage().getViewLogsButton().shouldBe(attribute("width", "136px"));
    companyEditPage().getViewLogsButton().shouldBe(attribute("color", "default"));

    companyEditPage().getViewAssessmentButton().shouldBe(visible);
    companyEditPage().getViewAssessmentButton()
        .shouldBe(exactText("View AI Assessment"));
    companyEditPage().getViewAssessmentButton().shouldBe(attribute("type", "submit"));
    companyEditPage().getViewAssessmentButton().shouldBe(attribute("title", "View AI Assessment "));
    companyEditPage().getViewAssessmentButton().shouldBe(attribute("width", "165px"));
    companyEditPage().getViewAssessmentButton().shouldBe(attribute("color", "default"));


    companyEditPage().getCompanyDetailsButton().shouldBe(visible);
    companyEditPage().getCompanyDetailsButton().shouldBe(exactText("Company Details"));
    companyEditPage().getLargeUserpic().shouldBe(visible);
    companyEditPage().getLargeUserpic().shouldBe(attribute("width", "136"));
    companyEditPage().getLargeUserpic().shouldBe(attribute("height", "136"));
    companyEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
    companyEditPage().getPictureText()
        .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));
    companyEditPage().getCompanyNameField().shouldBe(attributeMatching("value", companyName));
    companyEditPage().getCompanyNameField().shouldBe(attribute("type", "text"));
    companyEditPage().getCompanyNameField().shouldBe(attribute("maxlength", "100"));
    companyEditPage().getCompanyOwnerField().shouldBe(visible);
    companyEditPage().getCompanyOwnerField().shouldBe(matchText("Company Owner"));
    companyEditPage().getEmailField().shouldBe(visible);
    companyEditPage().getEmailField().shouldBe(attribute("name", "email"));
    companyEditPage().getEmailField().shouldBe(attribute("font-family", "Manrope"));
    companyEditPage().getEmailField().shouldBe(attribute("type", "email"));
    companyEditPage().getEmailField().shouldBe(disabled);
    companyEditPage().getEmailField().sibling(0).shouldBe(matchText("Email"));

    companyEditPage().getDeleteButton().shouldBe(visible);
    companyEditPage().getDeleteButton().shouldBe(exactText("Delete"));
    companyEditPage().getDeleteButton().shouldBe(attribute("type", "button"));
    companyEditPage().getDeleteButton().shouldBe(attribute("color", "warning"));

    companyEditPage().getUpdateButton().shouldBe(visible);
    companyEditPage().getUpdateButton().shouldBe(exactText("Update"));
    companyEditPage().getUpdateButton().shouldBe(attribute("type", "submit"));
    companyEditPage().getUpdateButton().shouldBe(attribute("color", "default"));

  }

  /**
   * Assert elements on Company page.
   */
  public static void assertElementsOnCompanyPage() {
    companySelector().getCompanySelector().click();
    companySelector().getAdminCompanyElement().click();
    companyPage().getCompanyHeaderText().shouldBe(exactText("Company Accounts"));
    companyPage().getSearchField().shouldBe(visible);
    companyPage().getSearchFieldIcon().shouldBe(visible);
    companyPage().getSearchField().shouldBe(attribute("type", "text"));
    companyPage().getSearchField().shouldBe(attribute("font-size", "13px"));
    companySelector().getCompanySelector().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(exactText("Practis"));
  }
}
