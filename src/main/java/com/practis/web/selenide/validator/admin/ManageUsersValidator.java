package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.manageUsersPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ManageUsersValidator {

    /** Assert elements on Manage Users page. */
    public static void assertElementsOnManageUsersPage() {
        manageUsersPage().getManageUsersTitle().shouldBe(visible);
        manageUsersPage().getManageUsersTitle().shouldBe(exactText("Manage Users"));
        manageUsersPage().getUpdatedTimestampText().shouldBe(visible);
        manageUsersPage().getUpdatedTimestampText().shouldBe(matchText("Updated"));
        manageUsersPage().getUpdateButton().shouldBe(visible);
        manageUsersPage().getUpdateButton().shouldBe(attribute("type", "submit"));

        manageUsersPage().getSearchField().shouldBe(visible);
        manageUsersPage().getSearchField().shouldBe(enabled);
        manageUsersPage()
                .getSearchField()
                .shouldBe(attribute("placeholder", "Search by the user's name or email..."));
        manageUsersPage().getSearchField().shouldBe(attribute("type", "text"));
        manageUsersPage().getSearchField().shouldBe(attribute("font-size", "13px"));
        manageUsersPage().getSearchFieldIcon().shouldBe(visible);
        manageUsersPage().getPaginationCounterText().shouldBe(hidden);
        manageUsersPage().getPaginationBackButton().shouldBe(visible);
        manageUsersPage().getPaginationBackButton().shouldBe(disabled);
        manageUsersPage().getPaginationNextButton().shouldBe(visible);
        manageUsersPage().getPaginationNextButton().shouldBe(disabled);

        manageUsersPage().getNameColumn().shouldBe(visible);
        manageUsersPage().getNameColumn().shouldBe(attribute("disabled"));
        manageUsersPage().getNameColumn().shouldBe(exactText("Users"));
        manageUsersPage().getNameColumn().shouldBe(attribute("width", "19"));
        manageUsersPage().getEmailColumn().shouldBe(visible);
        manageUsersPage().getEmailColumn().shouldBe(attribute("disabled"));
        manageUsersPage().getEmailColumn().shouldBe(exactText("Email"));
        manageUsersPage().getEmailColumn().shouldBe(attribute("width", "19"));
        manageUsersPage().getCompanyColumn().shouldBe(visible);
        manageUsersPage().getEmailColumn().shouldBe(attribute("disabled"));
        manageUsersPage().getCompanyColumn().shouldBe(exactText("Company"));
        manageUsersPage().getCompanyColumn().shouldBe(attribute("width", "19"));
        manageUsersPage().getRoleColumn().shouldBe(visible);
        manageUsersPage().getRoleColumn().shouldBe(attribute("disabled"));
        manageUsersPage().getRoleColumn().shouldBe(exactText("Role"));
        manageUsersPage().getRoleColumn().shouldBe(attribute("width", "10"));
        manageUsersPage().getStatusColumn().shouldBe(visible);
        manageUsersPage().getStatusColumn().shouldBe(attribute("disabled"));
        manageUsersPage().getStatusColumn().shouldBe(exactText("Status"));
        manageUsersPage().getStatusColumn().shouldBe(attribute("width", "18"));
    }

    /** Assert elements on Manage Users page. */
    public static void assertEmptyState() {
        manageUsersPage().getNoUsersIcon().shouldBe(visible);
        manageUsersPage().getNoUsersText().shouldBe(visible);
        manageUsersPage()
                .getNoUsersText()
                .shouldBe(exactText("Search for a user in any company to modify their settings"));
    }

    /** Assert elements on Manage Users page - No Search Result. */
    public static void assertElementsOnNoSearchResultManageUsersPage() {
        assertElementsOnManageUsersPage();
        manageUsersPage().getNoUsersSearchIcon().shouldBe(visible);
        manageUsersPage().getNoUserSearchText().shouldBe(visible);
        manageUsersPage().getNoUserSearchText().shouldBe(exactText("No Users Found"));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertSearchAfter1CharUsers(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        manageUsersPage().getSearchField().append(String.valueOf(input));
    }

    /** Assert 1 Search Result. */
    public static void assertSearchResultsOnManageUsersPage() {
        manageUsersPage().getPaginationCounterText().shouldBe(exactText("1-1 of 1 Items"));
        assertSeveralSearchResultsOnManageUsersPage();
    }

    /** Assert Several Search Results. */
    public static void assertSeveralSearchResultsOnManageUsersPage() {
        manageUsersPage().getSearchCleanIcon().shouldBe(visible);
        manageUsersPage().getPaginationCounterText().shouldBe(visible);
        manageUsersPage().getSearchCleanIcon().shouldBe(visible);
        manageUsersPage().getPaginationCounterText().shouldBe(visible);
        manageUsersPage().getPaginationCounterText().shouldBe(matchText("Items"));
        manageUsersPage().getUsersRow().get(0).shouldBe(visible);
        manageUsersPage().getCompanyRow().get(0).shouldBe(visible);
        manageUsersPage().getEmailRow().get(0).shouldBe(visible);
        manageUsersPage().getStatusRow().get(0).shouldBe(visible);
    }

    /** Click on Clear button in Search field. */
    public static void assertClickOnClearButton() {
        manageUsersPage().getSearchCleanIcon().click();
        manageUsersPage().getSearchCleanIcon().shouldBe(hidden);
        assertEmptyState();
    }
}
