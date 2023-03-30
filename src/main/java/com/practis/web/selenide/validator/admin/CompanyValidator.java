package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.dto.NewCompanyInput;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.web.selenide.component.GridRow;
import com.practis.web.selenide.page.admin.CompanySettingsPage;

public class CompanyValidator {

    /** Assert grid row with input data. */
    public static void assertCompanyGridRow(
            final NewCompanyInput inputData, final GridRow gridRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        gridRow.get("Company").shouldBe(matchText(".*\\n" + inputData.getName()));
    }

    /** Assert grid row with input data. */
    public static void assertCompanyGridRow(
            final RestCompanyResponse company, final GridRow gridRow) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        gridRow.get("Company").shouldBe(matchText(".*\\n" + company.getName()));
    }

    /** Assert data on edit page with input. */
    public static void assertCompanyData(
            final NewCompanyInput inputData, final CompanySettingsPage editPage) {
        editPage.getCompanyName().shouldBe(text(inputData.getName()));
        editPage.getCompanyNameField().shouldBe(attribute("value", inputData.getName()));
    }

    /** Assert elements on New Company page. */
    public static void assertElementsOnCreateCompanyPage() {
        companyCreatePage().getNewPractisCompanyTitle().shouldBe(exactText("New Company Account"));
        companyCreatePage().getInviteButton().shouldBe(visible);
        companyCreatePage().getInviteButton().shouldBe(exactText("Invite"));
        companyCreatePage().getInviteButton().shouldBe(disabled);
        companyCreatePage().getInviteButton().shouldBe(attribute("color", "default"));
        companyCreatePage().getInviteButton().shouldBe(attribute("width", "128px"));

        companyCreatePage().getCompanyNameField().shouldBe(size(1));
        companyCreatePage().getCompanyNameField().get(0).sibling(0).shouldBe(exactText("Company"));
        companyCreatePage().getCompanyNameField().get(0).shouldBe(visible);

        companyCreatePage().getCompanyEmailField().shouldBe(size(1));
        companyCreatePage()
                .getCompanyEmailField()
                .get(0)
                .sibling(0)
                .shouldBe(exactText("Email Address"));
        companyCreatePage().getCompanyEmailField().get(0).shouldBe(visible);

        companyCreatePage().getFirstNameField().shouldBe(size(1));
        companyCreatePage().getFirstNameField().get(0).sibling(0).shouldBe(exactText("First Name"));
        companyCreatePage().getFirstNameField().get(0).shouldBe(visible);

        companyCreatePage().getLastNameField().shouldBe(size(1));
        companyCreatePage().getLastNameField().get(0).sibling(0).shouldBe(exactText("Last Name"));
        companyCreatePage().getLastNameField().get(0).shouldBe(visible);

        companyCreatePage().getAddRowLink().shouldBe(visible);
        companyCreatePage().getAddRowLink().shouldBe(attribute("color", "#4aa9e2"));
        companyCreatePage().getAddRowLink().shouldBe(attribute("font-size", "13"));

        companyCreatePage().getDeleteRowButton().get(0).click();
    }
}
