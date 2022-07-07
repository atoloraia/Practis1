package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.scenarioEditPage;

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
    editPage.getHeaderNameElement().shouldBe(text(inputData.getName()));
    editPage.getCompanyNameFieldElement().shouldBe(attribute("value", inputData.getName()));
  }

  /**
   * Assert elements on New Company page.
   */
  public static void assertElementsOnCreateCompanyPage() {
    companyCreatePage().getNewPractisCompanyTitle().shouldBe(exactText("New Company Account"));
    companyCreatePage().getInviteButtonElement().shouldBe(visible);
    companyCreatePage().getInviteButtonElement().shouldBe(attribute("color", "default"));

    companyCreatePage().getCompanyNameFieldElements().shouldBe(size(1));
    companyCreatePage().getCompanyNameFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Company"));
    companyCreatePage().getCompanyNameFieldElements().get(0).shouldBe(visible);

    companyCreatePage().getCompanyEmailFieldElements().shouldBe(size(1));
    companyCreatePage().getCompanyEmailFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Email Address"));
    companyCreatePage().getCompanyEmailFieldElements().get(0).shouldBe(visible);

    companyCreatePage().getFirstNameFieldElements().shouldBe(size(1));
    companyCreatePage().getFirstNameFieldElements().get(0).sibling(0)
        .shouldBe(exactText("First Name"));
    companyCreatePage().getFirstNameFieldElements().get(0).shouldBe(visible);

    companyCreatePage().getLastNameFieldElements().shouldBe(size(1));
    companyCreatePage().getLastNameFieldElements().get(0).sibling(0)
        .shouldBe(exactText("Last Name"));
    companyCreatePage().getLastNameFieldElements().get(0).shouldBe(visible);

    companyCreatePage().getAddRowLinkElement().shouldBe(visible);
  }

  /**
   * Assert elements on Company Settings page.
   */
  public static void assertElementsOnCompanySettingsPage() {
    companyEditPage().getTitleNameElement().shouldBe(exactText("Company Settings"));
    companyEditPage().getHeaderNameElement().shouldBe(visible);
    companyEditPage().getBackButton().shouldBe(visible);
    companyEditPage().getCompanySelector().shouldBe(visible);
    companyEditPage().getCompanySelector().shouldBe(exactText("Practis"));
    companyEditPage().getActionButton().shouldBe(visible);

    companyEditPage().getSmallUserpic().shouldBe(visible);
    companyEditPage().getCompanyTitle().shouldBe(visible);
    companyEditPage().getCompanyTitle()
            .equals((companyEditPage().getHeaderNameElement()));
    companyEditPage().getCreatedAtText().shouldBe(visible);
    companyEditPage().getCreatedAtText().shouldBe(matchText("Created"));
    companyEditPage().getCreatedAtText().shouldBe(visible);
    companyEditPage().getDownloadReportButton().get(0).shouldBe(visible);
    companyEditPage().getDownloadReportButton().get(0).shouldBe(exactText("Download Report"));
    companyEditPage().getDownloadReportButton().get(1).shouldBe(visible);
    companyEditPage().getDownloadReportButton().get(1).shouldBe(exactText("View Logs"));
    companyEditPage().getViewAssessmentButton().shouldBe(visible);
    companyEditPage().getViewAssessmentButton()
        .shouldBe(exactText("View AI Assessment"));

    companyEditPage().getCompanyDetailsButton().shouldBe(exactText("Company Details"));
    companyEditPage().getLargeUserpic().shouldBe(visible);
    companyEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
    companyEditPage().getPictureText()
        .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));
    companyEditPage().getCompanyNameFieldElement()
        .equals((companyEditPage().getHeaderNameElement()));
    companyEditPage().getCompanyOwnerField().shouldBe(visible);
    companyEditPage().getEmailField().shouldBe(visible);

    companyEditPage().getDeleteButton().shouldBe(visible);
    companyEditPage().getDeleteButton().shouldBe(exactText("Delete"));

    companyEditPage().getUpdateButton().shouldBe(visible);
    companyEditPage().getUpdateButton().shouldBe(exactText("Update"));

  }
}
