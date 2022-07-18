package com.practis.web.selenide.validator;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static java.util.function.Predicate.isEqual;

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
    companySelector().getCompanySelector().shouldBe(visible);
    companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    newItemSelector().getNewItemSelector().shouldBe(visible);

    companyEditPage().getSmallUserPic().shouldBe(visible);
    companyEditPage().getCompanyTitle().shouldBe(visible);
    String companyName = companyEditPage().getHeaderNameElement().text();
    companyEditPage().getCompanyTitle().shouldBe(matchText(companyName));
    companyEditPage().getCreatedAtText().shouldBe(visible);
    companyEditPage().getCreatedAtText().shouldBe(matchText("Created"));
    companyEditPage().getDownloadReportButton().shouldBe(visible);
    companyEditPage().getDownloadReportButton().shouldBe(exactText("Download Report"));
    companyEditPage().getViewLogsButton().shouldBe(visible);
    companyEditPage().getViewLogsButton().shouldBe(exactText("View Logs"));
    companyEditPage().getViewAssessmentButton().shouldBe(visible);
    companyEditPage().getViewAssessmentButton()
        .shouldBe(exactText("View AI Assessment"));

    companyEditPage().getCompanyDetailsButton().shouldBe(visible);
    companyEditPage().getCompanyDetailsButton().shouldBe(exactText("Company Details"));
    companyEditPage().getLargeUserpic().shouldBe(visible);
    companyEditPage().getUploadPictureButton().shouldBe(exactText("Upload a new picture"));
    companyEditPage().getPictureText()
        .shouldBe(exactText("JPG, PNG, BMP only. Less than 10 MB"));
    companyEditPage().getCompanyNameFieldElement()
        .shouldBe(attributeMatching("value", companyName));
    companyEditPage().getCompanyOwnerField().shouldBe(visible);
    companyEditPage().getCompanyOwnerField().shouldBe(matchText("Company Owner"));
    companyEditPage().getEmailField().shouldBe(visible);
    companyEditPage().getEmailField().sibling(0).shouldBe(matchText("Email"));

    companyEditPage().getDeleteButton().shouldBe(visible);
    companyEditPage().getDeleteButton().shouldBe(exactText("Delete"));

    companyEditPage().getUpdateButton().shouldBe(visible);
    companyEditPage().getUpdateButton().shouldBe(exactText("Update"));

  }
}
