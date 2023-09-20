package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyAccountsPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.practis.rest.dto.admin.RestCompanyResponse;
import com.practis.web.selenide.component.GridRow;

public class CompanyAccountsValidator {

    public static void assertSingleActionCompanies() {
        companyAccountsPage().getSettingsSingleAction().shouldBe(visible);
        companyAccountsPage().getSettingsSingleAction().shouldBe(exactText("Settings"));
    }

    /** Assert elements on Company page. */
    public static void assertElementsOnCompaniesPage() {
        companyAccountsPage().getCompanyHeaderText().shouldBe(exactText("Company Accounts"));

        search().getSearchField().shouldBe(visible);
        search().getSearchFieldIcon().shouldBe(visible);
        search().getSearchField().shouldBe(attribute("type", "text"));
        search().getSearchField().shouldBe(attribute("font-size", "13px"));

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
        companyAccountsPage().getCompanyOwnerColumn().shouldBe(exactText("Account Owner"));
        companyAccountsPage().getOwnerColumn().shouldBe(visible);
        companyAccountsPage().getOwnerColumn().shouldBe(exactText("Owner"));
        companyAccountsPage().getStatusColumn().shouldBe(visible);
        companyAccountsPage().getStatusColumn().shouldBe(exactText("Status"));
        companyAccountsPage().getDateActivatedColumn().shouldBe(visible);
        companyAccountsPage().getDateActivatedColumn().shouldBe(exactText("Created On"));
        companyAccountsPage().getLimitColumn().shouldBe(visible);
        companyAccountsPage().getLimitColumn().shouldBe(exactText("Licensed Seats"));
        companyAccountsPage().getUsedSeatsColumn().shouldBe(visible);
        companyAccountsPage().getUsedSeatsColumn().shouldBe(exactText("Used Seats"));
        companyAccountsPage().getLimitRow().get(0).shouldBe(exactText("Unlimited"));
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
            final RestCompanyResponse company, final GridRow gridRow, String status) {
        gridRow.get("Company").shouldBe(matchText(company.getName()));
        gridRow.get("Status").shouldBe(matchText(status));
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

    /** Assert Search Results. */
    public static void assertSearchResultsOnCompanyAccounts(String input) {
        search().getSearchField().shouldBe(visible);
        companyAccountsPage().getCompanyRow().shouldBe(CollectionCondition.size(1));
        final var companyRow =
                companyAccountsPage().getCompanyRow().find(Condition.matchText(input));
        companyRow.shouldBe(visible);
    }

    /** Assert status on company accounts page. */
    public static void assertRowColumn(final GridRow row, final String column, final String value) {
        row.get(column).shouldBe(matchText(value));
    }

    /** Assert Search any Results. */
    public static void assertSearchAnyResultsOnCompanyAccounts() {
        search().getSearchFieldClearButton().shouldBe(visible);
        companyAccountsPage().getCompanyRow().get(0).shouldBe(visible);
    }

    /** Assert Used Seats Default state. */
    public static void assertUsedSeatsDefaultValue() {
        companyAccountsPage().getCompanyRow().get(0).shouldBe(visible);
        companyAccountsPage().getUsedSeatsRow().get(0).shouldBe(visible);
        companyAccountsPage().getUsedSeatsRow().get(0).shouldBe(exactText("0"));
    }

    /** Assert Used Seats filled state. */
    public static void assertUsedSeatsFilledValue() {
        companyAccountsPage().getCompanyRow().get(0).shouldBe(visible);
        companyAccountsPage().getUsedSeatsRow().get(0).shouldBe(visible);
        companyAccountsPage().getUsedSeatsRow().get(0).shouldNotBe(exactText("0"));
    }
}
