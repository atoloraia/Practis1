package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.companiesPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Selenide;

public class CompaniesValidator {

    public static void assertSingleActionCompanies() {
        companiesPage().getEditSingleAction().shouldBe(visible);
        companiesPage().getEditSingleAction().shouldBe(exactText("Edit"));
    }

    /** Assert elements on Company page. */
    public static void assertElementsOnCompaniesPage() {
        companiesPage().getCompanyHeaderText().shouldBe(exactText("Company Accounts"));

        companiesPage().getSearchField().shouldBe(visible);
        companiesPage().getSearchFieldIcon().shouldBe(visible);
        companiesPage().getSearchField().shouldBe(attribute("type", "text"));
        companiesPage().getSearchField().shouldBe(attribute("font-size", "13px"));

        companiesPage().getUpdateButton().shouldBe(visible);
        companiesPage().getUpdatedTimestampText().shouldBe(visible);
        companiesPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));

        companiesPage().getPaginationBackButton().shouldBe(visible);
        companiesPage().getPaginationNextButton().shouldBe(visible);

        companySelector().getCompanySelector().shouldBe(visible);
        companySelector().getCompanySelector().shouldBe(exactText("Practis"));

        // check columns
        companiesPage().getCompanyColumn().shouldBe(visible);
        companiesPage().getCompanyColumn().shouldBe(exactText("Company"));
        companiesPage().getOwnerColumn().shouldBe(visible);
        companiesPage().getOwnerColumn().shouldBe(exactText("Company Owner"));
        companiesPage().getDateActivatedColumn().shouldBe(visible);
        companiesPage().getDateActivatedColumn().shouldBe(exactText("Date Activated"));
        companiesPage().getOwnerColumn().shouldBe(visible);
        companiesPage().getOwnerColumn().shouldBe(exactText("Owner"));
        companiesPage().getStatusColumn().shouldBe(visible);
        companiesPage().getStatusColumn().shouldBe(exactText("Status"));
    }

    /** Assert Filter button. */
    public static void assertFilterButtonCompanies() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companiesPage().getFiltersButton().shouldBe(visible);
        final var hoveredElement = companiesPage().getFiltersButton();
        Selenide.actions().moveToElement(hoveredElement).perform();
        companiesPage().getFilterTooltip().shouldBe(exactText("Filters"));
    }

    /** Assert Active status. */
    public static void assertActiveStatusForCompanyRow() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companiesPage().getStatusRow().get(0).$("status").shouldBe(exactText("Active"));
    }

    /** Assert inactive status. */
    public static void assertInactiveStatusForCompanyRow() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companiesPage().getStatusRow().get(0).$("status").shouldBe(exactText("Inactive"));
    }

    /** Assert inactive + Active status. */
    public static void assertInactiveActiveStatusForCompanyRow() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companiesPage().getStatusRow().get(0).$("status").shouldBe(exactText("Inactive"));
    }
}
