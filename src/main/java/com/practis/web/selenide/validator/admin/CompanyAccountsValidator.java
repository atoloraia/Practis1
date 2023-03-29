package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.web.selenide.component.GridRow;

public class CompanyAccountsValidator {

    public static void assertSingleActionCompanies() {
        companyAccountsPage().getEditSingleAction().shouldBe(visible);
        companyAccountsPage().getEditSingleAction().shouldBe(exactText("Edit"));
    }

    /** Assert elements on Company page. */
    public static void assertElementsOnCompaniesPage() {
        companyAccountsPage().getCompanyHeaderText().shouldBe(exactText("Company Accounts"));

        companyAccountsPage().getSearchField().shouldBe(visible);
        companyAccountsPage().getSearchFieldIcon().shouldBe(visible);
        companyAccountsPage().getSearchField().shouldBe(attribute("type", "text"));
        companyAccountsPage().getSearchField().shouldBe(attribute("font-size", "13px"));

        companyAccountsPage().getFiltersButton().shouldBe(visible);
        companyAccountsPage().getFiltersCounter().shouldBe(visible);

        companyAccountsPage().getUpdateButton().shouldBe(visible);
        companyAccountsPage().getUpdatedTimestampText().shouldBe(visible);
        companyAccountsPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

        companyAccountsPage().getPaginationBackButton().shouldBe(visible);
        companyAccountsPage().getPaginationNextButton().shouldBe(visible);

        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(exactText("Practis"));

        // check columns
        companyAccountsPage().getCompanyColumn().shouldBe(visible);
        companyAccountsPage().getCompanyColumn().shouldBe(exactText("Company"));
        companyAccountsPage().getCompanyOwnerColumn().shouldBe(visible);
        companyAccountsPage().getCompanyOwnerColumn().shouldBe(exactText("Company Owner"));
        companyAccountsPage().getOwnerColumn().shouldBe(visible);
        companyAccountsPage().getOwnerColumn().shouldBe(exactText("Owner"));
        companyAccountsPage().getStatusColumn().shouldBe(visible);
        companyAccountsPage().getStatusColumn().shouldBe(exactText("Status"));
        companyAccountsPage().getDateActivatedColumn().shouldBe(visible);
        companyAccountsPage().getDateActivatedColumn().shouldBe(exactText("Created"));
    }

    /** Assert Filter button. */
    public static void assertFilterButtonCompanies() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyAccountsPage().getFiltersButton().shouldBe(visible);
        final var hoveredElement = companyAccountsPage().getFiltersButton();
        Selenide.actions().moveToElement(hoveredElement).perform();
        companyAccountsPage().getFilterTooltip().shouldBe(exactText("Filters"));
    }

    /** Assert data for Company row with input. */
    public static void assertRowCompanyAccounts(
            final RestCompanyResponse company, final GridRow gridRow) {
        gridRow.get("Company").shouldBe(matchText(company.getName()));
    }

    /** Assert Active status. */
    public static void assertStatusForCompanyRow(String status) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyAccountsPage().getStatusRow().get(0).$("status").shouldBe(exactText("Active"));
    }

    /** Assert inactive + Active status. */
    public static void assertInactiveActiveStatusForCompanyRow() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companyAccountsPage().getStatusRow().get(0).$("status").shouldBe(exactText("Inactive"));
    }

    /** Assert Company Action button. */
    public static void assertCompanyActionButton(String button) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companySettingsPage().getDeactivateButton().shouldBe(exactText(button));
    }

    /** Assert No search results on 'Company Accounts' page. */
    public static void assertNoResultsCompanyAccounts() {
        companyAccountsPage().getStatusRow().shouldHave(CollectionCondition.size(0));
        companyAccountsPage().getNoCompanySearchIcon().shouldHave(visible);
        companyAccountsPage().getNoCompanySearchText().shouldHave(visible);
        companyAccountsPage().getNoCompanySearchText().shouldHave(exactText("No Companies Found"));
    }

    /** Assert status on company accounts page. */
    public static void assertRowColumn(final GridRow row, final String column, final String value) {
        row.get(column).shouldBe(matchText(value));
    }
}
