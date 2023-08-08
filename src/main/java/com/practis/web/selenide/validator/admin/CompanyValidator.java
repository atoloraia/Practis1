package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.feedPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
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
        companyAccountsPage().getLimitRow().get(0).shouldBe(exactText("Unlimited"));
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
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        editPage.getCompanyName().shouldBe(text(inputData.getName()));
        editPage.getCompanyNameField().shouldBe(attribute("value", inputData.getName()));
        editPage.getStatusBadge().shouldBe(exactText("Active"));
    }

    /** Assert elements on New Company page. */
    public static void assertElementsOnCreateCompanyPage() {

        // New
        companyCreatePage().getNewCompanyTitle().shouldBe(visible);
        companyCreatePage().getNewCompanyTitle().shouldBe(exactText("New Company"));
        companyCreatePage().getCrossButton().shouldBe(visible);
        companyCreatePage().getCompanyNameTitle().shouldBe(visible);
        companyCreatePage().getCompanyNameTitle().shouldBe(exactText("Company Name"));
        companyCreatePage().getCompanyNameField1().shouldBe(visible);
        companyCreatePage().getCompanyNameField1().shouldBe(attribute("type", "text"));

        companyCreatePage().getCompanyNameCharacterCounter().shouldBe(visible);
        companyCreatePage().getCompanyNameCharacterCounter().shouldBe(exactText("80"));
        companyCreatePage().getWorkspaceUrlTitle().shouldBe(visible);
        companyCreatePage().getWorkspaceUrlTitle().shouldBe(exactText("Workspace URL"));
        companyCreatePage().getWorkspaceUrlField().shouldBe(visible);
        companyCreatePage().getWorkspaceUrlField().shouldBe(attribute("type", "text"));
        companyCreatePage().getWorkspaceUrlGopractisText().shouldBe(visible);
        companyCreatePage().getWorkspaceUrlGopractisText().shouldBe(exactText(".gopractis.com"));
        companyCreatePage().getWorkspaceUrlValidationText().shouldBe(visible);
        companyCreatePage()
                .getWorkspaceUrlValidationText()
                .shouldBe(
                        exactText(
                                "Must be at least 3 letters (a-z), numbers (0-9). Hyphens (- and"
                                        + " _) are OK if they're in the middle."));
        companyCreatePage().getCreateButton().shouldBe(visible);
        companyCreatePage().getCreateButton().shouldBe(disabled);
    }

    /** Assert elements on New Company page. */
    public static void assertCompanyCreatedModal(final NewCompanyInput inputData) {
        companyCreatePage().getCloseButton().shouldBe(visible);
        companyCreatePage().getIcon().shouldBe(visible);
        companyCreatePage().getCompanyTitle().shouldBe(visible);
        companyCreatePage().getCompanyTitle().shouldBe(text(inputData.getName()));
        companyCreatePage().getCompanyTitleText().shouldBe(visible);
        companyCreatePage().getCompanyTitleText().shouldBe(exactText("is now active."));
        companyCreatePage().getConfigureCompanyButton().shouldBe(visible);
        companyCreatePage().getConfigureCompanyButton().shouldBe(exactText("Configure Company"));
        companyCreatePage().getOpenWebPortalButton().shouldBe(visible);
        companyCreatePage().getOpenWebPortalButton().shouldBe(exactText("Open Web Portal"));
    }

    /** Assert Company Exists message on New Company page. */
    public static void assertCompanyExistsErrorMessage() {
        companyCreatePage().getCompanyAlreadyExistsError().shouldBe(visible);
        companyCreatePage()
                .getCompanyAlreadyExistsError()
                .shouldBe(exactText("The Company Name is already in use"));
    }

    /** Assert Workspace Exist message on New Company page. */
    public static void assertWorkspaceExistErrorMessage() {
        companyCreatePage().getWorkspaceUrlExistsError().shouldBe(visible);
        companyCreatePage()
                .getWorkspaceUrlExistsError()
                .shouldBe(exactText("The Workspace URL is already in use"));
        companyCreatePage().getWorkspaceUrlValidationText().shouldBe(hidden);
    }

    /** Assert Workspace Exist message on New Company page. */
    public static void assetEmptyFields() {
        companyCreatePage().getEmptyCompanyError().shouldBe(visible);
        companyCreatePage().getEmptyCompanyError().shouldBe(exactText("Company name is required"));
        companyCreatePage().getWorkspaceUrlValidationErrorText().shouldBe(visible);
        companyCreatePage()
                .getWorkspaceUrlValidationErrorText()
                .shouldBe(
                        exactText(
                                "Must be at least 3 letters (a-z), numbers (0-9). Hyphens (- and"
                                        + " _) are OK if they're in the middle."));
    }

    /** Assert Company Web Portal. */
    public static void assertCompanyWebPortal(final NewCompanyInput inputData) {
        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(matchText(inputData.getName()));
        feedPage().getAccuracyFeedTitle().shouldBe(visible);
        feedPage().getAccuracyFeedTitle().shouldBe(exactText("Feed"));
    }

    public static void assertAdminWebPortal() {
        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(exactText("Practis"));
    }

    public static void assertCompanyCreateClosed() {
        companyCreatePage().getCreateCompanyModal().shouldBe(hidden);
    }

    public static void assertCompanyCreatedClosed() {
        companyCreatePage().getCompanyCreatedModal().shouldBe(hidden);
    }
}
